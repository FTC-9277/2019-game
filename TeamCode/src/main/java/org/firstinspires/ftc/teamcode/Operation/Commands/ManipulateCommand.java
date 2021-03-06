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
        intake.closeDoor(IntakeSubsystem.DoorSide.both);
    }

    @Override
    public void start() {
    }

    public boolean leftLocked = false;
    public boolean rightLocked = false;

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

//
//        if (locked == false) {
        if (mController.dpad_up) {
//                locked = true;
            intake.pullUp();
        } else if (mController.dpad_down) {
//                locked = true;
            intake.drop();
        }
//        } else if (!mController.dpad_up && !mController.dpad_down) {
//            locked = false;
//        }

        if(mController.y) {
            intake.leftIntake.setPosition(-1);
            intake.rightIntake.setPosition(1);
        } else if (mController.a) {
            intake.leftIntake.setPosition(0);
            intake.rightIntake.setPosition(0);
        }

        if(mController.left_bumper) {
            if (leftLocked == false) {
                leftLocked = true;
                //Toggle the left door
                if (intake.isDoorOpen(IntakeSubsystem.DoorSide.left)) {
                    intake.closeDoor(IntakeSubsystem.DoorSide.left);
                } else {
                    intake.openDoor(IntakeSubsystem.DoorSide.left);
                }
            }
        } else {
            leftLocked = false;
        }

        if(mController.right_bumper) {
            if (rightLocked == false) {
                rightLocked = true;
                //Toggle the right door
                if (intake.isDoorOpen(IntakeSubsystem.DoorSide.right)) {
                    intake.closeDoor(IntakeSubsystem.DoorSide.right);
                } else {
                    intake.openDoor(IntakeSubsystem.DoorSide.right);
                }
            }
        } else {
            rightLocked = false;
        }

        if(mController.b) {
            shooter.setShooter(1.0);
        } else {
            shooter.setShooter(0.0);
        }

        if(dController.dpad_up) {
            diverter.setDiverter(-0.1);
        } else if (dController.dpad_down) { // starting position
            diverter.setDiverter(0.5);
        } else if(dController.dpad_left || dController.dpad_right) {
            diverter.setDiverter(-0.5);
        }

    }

    @Override
    public void stop() {

    }
}
