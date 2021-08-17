package tictactoe;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] grid = new char[3][3];
    static int xCounter;
    static int oCounter;
    static int _Counter;
    static int coordinate1;
    static int coordinate2;
    static char move;

    public static void main(String[] args) {

        char[] inputArr = new char[]{'_', '_', '_', '_', '_', '_', '_', '_', '_'};
        _Counter = 9;
        generateGrid(inputArr);
        printGrid(grid);
        move = 'X';
        placeCoordinates();
    }

    private static void placeCoordinates() {
        System.out.print("Enter the coordinates: ");

        String input1 = scanner.next();
        String input2 = scanner.next();
        if ((input1.matches("\\d")) && (input2.matches("\\d"))) { // checks if the input consists of digit

            coordinate1 = Integer.parseInt(input1);
            coordinate2 = Integer.parseInt(input2);

            if (coordinate1 < 1 || coordinate1 > 3 || coordinate2 < 1 || coordinate2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                placeCoordinates();
            } else if (grid[coordinate1 - 1][coordinate2 - 1] == 'X' || grid[coordinate1 - 1][coordinate2 - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                placeCoordinates();
            } else {
                if (move == 'X') {
                    grid[coordinate1 - 1][coordinate2 - 1] = 'X';
                    xCounter++;
                    _Counter--;
                    move = 'O';
                } else {
                    grid[coordinate1 - 1][coordinate2 - 1] = 'O';
                    oCounter++;
                    _Counter--;
                    move = 'X';
                }
                printGrid(grid);
                calculateResult();
            }
        } else {
            System.out.println("You should enter numbers!");
            placeCoordinates();
        }
    }

    private static void generateGrid(char[] arr) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = arr[counter];
                counter++;
            }
        }
    }

    private static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean oWin(char[][] grid) {
        return (grid[0][0] == 'O' && grid[0][1] == 'O' && grid[0][2] == 'O')
                || (grid[1][0] == 'O' && grid[1][1] == 'O' && grid[1][2] == 'O')
                || (grid[2][0] == 'O' && grid[2][1] == 'O' && grid[2][2] == 'O')
                || (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O')
                || (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O')
                || (grid[0][0] == 'O' && grid[1][0] == 'O' && grid[2][0] == 'O')
                || (grid[0][1] == 'O' && grid[1][1] == 'O' && grid[2][1] == 'O')
                || (grid[0][2] == 'O' && grid[1][2] == 'O' && grid[2][2] == 'O');
    }

    private static boolean xWin(char[][] grid) {
        return (grid[0][0] == 'X' && grid[0][1] == 'X' && grid[0][2] == 'X')
                || (grid[1][0] == 'X' && grid[1][1] == 'X' && grid[1][2] == 'X')
                || (grid[2][0] == 'X' && grid[2][1] == 'X' && grid[2][2] == 'X')
                || (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X')
                || (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X')
                || (grid[0][0] == 'X' && grid[1][0] == 'X' && grid[2][0] == 'X')
                || (grid[0][1] == 'X' && grid[1][1] == 'X' && grid[2][1] == 'X')
                || (grid[0][2] == 'X' && grid[1][2] == 'X' && grid[2][2] == 'X');
    }

    private static void calculateResult() {
        if ((xWin(grid) && oWin(grid)) || (Math.abs(xCounter - oCounter) >= 2)) {
            System.out.println("Impossible");
        } else if (!xWin(grid) && !oWin(grid) && _Counter > 0) {
            // Game not finished, continue asking for coordinates
            placeCoordinates();
        } else if (!xWin(grid) && !oWin(grid)) {
            System.out.println("Draw");
        } else if (oWin(grid)) {
            System.out.println("O wins");
        } else if (xWin(grid)) {
            System.out.println("X wins");
        } else {
            System.out.println("Wrong input");
        }
    }
}
