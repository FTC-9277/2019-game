package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.ManipulateCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;

@TeleOp(name = "Intake Test")
public class IntakeTest extends ExplosiveTele {

    IntakeSubsystem intakeSubsystem;
    DcMotor leftSlide, rightSlide;
    CRServo leftIntake, rightIntake, intake;
    Servo indexer;

    @Override
    public void initHardware() {

        //Intake
        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        intake = hardwareMap.get(CRServo.class, "intake");
        leftIntake = hardwareMap.get(CRServo.class, "left");
        rightIntake = hardwareMap.get(CRServo.class, "right");
        indexer = hardwareMap.get(Servo.class, "indexer");

        intakeSubsystem = new IntakeSubsystem(leftSlide, rightSlide, intake, leftIntake, rightIntake, indexer, this);

    }

    @Override
    public void initAction() {
    }

    @Override
    public void firstLoop() {
        intakeSubsystem.enable();
    }

    boolean dpadUpLocked = false;
    boolean dpadDownLocked = false;

    @Override
    public void bodyLoop() {
        if(dController.rightBumper() || mController.rightBumper()) {
            intakeSubsystem.setIntake(1.0);
        }

        if(Math.abs(mController.ly()) > 0.1) {
            intakeSubsystem.setLeftSlide(mController.ly());
        } else {
            intakeSubsystem.setLeftSlide(0.0);
        }

        if(Math.abs(mController.ry()) > 0.1) {
            intakeSubsystem.setRightSlide(mController.ry());
        } else {
            intakeSubsystem.setRightSlide(0.0);
        }

        if(mController.dpadUp()) {
            intakeSubsystem.pullUp();
        } else if(mController.dpadDown()) {
            intakeSubsystem.drop();
        }

        if(mController.leftTrigger() > 0.75) {
            intakeSubsystem.indexerUp();
        } else if (mController.rightTrigger() > 0.75) {
            intakeSubsystem.indexerDown();
        }

    }

    @Override
    public void exit() {

    }

    @Override
    public void loop() {

    }
}
