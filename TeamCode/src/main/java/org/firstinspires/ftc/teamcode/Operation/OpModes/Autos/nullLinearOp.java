package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;

@Autonomous(name = "Null Auto")
public class nullLinearOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Utils.sleep(29000);
    }
}
