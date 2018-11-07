package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@Autonomous(name = "Drive")
public class AutoDrive extends LinearOpMode {
    DcMotor fLeft, fRight, bLeft, bRight;

    @Override
    public void runOpMode() throws InterruptedException {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");

        waitForStart();

        fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        double currentLeft = 0, currentRight = 0;

        fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(currentLeft < 600 || currentRight > -600){
            currentLeft = (fLeft.getCurrentPosition() + bLeft.getCurrentPosition())/2;
            currentRight = (fRight.getCurrentPosition() + bRight.getCurrentPosition())/2;
            telemetry.addData("Right", currentRight);
            telemetry.addData("fRight", fRight.getCurrentPosition());
            telemetry.addData("bRIght", bRight.getCurrentPosition());
            telemetry.update();

            if(currentLeft < 600){
                bLeft.setPower(0.5);
                fLeft.setPower(0.5);
            } else{
                bLeft.setPower(0);
                fLeft.setPower(0);
            }

            if(currentRight > -600){
                bRight.setPower(-0.5);
                fRight.setPower(-0.5);
            } else{
                bRight.setPower(0);
                fRight.setPower(0);
            }
        }

    }
}
