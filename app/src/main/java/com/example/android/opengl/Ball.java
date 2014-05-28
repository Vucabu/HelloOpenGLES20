package com.example.android.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Ball {

    public FloatBuffer ballVB;

    private float cx, cy, r;

    float numSegments = 360;

    public void initShapes(float tx, float ty, float tr) {

        cx = (tx / 240.f) - 1.f;
        cy = (ty / 360.f) - 1.f;
        r = (tr / 240.f);

        float ballCoords[] = new float[(int) (numSegments * 3)];

        double theta = (2 * 3.1415926 / numSegments);
        float c = (float) Math.cos(theta);//precalculate the sine and cosine
        float s = (float) Math.sin(theta);
        float t;

        float x = r;//we start at angle = 0
        float y = 0;

        for (int i = 0; i < (numSegments * 3); i = i + 3) {

            ballCoords[i] = (x + cx);
            ballCoords[i + 1] = (y + cy);
            ballCoords[i + 2] = (0);

            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;

        }

        // initialize vertex Buffer for triangle
        ByteBuffer vbb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                ballCoords.length * 4);
        vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
        ballVB = vbb.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
        ballVB.put(ballCoords);    // add the coordinates to the FloatBuffer
        ballVB.position(0);            // set the buffer to read the first coordinate

    }

    public float getNumSeg() {

        return numSegments;

    }
}