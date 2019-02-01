package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "IntakeServoTest")
public class IntakeServoTest extends OpMode {

    CRServo intake;

    @Override
    public void init() {
        intake = hardwareMap.crservo.get("intake");
    }

    @Override
    public void loop() {

        intake.setPower(gamepad1.left_stick_y);
        /*
        if(gamepad1.a) {
            intake.setPower(1.0);
        } else {
            intake.setPower(0.0);
        }*/
        telemetry.addData(">", gamepad1.left_stick_y);
        telemetry.addData(">", intake.getPower());
        telemetry.addData(">",intake.getPortNumber());
        telemetry.update();
    }
}
