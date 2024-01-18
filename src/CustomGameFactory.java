// CustomGameFactory.java
public class CustomGameFactory implements GameFactory {
    private static final int CUSTOM_SIZE = 12;
    private static final int CUSTOM_MINES = 15;

    @Override
    public Minesweeper createGame() {
        return Minesweeper.createGame(CUSTOM_SIZE, CUSTOM_MINES);
    }
}

