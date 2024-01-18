// LoggerDecorator.java
public class LoggerDecorator implements Observer {
    private Minesweeper minesweeper;

    public LoggerDecorator(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
        this.minesweeper.addObserver(this);
    }

    @Override
    public void update() {
        // Implement the logic to log game state changes
        System.out.println("Logging game state changes:");
        char[][] board = minesweeper.getBoard();
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        // You can add more specific logging logic based on your requirements
        // For instance, you might log the changes to a file, database, etc.
    }
}
