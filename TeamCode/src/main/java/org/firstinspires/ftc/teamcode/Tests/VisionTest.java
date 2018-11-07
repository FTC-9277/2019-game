package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Vision.Sampler;

@Autonomous(name = "Vision Test")
public class VisionTest extends LinearOpMode {
    Sampler sampler;

    @Override
    public void runOpMode() throws InterruptedException {
        sampler = new Sampler(this);
        waitForStart();

        telemetry.addData("Seen", sampler.sample());
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < 10000 && opModeIsActive()){
            Thread.sleep(5);
            telemetry.addData("Seen", sampler.sample());
            telemetry.update();
        }
    }
}
