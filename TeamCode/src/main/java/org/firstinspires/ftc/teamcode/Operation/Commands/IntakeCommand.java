package org.firstinspires.ftc.teamcode.Operation.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    IntakeSubsystem intake;
    Controller mController;

    public IntakeCommand(IntakeSubsystem subsystem, ExplosiveTele opmode) {
        super(opmode,subsystem);
        this.intake = subsystem;
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
        if (mController.leftTrigger() == 1) {
            intake.setSlider(-1.0);
        } else if (mController.rightTrigger() == 1) {
            intake.setSlider(1.0);
        } else {
            intake.setSlider(0.0);
        }

        if(Math.abs(mController.ly()) > 0.1) {
            intake.setIntake(mController.ly());
        } else {
            intake.setIntake(0.0);
        }

        if(mController.dpadUp()){
            intake.drop();
        } else if(mController.dpadDown()){
            intake.pullUp();
        }
    }

    @Override
    public void stop() {

    }
}
