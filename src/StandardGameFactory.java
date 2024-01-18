public class StandardGameFactory implements GameFactory {
    @Override
    public Minesweeper createGame() {
        return Minesweeper.getInstance();
    }
}
