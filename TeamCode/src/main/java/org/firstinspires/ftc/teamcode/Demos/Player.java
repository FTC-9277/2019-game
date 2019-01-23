package org.firstinspires.ftc.teamcode.Demos;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Hashtable;

public class Player {
    public HardwareMap hardwareMap;

    public Hashtable<String, Integer> sounds;


    public Player(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        this.sounds = new Hashtable<>();
    }

    public boolean add(String name){
        int id = hardwareMap.appContext.getResources().getIdentifier(name, "raw", hardwareMap.appContext.getPackageName());
        boolean b = SoundPlayer.getInstance().preload(hardwareMap.appContext, id);

        if (b){
            sounds.put(name,id);
        }
        return b;
    }

    public void play(String name){
        if (sounds.get(name) == null){return;}
        int id = sounds.get(name);
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, id);
    }

    public void kill(){
        SoundPlayer.getInstance().stopPlayingAll();
    }
}
