package org.firstinspires.ftc.teamcode.Operation;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroupPIDSource;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

public class ExplosivesRobot {
    DcMotor fLeft, bLeft, fRight, bRight;
    Servo left, right;
    ExplosivePIDEnabledHardware gyro;
    OpMode opMode;
    MotorGroup a,b;

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
        //gyro = ExplosiveBNO055.getInstance(opmode, "gyro");
        a = new MotorGroup(opmode,fLeft, bLeft);
        b = new MotorGroup(opmode, fRight, bRight);
        gyro = new MotorGroupPIDSource(b,a);

        drive = new DriveSubsystem(fLeft, fRight, bLeft, bRight, gyro, opmode);
        climb = new ClimbSubsystem(left, right, opmode);
    }
}
