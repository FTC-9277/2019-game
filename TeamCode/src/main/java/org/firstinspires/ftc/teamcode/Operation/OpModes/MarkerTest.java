package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "marker")
public class MarkerTest extends OpMode {
    Servo marker;
    @Override
    public void init() {
        marker = hardwareMap.get(Servo.class, "marker");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            marker.setPosition(0.25);
        } else if(gamepad1.b){
            marker.setPosition(0.5);
        } else if(gamepad1.x){
            marker.setPosition(0.75);
        } else if(gamepad1.y){
            marker.setPosition(1);
        } else{
            marker.setPosition(0);
        }
    }
}
