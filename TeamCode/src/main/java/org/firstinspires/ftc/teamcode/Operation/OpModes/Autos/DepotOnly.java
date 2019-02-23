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

        telemetry.addData("Encoder", robot.climbSubsystem.climber.getCurrentPosition());
        telemetry.update();

        //Climb
        robot.climbSubsystem.climber.setPower(-1);
        while(robot.climbSubsystem.climber.getCurrentPosition() > -19000 && opModeIsActive()) {
            telemetry.addData("Encoder", robot.climbSubsystem.climber.getCurrentPosition());
            telemetry.update();
        }

        telemetry.addData("Encoder", robot.climbSubsystem.climber.getCurrentPosition());
        telemetry.update();

        robot.driveSubsystem.right.set(0.8);
        robot.driveSubsystem.left.set(-0.8);
        waitMillis(200);
        robot.driveSubsystem.driveEncoders(10, 0.8);
        robot.driveSubsystem.right.set(-0.6);
        robot.driveSubsystem.left.set(0.8);
        waitMillis(150);
        robot.driveSubsystem.driveEncoders(-5, -0.5);

        //Sampling
        int mineralPosition = 3;
        if(sample.sample() == 1) {
            //Good
        } else {
            //Turn 45 degrees
            robot.driveSubsystem.turn(-60);
            if(sample.sample() == 1) {
                //Left mineral
                mineralPosition = 1;
                robot.driveSubsystem.turn(60);
            } else {
                //Right mineral
                mineralPosition = 2;
                robot.driveSubsystem.turn(60);
            }
        }

        waitMillis(500);

        Log.d("Robot", "Mineral Position Seen: " + mineralPosition);
        robot.driveSubsystem.driveEncoders(200,0.5);
        if(mineralPosition == 3){ // middle mineral
            robot.driveSubsystem.driveEncoders(1130, 0.7);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-500, 0.7);
        } else if(mineralPosition == 1){// right mineral
            robot.driveSubsystem.turn(-55);
            robot.driveSubsystem.driveEncoders(450, 0.5);
            robot.driveSubsystem.turn(70);
            robot.driveSubsystem.driveEncoders(550, 0.5);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-100, -0.5);
        } else if(mineralPosition == 2){//left mineral
            robot.driveSubsystem.turn(55);
            robot.driveSubsystem.driveEncoders(450, 0.5);
            robot.driveSubsystem.turn(-70);
            robot.driveSubsystem.driveEncoders(550, 0.5);
            marker.setPosition(0);
            waitMillis(1000);
            robot.driveSubsystem.driveEncoders(-100, -0.5);
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
        while(System.currentTimeMillis()<t && opModeIsActive()) {
            //Do nothing
        }
    }
}