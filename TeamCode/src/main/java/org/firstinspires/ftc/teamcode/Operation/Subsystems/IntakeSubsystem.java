package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class IntakeSubsystem extends Subsystem {
    DcMotor leftSlide, rightSlide;
    Servo leftIntake, rightIntake;
    CRServo intake;

    public IntakeSubsystem(DcMotor leftSlide, DcMotor rightSlide, CRServo intake, Servo leftIntake, Servo rightIntake, OpMode opmode){
        super(opmode);
        this.intake = intake;
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
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

    public void setSlider(Double pow) {
        leftSlide.setPower(pow);
        rightSlide.setPower(-pow);
    }

    public void setIntake(Double pow) {
        intake.setPower(pow);
    }

    public void drop() {
        if (leftIntake.getPosition() < 0.95) {
            leftIntake.setPosition(leftIntake.getPosition() + 0.1);
        }

        if (rightIntake.getPosition() > -0.95) {
            rightIntake.setPosition(rightIntake.getPosition() - 0.1);
        }
    }

    public void pullUp() {
        if (rightIntake.getPosition() < 0.95) {
            rightIntake.setPosition(leftIntake.getPosition() + 0.1);
        }

        if (leftIntake.getPosition() > -0.95) {
            leftIntake.setPosition(rightIntake.getPosition() - 0.1);
        }
    }

}
