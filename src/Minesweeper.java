// Minesweeper.java
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private static final int SIZE = 9; // Adjust the size of the board as needed
    private static final int MINES = 10; // Adjust the number of mines as needed
    private static final char UNREVEALED = '-';
    private static final char MINE = '*';

    private char[][] board;
    private char[][] mineBoard;
    private boolean gameOver;

    // Singleton instance
    private static Minesweeper instance;

    // Private constructor to prevent instantiation from outside the class
    private Minesweeper() {
        board = new char[SIZE][SIZE];
        mineBoard = new char[SIZE][SIZE];
        gameOver = false;
        initializeBoard();
        placeMines();
    }
    // Public method to get the singleton instance
    public static Minesweeper getInstance() {
        if (instance == null) {
            instance = new Minesweeper();
            instance.initializeInstance(); // Call the initializeInstance method
        }
        return instance;
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
            System.out.println("Game Over! Je hebt een mijn geraakt.");
        } else {
            int count = countAdjacentMines(row, col);
            board[row][col] = (char) (count + '0');
            if (count == 0) {
                // Auto-reveal adjacent cells if no mines nearby
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (isValidMove(i, j)) {
                            revealCell(i, j);
                        }
                    }
                }
            }
        }
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

        System.out.println("Welkom bij Minesweeper! Het bord is " + SIZE + " bij " + SIZE + " groot, en er zijn " + MINES + " mijnen.");

        while (!gameOver) {
            printBoard();
            System.out.print("Voor rij en kolom in, houdt een spatie tussen de twee cijfers (bijvoorbeeld, 0 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                revealCell(row, col);
            } else {
                System.out.println("Ongeldige zet. Probeer opnieuw.");
            }

            // Check for win
            if (isGameWon()) {
                System.out.println("Gefeliciteerd! Je hebt gewonnen!");
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
}
