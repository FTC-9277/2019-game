package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Full Intake")
public class FullIntake extends OpMode {
    DcMotor leftSlide,rightSlide;
    Servo left, right;
    CRServo in;
    @Override
    public void init() {
        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        left = hardwareMap.get(Servo.class, "leftIntake");
        right = hardwareMap.get(Servo.class, "rightIntake");
        in = hardwareMap.get(CRServo.class, "intake");
    }

    @Override
    public void loop() {
        if(gamepad1.left_trigger > 0.1){
            leftSlide.setPower(gamepad1.left_trigger);
            rightSlide.setPower(gamepad1.left_trigger);
        } else if(gamepad1.right_trigger > 0.1){
            leftSlide.setPower(-gamepad1.right_trigger);
            rightSlide.setPower(-gamepad1.right_trigger);
        } else{
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }

        if(gamepad1.a){
            left.setPosition(1);
            right.setPosition(0);
        } else if(gamepad1.b){
            left.setPosition(0.75);
            right.setPosition(0.25);
        } else if(gamepad1.x){
            left.setPosition(0.25);
            right.setPosition(0.75);
        } else if(gamepad1.y){
            left.setPosition(0);
            right.setPosition(1);
        } else{
            left.setPosition(0.5);
            right.setPosition(0.5);
        }

        if(gamepad1.left_bumper){
            in.setPower(-0.9);
        } else{
            in.setPower(0);
        }
    }
}
