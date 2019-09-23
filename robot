package com.company;
import java.util.Array;

enum Direction
{
	UP,DOWN,LEFT,RIGHT;
}

class Robot
{
	int x,y;
	Direction dir;
	
	
	public Robot(int x,int y,Direction dir) 
    {
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	


		public Direction getDirection() 
		{
			 return this.dir; // текущее направление взгляда
		}

		public int getX() 
		{
			 return x;	// текущая координата X
		}

		public int getY() 
		{
			 return y;	// текущая координата Y
		}

		public void turnLeft() 
		{
			// повернуться на 90 градусов против часовой стрелки
			switch(dir)
		   {
			  case UP:
                    System.out.println("turn left");
                    dir = Direction.LEFT;
                    break;
				case LEFT:
                    System.out.println("turn left");
                    dir = Direction.DOWN;
					          break;
				case DOWN:
                    System.out.println("turn left");
                    dir = Direction.RIGHT;
                    break;
        case RIGHT:
                    System.out.println("turn left");
                    dir = Direction.UP;
                    break;
        default:
					          dir = Direction.LEFT;
                    break;
            }
		}

		public void turnRight() 
		{
			// повернуться на 90 градусов по часовой стрелке
			switch (dir) 
			{
				case UP:
               System.out.println("turn right");
               dir = Direction.RIGHT;
               break;
				case LEFT:
               System.out.println("turn right");
               dir = Direction.UP;
               break;
        case DOWN:
             System.out.println("turn right");
              dir = Direction.LEFT;
                    break;
        case RIGHT:
             System.out.println("turn right");
             dir = Direction.DOWN;
             break;
        default:
					  dir = Direction.RIGHT;
            break;
       }
		}

		public void stepForward() 
		{
			// шаг в направлении взгляда
			// за один шаг робот изменяет одну свою координату на единицу
			switch (dir) 
			{
				case UP:
                    System.out.println("moving up");
                    y++;
                    break;
				case LEFT:
					x--;
                    System.out.println("moving left");
                    break;
                case DOWN:
                    y--;
                    System.out.println("moving down");
                    break;
                case RIGHT:
                    x++;
                    System.out.println("moving right");
                    break;
                default:
                    break;
            }
		}
}



public class Main
{
	public static void moverobot(Robot myrobot,int toX,int toY) 
    {
		//переместить робота из текущего положения в другое
		while (( myrobot.getX()!= toX) | ( myrobot.getY()!= toY))	//пока не дошли до нужной координаты- перемещать
        {
            if (toX > myrobot.getX())	//если необходимая координата правее,то вправо  
            {
                while (myrobot.getDirection()!= Direction.RIGHT)
                {
                    myrobot.turnRight();	//поворачивать,пока не повернется направо по оси координат
                }
                while (myrobot.getX()!= toX) 
				{
                    myrobot.stepForward();	//шагать пока не дойдет до нужной координаты
                }
                System.out.println(myrobot.getX());
            }
            else if (toX < myrobot.getX())	//иначе если необходимая координата левее,то налево 
            {								//далее все аналогично
                while (myrobot.getDirection () != Direction.LEFT)
                {
                    myrobot.turnRight();
                }
                while (myrobot.getX()!= toX) 
				{
                    myrobot.stepForward();
                }
                System.out.println(myrobot.getX());
            }
            if (toY > myrobot.getY())
            {
                while (myrobot.getDirection () != Direction.UP)
                {
                    myrobot.turnRight();
                }
                while (myrobot.getY()!= toY)
				{
                    myrobot.stepForward();
                }
                System.out.println(myrobot.getY());
            }
            if (toY < myrobot.getY())
            {
                while (myrobot.getDirection () != Direction.DOWN)
                {
                    myrobot.turnRight();
                }
				while (myrobot.getY()!= toY) 
				{
                    myrobot.stepForward();
                }
                System.out.println(myrobot.getY());
            }
            System.out.println("myrobot in position X = " +myrobot.getX()+ " Y=" + myrobot.getY());
        }	
	}


	public static void main(String[] args) 
    {
		Robot myrobot; //= new Robot();
		myrobot.x=1;
		myrobot.y=1;
		myrobot.dir=UP;
		moverobot(myrobot,5,3);
	}
}
