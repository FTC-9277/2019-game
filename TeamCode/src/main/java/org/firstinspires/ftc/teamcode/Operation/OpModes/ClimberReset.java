package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;

@TeleOp(name = "Climber Reset")
public class ClimberReset extends LinearOpMode {

    /*
    This is used to manually reset the climber to the bottom before
    Auto, since the FullTele has a limit that to make sure it
    doesn't go below a certain value
    */

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y) {
                robot.climbSubsystem.climber.setPower(-1);
            } else if (gamepad1.a) {
                robot.climbSubsystem.climber.setPower(1);
            } else {
                robot.climbSubsystem.climber.setPower(0);
            }
        }
    }

}
