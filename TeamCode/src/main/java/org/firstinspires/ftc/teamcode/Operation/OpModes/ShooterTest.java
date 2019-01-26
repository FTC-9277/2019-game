package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveTele;
import org.firstinspires.ftc.teamcode.Operation.Commands.ManipulateCommand;
import org.firstinspires.ftc.teamcode.Operation.ExplosivesRobot;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Operation.Subsystems.ShooterSubsystem;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends OpMode {

    ShooterSubsystem shooterSubsystem;
    DcMotor shooter;

//    @Override
    public void initHardware() {

        shooter = hardwareMap.get(DcMotor.class, "shooter");

//        shooterSubsystem = new ShooterSubsystem(shooter, this);
    }

//    @Override
    public void initAction() {
    }

//    @Override
    public void firstLoop() {
        shooterSubsystem.enable();
    }

    boolean shooterLocked = false;

//    @Override
    public void bodyLoop() {
        //Shooter
//        if(mController.b()) {
//            if (shooterLocked == false) { //If this is the first loop with the button pressed
//                shooterLocked = true;
//                if(shooterSubsystem.getShooterPower() < 0.5) { //If the shooter is already stopped
//                    //Start the shooter
//                    shooterSubsystem.setShooter(1.0);
//                } else {
//                    //Stop the shooter
//                    shooterSubsystem.setShooter(0.0);
//                }
//            }
//        } else {
//            //The button is not being pressed, unlock it
//            shooterLocked = false;
//        }
//
//        if(Math.abs(mController.ly()) > 0.1) {
//            shooterSubsystem.setShooter(Math.abs(mController.ly()));
//        }

    }

//    @Override
    public void exit() {
//
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
