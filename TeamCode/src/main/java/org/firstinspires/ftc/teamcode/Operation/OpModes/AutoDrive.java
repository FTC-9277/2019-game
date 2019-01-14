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
        robot.drive.resetEncoders();
        yaw = robot.drive.getOutput();
        robot.drive.PIDDrive(1500, 0.5);
        robot.drive.PIDTurnToAngle(yaw,5000);
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
