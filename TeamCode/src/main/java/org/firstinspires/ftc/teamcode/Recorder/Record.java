package org.firstinspires.ftc.teamcode.Recorder;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

import java.io.IOException;
import java.io.PrintWriter;

public class Record implements Runnable{
    public Gamepad gamepad;
    public LinearOpMode opMode;

    public Thread t;

    public PrintWriter printer;

    public DriveSubsystem drive;

    public long delay = 10;

    public boolean running;


    public Record(LinearOpMode opMode, DriveSubsystem drive){
        this.opMode = opMode;
        this.gamepad = opMode.gamepad1;
        this.t = new Thread(this);
        this.drive = drive;
        this.running = false;
    }

    public void init(String recording){
        try {
            this.printer = new PrintWriter("/sdcard/robotRuns/"+ recording +".csv", "UTF-8");
        } catch (IOException e){

        }
    }

    public void start(){
        t.start();
        running = true;
    }

    @Override
    public void run(){
        while (opMode.opModeIsActive() && !gamepad.left_stick_button){
            print();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e){

            }
        }
        printer.close();
        running = false;
    }


    public void print(){
        printer.print(gamepad.left_stick_y);
        comma();
        printer.print(gamepad.right_stick_y);
        enter();
    }
    public void comma(){
        printer.print(",");
    }
    public void enter(){
        printer.println("");
    }
}

