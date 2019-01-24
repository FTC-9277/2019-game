package org.firstinspires.ftc.teamcode.Operation;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroupPIDSource;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;

public class ExplosivesRobot {
    DcMotor fLeft, bLeft, fRight, bRight, leftSlide, rightSlide, shooter, climber;
    CRServo intake, leftIntake, rightIntake;
    Servo indexer;
    ExplosivePIDEnabledHardware gyro;
    OpMode opMode;
    MotorGroup a,b;

    public DriveSubsystem driveSubsystem;
    public ClimbSubsystem climbSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public ShooterSubsystem shooterSubsystem;

    public ExplosivesRobot(OpMode opmode) {
        this.opMode = opmode;

        //Drive
        fLeft = opmode.hardwareMap.get(DcMotor.class, "fLeft");
        bLeft = opmode.hardwareMap.get(DcMotor.class, "bLeft");
        bRight = opmode.hardwareMap.get(DcMotor.class, "bRight");
        fRight = opmode.hardwareMap.get(DcMotor.class, "fRight");

        //Climber
        climber = opmode.hardwareMap.get(DcMotor.class, "climber");

        //Intake
        leftSlide = opmode.hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = opmode.hardwareMap.get(DcMotor.class, "rightSlide");
        intake = opmode.hardwareMap.get(CRServo.class, "intake");
        leftIntake = opmode.hardwareMap.get(CRServo.class, "left");
        rightIntake = opmode.hardwareMap.get(CRServo.class, "right");
        indexer = opmode.hardwareMap.get(Servo.class, "indexer");

        //Shooter
        shooter = opmode.hardwareMap.get(DcMotor.class, "shooter");

        a = new MotorGroup(opmode,fLeft, bLeft);
        b = new MotorGroup(opmode, fRight, bRight);
        gyro = new MotorGroupPIDSource(b,a);

        driveSubsystem = new DriveSubsystem(fLeft, fRight, bLeft, bRight, gyro, opmode);
        climbSubsystem = new ClimbSubsystem(climber, opmode);
        intakeSubsystem = new IntakeSubsystem(leftSlide, rightSlide, intake, leftIntake, rightIntake, indexer, opmode);
        shooterSubsystem = new ShooterSubsystem(shooter, opmode);

    }
}
