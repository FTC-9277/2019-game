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
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;

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

        manipulateCommand.diverter.setDiverter(0.5);
        manipulateCommand.intake.closeDoor(IntakeSubsystem.DoorSide.both);
    }

    @Override
    public void firstLoop() {
        driveCommand.enable();
        manipulateCommand.enable();
    }

    @Override
    public void bodyLoop() {
        telemetry.addData("Heading", robot.driveSubsystem.heading());
        telemetry.addData("Roll", robot.driveSubsystem.roll());
        telemetry.addData("Pitch", robot.driveSubsystem.pitch());
        telemetry.addData("Right Encoder", robot.driveSubsystem.rightEncoder());
        telemetry.addData("Left Encoder", robot.driveSubsystem.leftEncoder());
        telemetry.addData("Climber Encoder", robot.climbSubsystem.getEncoderTicks());

        telemetry.update();
    }

    @Override
    public void exit() {

    }

}
