package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "SingleMotorTest")
public class SingleMotorTest extends OpMode {

    DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_up) {
            motor.setPower(1);
        } else if(gamepad1.dpad_down) {
            motor.setPower(-1);
        }

        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            motor.setPower(gamepad1.left_stick_y);
        }


    }
}
