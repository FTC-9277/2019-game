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

@Autonomous(name = "Crater Only")
public class CraterOnly extends ExplosiveAuto{
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
        marker.setPosition(1);
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
    robot.driveSubsystem.driveEncoders(200, 0.5);
        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        if(mineralPosition == 3){
            robot.driveSubsystem.driveEncoders(800, 0.8);
        } else if(mineralPosition == 2){ // right mineral
            robot.driveSubsystem.turn(50);
            robot.driveSubsystem.driveEncoders(750, 0.5);
            robot.driveSubsystem.turn(-60);
            robot.driveSubsystem.driveEncoders(100, 0.7);
        } else if(mineralPosition == 1){ // left mineral
            robot.driveSubsystem.turn(-50);
            robot.driveSubsystem.driveEncoders(750, 0.5);
            robot.driveSubsystem.turn(60);
            robot.driveSubsystem.driveEncoders(100, 0.7);
        }
    }

    @Override
    public void exit() throws InterruptedException {
    }

    @Override
    protected void initLoop() {
//        robot.climbSubsystem.maintain();
    }

    void waitMillis(int millis) {
        long t = System.currentTimeMillis()+millis;
        while(System.currentTimeMillis()<t) {
            //Do nothing
        }
    }

}