package org.firstinspires.ftc.teamcode.Dash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Climb Auto")
public class ClimbAuto extends LinearOpMode {
    Servo left, right;
    double climb = 0.23, unhook = 0.975, redeploy = 0.3;
    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
        left.setPosition(1-climb);
        right.setPosition(climb);

        waitForStart();

        left.setPosition(1-unhook);
        right.setPosition(unhook);

        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < 10000 && opModeIsActive()){
            Thread.sleep(5);
        }
    }
}
