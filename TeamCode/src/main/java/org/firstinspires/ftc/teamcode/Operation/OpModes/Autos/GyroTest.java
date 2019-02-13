package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Sensors.Gyro;

@Autonomous(name = "Gyro Test")
public class GyroTest extends LinearOpMode {

    ExplosivesRobot robot;

    Orientation last = new Orientation();

    double globalangle;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);

        waitForStart();

        robot.driveSubsystem.turn(-90);

        wait(4000);

        robot.driveSubsystem.turn(90);

    }

    public void wait(int millis) {
        long t = System.currentTimeMillis() + millis;
        while(System.currentTimeMillis() < t) {
            //Wait
        }
    }

}
