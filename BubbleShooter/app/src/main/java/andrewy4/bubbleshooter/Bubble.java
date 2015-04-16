package andrewy4.bubbleshooter;

/**
 * Created by Andrew Yu on 4/9/2015.
 */
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.graphics.*;
import android.view.Menu;
import android.view.MenuItem;
import android.util.*;
import java.util.*;
import android.app.Activity;

public class Bubble {
    private float radius;
    private Paint bubblePaint; //depends on the number, it will generate the color
    private int color;
    private  int[] cs ;
    float x_bubble_locat;
    float y_bubble_locat;
    public float mVelX;
    public float mVelY;



    public Paint returnColor(){
        return bubblePaint;
    }

    Bubble(float Width, int x, int y){
        radius = Width;
        this.x_bubble_locat = Width * (x+1);
        this.y_bubble_locat = Width*(2*y+1);
        bubblePaint  = new Paint ();
        bubblePaint.setStrokeWidth(4.5f);
        Random rand = new Random();
        color = rand.nextInt(7);
        int [] cs = {-256,-65536,-65281,-16711936,-16711681,-16776961,-16777216};
        bubblePaint . setColor (cs[color]);
        mVelX = 0;
        mVelY = 0;


    }
    public void drawBubble(Canvas c){

        c.drawCircle(x_bubble_locat, y_bubble_locat, radius, bubblePaint);
        if(x_bubble_locat>radius && x_bubble_locat<c.getWidth()-radius && y_bubble_locat>radius && y_bubble_locat<c.getHeight()-radius) {
            x_bubble_locat += mVelX;
            y_bubble_locat += mVelY;
        }
        else{
            mVelX = 0;
            mVelY = 0;
        }
    }
    public void velCalculation(float curX, float curY){
        mVelX = (curX-x_bubble_locat)/20;
        mVelY = (curY-y_bubble_locat)/20;


    }

}
