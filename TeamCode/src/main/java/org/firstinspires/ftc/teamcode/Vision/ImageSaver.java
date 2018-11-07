package org.firstinspires.ftc.teamcode.Vision;

import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageSaver implements Runnable {

    public Bitmap img;
    public String name;

    public ImageSaver(Bitmap img, String name){
        this.img = img;
        this.name = name;

        Thread t = new Thread(this);
        t.setName(name);
        t.start();
    }


    @Override
    public void run(){
        String filename = "/sdcard/"+name + ".PNG";

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            img.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}