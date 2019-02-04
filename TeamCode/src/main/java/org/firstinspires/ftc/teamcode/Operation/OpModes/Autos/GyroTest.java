package org.firstinspires.ftc.teamcode.Operation.OpModes.Autos;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "Gyro Test")
public class GyroTest extends LinearOpMode {

    BNO055IMU imu;

    Orientation last = new Orientation();

    double globalangle;

    @Override
    public void runOpMode() throws InterruptedException {
        BNO055IMU.Parameters parms = new BNO055IMU.Parameters();
        parms.mode = BNO055IMU.SensorMode.IMU;
        parms.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class,"imu");

        imu.initialize(parms);


        waitForStart();

        while(opModeIsActive()){
            telemetry.addData(">", imu.getCalibrationStatus());
            telemetry.addData(">",angle());
            telemetry.update();


        }
    }


    public double angle(){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.DEGREES);

        double delta = angles.firstAngle - last.firstAngle;
        return delta;
    }
}
