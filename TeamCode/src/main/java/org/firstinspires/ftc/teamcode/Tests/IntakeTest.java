package org.firstinspires.ftc.teamcode.Tests;

import android.app.ApplicationErrorReport;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

//@TeleOp(name = "Intake Test")
public class IntakeTest extends OpMode {
    CRServo front, back;

    @Override
    public void init() {
        front = hardwareMap.get(CRServo.class, "front");
        //back = hardwareMap.get(CRServo.class, "back");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1){
            front.setPower(gamepad1.left_stick_y);
            //back.setPower(-gamepad1.left_stick_y);
        } else{
            front.setPower(0);
            //back.setPower(0);
        }
    }
}
