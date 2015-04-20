package andrewy4.bubbleshooter;

/*
 * Created by Chau, Andrew on 4/8/2015.
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.graphics.*;
import android.view.Menu;
import android.view.MenuItem;
import android.util.*;
import android.app.Activity;
import java.util.Random;

public class BubbleShooterView extends SurfaceView implements SurfaceHolder.Callback {
    BubbleShooterThread bst;
    int Height;
    int Width;      //the radius of the bubble
    int counter;              //count deletion
    Bubble[][] bubble = new Bubble[14][19];             // bubble array
    GameFunction[][] stick = new GameFunction[14][19];  //stick array
    Bubble shootingBubble;
    Bubble tempBubble;
    float curX, arrowX;   //click point, arrow point
    float curY, arrowY;
    boolean starting = false;
    float arrowX_origin;  //starting of the arrow point
    float arrowY_origin;

    public BubbleShooterView ( Context context ) {
        super ( context ) ;
// Notify the SurfaceHolder that you ’d like to receive
// SurfaceHolder callbacks .
        getHolder () . addCallback ( this ) ;
        setFocusable ( true ) ;

// Initialize game state variables . DON ’T RENDER THE GAME YET .
    //do stuff here//
    }
    @Override
    public void surfaceCreated ( SurfaceHolder holder ) {
// Construct game initial state ( bubbles , etc .)
        //Do stuff here//
        Height = getHeight();
        Width = getWidth();
        Width = Width/20;
        for(int y =0;y<14;y=y+2) {                         //following t=2 double for loops create the bubble array in the even and odd row
            for (int x = 0; x <= 17; x = x + 2) {
                bubble[y][x + 1] = null;

                if (y<7) {
                    bubble[y][x] = new Bubble(Width,x,y);
                }
                else{
                    bubble[y][x] = null;
                }
            }
                if(y<7)
                    bubble[y][18] = new Bubble(Width,18,y);

        }
        for(int y = 1; y<14; y=y+2){
            for (int x = 1; x <=18; x = x +2){
                bubble[y][x-1] = null;

                if (y<7) {
                    bubble[y][x] = new Bubble(Width, x,y);
                }
                else{
                    bubble[y][x] = null;
                }
            }
            bubble[y][18] = null;
        }


        for(int y=0; y<14;y++)                                  //creating sticking array for the sticking function
            for(int x=0; x<19;x++)
                stick[y][x]= new GameFunction(Width, x,y);


    bubble[13][9] = new Bubble(Width, 9, 13);               //location of the shooting bubble
    arrowX_origin = bubble[13][9].x_bubble_locat;
    arrowY_origin = bubble[13][9].y_bubble_locat;
// Launch animator thread .
        bst = new BubbleShooterThread ( this ) ;
        bst.start();

    }
    @Override
    public void surfaceChanged ( SurfaceHolder holder ,int format , int width , int height ) {
// Respond to surface changes , e . g . , aspect ratio changes .
    }
    @Override
    public void surfaceDestroyed ( SurfaceHolder holder) {
// The cleanest way to stop a thread is by interrupting it .
// BubbleShooterThread regularly checks its interrupt flag .
        bst.interrupt();
    }
    @Override
    public void onDraw ( Canvas c ) {
        super . onDraw ( c ) ;
        renderGame(c) ;
    }
    @Override
    public boolean onTouchEvent ( MotionEvent e ) {
// Update game state in response to events :
// touch - down , touch - up , and touch - move .
// Current finger position .


        switch ( e . getAction () ) {
            case MotionEvent . ACTION_DOWN :
                starting = true;
                curX = e . getX () ;
                curY = e . getY () ;

// Update Game State .
                break ;
            case MotionEvent . ACTION_MOVE :
                curX = e . getX () ;
                curY = e . getY () ;
                Arrow arrow = new Arrow(bubble[13][9],curX,curY,getWidth());      //initialize the arrow
                this.arrowX = arrow.arrowX;
                this.arrowY = arrow. arrowY;

                // Update Game State .
                break ;
            case MotionEvent . ACTION_UP :
                curX = e . getX();
                curY = e . getY () ;
                shootingBubble = bubble[13][9];                  //assign shooting bubble
                shootingBubble.velCalculation(curX, curY);       //speed of the shooting bubble
                bubble[13][9] = new Bubble(Width,9,13);          //put new bubble in





// Update Game State .
                break ;
        }
        return true ;
    }
    public void advanceFrame ( Canvas c ) {
// Update game state to animate moving or exploding bubbles
// ( e . g . , advance location of moving bubble ).
        // do stuff here //

        renderGame ( c ) ;
    }
    private void renderGame ( Canvas c ) {
// Render the game elements : bubbles ( fixed , moving , exploding )
// and aiming arrow .
        Paint paint = new Paint();                        //background step up start
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        c.drawPaint(paint);                              //background step up end
        stick[0][0].checkAvailableSpot(bubble,stick);    //check where can the shooting bubble stick
        for(int y = 0; y<14; y++)
            for(int x =0; x<19;x++) {
                if(bubble[y][x] == null)
                    continue;
                bubble[y][x].drawBubble(c);
                if(counter<3){  //deleting condition
                    bubble[y][x].changeDeleteToFalse();
                }
                if (bubble[y][x].returnColorDelete()) {
                    bubble[y][x] = null;
                }
            }
        if(starting) {   //arrow condition
            c.drawLine(arrowX_origin,arrowY_origin, arrowX, arrowY, bubble[13][9].returnColor());
        }
        if(shootingBubble != null) { //sticking shooting bubble condition
            shootingBubble.drawBubble(c);
            tempBubble = stick[0][0].stickBubble(c, bubble, shootingBubble, stick);
        }
        if(tempBubble!= null){  //deleting bubble condition

            shootingBubble = null;
            counter = stick[0][0].bubbleDeletion(bubble,tempBubble,(tempBubble.x),(tempBubble.y),0);
            tempBubble = null;
        }

        stick[0][0].checkAvailableSpot(bubble,stick); //check where to stick
   }
}
