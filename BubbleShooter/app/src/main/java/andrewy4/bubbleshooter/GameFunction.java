package andrewy4.bubbleshooter;


import android.graphics.*;

/*
 * Created by Chau on 4/19/2015.
 */
public class GameFunction {
    public boolean stickFlag;
    float top;
    float down;
    float left;
    float right;

    public GameFunction( float width, int x, int y){
        stickFlag = false;
        top = width*(2 * y + 1) - width;
        down = width*(2 * y + 1) + width;
        left = width* x;
        right = width* (x + 2);



    }

    public void checkAvailableSpot(Bubble[][] bubble, GameFunction[][] stick){ //call this constantly to see open spots for the bubble to latch
        int x;
        int y;

        for(x=0;x<19;x++)
            for(y=0;y<=13;y++)
            {
                if(bubble[y][x]!= null) {
                    //might need to add another if statement for the odd and even rows do this of something is not right
                    if (x - 2 >= 0 && bubble[y][x - 2] == null) { //left bubble
                        stick[y][x - 2].stickFlag = true;
                    }

                    if (x - 1 >= 0 && y - 1 >= 0 && bubble[y - 1][x - 1] == null) { // top left bubble
                        stick[y - 1][x - 1].stickFlag = true;
                    }

                    if (x + 1 < 19 && y - 1 >=0 && bubble[y - 1][x + 1] == null) { //top right bubble
                        stick[y - 1][x + 1].stickFlag = true;
                    }

                    if (x + 2 <19 && bubble[y][x + 2] == null) { //right bubble
                        stick[y][x + 2].stickFlag = true;
                    }

                    if (x + 1 < 19 && y+1<=13 && bubble[y+1][x+1] == null) { //bottom right bubble
                        stick[y+1][x+1].stickFlag = true;
                    }

                    if (x - 1 >= 0 && y+1<=13 && bubble[y + 1][x - 1] == null) { //bottom right bubble
                        stick[y + 1][x - 1].stickFlag = true;
                    }

                    stick[13][9].stickFlag=false;//shootingBall wont stick along with its surrounding spots
                    stick[13][7].stickFlag=false;
                    stick[13][11].stickFlag=false;
                    stick[12][8].stickFlag=false;
                    stick[12][10].stickFlag=false;
                }
            }
    }

    public void stickBubble(Canvas c,Bubble[][] bubble, Bubble shootingBubble,GameFunction[][] stick){//call this function to get the ball to stick in the appropriate place
        int x;
        int y;
        for(x=0;x<=19;x++)
            for(y=0;y<=13;y++){
                if(stick[y][x].stickFlag) {
                    if ((shootingBubble.y_bubble_locat<stick[y][x].down)&& (shootingBubble.y_bubble_locat>stick[y][x].top)&&(shootingBubble.x_bubble_locat>stick[y][x].left) &&( shootingBubble.x_bubble_locat<stick[y][x].right)) {
                        bubble[y][x] = new Bubble(shootingBubble.returnRadius(), x, y);
                        bubble[y][x].changeColor(shootingBubble.returnColor(),shootingBubble.returnColorInt());
                        shootingBubble.mVelX = 0;
                        shootingBubble.mVelY = 0;
                        shootingBubble = null;

                        break;
                    }
                }

            }
        }

    public void bubbleDeletion(Bubble[][] bubble){
        
    }

    }

