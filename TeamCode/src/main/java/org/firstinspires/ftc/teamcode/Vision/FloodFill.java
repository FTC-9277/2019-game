package org.firstinspires.ftc.teamcode.Vision;


import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FloodFill {

    public Bitmap img;

    public boolean log;
    public boolean[][] been;
    public ArrayList<ArrayList<int[]>> yellow;
    public ArrayList<ArrayList<int[]>> white;

    public boolean yhb;
    public int yws;
    public int yhs;

    public boolean whb;
    public int wws;
    public int whs;

    public int xunit = 3;
    public int yunit = 3;

    public String POSITION;

    public LinearOpMode opMode;

    public String stuff;
    public int c;

    public FloodFill(LinearOpMode opMode, Camera camera, boolean log){
        this.opMode = opMode;
        this.log = log;

        wws = 0;
        whs = 0;
        yws = 0;
        yhs = 0;
        yhb = true;
        whb = true;
        c = 0;

        this.been = new boolean[camera.bitmap.getWidth()][camera.bitmap.getHeight()];
        for (int i = 0; i < camera.bitmap.getWidth(); i++){
            for (int j = 0; j < camera.bitmap.getHeight(); j++){
                this.been[i][j] = false;
            }
        }
    }

    public String sample(Bitmap bmp){
        fill(bmp);
        this.POSITION = sampling();
        return POSITION;
    }

    public String sampling(){
        ArrayList<ArrayList<int[]>> silver = top(white, 2);
        ArrayList<ArrayList<int[]>> gold = top(yellow, 1);

        ArrayList<Integer> s = avg_x(silver);
        ArrayList<Integer> g = avg_x(gold);

        if (s.size() == 2 && g.size() == 1) {
            int gx = g.get(0);
            int s1 = s.get(0);
            int s2 = s.get(1);
            stuff = gx + " " + s1 + " " + s2;
            if (s1 > gx && s2 > gx) {
                return ("LEFT");
            } else if (s1 < gx && s2 > gx) {
                return ("MIDDLE");
            } else if (s1 > gx && s2 < gx) {
                return ("MIDDLE");
            } else if (s1 < gx && s2 < gx) {
                return ("RIGHT");
            }
        }
        return ("UNKNOWN");
    }

    public ArrayList<ArrayList<int[]>> top(ArrayList<ArrayList<int[]>> l, int n){

        //Collections.sort(l, (a1,a2) -> a2.size()-a1.size());
        Collections.sort(l, new Comparator<ArrayList<int[]>>(){
                    public int compare(ArrayList a1, ArrayList a2) {
                        return a2.size() - a1.size();
                    }
                }

        );

        ArrayList<ArrayList<int[]>> o = new ArrayList<>();

        if (l.size() < n){
            for(ArrayList<int[]> k : l){
                o.add(k);
                if(a()){
                    return o;
                }
            }
        } else {
            for (int i = 0; i < n; i++){
                o.add(l.get(i));
                if(a()){
                    return o;
                }
            }
        }

        return o;
    }
    public ArrayList<Integer> avg_x(ArrayList<ArrayList<int[]>> l){
        ArrayList<Integer> o = new ArrayList<>();
        int sum = 0;
        for (ArrayList<int[]> p : l){
            for (int[] i : p){
                sum += i[0];
                if(a()){
                    return o;
                }
            }
            o.add(sum/p.size());
            sum = 0;
        }
        return o;
    }


    public void fill(Bitmap bmp){
        this.img = bmp;

        this.yellow = new ArrayList<>();
        this.white = new ArrayList<>();

        yfill();
        wfill();
    }

    public void yfill(){
        int[] s = ys();

        int x, y;
        ArrayList<int[]> queue = new ArrayList<>();

        ArrayList<int[]> points = new ArrayList<>();

        queue.add(s);
        while (s[0] != -1){
            fetch(s);
            while (queue.size() != 0){
                x = s[0] + xunit;
                y = s[1];
                if (in_bound_y(x,y)){
                    queue.add(new int[] {x,y});
                }

                x = s[0] - xunit;
                y = s[1];
                if (in_bound_y(x,y)){
                    queue.add(new int[] {x,y});
                }

                x = s[0];
                y = s[1] - yunit;
                if (in_bound_y(x,y)){
                    queue.add(new int[] {x,y});
                }

                x = s[0];
                y = s[1] + yunit;
                if (in_bound_y(x,y)){
                    queue.add(new int[] {x,y});
                }

                points.add(new int[] {s[0], s[1]});

                s = queue.get(0);
                queue.remove(0);
                if(a()){
                    return;
                }
            }
            this.yellow.add(points);
            points = new ArrayList<>();
            s = ys();
            queue.add(s);
        }
    }

    public void wfill(){
        int[] s = ws();

        int x, y;
        ArrayList<int[]> queue = new ArrayList<>();

        ArrayList<int[]> points = new ArrayList<>();

        queue.add(s);
        while (s[0] != -1){
            fetch(s);
            while (queue.size() != 0){
                //fetch(s);

                x = s[0] + xunit;
                y = s[1];
                if (in_bound_w(x,y)){
                    queue.add(new int[] {x,y});
                }


                x = s[0] - xunit;
                y = s[1];
                if (in_bound_w(x,y)){
                    queue.add(new int[] {x,y});
                }

                x = s[0];
                y = s[1] - yunit;
                if (in_bound_w(x,y)){
                    queue.add(new int[] {x,y});
                }

                x = s[0];
                y = s[1] + yunit;
                if (in_bound_w(x,y)){
                    queue.add(new int[] {x,y});
                }

                points.add(new int[] {s[0], s[1]});

                s = queue.get(0);
                queue.remove(0);
                if(a()){
                    return;
                }
            }
            white.add(points);
            points = new ArrayList<>();
            s = ws();
            queue.add(s);
        }
    }

    public int[] ws(){
        for (int i = wws; i < img.getWidth(); i+=xunit){
            for (int j = 0; j < img.getHeight(); j+=yunit){
                if (!whb){
                    whb = true;
                    j = whs;
                }
                if (!been[i][j]){
                    wws = i;
                    whs = j;
                    whb = false;
                    return new int[] {i,j};
                } else {
                    been[i][j] = true;
                }
                if(a()){
                    return new int[] {-1,-1};
                }
            }
        }
        return new int[] {-1,-1};
    }
    public int[] ys(){
        for (int i = yws; i < img.getWidth(); i+=xunit){
            for (int j = 0; j < img.getHeight(); j+=yunit){
                if (!yhb){
                    yhb = true;
                    j = yhs;
                }
                if (!been[i][j] && color.isYellow(c(i,j))){
                    yws = i;
                    yhs = j;
                    yhb = false;
                    return new int[] {i,j};
                } else if (!color.isWhite(c(i,j))) {
                    been[i][j] = true;
                }
                if(a()){
                    return new int[] {-1,-1};
                }
            }
        }
        return new int[] {-1,-1};
    }


    public boolean in_bound_w(int x, int y){
        if ((x > 0) && (x < img.getWidth()) && (y > 0) && (y < img.getHeight())){
            if (!been[x][y]){
                been[x][y] = true;
                return color.isWhite(c(x,y));
            }
        }
        return false;
    }
    public boolean in_bound_y(int x, int y){
        if ((x > 0) && (x < img.getWidth()) && (y > 0) && (y < img.getHeight())){
            if (!been[x][y]){
                been[x][y] = true;
                return color.isYellow(c(x,y));
            }
        }
        return false;
    }

    public void fetch(int[] p){
        been[p[0]][p[1]] = true;
    }
    public int c(int x, int y){
        return img.getPixel(x,y);
    }

    public void save(){
        if (this.img != null && log){
            Camera.download(this.img,"SamplingSource");
        }
    }

    public void save_flood(){
        Bitmap map = Bitmap.createBitmap(img.getWidth(),img.getHeight(),Bitmap.Config.RGB_565);
        for (ArrayList<int[]> p : yellow){
            for (int[] i : p){
                map.setPixel(i[0],i[1],Color.YELLOW);
                if(a()){
                    return;
                }
            }
        }
        for (ArrayList<int[]> p : white){
            for (int[] i : p){
                map.setPixel(i[0],i[1],Color.WHITE);
                if(a()){
                    return;
                }
            }
        }
        Camera.download(map,"SampledFlood");

    }

    public boolean a(){
        return !this.opMode.opModeIsActive();
    }
}