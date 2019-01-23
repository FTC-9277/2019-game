package org.firstinspires.ftc.teamcode.Recorder;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Record")
public class RecordTest extends LinearOpMode {

    public int position;
    public boolean up;
    public boolean down;

    public void runOpMode(){
        ExplosivesRobot robot = new ExplosivesRobot(this);
        Record r = new Record(this, robot.driveSubsystem);

        position = 1;

        waitForStart();

        if (position == 1){
            r.init("A");
        } else {
            r.init(position==2 ? "B" : "Y");
        }
        r.start();

        while (opModeIsActive()){
            telemetry.addData(">",gamepad1.left_stick_y);
            telemetry.addData(">",gamepad1.right_stick_y);
            telemetry.addData(">", r.running);
            telemetry.update();
            robot.driveSubsystem.tankDrive(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
        }
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
