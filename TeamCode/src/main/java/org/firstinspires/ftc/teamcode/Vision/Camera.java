package org.firstinspires.ftc.teamcode.Vision;
import android.content.Context;
import android.graphics.Bitmap;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.FileOutputStream;
import java.io.IOException;

public class Camera {

    public Bitmap bitmap;

    public VuforiaLocalizer vuforia;
    private VuforiaLocalizer.Parameters parameters;

    public boolean save;

    public Camera(HardwareMap hardwareMap, boolean save){
        initVuforia(hardwareMap,true);
        this.save = save;
        cycle();
    }

    public void initVuforia(HardwareMap hardwareMap, boolean cameraView) //False saves battery, true displays the screen
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        if (cameraView) {
            this.parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        } else {
            this.parameters = new VuforiaLocalizer.Parameters();

        }

        //HAZMAT KEY
        this.parameters.vuforiaLicenseKey = "AQX0J8v/////AAAAGZRPEwtYLUbljpFlk05IHDJdt2kTg7E8J7V0TlHB4v2Wj8UwHlZzgn4Fv3nA/sjxWSUVPX7/dILmxItMvkwIJqKTV3ZFHNPNLk8GAQYyRY8FExUIyTihu+qdD5P3inxdJ6dFnuMhU/k8Aj3ajV4OS9JKLtw6N2BkNKLGRM/VDsY6Qv+InZNEZ7hpH/zdJ1J8UXFqe67rSNH6nkQj9nhVmgWIbZQZSaSLCIJPc1i78FBrysjHxhvLmDfrk9tWSyB6mmKxDiO+6iL+lrVp1BnNSMMUTPIfNSma5scw48RFyCIpZ6/TxDev3VyVTGI+3RoIIli4PTEHlt9LFIIHP3WUsAuqq7MnTucj2uoinypj0b6K";

        this.parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.getInstance().createVuforia(parameters);

        com.vuforia.Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true); //enables RGB565 format for the image
        this.vuforia.setFrameQueueCapacity(1); //tells VuforiaLocalizer to only store one frame at a time
    }

    public int getColor(int x, int y){
        return this.bitmap.getPixel(x,y);
    }
    public int getRed(int x, int y){
        return color.getRed(this.bitmap.getPixel(x,y));
    }
    public int getGreen(int x, int y){
        return color.getGreen(this.bitmap.getPixel(x,y));
    }
    public int getBlue(int x, int y){
        return color.getBlue(this.bitmap.getPixel(x,y));
    }

    public void cycle(){
        try
        {
            VuforiaLocalizer.CloseableFrame frame = this.vuforia.getFrameQueue().take();
            long numImages = frame.getNumImages();

            for (int i = 0; i < numImages; i++) {
                if (frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                    this.bitmap = imgToBitmap(frame.getImage(i));
                    break;
                }
            }
        }catch(InterruptedException ie)
        {
            this.bitmap = null;
        }
    }
    public Bitmap imgToBitmap(Image image){
        Bitmap img = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.RGB_565);
        img.copyPixelsFromBuffer(image.getPixels());
        return img;
    }

    public void save(Bitmap bmp, String name){
        if(save) {
            String filename = name + ".PNG";

            FileOutputStream out = null;
            try {
                out = new FileOutputStream(filename);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
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

    public void save(String name){
        save(this.bitmap,name);
    }

    public void take_picture(){
        cycle();
        save("Image_Capture");
    }

    public static void download(Bitmap bmp, String name){
        String filename = "/sdcard/"+name + ".PNG";

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
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

    public int[] bitmapToArray(Bitmap bmp){
        int[] o = new int[bmp.getWidth()*bmp.getHeight()];

        bmp.getPixels(o,0,bmp.getWidth(),0,0,bmp.getWidth(),bmp.getHeight());
        return o;
    }
}