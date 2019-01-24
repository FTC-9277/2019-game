package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class IntakeSubsystem extends Subsystem {
    DcMotor leftSlide, rightSlide;
    Servo indexer;
    CRServo intake, leftIntake, rightIntake;

    public IntakeSubsystem(DcMotor leftSlide, DcMotor rightSlide, CRServo intake, CRServo leftIntake, CRServo rightIntake, Servo indexer, OpMode opmode){
        super(opmode);
        this.indexer = indexer;
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
        leftSlide.setPower(-pow);
        rightSlide.setPower(pow);
    }

    public void setIntake(Double pow) {
        intake.setPower(pow);
    }

    public void drop() {
        leftIntake.setPower(0.5);
    }

    public void pullUp() {
        rightIntake.setPower(0.5);
    }

    public void indexerUp() {
        indexer.setPosition(1);
    }

    public void indexerDown() {
        indexer.setPosition(0);
    }

}
