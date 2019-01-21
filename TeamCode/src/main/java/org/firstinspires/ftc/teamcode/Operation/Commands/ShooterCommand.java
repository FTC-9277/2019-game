package org.firstinspires.ftc.teamcode.Operation.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {
    ShooterSubsystem shooter;
    Controller mController;

    public ShooterCommand(ShooterSubsystem subsystem, ExplosiveTele opmode){
        super(opmode,subsystem);
        this.shooter = subsystem;
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
        if(Math.abs(mController.ry()) > 0.1) {
            shooter.setShooter(mController.ry());
        } else {
            shooter.setShooter(0.0);
        }
    }

    @Override
    public void stop() {

    }
}
