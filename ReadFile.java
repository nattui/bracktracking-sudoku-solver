import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Created by Nhat Nguyen on 12/26/2017
 *
 * @author  Nhat Nguyen
 * @version 1.0
 * @since   2017-12-26
 */
public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Hello World");
        Scanner input = new Scanner(new File("sudoku-puzzle.txt"));
        String sudoku = input.nextLine();
        System.out.println("Sudoku: " + sudoku);
        System.out.println("The length of the string is " + sudoku.length());

        // Should be replaced by assert
        if (sudoku.length() == 81) {
            System.out.println("Valid amount of characters");
        } else {
            System.out.println("Invalid amount of characters " + sudoku.length());
        }

        String grid = grid9x9(sudoku);
        System.out.println(grid);

        int[][] array = grid(sudoku);
        System.out.println(array[0][0] + " " + array[8][8] + " " + array[8][7]);
        System.out.println(Arrays.deepToString(array));

        solve(grid(sudoku), 0, 0);
    }

    /**
     * Formats the sudoku puzzle
     *
     * @param sudoku The sudoku puzzle
     * @return grid The string representation of sudoku puzzle
     */
    public static String grid9x9(String sudoku) {
        String grid = "";
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                if (rowIndex != 8) {
                    grid = grid + sudoku.substring((columnIndex * 9) + rowIndex,
                            (columnIndex * 9) + rowIndex + 1) + " ";
                } else {
                    grid = grid + sudoku.substring((columnIndex * 9) + rowIndex,
                            (columnIndex * 9) + rowIndex + 1) + System.lineSeparator();
                }
            }
        }
        return grid;
    }

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

    public static void solve(int[][] element, int columnIndex, int rowIndex) {
        // When the element is 1 to 9, move to next element
        if (element[columnIndex][rowIndex] != 0) {
            nextElement(element, columnIndex, rowIndex);
        } else {
            element[columnIndex][rowIndex]++;
            
        }
    }

    public static void nextElement(int[][] element, int columnIndex, int rowIndex) {
        if (rowIndex == 0) {
            solve(element, columnIndex++, 0);
        } else {
            solve(element, columnIndex, rowIndex++);
        }
    }
}
