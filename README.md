# Tetris-game
Tetris game on Java for Windows and Android

# tetris-desktop
Desktop version for Windows written on JavaFX. It contains library that could be used to build game on any UI.

# tetris-android-app
Android application - simple tetris.

# Library
Library is available from tetris-model-1.01.jar. It is abstract model of a game. Using
this library you could build your own game with any UI. 

To build game it is necessery to extend abstract GameProcess class and implement start() method - it should perform 
usage of startGameStep() with some time period. It was not implemented, because UI frameworks and libraries could use different 
multithreading API.

Also, you sould implement TetrisController interface with methods for game elements drawing. This interface is used
within GameProcess when changes in game appears and sould be shown on a screen.

To start game, call GameProcess#start() method.
