package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDController;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroupPIDSource;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class DriveSubsystem extends Subsystem {
    MotorGroup left, right;
    double currentLeft, currentRight;
    OpMode opMode;
    ExplosiveAuto auto;
    ExplosivePIDEnabledHardware gyro;
    public ExplosivePIDController controller;
    boolean isAuto, hasGyro;

    public BNO055IMU imu;
    Orientation last = new Orientation();



    public static int lengthToTicks(double l){
        double cir = 4 * Math.PI;

        return (int) (l/cir)*33*2240;
    }
//2240 ticks per rotation
    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, OpMode opmode){
        super(opmode);
        this.opMode = opmode;
        this.left = new MotorGroup(opmode,fLeft,bLeft);
        this.right = new MotorGroup(opmode,fRight,bRight);
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        isAuto = opmode instanceof ExplosiveAuto;
        if(isAuto) auto = (ExplosiveAuto)opmode;
        hasGyro = false;
        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
        parms.mode = BNO055IMU.SensorMode.IMU;
        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = opmode.hardwareMap.get(BNO055IMU.class,"imu");

        imu.initialize(parms);
    }

    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, ExplosivePIDEnabledHardware gyro, OpMode opmode){
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
        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
        parms.mode = BNO055IMU.SensorMode.IMU;
        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = opmode.hardwareMap.get(BNO055IMU.class,"imu");

        imu.initialize(parms);
    }

    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, MotorGroupPIDSource gyro, OpMode opmode){
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
        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
        parms.mode = BNO055IMU.SensorMode.IMU;
        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = opmode.hardwareMap.get(BNO055IMU.class,"imu");

        imu.initialize(parms);
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

    public double getOutput(){
        if(hasGyro) return gyro.getOutput();
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

    public void arcadeDrive(double x, double y){
        left.set(y-x);
        right.set(y+x);
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
            controller.setTarget(gyro.getOutput());
            controller.setTolerance(0);
            controller.enable(0.004,0.00005,0); // 0.1
            while ((currentLeft < clicks || currentRight < clicks || controller.output > controller.tolerance) && auto.opModeIsActive()) {
                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;
                left.set(pow * sign + controller.getOutput());
                Log.d("Robot", "PID Output: " + controller.getOutput() + " Error: " + controller.p);
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
            controller.enable(0.0008,0.0001,0); //0.012, 0.0001
            while(System.currentTimeMillis() - start < time && auto.opModeIsActive()){
                left.set(controller.getOutput());
                right.set(-controller.getOutput());
                Log.d("Robot", "PID Turn Output: " + controller.getOutput() + " Error: " + controller.p);
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

    public void autoScaledDrive(int clicks, double pow, double scalar) {
        if (isAuto) {
            double sign = 1;
            currentLeft = 0;
            currentRight = 0;
            if (clicks < 0) {
                clicks = -clicks;
                sign = -1;
            }
            while ((currentRight < clicks) && auto.opModeIsActive()) {
//                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;

                /*if (currentLeft < clicks) {
                    left.set(pow * sign);
                } else {
                    left.set(0);
                }

                if (currentRight < clicks) {
                    right.set(pow * sign * scalar);
                } else {
                    right.set(0);
                }*/
                left.set(pow * sign);
                right.set(pow * sign * scalar);
            }
            left.set(0);
            right.set(0);
        }
    }

    public void autoScaledTurn(int degrees, double pow, double scalar){
        if(isAuto){
            double sign = 1;
            double clicks = degrees * 3.167;
            currentLeft = 0;
            currentRight = 0;
            if (clicks < 0) {
                clicks = -clicks;
                sign = -1;
            }
            while ((currentLeft < clicks || currentRight > clicks) && auto.opModeIsActive()) {
                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;

                left.set(pow * sign);
                right.set(pow * sign * scalar * -1);
            }
            left.set(0);
            right.set(0);
        }
    }

    public void turn(int degrees) { //gyro = -180, degrees = 90
        last = imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.DEGREES);
        double current = angle();
        double target = current+degrees;

        double difference = target-current;

        if (target < -180){
            target+=360;
        } else if (target > 180){
            target-=360;
        }

        long start = System.currentTimeMillis();

        while(auto.opModeIsActive() && Math.abs(angle()-target)>2 && start+2000 > System.currentTimeMillis()) {
            current = angle();
            difference = target - current;

            if (difference > 25) {
                left.set(-0.5);
                right.set(0.5);
            } else if (difference > 10) {
                left.set(-0.1);
                right.set(0.1);
            } else if (difference < -25) {
                left.set(0.5);
                right.set(-0.5);
            } else if (difference <-10){
                left.set(0.1);
                right.set(-0.1);
            }
            opMode.telemetry.addData(">",angle());
            opMode.telemetry.addData("Targ",target);
            opMode.telemetry.update();
        }
        left.set(0);
        right.set(0);
        /*
        if(isAuto) {

            double sign = 1;
            double clicks = degrees + angle(); //clicks = -90

            if (clicks < gyro.getOutput()) { // -90 < -180
//                clicks = -clicks;
                sign = -1;
            }

            if(degrees < 0) { // -90 < 0
                while ((gyro.getOutput() > clicks) && auto.opModeIsActive()) { // -180 > -270
                    left.set(1 * sign);
                    right.set(1 * sign * -1);
                }
            } else {
                while ((gyro.getOutput() < clicks) && auto.opModeIsActive()) {
                    left.set(1 * sign);
                    right.set(1 * sign * -1);
                }
            }

            left.set(0);
            right.set(0);
        }*/
    }

    public double angle(){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.DEGREES);

        double delta = angles.firstAngle - last.firstAngle;
        return delta;
    }
}
