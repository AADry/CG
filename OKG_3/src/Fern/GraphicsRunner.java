package Fern;

import javax.swing.JFrame;



public class GraphicsRunner extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 500;
    private static final int HEIGHT= 800;



    public GraphicsRunner() {
        super("Barnsley Fern.Fern");
        setSize(WIDTH,HEIGHT);
        getContentPane().add(new Fern());
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);
    }



    public static void main( String args[] )
    {
        GraphicsRunner run = new GraphicsRunner();
    }
}