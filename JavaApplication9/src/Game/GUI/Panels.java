/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.GUI;

import Game.Game;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Panels class gets the images and paints it on the JFrame and creates all the buttons
 * It interacts with the Main and Game methods
 * It creates all the buttons
 * Stores all the images in the folders in arrays
 * Updates the JPanel
 * @author Joca
 */
public class Panels extends JPanel {

    public Image[] questionImages = new Image[10];// The array that stores the question images
    public Image[] extraImages = new Image[3];// The array that stores all other images
    private int questionNumber = 1;// Int that changes which question is being painted
    private int selector = 0;// Int that changes which array is being painted
    private int extraPanelsSelector = 0;// Int that changes which of the extra panels is being painted
    private Game game;

    // Extra panels buttons
    public static final Rectangle2D PLAY = new Rectangle2D.Double(224, 179, 151, 42);
    public static final Rectangle2D HELP = new Rectangle2D.Double(224, 237, 151, 42);
    public static final Rectangle2D HELPBACK = new Rectangle2D.Double(23, 438, 151, 42);
    public static final Rectangle2D SCORE = new Rectangle2D.Double(225, 302, 151, 42);
    public static final Rectangle2D SCOREBACK = new Rectangle2D.Double(22, 439, 151, 42);

    // Question buttons
    public static final Ellipse2D NUMBER_BUTTON = new Ellipse2D.Double(21, 17, 56, 55);
    public static final Rectangle2D OPTION1 = new Rectangle2D.Double(109, 237, 151, 42);
    public static final Rectangle2D OPTION2 = new Rectangle2D.Double(329, 237, 151, 42);
    public static final Rectangle2D OPTION3 = new Rectangle2D.Double(109, 337, 151, 42);
    public static final Rectangle2D OPTION4 = new Rectangle2D.Double(329, 337, 151, 42);
    public static final Rectangle2D YEAR_BUTTON = new Rectangle2D.Double(563, 485, 30, 9);

    // Array with the correct button for each questions
    public static Shape[] QUESTIONBUTTONS = {
        OPTION1,
        OPTION3,
        NUMBER_BUTTON,
        YEAR_BUTTON,
        OPTION1,
        OPTION1,
        OPTION4,
        OPTION4,
        NUMBER_BUTTON,
        OPTION1
    };

    /*
    * Gets all the images from the files and stores them in the array
    */
    public Panels(Game game) {

        this.game = game;
        
        this.setPreferredSize(new Dimension(600, 500));

        try {
            for (int i = 0; i < 10; i++) {
                String fileName = "Question " + (i + 1) + ".png";
                System.out.println(new File(fileName).getAbsolutePath());
                if (i <= 9) {
                    questionImages[i] = ImageIO.read(new File("Questions/" + fileName));
                }
            }System.out.println(new File("MainScreen").getAbsolutePath());
            extraImages[0] = ImageIO.read(new File("ExtraPanels/MainScreen.png"));
            extraImages[1] = ImageIO.read(new File("ExtraPanels/Help.png"));
            extraImages[2] = ImageIO.read(new File("ExtraPanels/Scores.png"));

        } catch (IOException ex) {

            ex.printStackTrace();
            System.exit(0);

        }

        /*
        * When the mouse is clicked gets it's location and acts according to where
        * it was clicked and in which panel it is currently
        */
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Shape questionsButton = QUESTIONBUTTONS[questionNumber - 1];
                Shape playButton = PLAY;
                Shape helpButton = HELP;
                Shape helpBackButton = HELPBACK;
                Shape scoreButton = SCORE;
                Shape scoreBackButton = SCOREBACK;

                /*
                * selector 0 = Extra Panels
                * selector 1 = Questions Panels
                * extraPanelsSelector 0 = Main Menu
                * extraPanelsSelector 1 = Help
                * extraPanelsSelector 2 = Score
                */
                if (playButton.contains(x, y) && extraPanelsSelector == 0 && selector == 0) {
                    selector = 1;
                    game.startGame();
                    repaint();
                } else if (helpButton.contains(x, y) && extraPanelsSelector == 0 && selector == 0) {
                    extraPanelsSelector = 1;
                    repaint();
                } else if (scoreButton.contains(x, y) && extraPanelsSelector == 0 && selector == 0) {
                    extraPanelsSelector = 2;
                    repaint();
                } else if (helpBackButton.contains(x, y) && extraPanelsSelector == 1 && selector == 0) {
                    extraPanelsSelector = 0;
                    repaint();
                } else if (scoreBackButton.contains(x, y) && extraPanelsSelector == 2 && selector == 0) {
                    extraPanelsSelector = 0;
                    repaint();
                } else if (questionsButton.contains(x, y) && selector == 1 && questionNumber < 10) {
                    questionNumber++;
                    selector = 1;
                    repaint();
                } else if (questionsButton.contains(x, y) == false && selector == 1) {
                    game.wrongAnswer();
                    reset();
                    selector = 1;
                    repaint();
                }
                else if (questionNumber == 10 && questionsButton.contains(x, y) && selector == 1){
                    selector = 0;
                    game.endGame();
                    repaint();
                }

            }
        });
    }

    public int getQuestionNumber() {

        return questionNumber;

    }
    
    /*
    * When an incorrect answer is given or the game is reset sets question number
    * to the first question
    */
    public void reset() {

        questionNumber = 1;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selector == 0) {
            g.drawImage(extraImages[extraPanelsSelector], 0, 0, this);
        }

        if (selector == 1) {
            g.drawImage(questionImages[questionNumber - 1], 0, 0, this);
        }
        // Used only for printing the score on the screen
        if (extraPanelsSelector == 2 && selector == 0) {
            String[] scoreLines = game.getScore().scoreReader();
            
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
            
            for (int y = 0; y < scoreLines.length; y++){
                System.out.println(scoreLines[y]);
                g.drawString(scoreLines[y], 250, y*28+200);
            }
        }
    }

}
