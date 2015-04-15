package andrewy4.bubbleshooter;

/**
 * Created by Chau on 4/8/2015.
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
    int Width;
    Bubble[][] bubble = new Bubble[14][20];
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
        int z = 0;
        int color;
        for(int y =0;y<14;y=y+2) {
            for (int x = 0; x <= 17; x = x + 2) {
                bubble[y][x + 1] = null;

                if (y<7) {
                    bubble[y][x] = new Bubble(Width,Width * (x+1), Width*(2*y+1));
                }
                else{
                    bubble[y][x] = null;
                }
            }
                if(y<7)
                    bubble[y][18] = new Bubble(Width, Width*(19), Width*(2*y+1));

        }
        for(int y = 1; y<14; y=y+2){
            for (int x = 1; x <=18; x = x +2){
                bubble[y][x-1] = null;

                if (y<7) {
                    bubble[y][x] = new Bubble(Width, Width*(x+1), Width*(2*y+1));
                }
                else{
                    bubble[y][x] = null;
                }
            }
            bubble[y][18] = null;
        }

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

        float curX = e . getX () ;
        float curY = e . getY () ;
        switch ( e . getAction () ) {
            case MotionEvent . ACTION_DOWN :
// Update Game State .
                break ;
            case MotionEvent . ACTION_MOVE :
// Update Game State .
                break ;
            case MotionEvent . ACTION_UP :
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
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        c.drawPaint(paint);

        for(int y = 0; y<14; y++)
            for(int x =0; x<20;x++) {
                if(bubble[y][x] == null)
                    continue;
                bubble[y][x].drawBubble(c);
            }
   }
}
