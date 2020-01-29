/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.GUI.Panels;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Joca
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Outside The Box");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                Game game = new Game();
                Panels panel = new Panels(game);
                game.setQuestionPanel(panel);
                frame.add(new Panels(game));
                // 
                frame.setResizable(false);
                frame.pack();
                frame.setVisible(true);

            }
        });

    }

}
