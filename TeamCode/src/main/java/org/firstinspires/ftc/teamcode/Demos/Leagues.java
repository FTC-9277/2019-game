package org.firstinspires.ftc.teamcode.Demos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Judging Pres")
public class Leagues extends LinearOpMode {
    ExplosivesRobot robot;
    DcMotor leftSlide,rightSlide;
    Servo left, right, marker;
    CRServo in;

    public Player player;

    @Override
    public void runOpMode(){
        initHardware();
        initAction();
        telemetry.addData("Ready","Yes");
        telemetry.update();
        waitForStart();
        body();

    }

    public void initHardware() {
        robot = new ExplosivesRobot(this);
        //leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        //rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        marker = hardwareMap.get(Servo.class, "marker");
        //left = hardwareMap.get(Servo.class, "leftIntake");
        //right = hardwareMap.get(Servo.class, "rightIntake");
        //in = hardwareMap.get(CRServo.class, "intake");

        player = new Player(hardwareMap);
    }

    public void initAction() {

        String[] sounds = {"hello","connect", "design", "control", "innovate", "motivate", "end"};

        for (String s : sounds){
            if (!player.add(s)){
                telemetry.addData(s,"failed");
            } else {
                telemetry.addData(s,"success");
            }
        }
    }

    public void body() {
        wait_for_button();
        telemetry.addData("Section: ", "Opening");
        telemetry.update();

        player.play("hello");


        /*for (int i = 0; i < 5; i++){
            robot.intakeSubsystem.drop();
        }

        pause(50);

        for (int i = 0; i < 5; i++){
            robot.intakeSubsystem.pullUp();
        }

        pause(50);*/

        robot.intakeSubsystem.setSlider(1.0);
        pause(750);
        robot.intakeSubsystem.setSlider(0.0);


        telemetry.addData("Section: ", "Connect");
        telemetry.update();

        wait_for_button();
        player.play("connect");
        //No necessary robot action

        telemetry.addData("Section: ", "Design");
        telemetry.update();

        wait_for_button();
        player.play("design");

        //TEAM MARKER
        wait_for_button();
        marker.setPosition(0.5);

        telemetry.addData("Section: ", "Control");
        telemetry.update();

        wait_for_button();
        player.play("control");
        //Climber

        telemetry.addData("Section: ", "Innovate");
        telemetry.update();

        wait_for_button();
        player.play("innovate");
        //Intake out and in

        telemetry.addData("Section: ", "Motivate");
        telemetry.update();

        wait_for_button();
        player.play("motivate");
        //

        telemetry.addData("Section: ", "Ending");
        telemetry.update();

        wait_for_button();
        player.play("end");

        robot.intakeSubsystem.setSlider(-1.0);
        pause(500);
        robot.intakeSubsystem.setSlider(0.0);

        /*
        long start_time = System.currentTimeMillis();
        long total_time = 5000;
        double p;
        while (start_time + total_time > System.currentTimeMillis() && opModeIsActive()){
            p = ((double) start_time+total_time-System.currentTimeMillis())/ (double) total_time;
            robot.driveSubsystem.tankDrive(p,p);
            telemetry.addData("Power: ", p);
            telemetry.update();
        }
        robot.driveSubsystem.tankDrive(0,0);*/
    }


    public void wait_for_button(){
        try {
            Thread.sleep(750);
        } catch (InterruptedException e){

        }
        while(!button() && opModeIsActive()){

        }
    }

    public boolean button(){
        return gamepad1.right_trigger > .75;
    }

    public void pause(long t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e){

        }
    }
}
