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


    Bubble(float Width, int color){
        radius = Width;
        bubblePaint  = new Paint ();
        switch(color){
            case 1:
                bubblePaint . setColor ( Color . RED );
                break;
            case 2:
                bubblePaint . setColor ( Color . YELLOW );
                break;
            case 3:
                bubblePaint . setColor ( Color . GREEN );
                break;
            case 4:
                bubblePaint . setColor ( Color . CYAN );
                break;
            case 5:
                bubblePaint . setColor ( Color . BLUE );
                break;
            case 6:
                bubblePaint . setColor ( Color . MAGENTA );
                break;
            case 7:
                bubblePaint . setColor ( Color . BLACK );
                break;
            default:
                break;
        }
    }
    public void drawBubble(Canvas c, float Width, float Height){

        c.drawCircle(Width, Height, radius, bubblePaint);

    }
}
