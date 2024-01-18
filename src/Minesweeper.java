import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


// Minesweeper class
public class Minesweeper {
    private static final int SIZE = 9;
    private static final int MINES = 10;
    private static final char UNREVEALED = '-';
    private static final char MINE = '*';

    private char[][] board;
    private char[][] mineBoard;
    private boolean gameOver;

    private static Minesweeper instance;
    private List<Observer> observers = new ArrayList<>();

    private Minesweeper() {
        board = new char[SIZE][SIZE];
        mineBoard = new char[SIZE][SIZE];
        gameOver = false;
        initializeBoard();
        placeMines();
    }

    public static Minesweeper getInstance() {
        if (instance == null) {
            instance = new Minesweeper();
            instance.initializeInstance();
        }
        return instance;
    }

    public char[][] getBoard() {
        return board;
    }

    private void initializeInstance() {
        board = new char[SIZE][SIZE];
        mineBoard = new char[SIZE][SIZE];
        gameOver = false;
        initializeBoard();
        placeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = UNREVEALED;
                mineBoard[i][j] = UNREVEALED;
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        for (int i = 0; i < MINES; i++) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            while (mineBoard[row][col] == MINE) {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            }
            mineBoard[row][col] = MINE;
        }
    }

    private void printBoard() {
        // Print column labels
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(String.format("%3d ", i));
        }
        System.out.println("\n   " + "-------------------------------------");

        // Print board
        for (int i = 0; i < SIZE; i++) {
            // Print row label
            System.out.print(String.format("%2d |", i));
            for (int j = 0; j < SIZE; j++) {
                // Print playable positions
                System.out.print(String.format(" %s |", board[i][j]));
            }
            System.out.println("\n   " + "-------------------------------------");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == UNREVEALED;
    }

    private void revealCell(int row, int col) {
        if (mineBoard[row][col] == MINE) {
            gameOver = true;
            System.out.println("Game Over! You hit a mine.");
        } else {
            int count = countAdjacentMines(row, col);
            board[row][col] = (char) (count + '0');
            if (count == 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (isValidMove(i, j)) {
                            revealCell(i, j);
                        }
                    }
                }
            }
        }
        // Notify observers when the game state changes
        notifyObservers();
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE && mineBoard[i][j] == MINE) {
                    count++;
                }
            }
        }
        return count;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper! The board is " + SIZE + " by " + SIZE + " and there are " + MINES + " mines.");

        while (!gameOver) {
            printBoard();
            System.out.print("Enter row and column, separated by a space (e.g., 0 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                revealCell(row, col);
            } else {
                System.out.println("Invalid move. Try again.");
            }

            if (isGameWon()) {
                System.out.println("Congratulations! You've won!");
                gameOver = true;
            }
        }

        scanner.close();
    }

    private boolean isGameWon() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == UNREVEALED && mineBoard[i][j] != MINE) {
                    return false;
                }
            }
        }
        return true;
    }

    // Observer methods

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

