package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp(name = "Pod")
public class TestDrivePod extends OpMode {
    DcMotor a,b;
    double speed;

    @Override
    public void init() {
        a = hardwareMap.get(DcMotor.class, "a");
        b = hardwareMap.get(DcMotor.class, "b");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1){
            speed = gamepad1.left_stick_y;
        } else {
            speed = 0;
        }

        a.setPower(speed);
        b.setPower(speed);
        if(gamepad1.a){
            a.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            b.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else{
            a.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("A", a.getCurrentPosition());
        telemetry.addData("B", b.getCurrentPosition());
    }
}
