import java.util.*;

// === PLAYER (Pre-populated) ===
class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() { return name; }
    public char getSymbol() { return symbol; }
}

// === BOARD ===
class Board {
    private int size;
    private char[][] grid;

    /*
    TODO 1: Initialize the board
    - Assign the 'size' parameter to the class field.
    - Initialize the 'grid' array using this size.
    - Use nested loops to fill every cell with a blank space (' ').
    */
    public Board(int size) {
        // Write your initialization logic here
        this.size = size;
        this.grid = new char[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                grid[i][j] = ' ';
            }
        }
    }

    /*
    TODO 2: Place a symbol
    - Check if row and col are within bounds (0 to size-1). If not, print an error and return false.
    - Check if the cell is already occupied (not ' '). If not, print an error and return false.
    - If valid, assign the symbol to the grid and return true.
    */
    public boolean placeSymbol(int row, int col, char symbol) {
        if(row>= 0 && row< size && col>=0 && col< size) {
            if(grid[row][col] == ' ') {
                grid[row][col] = symbol;
                return true;
            }
        }
        return false; 
    }

    // --- PRE-POPULATED HELPER METHODS ---
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public int getSize() { return size; }
    public char[][] getGrid() { return grid; }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("[" + grid[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// === GAME ENGINE ===
class GameEngine {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public GameEngine(Player p1, Player p2, int boardSize) {
        this.board = new Board(boardSize);
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;
    }

    /*
    TODO 3: Dynamic Win Check
    - Do NOT hardcode for 3x3 (e.g., avoid grid[0][0]).
    - Use board.getSize() and loops to check rows, columns, and diagonals dynamically.
    - Return true if the current symbol has a winning line.
    */
    public boolean checkWin(char symbol) {
        // Write your dynamic win-checking logic here
        char[][] grid = board.getGrid();
        int size = board.getSize();
        // traverse row level
         int diagCount1 = 0;
         int diagCount2 = 0;
        for(int i=0;i<size;i++) {
            int cnt = 0;
            for(int j=0;j<size;j++) {
                if(grid[i][j]==symbol) {
                    cnt++;
                    if(i==j) {
                        diagCount1++;
                    }
                    if(i+j == size-1) {
                        diagCount2++;
                    }
                    
                }
            }
            if(cnt== size) {
                return true;
            }

        }

        if(diagCount1 == size || diagCount2 == size) {
            return true;
        }

         // traverse column level
        for(int i=0;i<size;i++) {
            int cnt = 0;
            for(int j=0;j<size;j++) {
                if(grid[j][i]==symbol) {
                    cnt++;
                }
            }
            if(cnt== size) {
                return true;
            }
        }
        return false;
    }

    /*
    TODO 4: Execute Turn
    1. Call board.placeSymbol() with the current player's symbol.
    2. If placement is successful:
        a. Print the updated board.
        b. Check if the player won. If yes, print victory message and return.
        c. Check if board is full. If yes, print draw message and return.
        d. If no win/draw, switch to the other player.
    */
    public void playTurn(int row, int col) {
        System.out.println(currentPlayer.getName() + " attempts to place '" + currentPlayer.getSymbol() + "' at (" + row + ", " + col + ")");
        // Write your turn delegation logic 
        
        if(board.isFull()) {
            System.out.println("Game Over! It's a draw!");
            return;
        }
        
        char symbol = currentPlayer.getSymbol();
        String name = currentPlayer.getName();
        boolean placeSymbol = board.placeSymbol(row,col,symbol);
        int size = board.getSize();
        char[][] grid = board.getGrid();
        if(placeSymbol) {
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++) {
                    System.out.print(grid[i][j]+" ");
                }
                System.out.println("");
            }
            boolean checkWin = checkWin(symbol);
            if(checkWin) {
               System.out.println("Game Over! ["+name+"] wins!");
               return;
            }
        }

      
        switchPlayer();
    }

    // --- PRE-POPULATED HELPER METHOD ---
    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}

// === Main Class === 
public class Codechef {
    public static void main(String[] args) {
        Player p1 = new Player("Alice", 'X');
        Player p2 = new Player("Bob", 'O');
        
        // Creating a dynamic 3x3 board
        GameEngine engine = new GameEngine(p1, p2, 3);
        
        // Simulating a short game
        engine.playTurn(0, 0); // X
        engine.playTurn(1, 1); // O
        engine.playTurn(0, 1); // X
        engine.playTurn(1, 2); // O
        engine.playTurn(0, 2); // X Wins
    }
}