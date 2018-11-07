//
// Created by Sachin Shah on 10/24/18.
//

//#include "native-lib.h"
#include <deque>
#include <jni.h>

extern "C" {
    JNIEXPORT jint JNICALL Java_org_firstinspires_ftc_teamcode_Vision_nativePipeline_FloodFill(JNIEnv *env, jobject obj, jintArray arr, jint width, jint height);
    int getRed(int colorInt);
    int getGreen(int colorInt);
    int getBlue(int colorInt);
    bool isW(int r, int g, int b);
    bool isWhite(int colorInt);
    bool isY(int r, int g, int b);
    bool isYellow(int colorInt);
    bool in_bound_y(int x, int y);
    bool in_bound_w(int x, int y);
    int * ys();
    int * ws();
    void fetch(int p[2]);
    void yfill();
    void wfill();
    int sample(int y, int w[2]);
}
typedef struct my_struct_t{
    int w, h;

    int xunit = 2;
    int yunit = 2;

    int yellow_threshold = 160;
    int white_threshold = 200;

    bool been[921600];

    int *pixels;

    bool yhb;
    int yws;
    int yhs;

    bool whb;
    int wws;
    int whs;

    int yellow;
    int white[2];
} my_struct_t;

my_struct_t my_struct;


JNIEXPORT jint JNICALL Java_org_firstinspires_ftc_teamcode_Vision_nativePipeline_FloodFill(JNIEnv *env, jobject obj, jintArray arr, jint width, jint height) {
    jboolean * b;

    my_struct = my_struct_t();
    /*
    bool k[921600];
    memcpy(my_struct.been, k, 921600);

    my_struct.wws = 0;
    my_struct.whs = 0;
    my_struct.yhs = 0;
    my_struct.yws = 0;*/

    my_struct.pixels = env->GetIntArrayElements(arr, b);

    my_struct.w = (int) width;
    my_struct.h = (int) height;

    yfill();
    wfill();


    int output = sample(my_struct.yellow,my_struct.white);

    //RELEASE
    env->ReleaseIntArrayElements(arr, my_struct.pixels, 0);
/*
    jintArray result = env->NewIntArray(4);

    int r[4];
    r[0] = output;
    r[1] = my_struct.yellow;
    r[2] = my_struct.white[0];
    r[3] = my_struct.white[1];

    env->SetIntArrayRegion(result,0,4,r);*/

    return output;//(jint) output;
}

//Right 1
//Left 2
//Middle 3
//Unknown 3
int sample(int y, int w[2]){
    if (y > w[0] && y > w[1]){
        return 1;
    } else if (y < w[0] && y < w[1]){
        return 2;
    } else {
        return 3;
    }
}

void wfill(){
    int* s;

    s = ws();

    int x, y;

    std::deque<int> qx;
    std::deque<int> qy;

    int avg_x = 0;
    int count = 0;

    int top_one = 0;
    int top_two = 0;

    qx.push_back(s[0]);
    qy.push_back(s[1]);

    while (s[0] != -1){
        fetch(s);
        while (qx.size() != 0){
            x = s[0] + my_struct.xunit;
            y = s[1];
            if (in_bound_w(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0] - my_struct.xunit;
            y = s[1];
            if (in_bound_w(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0];
            y = s[1] - my_struct.yunit;
            if (in_bound_w(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0];
            y = s[1] + my_struct.yunit;
            if (in_bound_w(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            count++;
            avg_x += s[0];


            s[0] = qx.front();
            s[1] = qy.front();
            qx.pop_front();
            qy.pop_front();
        }
        if(top_one < count){
            my_struct.white[1] = my_struct.white[0];
            top_two = top_one;
            my_struct.white[0] = avg_x/count;
            top_one = count;
        } else if(top_two < count){
            my_struct.white[1] = avg_x/count;
            top_two = count;
        }
        count = 0;
        avg_x = 0;

        s = ws();
        qx.push_back(s[0]);
        qy.push_back(s[1]);
    }
    delete s;
    std::deque<int>().swap(qx);
    std::deque<int>().swap(qy);
}
void yfill(){
    int* s;

    s = ys();

    int x, y;

    std::deque<int> qx;
    std::deque<int> qy;

    int avg_x = 0;
    int count = 0;

    int top = 0;

    qx.push_back(s[0]);
    qy.push_back(s[1]);

    while (s[0] != -1){
        fetch(s);
        while (qx.size() != 0){
            x = s[0] + my_struct.xunit;
            y = s[1];
            if (in_bound_y(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0] - my_struct.xunit;
            y = s[1];
            if (in_bound_y(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0];
            y = s[1] - my_struct.yunit;
            if (in_bound_y(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            x = s[0];
            y = s[1] + my_struct.yunit;
            if (in_bound_y(x,y)){
                qx.push_front(x);
                qy.push_front(y);
            }

            count++;
            avg_x += s[0];


            s[0] = qx.front();
            s[1] = qy.front();
            qx.pop_front();
            qy.pop_front();
        }
        if (count > top) {
            my_struct.yellow = (avg_x / count);
            top = count;
        }
        count = 0;
        avg_x = 0;

        s = ys();
        qx.push_back(s[0]);
        qy.push_back(s[1]);
    }
    delete s;
    std::deque<int>().swap(qx);
    std::deque<int>().swap(qy);
}

int * ys(){
    for (int i = my_struct.yws; i < my_struct.w-my_struct.xunit; i+=my_struct.xunit){
        for (int j = 0; j < my_struct.h-my_struct.yunit; j+=my_struct.yunit){
            if (!my_struct.yhb){
                my_struct.yhb = true;
                j = my_struct.yhs;
            }
            if (!my_struct.been[j*my_struct.w+i] && isYellow(my_struct.pixels[j*my_struct.w+i])){
                my_struct.yws = i;
                my_struct.yhs = j;
                my_struct.yhb = false;
                my_struct.been[j*my_struct.w+i] = true;
                return new int[2] {i,j};
            } else if (!isWhite(my_struct.pixels[j*my_struct.w+i])) {
                my_struct.been[j*my_struct.w+i] = true;
            }
        }
    }
    return new int[2] {-1,-1};
}

int * ws(){
    for (int i = my_struct.wws; i <my_struct. w-my_struct.xunit; i+=my_struct.xunit){
        for (int j = 0; j < my_struct.h-my_struct.yunit; j+=my_struct.yunit){
            if (!my_struct.whb){
                my_struct.whb = true;
                j = my_struct.whs;
            }
            if (!my_struct.been[j*my_struct.w+i]){
                my_struct.wws = i;
                my_struct.whs = j;
                my_struct.whb = false;
                my_struct.been[j*my_struct.w+i] = true;
                return new int[2] {i,j};
            }
        }
    }
    return new int[2] {-1,-1};
}

bool in_bound_w(int x, int y) {
    if (x > 0 && x < my_struct.w && y > 0 && y < my_struct.h){
        if (!my_struct.been[y*my_struct.w+x]){
            my_struct.been[y*my_struct.w+x] = true;
            return true;
        }
    }
    return false;
}

bool in_bound_y(int x, int y) {
    if (x > 0 && x < my_struct.w && y > 0 && y < my_struct.h){
        if (!my_struct.been[y*my_struct.w+x]){
            if (isYellow(my_struct.pixels[y*my_struct.w+x])){
                my_struct.been[y*my_struct.w+x] = true;
                return true;
            } else if (!isWhite(my_struct.pixels[y*my_struct.w+x])){
                my_struct.been[y*my_struct.w+x] = true;
           }
        }
    }
    return false;
}

bool isYellow(int c){
    return isY(getRed(c),getGreen(c),getBlue(c));
}

bool isY(int r, int g, int b){
    return (255-b > my_struct.yellow_threshold) && (255-g <= my_struct.yellow_threshold) && (255-r <= my_struct.yellow_threshold);
}

bool isWhite(int c){
    return isW(getRed(c),getGreen(c),getBlue(c));
}
bool isW(int r, int g, int b){
    return ((r > my_struct.white_threshold) && (g > my_struct.white_threshold) && (b > my_struct.white_threshold));
}

int getRed(int colorInt){
    return ((colorInt >> 16) & 0xff);
}
int getGreen(int colorInt){return ((colorInt >>  8) & 0xff);}
int getBlue (int colorInt){return ((colorInt      ) & 0xff);}

void fetch(int p[2]){
    my_struct.been[p[1]*my_struct.w+p[0]] = true;
}