package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Vision.Sampler;

@Autonomous(name = "LC Crater Park")
public class CraterMarker extends ExplosiveAuto{
    Sampler sample;
    ExplosivesRobot robot;
    Servo marker;

    final double DRIVE_SCALAR = 1;
    final double TURN_SCALAR = 1;

    @Override
    public void initHardware() {
        sample = new Sampler(this);
        marker = hardwareMap.get(Servo.class, "marker");
        robot = new ExplosivesRobot(this);
    }

    @Override
    public void initAction() {
        robot.diverterSubsystem.setDiverter(1.0);
    }

    @Override
    protected void climberMaintain() {
//        robot.climbSubsystem.maintain();
    }

    @Override
    public void body() throws InterruptedException {
        int mineralPosition = sample.sample();
        //int mineralPosition = 3;
        Log.d("Robot", "Sampled: " + mineralPosition);
/*
        long time = System.currentTimeMillis
        while(System.currentTimeMillis() - time < 2000 && opModeIsActive()){
            Thread.sleep(1);
        }*/
        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        if(mineralPosition == 3){
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(32), 0.5,DRIVE_SCALAR);
            //400
        } else if(mineralPosition == 1){
            robot.driveSubsystem.turn(45);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(24),0.5,DRIVE_SCALAR);
            robot.driveSubsystem.turn(-45);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(8), 0.5, DRIVE_SCALAR);
        } else if(mineralPosition == 2){
            robot.driveSubsystem.turn(-45);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(24),0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.turn(-45);
            robot.driveSubsystem.autoScaledDrive(DriveSubsystem.lengthToTicks(8), 0.5, DRIVE_SCALAR);
        }

    }

    @Override
    public void exit() throws InterruptedException {

    }
}
