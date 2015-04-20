package andrewy4.bubbleshooter;


import android.graphics.*;

/*
 * Created by Chau, Andrew on 4/19/2015.
 */

//game function include calculating where can the ball stick, stick the ball, and ball cancellation

public class GameFunction {
    public boolean stickFlag; //see if a place can be stick
    float top;
    float down;
    float left;
    float right;

    public GameFunction( float width, int x, int y){
        // draw a box around the ball location to make it a stick area
        stickFlag = false;
        top = width*(2 * y + 1) - width +5;
        down = width*(2 * y + 1) + width -5;
        left = width* x +5;
        right = width* (x + 2) -5;



    }

    public void checkAvailableSpot(Bubble[][] bubble, GameFunction[][] stick){ //call this constantly to see open spots for the bubble to latch
        int x;
        int y;
        for(x=0;x<19;x++)
            for(y=0;y<14;y++)
                stick[y][x].stickFlag = false;

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

    public Bubble stickBubble(Canvas c,Bubble[][] bubble, Bubble shootingBubble,GameFunction[][] stick){//call this function to get the ball to stick in the appropriate place. Return the ball that stick or null
        int x;
        int y;
        for(x=0;x<=19;x++)
            for(y=0;y<=13;y++){
                if(stick[y][x].stickFlag) {
                    if ((shootingBubble.y_bubble_locat<stick[y][x].down)&& (shootingBubble.y_bubble_locat>stick[y][x].top)&&(shootingBubble.x_bubble_locat>stick[y][x].left) &&( shootingBubble.x_bubble_locat<stick[y][x].right)) {
                        bubble[y][x] = new Bubble(shootingBubble.returnRadius(), x, y);
                        bubble[y][x].changeColor(shootingBubble.returnColor(),shootingBubble.returnColorInt());
                        shootingBubble.mVelX = 0; //make the shooting ball stop
                        shootingBubble.mVelY = 0;
                        shootingBubble.changeDeleteToTrue(); //turn the deletion on so later it can be delete
                        return bubble[y][x]; //return the ball that stick
                    }
                }

            }
        return null;
        }

    public int bubbleDeletion(Bubble[][] bubble,Bubble deleteStart,int x,int y, int _counter) { //calculate how many balls can be cancel after the ball stick to the array. Return # of balls can be destroyed
        int colorType = deleteStart.returnColorInt();
        int counter = _counter;
        if(x+2<19 && bubble[y][x+2]!=null && bubble[y][x+2].returnColorInt()==colorType && !bubble[y][x+2].returnColorDelete()){ //see right ball can be cancel

                bubble[y][x + 2].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y][x+2],x+2 ,y ,counter); //recursion of the right ball to move to the next 6 possibilities
        }
        if(x-2>=0 && bubble[y][x-2]!=null && bubble[y][x-2].returnColorInt()==colorType && !bubble[y][x-2].returnColorDelete()){ //left ball

                bubble[y][x - 2].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y][x-2],x-2 ,y ,counter);
        }
        if(x+1<19 && y+1<=13 && bubble[y+1][x+1]!=null && bubble[y+1][x+1].returnColorInt()==colorType && !bubble[y+1][x+1].returnColorDelete()){ //bottom right ball

                bubble[y + 1][x + 1].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y+1][x+1],x+1 ,y+1 ,counter);
        }
        if(x-1>=0 && y+1<=13 && bubble[y+1][x-1]!=null && bubble[y+1][x-1].returnColorInt()==colorType && !bubble[y+1][x-1].returnColorDelete()){ //bottom left ball

                bubble[y + 1][x - 1].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y+1][x-1],x-1 ,y+1 ,counter);

        }
        if(x+1<19 && y-1>=0 && bubble[y-1][x+1]!=null && bubble[y-1][x+1].returnColorInt()==colorType && !bubble[y-1][x+1].returnColorDelete()){ //top right ball

                bubble[y - 1][x + 1].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y-1][x+1],x+1 ,y-1 ,counter);

        }
        if(x-1>=0 && y-1>=0 && bubble[y-1][x-1]!=null && bubble[y-1][x-1].returnColorInt()==colorType && !bubble[y-1][x-1].returnColorDelete()){ //top left ball

                bubble[y - 1][x - 1].changeDeleteToTrue();
                counter++;
            counter = bubbleDeletion(bubble,bubble[y-1][x-1],x-1 ,y-1 ,counter);

        }

        return counter; //return the number of counter
    }
    }

