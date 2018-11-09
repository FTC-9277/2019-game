package org.firstinspires.ftc.teamcode.Operation.Commands;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Command;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Controller;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;

public class ClimberCommand extends Command {
    ClimbSubsystem climb;
    Controller mController;

    public ClimberCommand(ClimbSubsystem subsystem, ExplosiveTele opmode){
        super(opmode,subsystem);
        this.climb = subsystem;
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
        if(mController.a()){
            climb.set(climb.REST);
        } else if(mController.b()){
            climb.set(climb.TELECLIMB);
        } else if(mController.x()){
            climb.set(climb.HOOK);
        } else if(mController.y()){
            climb.set(climb.LEVEL);
        }
    }

    @Override
    public void stop() {

    }
}
