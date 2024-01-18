// Main.java
public class Main {
    public static void main(String[] args) {
        Minesweeper minesweeper = Minesweeper.getInstance();
        GameObserver gameObserver = new GameObserver(minesweeper); // Create an instance of GameObserver
        minesweeper.playGame();
    }
}

