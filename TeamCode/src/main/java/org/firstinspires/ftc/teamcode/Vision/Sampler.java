package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Sampler {

    private LinearOpMode opMode;
    private Camera camera;

    public Sampler(LinearOpMode opMode){
        this.opMode = opMode;
        this.camera = new Camera(opMode.hardwareMap, false);
    }

    public int sample(){
        int[] pixels;
        int w, h;
        nativePipeline pipeline = new nativePipeline();
        camera.cycle();
        pixels = camera.bitmapToArray(camera.bitmap);
        w = camera.bitmap.getWidth();
        h = camera.bitmap.getHeight();
        camera.bitmap.recycle();


        return pipeline.sample(pixels, w, h);
    }
}