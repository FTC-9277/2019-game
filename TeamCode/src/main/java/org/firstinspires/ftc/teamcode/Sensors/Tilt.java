package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Tilt extends Digital {

    public Tilt(LinearOpMode opMode, String name){
        super(opMode, name);
    }

    public boolean isUpright(){
        return state();
    }
}
