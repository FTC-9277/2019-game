package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;

@Autonomous(name = "Turn Auto")
public class TurnAuto extends ExplosiveAuto {
    ExplosivesRobot robot;
    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() throws InterruptedException {
        robot.drive.PIDTurnToAngle(robot.drive.getYaw() + 45, 5000);
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
