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
    float curX;
    float curY;
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
    bubble[13][9] = new Bubble(Width, 9, 13);
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
        float x_move;
        float y_move;
        switch ( e . getAction () ) {
            case MotionEvent . ACTION_DOWN :
                curX = e . getX () ;
                curY = e . getY () ;
// Update Game State .
                break ;
            case MotionEvent . ACTION_MOVE :
                curX = e . getX () ;
                curY = e . getY () ;
// Update Game State .
                break ;
            case MotionEvent . ACTION_UP :
                curX = e . getX () ;
                curY = e . getY() ;

                x_move = (curX/Width -1);
                y_move = (curY/Width-1)/2;
                float y2 = y_move - (int)y_move;
                if(0<=(int)y_move && (int)y_move<14)
                {
                    if((int)y_move%2 == 0){
                        if((int)x_move%2 ==0) {
                            bubble[(int) y_move][(int) x_move] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move].change_xy(Width, (int) x_move, (int) y_move);
                        }
                        else if((x_move - (int)x_move)>0.5 && ((int)x_move + 1) <20) {
                            bubble[(int) y_move][(int) x_move + 1] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move + 1].change_xy(Width, (int) x_move, (int) y_move+1);
                        }
                        else if (0<=((int)x_move - 1)){
                            bubble[(int) y_move][(int) x_move - 1] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move - 1].change_xy(Width, (int) x_move, (int) y_move-1);
                        }
                    }
                    if((int)y_move%2 != 0) {
                        if ((int) x_move % 2 != 0) {
                            bubble[(int) y_move][(int) x_move] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move].change_xy(Width, (int) x_move, (int) y_move);
                        }
                        else if((x_move- (int)x_move)>0.5 && ((int)x_move + 1) <20) {
                            bubble[(int) y_move][(int) x_move + 1] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move + 1].change_xy(Width, (int) x_move, (int) y_move+1);
                        }
                        else if(0<=((int)x_move - 1)){
                            bubble[(int) y_move][(int) x_move - 1] = bubble[13][9];
                            bubble[(int) y_move][(int) x_move - 1].change_xy(Width, (int) x_move, (int) y_move-1);
                        }
                    }
                }
                bubble[13][9] = new Bubble(Width, 9, 13);

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
        c.drawLine(bubble[13][9].x_bubble_locat, bubble[13][9].y_bubble_locat, curX, curY, bubble[13][9].returnColor());

   }
}
