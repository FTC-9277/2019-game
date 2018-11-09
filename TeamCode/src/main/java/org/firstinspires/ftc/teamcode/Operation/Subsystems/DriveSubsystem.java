package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDController;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class DriveSubsystem extends Subsystem {
    MotorGroup left, right;
    double currentLeft, currentRight;
    OpMode opMode;
    ExplosiveAuto auto;
    ExplosiveBNO055 gyro;
    public ExplosivePIDController controller;
    boolean isAuto, hasGyro;

    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, OpMode opmode){
        super(opmode);
        this.opMode = opmode;
        this.left = new MotorGroup(opmode,fLeft,bLeft);
        this.right = new MotorGroup(opmode,fRight,bRight);
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        isAuto = opmode instanceof ExplosiveAuto;
        if(isAuto) auto = (ExplosiveAuto)opmode;
        hasGyro = false;
    }

    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, ExplosiveBNO055 gyro, OpMode opmode){
        super(opmode);
        this.opMode = opmode;
        this.left = new MotorGroup(opmode,fLeft,bLeft);
        this.right = new MotorGroup(opmode,fRight,bRight);
        this.gyro = gyro;
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        isAuto = opmode instanceof ExplosiveAuto;
        if(isAuto) auto = (ExplosiveAuto)opmode;
        hasGyro = true;

        controller = new ExplosivePIDController(gyro);
    }


    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void stop() {

    }

    public double getYaw(){
        if(hasGyro) return gyro.getYaw();
        else return 0;
    }

    public void resetEncoders() throws InterruptedException {
        left.reset();
        right.reset();
    }

    public void tankDrive(double l, double r){
        left.set(l);
        right.set(r);
    }

    public void PIDDrive(int clicks, double pow){
        if(isAuto){
            double sign = 1;
            currentLeft = 0;
            currentRight = 0;
            if(clicks < 0){
                clicks = -clicks;
                sign = -1;
            }
            controller.setTarget(gyro.getYaw());
            controller.setTolerance(2);
            controller.enable(0.1,0,0);
            while ((currentLeft < clicks || currentRight < clicks || controller.output > controller.tolerance) && auto.opModeIsActive()) {
                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;
                left.set(pow * sign + controller.getOutput());
                //Log.d("Robot", "PID Output: " + controller.getOutput());
                right.set(pow * sign - controller.getOutput());
            }
            left.set(0);
            right.set(0);
        }
    }

    public void PIDTurnToAngle(double angle, int time){
        if(isAuto){
            double start = System.currentTimeMillis();
            controller.setTarget(angle);
            controller.setTolerance(0);
            controller.enable(0.015,0.0001,0); //0.012, 0.0001
            while(System.currentTimeMillis() - start < time && auto.opModeIsActive()){
                left.set(controller.getOutput());
                right.set(-controller.getOutput());
            }
            left.set(0);
            right.set(0);
        }
    }

    public void autoDrive(int clicks, double pow){
        if(isAuto){
            double sign = 1;
            currentLeft = 0;
            currentRight = 0;
            if(clicks < 0){
                clicks = -clicks;
                sign = -1;
            }
            while ((currentLeft < clicks || currentRight < clicks) && auto.opModeIsActive()) {
                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;

                if (currentLeft < clicks) {
                    left.set(pow * sign);
                } else {
                    left.set(0);
                }

                if (currentRight < clicks) {
                    right.set(pow * sign);
                } else {
                    right.set(0);
                }
            }
        }
    }
}
