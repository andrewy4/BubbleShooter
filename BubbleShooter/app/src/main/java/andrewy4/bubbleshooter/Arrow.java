package andrewy4.bubbleshooter;

/*
 * Created by Andrew, Cahu on 4/16/2015.
 */
//the Arrow class will draw the line from the shooting point to the edge of the screen with the color of the shooting ball.
public class Arrow {
    public float arrowX;
    public float arrowY;


    Arrow(Bubble bubble, float curX, float curY, int Width) {
        float x_origin = bubble.x_bubble_locat;
        float y_origin = bubble.y_bubble_locat;
        float x_diff = x_origin - curX;
        float y_diff = y_origin - curY;
        float slope = Float.MAX_VALUE;
        if (x_diff != 0)
            slope = y_diff / x_diff;
        //the following conditions are when the line is point to left/top/left wall
        if (slope == Float.MAX_VALUE) {
            arrowX = x_origin;
            arrowY = 0;
        } else if (slope == y_origin / x_origin) {
            arrowX = 0;
            arrowY = 0;
        } else if (slope == y_origin / (x_origin - Width)) {
            arrowX = Width;
            arrowY = 0;
        } else if (slope > 0) {
            if (slope > y_origin / x_origin) {
                arrowX = x_origin - (y_origin / slope);
                arrowY = 0;
            } else {
                arrowX = 0;
                arrowY = y_origin - x_origin * slope;
            }
        } else if (slope < 0) {
            if (slope > y_origin / (x_origin - Width)) {
                arrowX = Width;
                arrowY = y_origin - y_diff * ((Width - x_origin) / (curX - x_origin));
            } else {
                arrowX = -1 / slope * y_origin + x_origin;
                arrowY = 0;
            }
        }
    }
}
