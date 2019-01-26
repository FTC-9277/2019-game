package org.firstinspires.ftc.teamcode.Operation.OpModes.Meet2;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Vision.Sampler;

@Autonomous(name = "Depot Park")
public class DepotPark extends ExplosiveAuto{
    Sampler sample;
    ExplosivesRobot robot;
    Servo marker;
    //double currentLeft = 0, currentRight = 0;
    //double climb = 0.23, unhook = 0.975, redeploy = 0.3;
    final double TURN_SCALAR = 1.35;
    final double DRIVE_SCALAR = 0.86;

    @Override
    public void initHardware() {
        sample = new Sampler(this);
        marker = hardwareMap.get(Servo.class, "marker");
        robot = new ExplosivesRobot(this);
        robot.climbSubsystem.setEncoderTicks();
    }

    @Override
    public void initAction() {}

    @Override
    protected void climberMaintain() {
        robot.climbSubsystem.maintain();
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
        robot.climbSubsystem.ascend(1000);
        robot.driveSubsystem.resetEncoders();
        robot.driveSubsystem.autoScaledDrive(200,0.5,DRIVE_SCALAR);
        robot.climbSubsystem.descend(3000);
        robot.driveSubsystem.resetEncoders();
        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        if(mineralPosition == 3){
            robot.driveSubsystem.autoScaledDrive(1100, 0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            marker.setPosition(0.5);
            robot.driveSubsystem.autoScaledTurn(135,0.3,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(1000,0.5,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(-60,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-250, 0.5, DRIVE_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(10,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-1450, 0.8,DRIVE_SCALAR);
        } else if(mineralPosition == 1){
            robot.driveSubsystem.autoScaledTurn(45,0.3,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500,0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(-90,0.3,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500, 0.5,DRIVE_SCALAR);
            marker.setPosition(0.5);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(-1000,0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(-30,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(-1350,0.5,DRIVE_SCALAR);
        } else if(mineralPosition == 2){ // try curve driving
            robot.driveSubsystem.autoScaledTurn(-45,0.3,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500,0.5,DRIVE_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(60,0.3,TURN_SCALAR);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500, 0.5,DRIVE_SCALAR);
            marker.setPosition(0.5);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(45,0.3,TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(500, 0.3, DRIVE_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledTurn(25, 0.3, TURN_SCALAR);
            //robot.driveSubsystem.resetEncoders();
            //robot.driveSubsystem.autoScaledDrive(1500,0.8,DRIVE_SCALAR);
        }
    }

    @Override
    public void exit() throws InterruptedException {
    }

}