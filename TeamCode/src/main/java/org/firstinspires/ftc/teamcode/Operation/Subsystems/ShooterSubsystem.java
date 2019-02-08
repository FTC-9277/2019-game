package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class ShooterSubsystem {
    DcMotor shooter;

    public ShooterSubsystem(DcMotor shooter, OpMode opmode){
        this.shooter = shooter;
    }

    public void setShooter(Double pow) {
        shooter.setPower(-pow);
    }

    public double getShooterPower() {
        return shooter.getPower();
    }

}
