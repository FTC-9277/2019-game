package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Vision.Sampler;

@Autonomous(name = "One Mineral Crater")
public class OneMineralCrater extends LinearOpMode{
    Sampler sample;
    DcMotor fLeft, fRight, bLeft, bRight;
    Servo marker;
    double currentLeft = 0, currentRight = 0;
    Servo left, right;
    double climb = 0.23, unhook = 0.975, redeploy = 0.3;
    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
        fLeft = hardwareMap.get(DcMotor.class, "fLeft");
        fRight = hardwareMap.get(DcMotor.class, "fRight");
        bLeft = hardwareMap.get(DcMotor.class, "bLeft");
        bRight = hardwareMap.get(DcMotor.class, "bRight");
        marker = hardwareMap.get(Servo.class, "marker");
        sample = new Sampler(this);
        left.setPosition(1-climb);
        right.setPosition(climb);

        waitForStart();

        int mineralPosition = sample.sample();

        left.setPosition(1-unhook);
        right.setPosition(unhook);

        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < 10000 && opModeIsActive()){
            Thread.sleep(5);
        }

        resetEncoders();
        if(mineralPosition == 3){
            drive(600);
            //marker.setPosition(0.5);
        } else if(mineralPosition == 1){
            drive(200);
            resetEncoders();
            turn(125);
            resetEncoders();
            drive(600);
            resetEncoders();
            /*turn(-250);
            resetEncoders();
            drive(400);
            marker.setPosition(0.5);*/
        } else if(mineralPosition == 2){
            drive(200);
            resetEncoders();
            turn(-125);
            resetEncoders();
            drive(600);
            resetEncoders();
            /*turn(250);
            resetEncoders();
            drive(400);
            marker.setPosition(0.5);*/
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
