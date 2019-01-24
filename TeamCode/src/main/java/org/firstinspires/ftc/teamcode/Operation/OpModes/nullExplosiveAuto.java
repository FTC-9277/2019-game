package org.firstinspires.ftc.teamcode.Operation.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.ExplosiveAuto;
import org.firstinspires.ftc.teamcode.FtcExplosivesPackage.Utils;

@Autonomous(name = "Null Explosive Auto")
public class nullExplosiveAuto extends ExplosiveAuto {
    @Override
    public void initHardware() {

    }

    @Override
    public void initAction() {

    }

    @Override
    public void body() throws InterruptedException {
        Utils.sleep(29000);
    }

    @Override
    public void exit() throws InterruptedException {

    }

    @Override
    protected void climberMaintain() {

    }
}
