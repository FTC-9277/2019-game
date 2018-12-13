package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.ClimberCommand;
import org.firstinspires.ftc.teamcode.Operation.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

@TeleOp(name = "FullTele")
public class FullTele extends ExplosiveTele {
    ExplosivesRobot robot;
    DriveCommand driveCommand;
    ClimberCommand climberCommand;
    DcMotor leftSlide,rightSlide;
    Servo left, right;
    CRServo in;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
        driveCommand = new DriveCommand(robot.drive, this);
        climberCommand = new ClimberCommand(robot.climb, this);
        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        left = hardwareMap.get(Servo.class, "leftIntake");
        right = hardwareMap.get(Servo.class, "rightIntake");
        in = hardwareMap.get(CRServo.class, "intake");
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);
    }

    @Override
    public void firstLoop() {
        driveCommand.enable();
        climberCommand.enable();
    }

    @Override
    public void bodyLoop() {
        if(Math.abs(gamepad2.left_stick_y) > 0.1){
            leftSlide.setPower(gamepad2.left_stick_y);
            rightSlide.setPower(-gamepad2.left_stick_y);
        } else{
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }

        if(gamepad2.dpad_up){
            left.setPosition(1);
            right.setPosition(0);
        } else if(gamepad2.dpad_left){
            left.setPosition(0.75);
            right.setPosition(0.25);
        } else if(gamepad2.dpad_down){
            left.setPosition(0.25);
            right.setPosition(0.75);
        } else if(gamepad2.dpad_right){
            left.setPosition(0);
            right.setPosition(1);
        } else{
            left.setPosition(0.5);
            right.setPosition(0.5);
        }

        if(gamepad2.left_bumper){
            in.setPower(-0.9);
        } else if(gamepad2.right_bumper){
            in.setPower(0.8);
        } else{
            in.setPower(0);
        }
        telemetry.addData("Yaw", robot.drive.getOutput());
    }

    @Override
    public void exit() {

    }
}
