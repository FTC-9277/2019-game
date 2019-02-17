package org.firstinspires.ftc.teamcode.Operation.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Subsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DiverterSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;

public class ManipulateCommand extends Command {

    public IntakeSubsystem intake;
    public ClimbSubsystem climb;
    public ShooterSubsystem shooter;
    public DiverterSubsystem diverter;

    Gamepad dController, mController;

    public ManipulateCommand(IntakeSubsystem intake, ClimbSubsystem climb, ShooterSubsystem shooter, DiverterSubsystem diverter, ExplosiveTele opmode){
        super(opmode);
        this.intake = intake;
        this.diverter = diverter;
        this.climb = climb;
        this.shooter = shooter;
        this.dController = opmode.dController;
        this.mController = opmode.mController;
    }
    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

        if(dController.a) {
            climb.descend();
        } else if(dController.y) {
            climb.ascend();
        } else {
            climb.stopClimbing();
        }

        //Intake
        if (dController.left_trigger > 0.75) {
            intake.setSlider(1.0);
        } else if (dController.right_trigger > 0.75) {
            intake.setSlider(-0.75);
        } else {
            intake.setSlider(0.0);
        }

        intake.setIntake(mController.right_stick_y*0.9);

        if(mController.dpad_up){
            intake.pullUp();
        } else if(mController.dpad_down){
            intake.drop();
        } else {
            intake.stopIntake();
        }

        if(mController.left_bumper) {
            //Toggle the left door
            if(intake.isDoorOpen(IntakeSubsystem.DoorSide.left)) {
                intake.closeDoor(IntakeSubsystem.DoorSide.left);
            } else {
                intake.openDoor(IntakeSubsystem.DoorSide.left);
            }
        }

        if(mController.right_bumper) {
            //Toggle the right door
            if(intake.isDoorOpen(IntakeSubsystem.DoorSide.right)) {
                intake.closeDoor(IntakeSubsystem.DoorSide.right);
            } else {
                intake.openDoor(IntakeSubsystem.DoorSide.right);
            }
        }

        if(mController.b) {
            shooter.setShooter(1.0);
        } else {
            shooter.setShooter(0.0);
        }

        if(dController.dpad_up) {
            diverter.setDiverter(0.0);
        } else if (dController.dpad_down) { // starting position
            diverter.setDiverter(1.0);
        } else if(dController.dpad_left || dController.dpad_right) {
            diverter.setDiverter(0.35);
        }

    }

    @Override
    public void stop() {

    }
}
