package org.firstinspires.ftc.teamcode.FtcExplosivesPackage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Varun on 11/16/2017.
 */

public abstract class ExplosiveTele extends LinearOpMode {
    
    public Gamepad dController, mController;
    boolean isStarted, isLooping, isFinished;

    public abstract void initHardware();

    public abstract void firstLoop();

    public abstract void bodyLoop();

    public abstract void exit();

    @Override
    public void runOpMode() {

        teleInit();

        waitForStart();

        startTele();

        while(opModeIsActive()) {
            bodyLoop();
        }

        isFinished = true;
        isLooping = false;

        telemetry.addData("Exit", "Started");

        exit();

    }

    public void teleInit(){
        telemetry.addData("Initializing", "Started");
        telemetry.update();

        isFinished = false;
        isStarted = false;
        isLooping = false;

        dController = gamepad1;
        mController = gamepad2;

        initHardware();

        telemetry.addData("Initializing", "Finished");
        telemetry.update();
    }

    public void startTele(){
        telemetry.addData("First Loop", "Started");
        telemetry.update();

        firstLoop();

        isStarted = true;
        isLooping = true;

        telemetry.addData("First Loop", "Finished");
        telemetry.update();


    }

}
