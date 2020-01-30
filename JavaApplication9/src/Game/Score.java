/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Score class is where all the operations that involve the score happen It
 * only interacts with the Game class It calculates number of incorrect answers
 * and the amount of time needed to finish the game Calculates the final score
 * Prints and read it from a file
 *
 * @author Joca
 */
public class Score {

    int incorrect = 0; //Number of incorrect answer during the gameplay time
    public long startingTime = 0; //Used to calculate the total time passed during gameplay time
    public long endingTime; //Used to calculate the total time passed during gameplay time

    /*
    * Writes the new Score to the file while at the same time putting the score 
    * in increasing order.
    * If there isn't a score file it will create the score file and set the scores 
    * in the file to 0
     */
    public static void scoreWriter(long high) {

        BufferedWriter bw = null;

        BufferedReader br = null;

        try {

            File file = new File("Score.txt");

            if (!file.exists()) {

                FileWriter fw = new FileWriter(file);

                bw = new BufferedWriter(fw);

                for (int i = 1; i <= 3; i++) {

                    bw.write("0\n");

                }

                bw.close();

            }

            br = new BufferedReader(new FileReader("Score.txt"));

            // Adds the existing scores to an array
            long[] scores = new long[3];
            String line = br.readLine();
            int counter = 0;
            while (line != null) {

                long actualScore = Long.parseLong(line);
                scores[counter] = actualScore;
                counter++;
                line = br.readLine();
            }

            // Sorts the existing score with the addition of the called score and saves the 3 highest
            long tempValue;
            for (int i = 0; i <= scores.length - 1; i++) {

                if (scores[i] < high) {
                    tempValue = scores[i];
                    scores[i] = high;
                    high = tempValue;
                }
            }

            FileWriter fw = new FileWriter(file);

            bw = new BufferedWriter(fw);

            for (int i = 0; i < scores.length; i++) {

                String update = Long.toString(scores[i]);
                bw.write(update + "\n");
            }

        } catch (IOException ioe) {

            ioe.printStackTrace(System.out);

        } finally {

            try {

                if (bw != null) {

                    bw.close();

                }

                if (br != null) {

                    br.close();

                }

            } catch (IOException ioe) {

                System.out.println("Error in closing the BufferedWriter");

                ioe.printStackTrace(System.out);

            }

        }

    }

    /*
    * Reads the scores from the file
     */
    public static String[] scoreReader() {

        BufferedReader br = null;

        String[] scores = new String[3];

        try {

            File file = new File("Score.txt");
            
            if (!file.exists()){
                
                scoreWriter(0);
                
            }
            
            br = new BufferedReader(new FileReader("Score.txt"));

            String contentLine = br.readLine();

            int counter = 0;

            while (contentLine != null) {

                if (counter < 3) {
                    scores[counter] = contentLine;
                }

                counter++;
                contentLine = br.readLine();
            }

            return scores;

        } catch (IOException ioe) {

            ioe.printStackTrace(System.out);

        } finally {

            try {

                if (br != null) {

                    br.close();

                }

            } catch (IOException ioe) {

                System.out.println("Error in closing the BufferedReader");

                ioe.printStackTrace(System.out);

            }

        }

        return scores;

    }

    public void incAnswer() {

        incorrect++;

    }

    /*
    * Calculates the amount of passed during gameplay time by subtracting the
    * this method's second call by the first call.
     */
    public void timer() {

        if (startingTime == 0) {

            startingTime = System.currentTimeMillis();

        } else {

            endingTime = System.currentTimeMillis() - startingTime;

        }

    }

    public long scoreCalculator() {

        long score = 10000;
        long negatives = endingTime / 100 + incorrect * 500;
        score = score - negatives;
        return score;

    }

}
