public class Main {
    public static void main(String[] args) {
        // Create a standard Minesweeper game
        GameFactory standardFactory = new StandardGameFactory();
        Minesweeper standardGame = standardFactory.createGame();

        // Decorate Minesweeper with GameObserver
        GameObserver gameObserver = new GameObserver(standardGame);

        // Decorate Minesweeper with LoggerDecorator
        LoggerDecorator loggerDecorator = new LoggerDecorator(standardGame);

        standardGame.playGame();

        // Create a custom Minesweeper game
        GameFactory customFactory = new CustomGameFactory();
        Minesweeper customGame = customFactory.createGame();

        // Decorate Minesweeper with GameObserver
        GameObserver customGameObserver = new GameObserver(customGame);

        // Decorate Minesweeper with LoggerDecorator
        LoggerDecorator customLoggerDecorator = new LoggerDecorator(customGame);

        customGame.playGame();
    }
}