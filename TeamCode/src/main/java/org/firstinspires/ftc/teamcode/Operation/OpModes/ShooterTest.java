package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends OpMode {
    DcMotor shooter;
    @Override
    public void init() {
        shooter = hardwareMap.get(DcMotor.class, "shooter");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1){
            shooter.setPower(gamepad1.left_stick_y);
        } else if(gamepad1.a){
            shooter.setPower(0.2);
        } else if(gamepad1.b){
            shooter.setPower(0.4);
        } else if(gamepad1.x){
            shooter.setPower(0.6);
        } else if(gamepad1.y){
            shooter.setPower(0.8);
        } else{
            shooter.setPower(0);
        }
    }
}
