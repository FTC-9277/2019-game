package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Intake Test")
public class IntakeTest extends OpMode {
    CRServo a, b;
    @Override
    public void init() {
        a = hardwareMap.get(CRServo.class, "a");
        b = hardwareMap.get(CRServo.class, "b");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            a.setPower(0.95);
            b.setPower(-0.95);
        } else if(gamepad1.b){
            a.setPower(-0.95);
            b.setPower(0.95);
        } else{
            a.setPower(0);
            b.setPower(0);
        }
    }
}
