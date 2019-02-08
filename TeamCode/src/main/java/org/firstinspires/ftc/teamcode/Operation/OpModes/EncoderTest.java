package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Encoder Test")
public class EncoderTest extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new ExplosivesRobot(this);

        while(opModeIsActive()) {
            telemetry.addData("Left", robot.driveSubsystem.leftEncoder());
            telemetry.addData("Right", robot.driveSubsystem.rightEncoder()
            );
        }
    }
}
