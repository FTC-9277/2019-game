package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class ShooterSubsystem extends Subsystem {
    DcMotor shooter;

    public ShooterSubsystem(DcMotor shooter, OpMode opmode){
        super(opmode);
        this.shooter = shooter;
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

    public void setShooter(Double pow) {
        shooter.setPower(pow);
    }

    public double getShooterPower() {
        return shooter.getPower();
    }

}
