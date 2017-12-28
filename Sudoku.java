import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("sudoku-puzzle.txt"));
        String sudoku = input.nextLine();

        String grid = grid9x9(sudoku);
        System.out.println(grid);

        Long startTime = System.currentTimeMillis();
        
        int[][] matrix = grid(sudoku);

        solve(matrix);
        
        Long endTime = System.currentTimeMillis() - startTime;
        System.out.println("The time: " + endTime + " ms");

        System.out.print("The Solution: " + System.lineSeparator());
        for (int[] row : matrix)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    // Formats the sudoku puzzle
    public static String grid9x9(String sudoku) {
        String grid = "";
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                String nextElement = sudoku.substring((columnIndex * 9) + rowIndex, (columnIndex * 9) + rowIndex + 1);
                if (rowIndex != 8) {
                    grid = grid + nextElement + "  ";
                } else {
                    grid = grid + nextElement + System.lineSeparator();
                }
            }
        }
        return grid;
    }

    // Converts sudoku string to 2D int array
    public static int[][] grid(String sudoku) {
        int[][] values = new int[9][9];
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                String element = sudoku.substring((columnIndex * 9) + rowIndex, (columnIndex * 9) + rowIndex + 1);
                if (element.equals(".")) {
                    values[columnIndex][rowIndex] = 0;
                } else {
                    values[columnIndex][rowIndex] = Integer.valueOf(element);
                }
            }
        }
        return values;
    }

    public static boolean solve(int[][] matrix) {
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                if (matrix[columnIndex][rowIndex] > 0) {
                    continue;
                }
                for (int k=1; k<=9; k++) {
                    matrix[columnIndex][rowIndex] = matrix[columnIndex][rowIndex] + k;
                    if (checkColumn(matrix, columnIndex, rowIndex) && checkRow(matrix, columnIndex, rowIndex) && solve(matrix)) {
                        return true;
                    }
                    matrix[columnIndex][rowIndex] = 0;
                }
                return false;
            }
        }
    return true;
    }

    public static boolean checkColumn(int[][] matrix, int columnIndex, int rowIndex) {
        for (int targetColumn = 0; targetColumn < 9 ; targetColumn++) {
            if (columnIndex == targetColumn) {
                continue;
            }
            if (matrix[columnIndex][rowIndex] == matrix[targetColumn][rowIndex]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRow(int[][] matrix, int columnIndex, int rowIndex) {
        for (int targetRow = 0; targetRow < 9 ; targetRow++) {
            if (rowIndex == targetRow) {
                continue;
            }
            if (matrix[columnIndex][rowIndex] == matrix[columnIndex][targetRow]) {
                return false;
            }
        }
        return true;
    }

    // Work on progress
    public static boolean checkBlock(int[][] matrix, int columnIndex, int rowIndex) {
        for (int yBlock = 0; yBlock < 3; yBlock++) {
            for (int xBlock = 0; xBlock < 3; xBlock++) {
                int x = columnIndex / 3 * 3 + yBlock;
                int y = rowIndex / 3 * 3+ xBlock;
            }

        }
        return true;
    }

}
