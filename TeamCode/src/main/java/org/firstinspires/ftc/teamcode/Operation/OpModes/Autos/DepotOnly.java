package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;
import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Vision.Sampler;
@Autonomous(name = "Depot Only")
public class DepotOnly extends ExplosiveAuto{
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
        marker.setPosition(1);
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
        robot.driveSubsystem.driveEncoders(200,0.5);
        if(mineralPosition == 3){ // middle mineral
            robot.driveSubsystem.driveEncoders(1130, 0.7);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-1130, 0.7);
        } else if(mineralPosition == 1){// right mineral
            robot.driveSubsystem.turn(-45);
            robot.driveSubsystem.driveEncoders(450, 0.5);
            robot.driveSubsystem.turn(60);
            robot.driveSubsystem.driveEncoders(550, 0.5);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-550, -0.5);
            robot.driveSubsystem.turn(-60);
            robot.driveSubsystem.driveEncoders(-450, -0.5);
            robot.driveSubsystem.turn(45);
        } else if(mineralPosition == 2){//left mineral
            robot.driveSubsystem.turn(45);
            robot.driveSubsystem.driveEncoders(450, 0.5);
            robot.driveSubsystem.turn(-60);
            robot.driveSubsystem.driveEncoders(550, 0.5);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-550, -0.5);
            robot.driveSubsystem.turn(60);
            robot.driveSubsystem.driveEncoders(-450, -0.5);
            robot.driveSubsystem.turn(-45);
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