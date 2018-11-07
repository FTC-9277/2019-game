package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Vision.Camera;
import org.firstinspires.ftc.teamcode.Vision.FloodFill;

//@Autonomous(name = "Vision Auto")
public class VisionAuto extends LinearOpMode {
    DcMotor fLeft, fRight, bLeft, bRight;
    Servo marker;
    double currentLeft = 0, currentRight = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        marker = hardwareMap.get(Servo.class, "marker");
        waitForStart();

        marker.setPosition(0.5);
        Camera camera = new Camera(hardwareMap,false);
        FloodFill floodFill = new FloodFill(this, camera, false);
        camera.cycle();
        String output = floodFill.sample(camera.bitmap);
        floodFill.save_flood();

        resetEncoders();

        try{
            Thread.sleep(500);
        } catch(Exception e){
            //do nothing
        }

        telemetry.addData("Output", output);
        telemetry.update();
        if(output.equals("MIDDLE") || output.equals("UNKNOWN")){
            drive(1200);
            marker.setPosition(1);
        } else if(output.equals("LEFT")){
            turn(65);
            resetEncoders();
            drive(800);
            resetEncoders();
            turn(-120);
            resetEncoders();
            drive(400);
            marker.setPosition(1);
        } else if(output.equals("RIGHT")){
            turn(-65);
            resetEncoders();
            drive(800);
            resetEncoders();
            turn(120);
            resetEncoders();
            drive(400);
            marker.setPosition(1);
        }
    }

    public void resetEncoders() {
        fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentLeft = 0;
        currentRight = 0;

        fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void drive(int clicks) {
        while ((currentLeft < clicks || currentRight > -clicks) && opModeIsActive()) {
            currentLeft = (fLeft.getCurrentPosition() + bLeft.getCurrentPosition()) / 2;
            currentRight = (fRight.getCurrentPosition() + bRight.getCurrentPosition()) / 2;
            telemetry.addData("Right", currentRight);
            telemetry.addData("fRight", fRight.getCurrentPosition());
            telemetry.addData("bRIght", bRight.getCurrentPosition());
            telemetry.update();

            if (currentLeft < clicks) {
                bLeft.setPower(0.5);
                fLeft.setPower(0.5);
            } else {
                bLeft.setPower(0);
                fLeft.setPower(0);
            }

            if (currentRight > -clicks) {
                bRight.setPower(-0.5);
                fRight.setPower(-0.5);
            } else {
                bRight.setPower(0);
                fRight.setPower(0);
            }
        }
    }

    public void turn(int clicks) {
        if (clicks > 0) {
            while ((currentLeft < clicks || currentRight < clicks) && opModeIsActive()) {
                currentLeft = (fLeft.getCurrentPosition() + bLeft.getCurrentPosition()) / 2;
                currentRight = (fRight.getCurrentPosition() + bRight.getCurrentPosition()) / 2;
                telemetry.addData("Right", currentRight);
                telemetry.addData("fRight", fRight.getCurrentPosition());
                telemetry.addData("bRIght", bRight.getCurrentPosition());
                telemetry.update();

                if (currentLeft < clicks) {
                    bLeft.setPower(0.5);
                    fLeft.setPower(0.5);
                } else {
                    bLeft.setPower(0);
                    fLeft.setPower(0);
                }

                if (currentRight < clicks) {
                    bRight.setPower(0.5);
                    fRight.setPower(0.5);
                } else {
                    bRight.setPower(0);
                    fRight.setPower(0);
                }
            }
        } else {
            while ((currentLeft > clicks || currentRight > clicks) && opModeIsActive()) {
                currentLeft = (fLeft.getCurrentPosition() + bLeft.getCurrentPosition()) / 2;
                currentRight = (fRight.getCurrentPosition() + bRight.getCurrentPosition()) / 2;
                telemetry.addData("Right", currentRight);
                telemetry.addData("fRight", fRight.getCurrentPosition());
                telemetry.addData("bRIght", bRight.getCurrentPosition());
                telemetry.update();

                if (currentLeft > clicks) {
                    bLeft.setPower(-0.5);
                    fLeft.setPower(-0.5);
                } else {
                    bLeft.setPower(0);
                    fLeft.setPower(0);
                }

                if (currentRight > clicks) {
                    bRight.setPower(-0.5);
                    fRight.setPower(-0.5);
                } else {
                    bRight.setPower(0);
                    fRight.setPower(0);
                }
            }
        }
    }
}
