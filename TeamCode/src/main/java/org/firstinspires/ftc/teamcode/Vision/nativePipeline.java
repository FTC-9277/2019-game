package org.firstinspires.ftc.teamcode.Vision;

public class nativePipeline {

    public int sample(int[] a, int w, int h){
        return FloodFill(a,w,h);
    }
    static {
        System.loadLibrary("native-lib");
    }

    public native int FloodFill(int[] a, int w, int h);
}