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
    CRServo leftIntake, rightIntake, intake, indexer;
    Servo leftDoor, rightDoor;

    @Override
    public void initHardware() {

        //Intake
        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        intake = hardwareMap.get(CRServo.class, "intake");
        leftIntake = hardwareMap.get(CRServo.class, "left");
        rightIntake = hardwareMap.get(CRServo.class, "right");
        leftDoor = hardwareMap.servo.get("doorLeft");
        rightDoor = hardwareMap.servo.get("doorRight");

        intakeSubsystem = new IntakeSubsystem(leftSlide, rightSlide, intake, leftIntake, rightIntake, leftDoor, rightDoor, this);

    }

    @Override
    public void firstLoop() {
    }

    @Override
    public void bodyLoop() {
        if(dController.right_bumper || mController.right_bumper) {
            intakeSubsystem.setIntake(1.0);
        }

        if(Math.abs(mController.left_stick_y) > 0.1) {
            intakeSubsystem.setLeftSlide((double) mController.left_stick_y);
        } else {
            intakeSubsystem.setLeftSlide(0.0);
        }

        if(Math.abs(mController.right_stick_y) > 0.1) {
            intakeSubsystem.setRightSlide((double) mController.right_stick_y);
        } else {
            intakeSubsystem.setRightSlide(0.0);
        }

        if(mController.dpad_up) {
            intakeSubsystem.pullUp();
        } else if(mController.dpad_down) {
            intakeSubsystem.drop();
        }



    }

    @Override
    public void exit() {

    }

}
