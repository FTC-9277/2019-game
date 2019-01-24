package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.ManipulateCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends ExplosiveTele {

    ManipulateCommand manipulate;
    ExplosivesRobot robot;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
        manipulate = new ManipulateCommand(robot.intakeSubsystem, robot.climbSubsystem, robot.shooterSubsystem, this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {
         manipulate.enable();
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
