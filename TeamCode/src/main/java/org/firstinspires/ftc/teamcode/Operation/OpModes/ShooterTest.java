package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends ExplosiveTele {

    ShooterCommand shooter;
    ExplosivesRobot robot;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
        shooter = new ShooterCommand(robot.shooterSubsystem, this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {
        shooter.enable();
    }

    @Override
    public void bodyLoop() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void loop() {

    }
}
