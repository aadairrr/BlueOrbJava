import java.awt.Font;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Orb {
    
    private static final double RADIUS = 0.05; //Radius of all circles
    private static Scores scores;              //high scores
    private static double screenSize = 1;      //screenSize Ratio
    private static Font title = new Font ("Impact", Font.PLAIN, (int)(72*screenSize));
    private static Font titleFancy = new Font ("Brush Script MT", Font.PLAIN, (int)(48*screenSize));
    private static Font titleFancyBig = new Font ("Brush Script MT", Font.PLAIN, (int)(72*screenSize));
    private static Font info = new Font ("Impact", Font.PLAIN, (int)(36*screenSize));
    private static Font lostTitle = new Font ("Impact", Font.PLAIN, (int)(48*screenSize));
    private static Font lostInfo = new Font ("Impact", Font.PLAIN, (int)(24*screenSize));
    
    public static void main (String [] args) throws FileNotFoundException{
        StdDraw.setCanvasSize((int)(420*screenSize),(int)(540*screenSize)); 
        StdDraw.setXscale(-0.2,1.2);
        StdDraw.setYscale(-0.4,1.4);
        scores  = new Scores();
        while(true){
            int menuSelect = openingScreen();
            
            if (menuSelect == 1) howToScreen();
            if (menuSelect == 2){
                loseScreen(playScreen());
                while (true) {
                    if (StdDraw.mousePressed()) break;
                    try {TimeUnit.MILLISECONDS.sleep(10);}
                    catch (InterruptedException e) {}
                }
            }
        }
    }
    public static int openingScreen ()
    {
        try {TimeUnit.MILLISECONDS.sleep(100);}
        catch (InterruptedException e) {}
        int menuSelect = 0;
        while (true) {
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK); StdDraw.filledRectangle(.5,.5,.7,.9);      //BACKGROUND
            StdDraw.setPenColor(StdDraw.GREEN); StdDraw.filledCircle(StdDraw.mouseX(),StdDraw.mouseY(),RADIUS);  //MOUSE
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(1.075,1.275,.075);      //TopRight
            StdDraw.filledSquare(.875,1.275,.075);       //TopLeft
            
            StdDraw.setPenColor(StdDraw.BLUE);StdDraw.setFont(title); StdDraw.text(0.5,.7,"BLUE");
            StdDraw.setFont(titleFancyBig);StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(0.3,.95,"catch");StdDraw.text(0.7,.6,"orb");
            StdDraw.setFont(titleFancy); StdDraw.text(.22,.8,"the");
            
            StdDraw.filledRectangle(.5,1.2,.7,.2);      //TopWhite
            StdDraw.filledRectangle(.5,.385,.7,.135);
            StdDraw.filledRectangle(.5,-.025,.7,.05);
            StdDraw.filledRectangle(.5,-.35,.7,.05);
            StdDraw.filledRectangle(-.1,.06,.1,.46);
            StdDraw.filledRectangle(1.1,.06,.1,.46);
            //StdDraw.filledRectangle(.5,.06,.7,.46);      //BotWhite
            StdDraw.setFont(info);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(0.5,-0.2,"Play");
            StdDraw.text(0.5,0.125,"How to Play");
            StdDraw.show(20);
            if (StdDraw.mousePressed()&&StdDraw.mouseX()>.2&&StdDraw.mouseX()<.4
                    &&StdDraw.mouseY()>1&&StdDraw.mouseY()<1.2){
            }
            if (StdDraw.mousePressed()&&StdDraw.mouseX()>0&&StdDraw.mouseX()<1
                    &&StdDraw.mouseY()>-.3&&StdDraw.mouseY()<-.075){
                menuSelect = 2;
                break;
            }
            if (StdDraw.mousePressed()&&StdDraw.mouseX()>0&&StdDraw.mouseX()<1
                    &&StdDraw.mouseY()>.025&&StdDraw.mouseY()<.25){
                menuSelect = 1;
                break;
            }
        }
        return menuSelect;
    }
    public static void howToScreen ()
    {
        int menuSelect = 0;
        StdDraw.clear();
        StdDraw.setFont(title);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(0.5,1.2,"How To Play");
        Font info = new Font ("Impact", Font.PLAIN, (int)(12*screenSize));
        StdDraw.setFont(info);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.51,.9,"Move your mouse to control the GREEN orb");
        StdDraw.text(0.43,.7,"Collect BLUE orbs to score points");
        StdDraw.text(0.4,.5,"Avoid RED orbs, or lose a life!");
        StdDraw.text(0.5,.3,"Special PINK orbs will give you extra life!");
        StdDraw.setPenColor(StdDraw.GREEN);StdDraw.filledCircle(.1,.9,RADIUS);
        StdDraw.setPenColor(StdDraw.BLUE);StdDraw.filledCircle(.1,.7,RADIUS);
        StdDraw.setPenColor(StdDraw.RED);StdDraw.filledCircle(.1,.5,RADIUS);
        StdDraw.setPenColor(StdDraw.PINK);StdDraw.filledCircle(.1,.3,RADIUS);
        StdDraw.setPenColor(StdDraw.BLACK);
        double[] xs = { .2, .8, .8, .2 };
        double[] ys = { -.05, -.05, -.15, -.15 };
        StdDraw.filledPolygon(xs, ys);
        Font button = new Font ("Arial Black", Font.PLAIN, (int)(12*screenSize));
        StdDraw.setFont(button);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.5,-0.10,"Go Back");
        StdDraw.show(20);
        try {TimeUnit.MILLISECONDS.sleep(100);}
        catch (InterruptedException e) {}
        while (true) {
            if (StdDraw.mousePressed()&&StdDraw.mouseX()>.2&&StdDraw.mouseX()<.8
                    &&StdDraw.mouseY()>-.15&&StdDraw.mouseY()<-.05){
                break;
            }
            try {TimeUnit.MILLISECONDS.sleep(10);}
            catch (InterruptedException e) {}
        }
    }
    public static int playScreen ()
        throws FileNotFoundException{
        ArrayList<Pill> pill = new ArrayList<Pill>();
        boolean alive = true,
            press = false;
        pill.add(new Pill (0.5,0));
        Random ran = new Random();
        OrbGen orb = new OrbGen ();
        double orbX = orb.getX(),
            orbY = orb.getY(),
            mx = StdDraw.mouseX(),
            my = StdDraw.mouseY();
        int score = 0,
            lives = 3;
        while (alive){
            mx = mousex(mx);
            my = mousey(my);
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text(0.5,1.2,"Catch The Blue Orb");
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(.5,.5,.5);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(0.5,-0.10,"Score: " + score);
            StdDraw.text(0.5,1.1,"High Score: " + scores.getScore(0));
            //StdDraw.text(0.5,1.05,"Scored by: " + scores.getName(0));
            StdDraw.setPenColor(StdDraw.PINK);
            for(int i=0; i<lives; i++){
                int j = i;
                // draw diamond
                double[] xs = { .30+.15*j,  .35+.15*j, .40+.15*j, .35+.15*j };
                double[] ys = {  -.2, -.25, -.2, -.15 };
                StdDraw.filledPolygon(xs, ys);
                // circles
                StdDraw.filledCircle(.375+.15*j, -0.175, .025*Math.sqrt(2));
                StdDraw.filledCircle(0.325+.15*j, -0.175, .025*Math.sqrt(2));
            }
            
            //Objects
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(orbX,orbY,RADIUS);
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledCircle(mx,my,RADIUS);
            
            for(int p=0;p<pill.size();p++){
                if(pill.get(p).getLife())StdDraw.setPenColor(StdDraw.PINK);
                else StdDraw.setPenColor(StdDraw.RED);
                double xLoc = pill.get(p).getX();
                double yLoc = pill.get(p).getY();
                StdDraw.filledCircle(xLoc,yLoc,RADIUS);
                if(Math.sqrt(Math.pow(xLoc-mx,2)+Math.pow(yLoc-my,2)) < RADIUS*2){
                    if(pill.get(p).getLife()){
                        lives++;
                    } else {
                        lives--;
                        if(lives == 0){
                            alive = false;
                        }
                    }
                    pill.remove(p);
                }
            }
            
            if(Math.sqrt(Math.pow(orbX-mx,2)+Math.pow(orbY-my,2)) < RADIUS*2){
                orb = new OrbGen (orbX,orbY);
                orbX = orb.getX();
                orbY = orb.getY();
                score++;
                pill.add(new Pill(mx,my));
            }
            
            StdDraw.show(20);
        }
        return score;
    }
    public static void loseScreen (int newScore)throws FileNotFoundException
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(.5,.5,.5,.8);
        StdDraw.setPenColor(StdDraw.WHITE);
        //title = new Font ("Times New Roman", Font.PLAIN, (int)(36*screenSize));
        StdDraw.setFont(lostTitle);
        StdDraw.text(0.5,1.2,"You Lost!");
        //title = new Font ("Times New Roman", Font.PLAIN, (int)(24*screenSize));
        StdDraw.setFont(lostInfo);
        StdDraw.show(20);
        scores.newScore(newScore);
        StdDraw.text(0.5,1,"High Scores:");
        StdDraw.text(0.5,-0.2,"Click anywhere to restart");
        Font scoreFont = new Font ("Times New Roman", Font.PLAIN, (int)(16*screenSize));
        StdDraw.setFont(scoreFont);
        for(int i = 0;i<10;i++){
            StdDraw.text(0.4,.9-(i*.1),scores.getName(i));
            StdDraw.text(0.6,.9-(i*.1),""+scores.getScore(i));
        }
        StdDraw.show(20);
    }
    public static double mousex (double mx)
    {
        if(StdDraw.mouseX() > .95){
            mx = .95;
        } else if(StdDraw.mouseX() < .05){
            mx = .05;
        } else {
            mx = StdDraw.mouseX();
        }
        return mx;
    }
    public static double mousey (double my)
    {
        if(StdDraw.mouseY() > .95){
            my = .95;
        } else if(StdDraw.mouseY() < .05){
            my = .05;
        } else {
            my = StdDraw.mouseY();
        }
        return my;
    }
}
class Scores{
    File fileReference;
    Scanner inFile;
    String[] name = new String[10];
    int[] score = new int[10];
    
    public Scores()throws FileNotFoundException{
        final String fileName;
        File fileReference = new File("highScore.txt");
        Scanner inFile = new Scanner(fileReference);
        for(int i = 0; i<10; i++){
            name[i]=inFile.nextLine();
        }
        for(int i = 0; i<10; i++){
            String nextLine = inFile.nextLine();
            score[i]=Integer.parseInt(nextLine);
        }
    }
    public int getScore(int place){
        return score[place];
    }
    public String getName(int place){
        return name[place];
    }
    public void newScore(int newScore)throws FileNotFoundException{
        final String fileName = "highScore.txt";
        PrintWriter outFile = new PrintWriter(fileName);
        for(int i = 0;i<10;i++){
            if(newScore>score[i]){
                String newName = null;
                while(newName==null){
                    JFrame frame = new JFrame();
                    newName = JOptionPane.showInputDialog(frame, "You got a highscore! Enter your name:");
                    if(!(newName==null))if(newName.equals(""))newName=null;
                }
                for(int j = 9;j>i;j--){
                    score[j] = score[j-1];
                    name[j] = name[j-1];
                }
                score[i]=newScore;
                name[i]=newName;
                newScore=0;
            }
        }
        for (int i=0;i<10;i++){
            outFile.println(name[i]);
        }
        for (int i=0;i<10;i++){
            outFile.println(score[i]);
        }
        outFile.close();
    }
}
class OrbGen{
    private Random ran = new Random();
    private double orbX, orbY;
    
    public OrbGen (){
        orbX = 0.5;
        orbY = 0.8;
    }
    public OrbGen (double prevX, double prevY){
        orbX = ran.nextDouble()*0.9+.05;
        orbY = ran.nextDouble()*0.9+.05;
    }
    
    public double getX (){
        return orbX;
    }
    
    public double getY (){
        return orbY;
    }
}
class Pill{
    
    private Random ran = new Random();
    private double xLocation, yLocation, xVelocity, yVelocity;
    private boolean life;
    
    public Pill (){
        xLocation = .5;
        yLocation = .5;
        xVelocity = ran.nextDouble()*0.02-.01;
        yVelocity = ran.nextDouble()*0.02-.01;
    }
    
    public Pill (double x, double y){
        if(x == -1 && y == -1){
            xLocation = -1;
            yLocation = -1;
            xVelocity = 0;
            yVelocity = 0;
        } else {
            xLocation = ran.nextDouble()*0.9+.05;
            yLocation = ran.nextDouble()*0.9+.05;
            xVelocity = ran.nextDouble()*0.02-.01;
            yVelocity = ran.nextDouble()*0.02-.01;
            while(Math.sqrt(Math.pow(xLocation-x,2)+Math.pow(yLocation-y,2)) < .3){
                xLocation = ran.nextDouble()*0.9+.05;
                yLocation = ran.nextDouble()*0.9+.05;
            }
            int luck = ran.nextInt(10);
            if (luck == 7){
                life = true;
            } else {
                life = false;
            }
        }
    }
    
    public double getX (){
        if(xLocation + xVelocity > .95 || xLocation + xVelocity < .05){
            xVelocity = xVelocity * -1;
        }
        xLocation = xLocation + xVelocity;
        return xLocation;
    }
    
    public double getY (){
        if(yLocation + yVelocity > .95 || yLocation + yVelocity < .05){
            yVelocity = yVelocity * -1;
        }
        yLocation = yLocation + yVelocity;
        return yLocation;
    }
    
    public boolean getLife(){
        return life;
    }
    
}