// Main.java
public class Main {
    public static void main(String[] args) {
        Minesweeper minesweeper = Minesweeper.getInstance();

        // Decorate Minesweeper with GameObserver
        GameObserver gameObserver = new GameObserver(minesweeper);

        // Decorate Minesweeper with LoggerDecorator
        LoggerDecorator loggerDecorator = new LoggerDecorator(minesweeper);

        minesweeper.playGame();
    }
}

