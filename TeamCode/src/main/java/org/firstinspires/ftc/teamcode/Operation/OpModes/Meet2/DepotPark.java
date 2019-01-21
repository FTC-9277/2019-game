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
    double currentLeft = 0, currentRight = 0;
    double climb = 0.23, unhook = 0.975, redeploy = 0.3;

    @Override
    public void initHardware() {
        sample = new Sampler(this);
        marker = hardwareMap.get(Servo.class, "marker");
        robot = new ExplosivesRobot(this);
    }

    @Override
    public void initAction() {
        robot.climbSubsystem.set(climb);
    }

    @Override
    public void body() throws InterruptedException {
        int mineralPosition = sample.sample();
        Log.d("Robot", "Sampled: " + mineralPosition);
        robot.climbSubsystem.set(unhook);

        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < 10000 && opModeIsActive()){
            Thread.sleep(5);
        }

        robot.driveSubsystem.resetEncoders();
        robot.driveSubsystem.autoScaledDrive(200,0.5,0.89);
        robot.climbSubsystem.set(redeploy);
        robot.driveSubsystem.resetEncoders();
        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        if(mineralPosition == 3){
            robot.driveSubsystem.autoScaledDrive(1100, 0.5,0.89);
            marker.setPosition(0.5);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(-60,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(-250, 0.5, 0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(10,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(-1450, 0.8,0.89);
        } else if(mineralPosition == 1){
            robot.driveSubsystem.autoScaledTurn(45,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500,0.5,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(-60,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500, 0.5,0.89);
            marker.setPosition(0.5);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(-30,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(-1350,0.5,0.89);
        } else if(mineralPosition == 2){
            robot.driveSubsystem.autoScaledTurn(-45,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500,0.5,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(60,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(500, 0.5,0.89);
            marker.setPosition(0.5);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(75,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(400,0.5,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledTurn(60,0.3,0.89);
            robot.driveSubsystem.resetEncoders();
            robot.driveSubsystem.autoScaledDrive(1500,0.8,0.89);
        }

    }

    @Override
    public void exit() throws InterruptedException {

    }
}
