package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@Autonomous(name = "ForwardTest")
public class ForwardTest extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);

        long t = System.currentTimeMillis();

        robot.driveSubsystem.autoScaledDrive(150, 1, 1);
    }
}
