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
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DiverterSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;

public class ExplosivesRobot {
    DcMotor fLeft, bLeft, fRight, bRight, leftSlide, rightSlide, shooter, climber;
    CRServo intake, leftIntake, rightIntake, indexer;
    Servo diverter;
    ExplosivePIDEnabledHardware gyro;
    OpMode opMode;
    MotorGroup a,b;

    public DriveSubsystem driveSubsystem;
    public ClimbSubsystem climbSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public DiverterSubsystem diverterSubsystem;

    public ExplosivesRobot(OpMode opmode) {
        this.opMode = opmode;

        //Drive
        fLeft = opmode.hardwareMap.dcMotor.get("fLeft");
        bLeft = opmode.hardwareMap.dcMotor.get("bLeft");
        bRight = opmode.hardwareMap.dcMotor.get("bRight");
        fRight = opmode.hardwareMap.dcMotor.get("fRight");

        //Climber
        climber = opmode.hardwareMap.dcMotor.get("climber");

        //Intake
        leftSlide = opmode.hardwareMap.dcMotor.get("leftSlide");
        rightSlide = opmode.hardwareMap.dcMotor.get("rightSlide");
        intake = opmode.hardwareMap.crservo.get("intake");
        leftIntake = opmode.hardwareMap.crservo.get("left");
        rightIntake = opmode.hardwareMap.crservo.get("right");
        indexer = opmode.hardwareMap.crservo.get("indexer");

        //Shooter
        shooter = opmode.hardwareMap.dcMotor.get("shooter");

        //Diverter
        diverter = opmode.hardwareMap.servo.get("diverter");

        a = new MotorGroup(opmode,fLeft, bLeft);
        b = new MotorGroup(opmode, fRight, bRight);
        gyro = new MotorGroupPIDSource(b,a);

        driveSubsystem = new DriveSubsystem(fLeft, fRight, bLeft, bRight, gyro, opmode);
        climbSubsystem = new ClimbSubsystem(climber, opmode);
        intakeSubsystem = new IntakeSubsystem(leftSlide, rightSlide, intake, leftIntake, rightIntake, indexer, opmode);
        shooterSubsystem = new ShooterSubsystem(shooter, opmode);
        diverterSubsystem = new DiverterSubsystem(diverter, opmode);

    }
}
