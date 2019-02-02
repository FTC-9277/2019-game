package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Operation.Commands.ManipulateCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

@TeleOp(name = "FullTele")
public class FullTele extends ExplosiveTele {
    ExplosivesRobot robot;
    DriveCommand driveCommand;
    ManipulateCommand manipulateCommand;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
        driveCommand = new DriveCommand(robot.driveSubsystem, this);
        manipulateCommand = new ManipulateCommand(robot.intakeSubsystem, robot.climbSubsystem, robot.shooterSubsystem, robot.diverterSubsystem, this);
    }

    @Override
    public void initAction() {
        dController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);
        mController.setJoystickDeadzone(Controller.DeadzoneType.CIRCULAR, 0.1);
        manipulateCommand.setDiverter(0.5);
    }

    @Override
    public void firstLoop() {
        driveCommand.enable();
        manipulateCommand.enable();
    }

    @Override
    public void bodyLoop() {
        telemetry.addData("Yaw", robot.driveSubsystem.getOutput());
    }

    @Override
    public void exit() {

    }
}
