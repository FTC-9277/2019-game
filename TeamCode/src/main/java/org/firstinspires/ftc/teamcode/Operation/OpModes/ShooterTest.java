package org.firstinspires.ftc.teamcode.Operation.OpModes;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends OpMode {
    DcMotor shooter;
    @Override
    public void init() {
        shooter = hardwareMap.dcMotor.get("shooter");
    }

    boolean shooterLocked = false;
    double power = 0.5;

    @Override
    public void loop() {
//
//        if (Math.abs(gamepad1.left_stick_y) < 0.1) {
//
//            if (gamepad1.b == true) {
//                if (shooterLocked == false) { //If this is the first loop with the button pressed
//                    shooterLocked = true;
//                    if (shooter.getPower() > -0.5) { //If the shooter is already stopped
//                        //Start the shooter
//                        shooter.setPower(-power);
//                    } else {
//                        //Stop the shooter
//                        shooter.setPower(0.0);
//                    }
//                }
//            } else {
//                //The button is not being pressed, unlock it
//                shooterLocked = false;
//            }
//
//            if (gamepad1.y) {
//                power = 1.0;
//            }
//
//            if (gamepad1.x) {
//                power = 0.75;
//            }
//
//            if (gamepad1.a) {
//                power = 0.5;
//            }
//
//        } else {
            shooter.setPower(gamepad1.left_stick_y);
//        }

    }
}
