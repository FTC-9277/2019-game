package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;

@Autonomous(name = "Climb Auto")
public class ClimbAuto extends ExplosiveAuto {

    ClimbSubsystem climb;

    ExplosivesRobot robot;

    @Override
    public void initHardware() {
        robot = new ExplosivesRobot(this);
        robot.climbSubsystem.setEncoderTicks();
    }

    @Override
    public void initAction() {
    }

    @Override
    public void body() throws InterruptedException {

        climb.descend(5000);

        wait(5000);

        climb.ascend(5000);

    }

    @Override
    public void exit() throws InterruptedException {

    }

    @Override
    public void runOpMode() throws InterruptedException {

    }

    @Override
    protected void climberMaintain() {
        robot.climbSubsystem.maintain();
    }

    private void wait(int millis) {
        long endTime = System.currentTimeMillis() + millis;
        while(System.currentTimeMillis() < endTime) {
            //Wait
        }
    }
}
