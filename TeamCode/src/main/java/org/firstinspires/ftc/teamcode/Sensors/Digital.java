package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/*

    Abstract class for creating digital i/o sensors

    Extend this class for specific sensor types like touch, tilt
 */


public abstract class Digital {

    public LinearOpMode opMode;
    public DigitalChannel sensor;

    public String name;
    public Digital(LinearOpMode opMode, String name){
        this.opMode = opMode;
        sensor = opMode.hardwareMap.get(DigitalChannel.class, name);
    }

    public boolean state(){
        return sensor.getState();
    }

    public boolean isInput(){
        return sensor.getMode().equals(DigitalChannel.Mode.INPUT);
    }
}
