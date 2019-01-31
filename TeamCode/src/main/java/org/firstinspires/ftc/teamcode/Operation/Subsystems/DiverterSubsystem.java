package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class DiverterSubsystem extends Subsystem {

    Servo diverter;

    public DiverterSubsystem(Servo diverter, OpMode opMode) {
        super(opMode);
        this.diverter = diverter;

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

    public void setDiverter(Double pos) {
        diverter.setPosition(pos);
    }
}
