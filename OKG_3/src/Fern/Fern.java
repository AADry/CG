package Fern;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;


public class Fern extends Canvas {



    private static final long serialVersionUID = 1L;
    public static final int ITERATION = 100000;



    public  Fern() {
        setBackground(Color.WHITE);
    }



    public void paint(Graphics window) {
        double x = .5;
        double y = 0;
        window.setColor(Color.GREEN);
        window.drawRect(200,600,1,1);
        Color color;
        for(int i = 0; i<ITERATION; i++) {
            double chance = (Math.random());
            if(chance < .85) {
                double x2=.85*x+.04*y; //x=.85*x+.04*y;
                y=-.04*x+.85*y+1.6; //y=-.04*x+.85*y+1.6;
                x = x2;
                color = Color.GREEN;
            } else if(chance < .86){
                x=0; //x=0;
                y=.16*y; //y=.16*y;
                color = Color.blue;
            } else if(chance < .93){
                x=.2*x-.26*y; //x=.2*x-.26*y;
                y=.23*x+.22*y+1.6; //y=.23*x+.22*y+1.6;
                color = Color.red;
            } else {
                x=-.15*x+.28*y; //x=-.15*x+.28*y;
                y=.26*x+.24*y+.44; //y=.26*x+.24*y+.44;
                color = Color.orange;
            }
            window.setColor(color);
            window.fillRect((int)(66*x)+200,(int)(66*-y)+675,1,1);
        }
    }
}