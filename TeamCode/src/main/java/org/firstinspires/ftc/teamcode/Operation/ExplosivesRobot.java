package org.firstinspires.ftc.teamcode.Operation;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

public class ExplosivesRobot {
    DcMotor fLeft, bLeft, fRight, bRight;
    Servo left, right;
    ExplosiveBNO055 gyro;
    OpMode opMode;

    public DriveSubsystem drive;
    public ClimbSubsystem climb;

    public ExplosivesRobot(OpMode opmode){
        this.opMode = opmode;
        fLeft = opmode.hardwareMap.get(DcMotor.class, "fLeft");
        bLeft = opmode.hardwareMap.get(DcMotor.class, "bLeft");
        bRight = opmode.hardwareMap.get(DcMotor.class, "bRight");
        fRight = opmode.hardwareMap.get(DcMotor.class, "fRight");
        left = opmode.hardwareMap.get(Servo.class, "left");
        right = opmode.hardwareMap.get(Servo.class, "right");
        gyro = ExplosiveBNO055.getInstance(opmode, "gyro");

        drive = new DriveSubsystem(fLeft, fRight, bLeft, bRight, gyro, opmode);
        climb = new ClimbSubsystem(left, right, opmode);
    }
}
