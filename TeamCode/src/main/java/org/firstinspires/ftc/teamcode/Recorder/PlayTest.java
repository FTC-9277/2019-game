package org.firstinspires.ftc.teamcode.Recorder;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Play")
public class PlayTest extends LinearOpMode {

    public int position;

    public void runOpMode(){
        ExplosivesRobot robot = new ExplosivesRobot(this);

        position = 1;

        waitForStart();
        Playback pb;

        if (position == 1){
            pb = new Playback("A", this, robot.driveSubsystem);
        } else {
            pb = new Playback(position==2 ? "B" : "Y", this, robot.driveSubsystem);
        }

        pb.load();
        telemetry.addData(">", "LOADED");
        telemetry.update();
        pb.run();
    }

    @Override
    protected void handleLoop() {
        telemetry.addData("A", position==1 ? "*" : " ");
        telemetry.addData("B", position==2 ? "*" : " ");
        telemetry.addData("Y", position==3 ? "*" : " ");
        telemetry.addData(">", position);
        telemetry.update();

        if (gamepad1.a){
            position = 1;
        } else if (gamepad1.b){
            position = 2;
        } else if (gamepad1.y){
            position = 3;
        }

        synchronized (this) {
            this.notifyAll();
        }
    }
}