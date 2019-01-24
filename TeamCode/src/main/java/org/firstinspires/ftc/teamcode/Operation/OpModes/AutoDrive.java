package org.firstinspires.ftc.teamcode.Operation.OpModes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@Autonomous(name = "AutoDrive")
public class AutoDrive extends ExplosiveAuto {
    ExplosivesRobot robot;
    double yaw;
    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() throws InterruptedException {
        robot.driveSubsystem.resetEncoders();
        yaw = robot.driveSubsystem.getOutput();
        robot.driveSubsystem.PIDDrive(1500, 0.5);
        robot.driveSubsystem.PIDTurnToAngle(yaw,5000);
    }

    @Override
    public void exit() throws InterruptedException {

    }

    @Override
    protected void climberMaintain() {}

}
