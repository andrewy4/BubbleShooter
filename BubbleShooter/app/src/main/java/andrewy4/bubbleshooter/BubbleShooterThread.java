package andrewy4.bubbleshooter;

/**
 * Created by Chau on 4/8/2015.
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BubbleShooterThread extends Thread{
    BubbleShooterView bsv ;
    public BubbleShooterThread ( BubbleShooterView bsv ) {
        this . bsv = bsv ;
    }
    public void run () {
        SurfaceHolder sh = bsv . getHolder () ;
// Main game loop .
        while ( ! Thread . interrupted () ) {
            Canvas c = sh . lockCanvas ( null ) ;
            try {
                synchronized ( sh ) {
                    bsv . advanceFrame ( c ) ;
                }
            } catch ( Exception e ) {
            } finally {
                if ( c != null ) {
                    sh . unlockCanvasAndPost ( c ) ;
                }
            }
// Set the frame rate by setting this delay
            try {
                Thread . sleep (30) ;
            } catch ( InterruptedException e ) {
// Thread was interrupted while sleeping .
                return ;
            }
        }

}
