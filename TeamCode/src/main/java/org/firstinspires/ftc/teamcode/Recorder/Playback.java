package org.firstinspires.ftc.teamcode.Recorder;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Operation.Subsystems.DriveSubsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Playback {

    public String name;
    public boolean loaded;
    public ArrayList<double[]> data;

    public LinearOpMode opMode;
    public DriveSubsystem drive;

    public long delay = 10;

    public Playback(String name, LinearOpMode opMode, DriveSubsystem drive){
        this.name = "/sdcard/robotRuns/" + name +".csv";
        this.loaded = false;
        this.opMode = opMode;
        this.drive = drive;
        this.data = new ArrayList<>();
    }

    public void load(){
        loaded = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(new double[]{Double.valueOf(values[0]), Double.valueOf(values[1])});
                data.add(new double[]{Double.valueOf(values[0]), Double.valueOf(values[1])});
            }
        } catch (IOException e){
            loaded = false;
        }
    }

    public void run(){
        if (!loaded){return;}

        int place = 0;
        while (place < data.size() && opMode.opModeIsActive()){
            drive.tankDrive(-data.get(place)[0],-data.get(place)[1]);
            place++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e){

            }
        }
    }
}
