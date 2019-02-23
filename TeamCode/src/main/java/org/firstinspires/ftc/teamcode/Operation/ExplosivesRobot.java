package org.firstinspires.ftc.teamcode.Operation;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.annotations.MotorType;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveBNO055;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosivePIDEnabledHardware;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroup;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.MotorGroupPIDSource;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DiverterSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Sensors.Gyro;

public class ExplosivesRobot {
    DcMotor fLeft, bLeft, fRight, bRight, leftSlide, rightSlide, shooter, climber;
    CRServo intake;
    Servo diverter, leftDoor, rightDoor, leftIntake, rightIntake;
    Gyro gyro;
    LinearOpMode opMode;
    MotorGroup a,b;

    public DriveSubsystem driveSubsystem;
    public ClimbSubsystem climbSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public DiverterSubsystem diverterSubsystem;

    public ExplosivesRobot(LinearOpMode opmode) {
        this.opMode = opmode;

        //Drive
        fLeft = opmode.hardwareMap.dcMotor.get("fLeft");
        bLeft = opmode.hardwareMap.dcMotor.get("bLeft");
        bRight = opmode.hardwareMap.dcMotor.get("bRight");
        fRight = opmode.hardwareMap.dcMotor.get("fRight");

        //Climber
        climber = opmode.hardwareMap.dcMotor.get("climber");
        climber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Intake
        leftSlide = opmode.hardwareMap.dcMotor.get("leftSlide");
        rightSlide = opmode.hardwareMap.dcMotor.get("rightSlide");
        intake = opmode.hardwareMap.crservo.get("intake");
        leftIntake = opmode.hardwareMap.servo.get("left");
        rightIntake = opmode.hardwareMap.servo.get("right");
        leftDoor = opmode.hardwareMap.servo.get("doorLeft");
        rightDoor = opmode.hardwareMap.servo.get("doorRight");

        //Shooter
        shooter = opmode.hardwareMap.dcMotor.get("shooter");

        //Diverter
        diverter = opmode.hardwareMap.servo.get("diverter");

        a = new MotorGroup(opmode,fLeft, bLeft);
        b = new MotorGroup(opmode, fRight, bRight);

        gyro = new Gyro(opmode);

        driveSubsystem = new DriveSubsystem(fLeft, fRight, bLeft, bRight, gyro, opmode);
        climbSubsystem = new ClimbSubsystem(climber, opmode);
        intakeSubsystem = new IntakeSubsystem(leftSlide, rightSlide, intake, leftIntake, rightIntake, leftDoor, rightDoor, opmode);
        shooterSubsystem = new ShooterSubsystem(shooter, opmode);
        diverterSubsystem = new DiverterSubsystem(diverter, opmode);

    }
}
