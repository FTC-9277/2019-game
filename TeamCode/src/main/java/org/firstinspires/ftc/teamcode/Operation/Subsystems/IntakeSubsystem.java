package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class IntakeSubsystem extends Subsystem {
    DcMotor leftSlide, rightSlide;
    CRServo intake, leftIntake, rightIntake, indexer;
    OpMode opmode;

    public IntakeSubsystem(DcMotor leftSlide, DcMotor rightSlide, CRServo intake, CRServo leftIntake, CRServo rightIntake, CRServo indexer, OpMode opmode){
        super(opmode);
        this.opmode = opmode;
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

    public void setLeftSlide(Double pow) {
        leftSlide.setPower(pow);
    }

    public void setRightSlide(Double pow) {
        rightSlide.setPower(pow);
    }

    public void setSlider(Double pow) {
        leftSlide.setPower(-pow);
        rightSlide.setPower(pow);
    }

    public void setIntake(Double pow) {
        opmode.telemetry.addData("Power", intake.getPower());
        intake.setPower(pow);
    }

    public void drop() {
        leftIntake.setPower(-0.5);
        rightIntake.setPower(0.5);
    }

    public void pullUp() {
        rightIntake.setPower(-0.5);
        leftIntake.setPower(0.5);
    }

    public void stopIntake() {
        rightIntake.setPower(0.0);
        leftIntake.setPower(0.0);
    }

    public void indexerUp() {
        opmode.telemetry.addData("Pos", indexer.getPower());
        indexer.setPower(1);
    }

    public void indexerDown() {
        opmode.telemetry.addData("Pos", indexer.getPower());
        indexer.setPower(0);
    }

    public void setIndexer(Double pow) {
        indexer.setPower(pow);
        opmode.telemetry.addData("Pow", indexer.getPower());
    }

}
