package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@Autonomous(name = "Turnback")
public class Turnback extends LinearOpMode {
    DcMotor fLeft, fRight, bLeft, bRight;

    @Override
    public void runOpMode() throws InterruptedException {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");

        resetEncoders();

        while(true) {
            fLeft.setPower(-0.5);
            fRight.setPower(-0.5);
            bLeft.setPower(-0.5);
            bRight.setPower(-0.5);

            telemetry.addData("Front Left", fLeft.getCurrentPosition());
            telemetry.addData("Front Right", fRight.getCurrentPosition());
            telemetry.addData("Back Right", bRight.getCurrentPosition());
            telemetry.addData("Back Left", bLeft.getCurrentPosition());
            telemetry.update();
        }
    }

    public void resetEncoders() {
        fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
