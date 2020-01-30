# final-project
Final Project

#### Outside the Box

Outside the Box is a game created to challenge one's logical and creative thinking.
It consists of a set of 10 questions with sometimes unexpected answers.

The game is played with only the mouse and using it to click on the screen 
on the multiple buttons the game has.
There is more information on how to play the game on the "Help" section of the game
that can be accessed by pressing the "Help" button on the main menu.

The game also has a score mecanism that depending on how fast and flawlessly the game is 
completed the higher the score will be. This score is then saved to a file, if, the scores in the
file are smaller than the new score. The game only ever stores the 3 highest scores.
On the "Score" section of the game that can be accessed with the "Score" button on the 
main menu, the scores can be seen by the player.

To start the actual game all that is needed is to presse the "Play" button on the main menu.

The game implements it's buttons with the use of a actiolistener for mouse clicks,
it then gets the coordinates of the button click and checks to see if the coordinates
coincide with the created buttons.

Because of the amount of GUI the game possesses, and the main logic methods being 
score reader and score writers, there isn't any JUnit testing, as any other logic method
is so small that testing it would be redudant and unescessary ("scoreCalculator" method).
