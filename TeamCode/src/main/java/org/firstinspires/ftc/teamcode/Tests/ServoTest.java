package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp(name = "Servo Test")
public class ServoTest extends OpMode {
    Servo a,b;
    double aPos, bPos;

    @Override
    public void init() {
        a = hardwareMap.get(Servo.class, "a");
        b = hardwareMap.get(Servo.class, "b");
        aPos = 0;
        bPos = 0;
    }

    @Override
    public void loop() {
        a.setPosition(aPos);
        b.setPosition(bPos);
        if(aPos < 0.99 && gamepad1.left_stick_y > 0.1){
            aPos += gamepad1.left_stick_y * 0.01;
        } else if(aPos > 0.01 && gamepad1.left_stick_y < -0.1){
            aPos += gamepad1.left_stick_y * 0.01;
        }

        if(bPos < 0.99 && gamepad1.right_stick_y > 0.1){
            bPos += gamepad1.right_stick_y * 0.01;
        } else if(bPos > 0.01 && gamepad1.right_stick_y < -0.1){
            bPos += gamepad1.right_stick_y * 0.01;
        }

        telemetry.addData("A", aPos);
        telemetry.addData("B", bPos);
    }
}
