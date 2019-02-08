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

    IntakeSubsystem intake;
    ClimbSubsystem climb;
    ShooterSubsystem shooter;
    DiverterSubsystem diverter;

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

        //Climber
//        if(dController.b()) {
//            if(maintainLocked == false) {
//                maintainLocked = true;
//                if(isMaintaning) {
//                    isMaintaning = false;
//                } else {
//                    isMaintaning = true;
//                }
//            }
//            climb.maintain();
//        } else {
//            maintainLocked = false;
//        }
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

//        if(mController.rightBumper()) {
//            intake.setIntake(0.9);
//        }

        /*
        if(dController.rightBumper() || mController.rightBumper()) {
            intake.setIntake(0.9);
        } else {
            intake.setIntake(0.0);
        }*/

        if(mController.dpad_up){
            intake.pullUp();
        } else if(mController.dpad_down){
            intake.drop();
        } else {
            intake.stopIntake();
        }

        if(Math.abs(mController.left_stick_y) > 0.1) {
            intake.setIndexer(mController.left_stick_y/2.5);
        } else {
            intake.setIndexer(0.0);
        }

        //Shooter
//        if(mController.b()) {
//            if (shooterLocked == false) { //If this is the first loop with the button pressed
//                shooterLocked = true;
//                if(shooter.getShooterPower() < -0.5) { //If the shooter is already stopped
//                    //Start the shooter
//                    shooter.setShooter(-1.0);
//                } else {
//                    //Stop the shooter
//                    shooter.setShooter(0.0);
//                }
//            }
//        } else {
//            //The button is not being pressed, unlock it
//            shooterLocked = false;
//        }

        if(mController.b) {
            shooter.setShooter(1.0);
        } else {
            shooter.setShooter(0.0);
        }

        if(dController.dpad_up) {
            diverter.setDiverter(0.0);
        } else if (dController.dpad_down) {
            diverter.setDiverter(1.0);
        } else if(dController.dpad_left || dController.dpad_right) {
            diverter.setDiverter(0.35);
        }

    }

    public void setDiverter(Double pos) {
        diverter.setDiverter(pos);
    }

    @Override
    public void stop() {

    }
}
