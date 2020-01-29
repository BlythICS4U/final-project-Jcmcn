/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.GUI.MainScreen;
import Game.GUI.Panels;
import java.awt.Window;

/**
 * The Game class is the class that connects all the other classes
 * It interacts with all other classes
 * Starts and ends the game
 * Increments the amount of incorrect answers when a incorrect answer is given as input
 * Resets the game to it's initial state when starting a new game
 * @author Joca
 */
public class Game {

    private Score score = new Score();
    private MainScreen mainScreen;
    private Panels questionsPanel;
    private Window window;

    /*
    * Called when the button play is pressed on the main menu
    * Starts the timer and resets the timer, questions and amount of incorrect answers
    */
    public void startGame() {

        gameReset();
        score.timer();

    }

    /*
    * Called after the last question is answered
    * Calls the timer method a second time to set the endind time
    * Calculates the score and writes it to a file
    */
    public void endGame() {

        score.timer();
        long scoreValue = score.scoreCalculator();
        score.scoreWriter(scoreValue);

    }

    public void wrongAnswer() {

        score.incAnswer();

    }

    /*
    * Sets the values in score to their base value
    * Sets the questions back to the first question
    */
    public void gameReset() {

        score = new Score();
        questionsPanel.reset();

    }
    
    public Score getScore(){
        
        return score;
        
    }

    public Panels getQuestionPanel() {

        return questionsPanel;

    }

    public void setQuestionPanel(Panels panel) {

        questionsPanel = panel;

    }

}
