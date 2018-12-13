package org.firstinspires.ftc.teamcode.FtcExplosivesPackage;

public class MotorGroupPIDSource implements ExplosivePIDEnabledHardware {
    MotorGroup a, b;

    public MotorGroupPIDSource(MotorGroup a, MotorGroup b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double getOutput() {
        return a.getPosition() - b.getPosition();
    }

    @Override
    public double getLatency() {
        return 0;
    }

    @Override
    public void close() {

    }
}
