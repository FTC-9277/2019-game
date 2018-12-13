package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@Autonomous(name = "Jank Auto")
public class JankAutoDrive extends ExplosiveAuto {
    DcMotor fLeft, fRight, bLeft, bRight;
    MotorGroup left, right;
    ExplosivesRobot robot;

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
        robot.drive.autoScaledDrive(-1000,0.5,0.89);
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
