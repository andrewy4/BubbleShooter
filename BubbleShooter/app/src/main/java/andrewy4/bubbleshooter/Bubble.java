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

    Bubble(float Width, int x_bubble_locat, int y_bubble_locat){
        radius = Width;
        this.x_bubble_locat = x_bubble_locat;
        this.y_bubble_locat = y_bubble_locat;
        bubblePaint  = new Paint ();
        Random rand = new Random();
        color = rand.nextInt(7);
       int [] cs = {-256,-65536,-65281,-16711936,-16711681,-16776961,-16777216};

        bubblePaint . setColor (cs[color]);


    }
    public void drawBubble(Canvas c){

        c.drawCircle(x_bubble_locat, y_bubble_locat, radius, bubblePaint);

    }
}
