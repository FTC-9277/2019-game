package org.firstinspires.ftc.teamcode.Operation.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;

public class IntakeSubsystem {
    DcMotor leftSlide, rightSlide;
    CRServo intake;//, leftIntake, rightIntake;
    public Servo leftDoor, rightDoor, leftIntake, rightIntake;
    OpMode opmode;

    final double LEFT_DOOR_OPEN_POS = 0.3;
    final double LEFT_DOOR_CLOSE_POS = 0.8;
    final double RIGHT_DOOR_OPEN_POS = 0.3;
    final double RIGHT_DOOR_CLOSE_POS = 0.0;

    public IntakeSubsystem(DcMotor leftSlide, DcMotor rightSlide, CRServo intake, Servo leftIntake, Servo rightIntake, Servo leftDoor, Servo rightDoor, OpMode opmode){
        this.opmode = opmode;
        this.intake = intake;
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftDoor = leftDoor;
        this.rightDoor = rightDoor;
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
        intake.setPower(pow);
    }

    public void drop() {
        leftIntake.setPosition(leftIntake.getPosition()+0.025);
        rightIntake.setPosition(rightIntake.getPosition()-0.025);
    }

    public void pullUp() {
        leftIntake.setPosition(leftIntake.getPosition()-0.025);
        rightIntake.setPosition(rightIntake.getPosition()+0.025);
    }

    public void openDoor(DoorSide side) {
        if(side == DoorSide.left) {
            leftDoor.setPosition(LEFT_DOOR_OPEN_POS);
        } else if (side == DoorSide.right) {
            rightDoor.setPosition(RIGHT_DOOR_OPEN_POS);
        } else {
            leftDoor.setPosition(LEFT_DOOR_OPEN_POS);
            rightDoor.setPosition(RIGHT_DOOR_OPEN_POS);
        }
    }

    public void closeDoor(DoorSide side) {
        if(side == DoorSide.left) {
            leftDoor.setPosition(LEFT_DOOR_CLOSE_POS);
        } else if (side == DoorSide.right) {
            rightDoor.setPosition(RIGHT_DOOR_CLOSE_POS);
        } else {
            leftDoor.setPosition(LEFT_DOOR_CLOSE_POS);
            rightDoor.setPosition(RIGHT_DOOR_CLOSE_POS);
        }
    }

    public enum DoorSide {
        left, right, both
    }

    public boolean isDoorOpen(DoorSide side) {
        if(side == DoorSide.left) {
            return (leftDoor.getPosition() == LEFT_DOOR_OPEN_POS);
        } else if(side == DoorSide.right) {
            return (rightDoor.getPosition() == RIGHT_DOOR_OPEN_POS);
        } else {
            return ((leftDoor.getPosition() == LEFT_DOOR_OPEN_POS) && (rightDoor.getPosition() == RIGHT_DOOR_OPEN_POS));
        }
    }

}
