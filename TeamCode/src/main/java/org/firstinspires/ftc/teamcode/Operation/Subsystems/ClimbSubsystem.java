package org.firstinspires.ftc.teamcode.Operation.Subsystems;
import android.graphics.Path;
import android.sax.EndElementListener;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class ClimbSubsystem {

    public DcMotor climber;
    double encoderTicks;

    final double SPEED_MULTIPLIER = 0.1;

    public OpMode opMode;

    public ClimbSubsystem(DcMotor climber, OpMode opmode){
        this.climber = climber;
        this.opMode = opmode;
    }

    public void setEncoderTicks() {
        encoderTicks = climber.getCurrentPosition();
    }

    public void maintain(){
        if(Math.abs(encoderTicks - climber.getCurrentPosition()) < 10){
            climber.setPower(0);
            return;
        } else if (encoderTicks < climber.getCurrentPosition()){
            climber.setPower(-(climber.getCurrentPosition() * SPEED_MULTIPLIER));
        }else{
            climber.setPower((climber.getCurrentPosition() * SPEED_MULTIPLIER));
        }
    }

//    public void ascend(long millis){
//        long s = System.currentTimeMillis();
//        ascend();
//        while(System.currentTimeMillis() < s+millis) {
//        }
//        stopClimbing();
//    }

    public void ascend() {
        if(getEncoderTicks() > -22000) {
            climber.setPower(-1);
        } else {
            climber.setPower(0);
        }
    }

    public void descend() {
        if(getEncoderTicks() < -1000) {
            climber.setPower(1);
        } else {
            climber.setPower(0);
        }
    }

//    public void descend(long millis) {
//        long s = System.currentTimeMillis();
//        descend();
//        while (System.currentTimeMillis() < s+millis){
//        }
//        stopClimbing();
//    }

    public void stopClimbing() {
        climber.setPower(0.0);
    }

    public double getEncoderTicks() {
        return climber.getCurrentPosition();
    }

}