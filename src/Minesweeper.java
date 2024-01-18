import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


// Minesweeper class
public class Minesweeper {
    private static int SIZE = 10;  // Default size
    private static int MINES = 10;  // Default number of mines

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

    // Remove the final modifier and add a setter method for SIZE
    public static void setSize(int size) {
        SIZE = size;
    }

    // Remove the final modifier and add a setter method for MINES
    public static void setMines(int mines) {
        MINES = mines;
    }

    public static Minesweeper getInstance() {
        if (instance == null) {
            synchronized (Minesweeper.class) {
                if (instance == null) {
                    instance = new Minesweeper();
                }
            }
        }
        return instance;
    }

    public static Minesweeper createGame(int size, int mines) {
        instance = new Minesweeper();
        SIZE = size;
        MINES = mines;
        instance.initializeBoard();
        instance.placeMines();
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
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(String.format("%3d ", i));
        }
        System.out.println("\n   " + "-----------------------------------------");

        // Print board
        for (int i = 0; i < SIZE; i++) {
            // Print row label
            System.out.print(String.format("%2d |", i + 1));
            for (int j = 0; j < SIZE; j++) {
                // Print playable positions
                System.out.print(String.format(" %s |", board[i][j]));
            }
            System.out.println("\n   " + "-----------------------------------------");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 1 && row <= SIZE && col >= 1 && col <= SIZE && board[row - 1][col - 1] == UNREVEALED;
    }

    private void revealCell(int row, int col) {
        // Adjust indices to start from 0 internally
        int internalRow = row - 1;
        int internalCol = col - 1;

        if (mineBoard[internalRow][internalCol] == MINE) {
            gameOver = true;
            System.out.println("Game Over! You hit a mine.");
        } else {
            int count = countAdjacentMines(internalRow, internalCol);
            board[internalRow][internalCol] = (char) (count + '0');
            if (count == 0) {
                for (int i = internalRow - 1; i <= internalRow + 1; i++) {
                    for (int j = internalCol - 1; j <= internalCol + 1; j++) {
                        if (isValidMove(i + 1, j + 1)) {
                            revealCell(i + 1, j + 1);
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
            System.out.print("Enter row and column, separated by a space (e.g., 1 2): ");
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
    public void resetGame(int size, int mines) {
        SIZE = size;
        MINES = mines;
        board = new char[SIZE][SIZE];
        mineBoard = new char[SIZE][SIZE];
        gameOver = false;
        initializeBoard();
        placeMines();
    }
}

