package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp(name = "Tank")
public class TankDrive extends OpMode {
    DcMotor fLeft, fRight, bLeft, bRight;

    @Override
    public void init() {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
    }

    @Override
    public void loop() {
        /*if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > 0.1){
            fLeft.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x);
            bLeft.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x);
            fRight.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            bRight.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
        } else{
            fLeft.setPower(0);
            bLeft.setPower(0);
            fRight.setPower(0);
            bRight.setPower(0);
        }*/

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
    }
}
