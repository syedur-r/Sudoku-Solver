import java.io.*; // imports java.io utilities
import java.util.*; // imports java.util utilities

public class Main {

   // method to load the puzzle from the extension.txt file
   public static int[][] loadSudokuGrid() {
      File sudokuData = new File("extensionPuzzle.txt"); // loads the sudoku grid from the text file
      int[][] grid = new int[9][9]; // creates an empty 9x9 grid

      try {
         Scanner scanner = new Scanner(sudokuData); // creates a scanner class with the sudokuData file as the input
         while (scanner.hasNextLine()) { // checks each line of the file using a while loop
            for (int i = 0; i < grid.length; i++) { // iterates through the empty 2D array
               String row = scanner.nextLine().trim(); // each row of input represents a one row of cells
               String[] cells = row.split(","); // each row in cells represents an collection of cells
               for (int j = 0; j < cells.length; j++) { // iterates through the cells
                  grid[i][j] = Integer.parseInt(cells[j]); // assigns the cells as integers onto the 2D array, to form a 9x9 sudoku grid
               }
            }
         }
         scanner.close(); // closes the scanner
      } catch (FileNotFoundException e) { // handles the exception, so it fails gracefully
         System.out.println("Sudoku grid cannot be found"); // outputs the exception error to the console
      }
      return grid; // returns the sudoku grid
   }

   // main method
   public static void main(String[] args) {
      Grid sudoku = new Grid(loadSudokuGrid()); // creates an instance of the grid class to get the sudoku grid
      System.out.println(sudoku.toString()); // outputs the unsolved sudoku grid, with empty cells and pre-filled numbers

      // checks if the sudoku puzzle has been solved without any errors
      if (!Solver.backtrack(Grid.getGrid())) {
         System.out.println(sudoku.RED + "Sudoku puzzle is invalid!"); // if the condition has not been met, an error message is displayed
      } else {
         System.out.println(sudoku.WHITE + " Solved after " + Solver.tally + " iterations"); // outputs the number of iterations
         System.out.println(sudoku.toString()); // outputs the solved sudoku grid, with all cells filled
      }
   }
}