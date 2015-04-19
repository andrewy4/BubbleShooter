package andrewy4.bubbleshooter;

/*
 * Created by Andrew Yu on 4/9/2015.
 */

import android.graphics.*;
import java.util.*;


public class Bubble {
    private float radius;
    private Paint bubblePaint; //depends on the number, it will generate the color
    private int color;
    float x_bubble_locat;
    float y_bubble_locat;
    public float mVelX;
    public float mVelY;


    public Paint returnColor() {
        return bubblePaint;
    }

    public float returnRadius() {
        return radius;
    }

    public void changeColor(Paint color, int colorInt) {
        bubblePaint = color;
        this.color = colorInt;
    }

    public int returnColorInt() {
        return color;
    }

    Bubble(float Width, int x, int y) {
        radius = Width;
        this.x_bubble_locat = Width * (x + 1);
        this.y_bubble_locat = Width * (2 * y + 1);
        bubblePaint = new Paint();
        bubblePaint.setStrokeWidth(4.5f);
        Random rand = new Random();
        color = rand.nextInt(7);
        int[] cs = {-256, -65536, -65281, -16711936, -16711681, -16776961, -16777216};
        bubblePaint.setColor(cs[color]);
        mVelX = 0;
        mVelY = 0;


    }

    public void drawBubble(Canvas c) {

        c.drawCircle(x_bubble_locat, y_bubble_locat, radius, bubblePaint);
        if((x_bubble_locat>0 && x_bubble_locat<c.getWidth()/20 - 2) || (x_bubble_locat<c.getWidth() && x_bubble_locat>c.getWidth()- (c.getWidth()/20+2))){
            mVelX = -mVelX;
        }
        if (x_bubble_locat > 0 && x_bubble_locat < c.getWidth()  && y_bubble_locat > radius && y_bubble_locat < c.getHeight() - radius) {
            x_bubble_locat += mVelX;
            y_bubble_locat += mVelY;

        }
        else {
            mVelX = 0;
            mVelY = 0;
        }
    }

    public void velCalculation(float curX, float curY) { // for the moving ball
        mVelX = (curX-x_bubble_locat)/20;
        mVelY = (curY-y_bubble_locat)/20;
    }
}
