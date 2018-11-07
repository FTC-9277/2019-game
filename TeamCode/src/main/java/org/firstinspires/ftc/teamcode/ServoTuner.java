package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Dash.PIDDashboard;
import org.firstinspires.ftc.teamcode.Dash.PIDTunable;

/**
 * Created by Varun on 2/26/2018.
 */

@TeleOp(name = "Remote Servo Tuner ")
public class ServoTuner extends OpMode {
    PIDDashboard dash;
    PIDTunable tunable;
    Servo left, right;

    @Override
    public void init() {
        dash = new PIDDashboard(this);
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
    }


    @Override
    public void loop() {
        if(dash.hasNew()){
            tunable = dash.get();
            left.setPosition(1-tunable.get(0));
            right.setPosition(tunable.get(1));
            telemetry.addData("kP", tunable.get(0));
            telemetry.addData("kI", tunable.get(1));
            telemetry.addData("kD", tunable.get(2));
        }
    }

    @Override
    public void stop() {
        dash.close();
    }
}
