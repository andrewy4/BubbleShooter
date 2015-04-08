package andrewy4.bubbleshooter;

/**
 * Created by Chau on 4/8/2015.
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view;
import android.view.Menu;
import android.view.MenuItem;

public class BubbleShooterView extends SurfaceView implements SurfaceHolder.Callback {

    public BubbleShooterView ( Context context ) {
        super ( context ) ;
// Notify the SurfaceHolder that you ’d like to receive
// SurfaceHolder callbacks .
        getHolder () . addCallback ( this ) ;
        setFocusable ( true ) ;
// Initialize game state variables . DON ’T RENDER THE GAME YET .
        ...
    }
    @Override
    public void surfaceCreated ( SurfaceHolder holder ) {
// Construct game initial state ( bubbles , etc .)
        ...
// Launch animator thread .
        bst = new BubbleShooterThread ( this ) ;
        bst . start () ;
    }
    @Override
    public void surfaceChanged ( SurfaceHolder holder ,
                                 int format , int width , int height ) {
// Respond to surface changes , e . g . , aspect ratio changes .
    }
    @Override
    public void surfaceDestroyed ( SurfaceHolder holder ) {
// The cleanest way to stop a thread is by interrupting it .
// BubbleShooterThread regularly checks its interrupt flag .
        bst . interrupt () ;
    }
    @Override
    public void onDraw ( Canvas c ) {
        super . onDraw ( c ) ;
        renderGame ( c ) ;
    }
    @Override
    public boolean onTouchEvent ( MotionEvent e ) {
// Update game state in response to events :
// touch - down , touch - up , and touch - move .
// Current finger position .
        4
        int curX = e . getX () ;
        int curY = e . getY () ;
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
// ( e . g . , advance location of moving bubble ) .
        ...
        renderGame ( c ) ;
    }
    private void renderGame ( Canvas c ) {
// Render the game elements : bubbles ( fixed , moving , exploding )
// and aiming arrow .
    }
}
