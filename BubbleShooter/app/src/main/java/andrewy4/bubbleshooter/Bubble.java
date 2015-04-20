package andrewy4.bubbleshooter;

/*
 * Created by Andrew Yu, Chau on 4/9/2015.
 */

import android.graphics.*;
import java.util.*;


public class Bubble {
    private float radius;
    private Paint bubblePaint; //depends on the number, it will generate the color
    private int color;
    float x_bubble_locat; //bubble's center location on Canvas
    float y_bubble_locat;
    public float mVelX; //bubble's velocity
    public float mVelY;
    private boolean delete; //delete bottom
    int x; //bubble's location on the bubble array list
    int y;


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

    public boolean returnColorDelete(){return delete;}

    public void changeDeleteToFalse(){
        delete = false;
    }

    public void changeDeleteToTrue(){
        delete = true;
    }



    Bubble(float Width, int x, int y) {
        radius = Width;
        this.x_bubble_locat = Width * (x + 1); //convert array to Canvas bitmap
        this.y_bubble_locat = Width * (2 * y + 1);
        bubblePaint = new Paint();
        bubblePaint.setStrokeWidth(4.5f); //set the arrow's length in bubble because arrow use same Paint as bubble
        Random rand = new Random(); //assign a random color to the ball
        color = rand.nextInt(7);
        int[] cs = {Color.YELLOW, Color.RED, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.BLUE, Color.BLACK};
        bubblePaint.setColor(cs[color]);
        mVelX = 0; //default speed = 0
        mVelY = 0;
        delete = false; //default deletion is false
        this.x = x;
        this.y = y;

    }

    public void drawBubble(Canvas c) {

        c.drawCircle(x_bubble_locat, y_bubble_locat, radius, bubblePaint);

        if((x_bubble_locat>0 && x_bubble_locat<c.getWidth()/20 - 2) || (x_bubble_locat<c.getWidth() && x_bubble_locat>c.getWidth()- (c.getWidth()/20+2))){ //when the ball reach the left and right side. it will bounce back
            mVelX = -mVelX;
        }
        if (x_bubble_locat > 0 && x_bubble_locat < c.getWidth()  && y_bubble_locat > radius && y_bubble_locat < c.getHeight() - radius) { //the animation function: constantly changing the center of the ball
            x_bubble_locat += mVelX;
            y_bubble_locat += mVelY;

        }
        else {
            mVelX = 0;
            mVelY = 0;
        }
    }

    public void velCalculation(float curX, float curY) { // for the moving ball, calculate the speed of the ball
        mVelX = (curX-x_bubble_locat)/20;
        mVelY = (curY-y_bubble_locat)/20;
    }
}
