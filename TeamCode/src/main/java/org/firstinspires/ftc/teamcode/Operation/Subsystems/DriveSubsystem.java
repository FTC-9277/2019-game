package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDController;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroupPIDSource;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;
import org.firstinspires.ftc.teamcode.Sensors.Gyro;

public class DriveSubsystem {
    MotorGroup left, right;
    double currentLeft, currentRight;
    OpMode opMode;
    LinearOpMode auto;
    Gyro gyro;
    public ExplosivePIDController controller;
    boolean isAuto, hasGyro;

    Orientation last = new Orientation();

    public static int lengthToTicks(double l) {
//        double cir = 4 * Math.PI;
//
//        return (int) (l/cir)*33*2240;//0.054
        //27in = 500 ticks
        //Every tick is about 0.054 inches

        return (int) Math.round(l*0.044);

//        return (int) l*0.054;
    }
    //2240 ticks per rotation
    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, OpMode opmode){
        this.opMode = opmode;
        this.left = new MotorGroup(opmode,fLeft,bLeft);
        this.right = new MotorGroup(opmode,fRight,bRight);
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        isAuto = opmode instanceof LinearOpMode;
        if(isAuto) auto = (LinearOpMode) opmode;
        hasGyro = false;
    }

    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, Gyro gyro, OpMode opmode){
        this.opMode = opmode;
        this.left = new MotorGroup(opmode,fLeft,bLeft);
        this.right = new MotorGroup(opmode,fRight,bRight);
        this.gyro = gyro;
        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
        isAuto = opmode instanceof LinearOpMode;
        if(isAuto) auto = (LinearOpMode) opmode;
        hasGyro = true;
    }

//    public DriveSubsystem(DcMotor fLeft, DcMotor fRight, DcMotor bLeft, DcMotor bRight, MotorGroupPIDSource gyro, OpMode opmode){
//        this.opMode = opmode;
//        this.left = new MotorGroup(opmode,fLeft,bLeft);
//        this.right = new MotorGroup(opmode,fRight,bRight);
//        this.gyro = gyro;
//        right.setDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);
//        isAuto = opmode instanceof ExplosiveAuto;
//        if(isAuto) auto = (ExplosiveAuto)opmode;
//        hasGyro = true;
//
//        controller = new ExplosivePIDController(gyro);
//        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
//        parms.mode = BNO055IMU.SensorMode.IMU;
//        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//
//        imu = opmode.hardwareMap.get(BNO055IMU.class,"imu");
//
//        imu.initialize(parms);
//    }

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

    //    public void PIDDrive(int clicks, double pow){
//        if(isAuto){
//            double sign = 1;
//            currentLeft = 0;
//            currentRight = 0;
//            if(clicks < 0){
//                clicks = -clicks;
//                sign = -1;
//            }
//            controller.setTarget(gyro.getOutput());
//            controller.setTolerance(0);
//            controller.enable(0.004,0.00005,0); // 0.1
//            while ((currentLeft < clicks || currentRight < clicks || controller.output > controller.tolerance) && auto.opModeIsActive()) {
//                currentLeft = left.getPosition() * sign;
//                currentRight = right.getPosition() * sign;
//                left.set(pow * sign + controller.getOutput());
//                Log.d("Robot", "PID Output: " + controller.getOutput() + " Error: " + controller.p);
//                right.set(pow * sign - controller.getOutput());
//            }
//            left.set(0);
//            right.set(0);
//        }
//    }
//
//    public void PIDTurnToAngle(double angle, int time){
//        if(isAuto){
//            double start = System.currentTimeMillis();
//            controller.setTarget(angle);
//            controller.setTolerance(0);
//            controller.enable(0.0008,0.0001,0); //0.012, 0.0001
//            while(System.currentTimeMillis() - start < time && auto.opModeIsActive()){
//                left.set(controller.getOutput());
//                right.set(-controller.getOutput());
//                Log.d("Robot", "PID Turn Output: " + controller.getOutput() + " Error: " + controller.p);
//            }
//            left.set(0);
//            right.set(0);
//        }
//    }
//
    public void autoDrive(int clicks, double pow){
        if(isAuto){
            double sign = 1;
            currentLeft = 0;
            currentRight = 0;
            if(clicks < 0){
                clicks = -clicks;
                sign = -1;
            }

            Double last = gyro.heading();

            while ((currentLeft < clicks || currentRight < clicks) && auto.opModeIsActive()) {

                double difference = last-gyro.heading();

                currentLeft = left.getPosition() * sign;
                currentRight = right.getPosition() * sign;

                if (Math.abs(difference) < 3) {
                    if (currentLeft < clicks) {
                        left.set((pow * sign));
                    } else {
                        left.set(0);
                    }

                    if (currentRight < clicks) {
                        right.set(pow * sign);
                    } else {
                        right.set(0);
                    }
                } else if (difference < 0) { //It's more than it should be; counterclockwise, should turn clockwise
                    if (currentLeft < clicks) {
                        left.set((pow * sign));
                    } else {
                        left.set(0);
                    }

                    if (currentRight < clicks) {
                        right.set((pow-0.1) * sign);
                    } else {
                        right.set(0);
                    }
                } else {
                    if (currentLeft < clicks) {
                        left.set(((pow-0.1) * sign));
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

    public void driveInches(double inches) {
        autoDrive(lengthToTicks(inches), 0.5);
    }

    public void driveEncoders(int clicks, double pow) {

        double initialRight = rightEncoder();
        double initialLeft = leftEncoder();

        if(clicks > 0) {
            while (rightEncoder() < initialRight + clicks || leftEncoder() < initialLeft + clicks) {
                opMode.telemetry.addData("Left", leftEncoder());
                opMode.telemetry.addData("Right", rightEncoder());
                opMode.telemetry.update();
                if (leftEncoder() < initialLeft + clicks) {
                    left.set(pow);
                } else {
                    left.set(0);
                }
                if (rightEncoder() < initialRight + clicks) {
                    right.set(pow);
                } else {
                    right.set(0);
                }
            }
        } else {
            while (rightEncoder() > initialRight + clicks || leftEncoder() > initialLeft + clicks) {
                opMode.telemetry.addData("Left", leftEncoder());
                opMode.telemetry.addData("Right", rightEncoder());
                opMode.telemetry.update();
                if (leftEncoder() > initialLeft + clicks) {
                    left.set(pow);
                } else {
                    left.set(0);
                }
                if (rightEncoder() > initialRight + clicks) {
                    right.set(pow);
                } else {
                    right.set(0);
                }
            }
        }

        left.set(0);
        right.set(0);
    }

    public void drive(int clicks) throws InterruptedException {

        double initialGyro = gyro.heading();

        if (clicks > 0) {
            while (left.getPosition() < clicks) {
                tankDrive(1, 1);
            }
        } else {
            while(left.getPosition() > -clicks) {
                tankDrive(-1, -1);
            }
        }

        while(Math.abs(initialGyro-gyro.heading()) > 5) {
            if (gyro.heading() > initialGyro) { //Counterclockwise, should be clockwise
                left.set(0.3);
                right.set(0);
            } else { //Clockwise, should be counterclockwise
                left.set(0);
                right.set(0.3);
            }
        }

        tankDrive(0,0);

        resetEncoders();

//        resetEncoders();
//
//        double initialAngle = gyro.heading();
//
//        while(right.getPosition() < clicks) {
//
//            opMode.telemetry.addData("Difference",Math.abs(initialAngle)-gyro.heading());
//            opMode.telemetry.addData("<", gyro.heading());
//            opMode.telemetry.addData("Initial", initialAngle);
//            opMode.telemetry.addData("Encoder",right.getPosition());
//            opMode.telemetry.update();
//
//            if(Math.abs(initialAngle)-gyro.heading() < 3) {
//                left.set(0.6);
//                right.set(0.725);
//            } else if(initialAngle < gyro.heading()) { //It's more than it needs to be; counterclockwise, should be clockwise
//                left.set(0.4);
//                right.set(0.7);
//            } else if(initialAngle > gyro.heading()) { //It's less than it needs to be; clockwise, should be counterclockwise
//                left.set(0.3);
//                right.set(1);
//            }
//        }
//
//        left.set(0);
//        right.set(0);
//
//        resetEncoders();
    }

    public void autoScaledDrive(int clicks, double pow, double scalar) {

        try {
            drive(clicks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        if (isAuto) {
//
//            try {
//                resetEncoders();
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            double sign = 1;
//            currentLeft = 0;
//            currentRight = 0;
//            if (clicks < 0) {
//                clicks = -clicks;
//                sign = -1;
//            }
//            while ((currentRight < clicks) && auto.opModeIsActive()) {
////                currentLeft = left.getPosition() * sign;
//                currentRight = right.getPosition() * sign;
//
//                /*if (currentLeft < clicks) {
//                    left.set(pow * sign);
//                } else {
//                    left.set(0);
//                }
//
//                if (currentRight < clicks) {
//                    right.set(pow * sign * scalar);
//                } else {
//                    right.set(0);
//                }*/
//                left.set(pow * sign);
//                right.set(pow * sign * scalar);
//            }
//            left.set(0);
//            right.set(0);
//        }
//
//        try {
//            resetEncoders();
//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }
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

    public void turn(int degrees) {
        last = gyro.getOrientation();
        double current = heading();
        double target = current+degrees;

        double difference = target-current;

        if (target < -180){
            target+=360;
        } else if (target > 180){
            target-=360;
        }

        long start = System.currentTimeMillis();

        while(auto.opModeIsActive() && Math.abs(heading()-target)>2 && start+2000 > System.currentTimeMillis()) {
            current = heading();
            difference = target - current;

            if (difference > 50) {
                left.set(-0.8);
                right.set(0.8);
            } else if (difference > 30) {
                left.set(-0.3);
                right.set(0.3);
            } else if (difference > 15) {
                left.set(-0.25);
                right.set(0.25);
            } else if (difference < -50) {
                left.set(0.8);
                right.set(-0.8);
            } else if (difference <-30){
                left.set(0.3);
                right.set(-0.3);
            } else if (difference <-15) {
                left.set(0.25);
                right.set(-0.25);
            }

            opMode.telemetry.addData(">",heading());
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



    public double leftEncoder() {
        return left.getPosition();
    }

    public double rightEncoder() {
        return right.getPosition();
    }

    public double heading() {
//        Orientation angles = gyro.getOrientation();

//        double delta = angles.firstAngle - last.firstAngle;
//        return delta;

        return gyro.heading();
    }

    public double roll() {
        return gyro.roll();
    }

    public double pitch() {
        return gyro.pitch();
    }

    public Position getPosition() {
        return gyro.getPosition();
    }

    public void swingTurn(double speed, double swingDistance){
        final double r = 15.789;
        double leftPower = speed*(1+((swingDistance/(2*r))));
        double rightPower = speed*(1-((swingDistance/(2*r))));
        //while(auto.opModeIsActive() && Math.abs(heading()-target)>2 && start+2000 > System.currentTimeMillis())

    }
}
