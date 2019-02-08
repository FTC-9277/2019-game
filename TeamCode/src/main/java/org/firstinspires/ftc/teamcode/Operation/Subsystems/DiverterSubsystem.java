package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class DiverterSubsystem {

    Servo diverter;

    public DiverterSubsystem(Servo diverter, OpMode opMode) {
        this.diverter = diverter;
    }

    public void setDiverter(Double pos) {
        diverter.setPosition(pos);
    }
}
