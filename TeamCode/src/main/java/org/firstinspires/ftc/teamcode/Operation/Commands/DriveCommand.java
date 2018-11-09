package org.firstinspires.ftc.teamcode.Operation.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

public class DriveCommand extends Command {
    DriveSubsystem drive;
    Controller dController;

    public DriveCommand(DriveSubsystem subsystem, ExplosiveTele opmode){
        super(opmode, subsystem);
        this.drive = subsystem;
        this.dController = opmode.dController;
    }
    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        drive.tankDrive(-dController.ly(),-dController.ry());
    }

    @Override
    public void stop() {
        drive.tankDrive(0,0);
    }
}
