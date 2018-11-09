package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Tank Drive")
public class TankDrive extends OpMode {
    DcMotor fLeft, fRight, bLeft, bRight;
    Servo left, right;
    double climb = 0.5, rest = 0.3, deploy = 0.85, hook = 0.7;

    @Override
    public void init() {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
    }

    @Override
    public void loop() {

        if(Math.abs(gamepad1.right_stick_y) > 0.1){
            fRight.setPower(gamepad1.right_stick_y);
            bRight.setPower(gamepad1.right_stick_y);
        } else{
            fRight.setPower(0);
            bRight.setPower(0);
        }

        if(Math.abs(gamepad1.left_stick_y) > 0.1){
            fLeft.setPower(-gamepad1.left_stick_y);
            bLeft.setPower(-gamepad1.left_stick_y);
        } else{
            fLeft.setPower(0);
            bLeft.setPower(0);
        }
        telemetry.addData("Front Left", fLeft.getCurrentPosition());
        telemetry.addData("Front Right", fRight.getCurrentPosition());
        telemetry.addData("Back Right", bRight.getCurrentPosition());
        telemetry.addData("Back Left", bLeft.getCurrentPosition());
        if(gamepad1.a){
            fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else{
            fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if(gamepad2.a) {
            left.setPosition(1-rest);
            right.setPosition(rest);
        } else if(gamepad2.b){
            left.setPosition(1-climb);
            right.setPosition(climb);
        } else if(gamepad2.x){
            left.setPosition(1-deploy);
            right.setPosition(deploy);
        } else if(gamepad2.y){
            left.setPosition(1-hook);
            right.setPosition(hook);
        }
    }
}


