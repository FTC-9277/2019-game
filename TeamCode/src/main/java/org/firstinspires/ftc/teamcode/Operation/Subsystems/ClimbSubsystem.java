package org.firstinspires.ftc.teamcode.Operation.Subsystems;
import android.graphics.Path;
import android.sax.EndElementListener;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class ClimbSubsystem extends Subsystem {

    DcMotor climber;
    double encoderTicks;

    final double SPEED_MULTIPLIER = 0.1;

    public OpMode opMode;

    public ClimbSubsystem(DcMotor climber, OpMode opmode){
        super(opmode);
        this.climber = climber;
        this.opMode = opmode;
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

    public void ascend(long time){
        long s = System.currentTimeMillis();
        climber.setPower(0.5);
        while(System.currentTimeMillis() < s+time) {
        }
        climber.setPower(0);
    }

    public void ascend() {
        climber.setPower(0.5);
    }

    public void descend() {
        climber.setPower(-0.5);
    }

    public void descend(long time) {
        while (System.currentTimeMillis()/1000 < time){
            climber.setPower(-0.5);
        }
        climber.setPower(0);
    }

}