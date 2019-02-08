package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Vision.Sampler;

@Autonomous(name = "LC Depot Marker")
public class DepotPark extends ExplosiveAuto{
    Sampler sample;
    ExplosivesRobot robot;
    Servo marker;
    final double TURN_SCALAR = 1;
    final double DRIVE_SCALAR = 1;

    BNO055IMU imu;

    Orientation last = new Orientation();

    double globalangle;

    @Override
    public void initHardware() {
        sample = new Sampler(this);
        marker = hardwareMap.get(Servo.class, "marker");
        robot = new ExplosivesRobot(this);
//        robot.climbSubsystem.setEncoderTicks();

        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
        parms.mode = BNO055IMU.SensorMode.IMU;
        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class,"imu");

        imu.initialize(parms);
    }

    @Override
    public void initAction() {
        robot.diverterSubsystem.setDiverter(1.0);
    }


    @Override
    public void body() throws InterruptedException {
        int mineralPosition = sample.sample();
        //int mineralPosition = 3;
        Log.d("Robot", "Sampled: " + mineralPosition);
/*
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < 2000 && opModeIsActive()){
            Thread.sleep(1);
        }*/
        robot.driveSubsystem.resetEncoders();
        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        if(mineralPosition == 3){
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(48), 0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.turn(180);
            marker.setPosition(0.5);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.turn(-180);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-200,0.5,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(-60,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-250, 0.5, DRIVE_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(10,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-1450, 0.8,DRIVE_SCALAR);
        } else if(mineralPosition == 1){
            robot.driveSubsystem.turn(60);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(32),0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.turn(165);
            robot.driveSubsystem.autoScaledDrive(-DriveSubsystem.lengthToTicks(28), 0.5, DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            marker.setPosition(0.5);
            robot.driveSubsystem.autoScaledDrive(-DriveSubsystem.lengthToTicks(12), 0.5, DRIVE_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-300, 0.5,DRIVE_SCALAR);
        } else if(mineralPosition == 2){ // try curve driving
            robot.driveSubsystem.turn(-60);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(32),0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.turn(-165);
            robot.driveSubsystem.autoScaledDrive(-DriveSubsystem.lengthToTicks(28), 0.5, DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            marker.setPosition(0.5);
            robot.driveSubsystem.autoScaledDrive(-DriveSubsystem.lengthToTicks(12), 0.5, DRIVE_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-300, 0.5,DRIVE_SCALAR);
        }
    }

    @Override
    public void exit() throws InterruptedException {
    }

    @Override
    protected void initLoop() {
        robot.climbSubsystem.maintain();
    }

}