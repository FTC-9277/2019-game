package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

//@Autonomous(name = "Turn Test")
public class TurnTest extends ExplosiveAuto {

    ExplosivesRobot robot;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void exit() throws InterruptedException {

    }

    @Override
    protected void initLoop() {

    }

    @Override
    public void body() throws InterruptedException {
        robot.driveSubsystem.turn(60);
        robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(24),0.5,1);
        robot.driveSubsystem.turn(-30);
        robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(10), 0.5, 1);
    }

}
