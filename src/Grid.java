import java.util.*; // imports java.util utilities

public class Grid {

   // colours to represent whether a cell is editable or not
   final String WHITE = "\u001B[0m"; // outputs text in white - grid colour
   final String RED = "\u001B[31m"; // outputs text in red - not editable
   final String GREEN = "\u001B[32m"; // outputs text in green - editable

   private static ArrayList<ArrayList<Cell>> grid; // creates a private instance of a 2D arraylist of Cells
   private String cellColour; // creates a private instance to set the colour of the cells

   // constructor for the Grid class
   public Grid(int[][] grid) {
      Grid.grid = new ArrayList<>(); // initialises the 2d arraylist
      this.cellColour = ""; // initialises the cellColour as an empty string

      for (int[] cells : grid) { // iterates through the rows of the grid
         ArrayList<Cell> row = new ArrayList<>(); // creates a row to store a collection of cells
         for (int cellRows : cells) { // iterates through the rows of the 2D grid
            Cell cell; // creates a single cell
            cell = cellRows == 0 ? new Cell(0, true) : new Cell(cellRows, false); // assigns a cell with values based on conditions
            row.add(cell); // adds the cell onto the row arraylist
         }
         Grid.getGrid().add(row); // adds the row arraylist onto the grid, to form a 2D array of cells
      }
   }

   // getter method for the 2D arraylist
   public static ArrayList<ArrayList<Cell>> getGrid() {
      return grid; // returns the 2D arraylist
   }

   // setter method for the 2D arraylist
   public void setGrid(ArrayList<ArrayList<Cell>> grid) {
      Grid.grid = grid; // assigns the grid with cells
   }

   // getter method for to get the colour of a cell
   public String getCellColour() {
      return cellColour; // gets the colour of a cell
   }

   // setter method for to set the colour of a cell
   public void setCellColour(String cellColour) {
      this.cellColour = cellColour; // sets the colour of a cell
   }

   // helper method to get the position of an empty cell
   // if the empty cell index contains a -1 in either the row or column, it means there are no empty cells left in the grid
   public static HashMap<Integer, Integer> getEmptyCellPosition(ArrayList<ArrayList<Cell>> grid) {
      HashMap<Integer, Integer> cellPosition = new HashMap<>(); // creates a new hashmap to store the empty cell indexes
      for (int i = 0; i < 2; i++) cellPosition.put(i, -1); // fills the hashmap with -1

      for (int i = 0; i < grid.size(); i++) { // traverses through the rows of the grid
         for (int j = 0; j < grid.size(); j++) { // traverses through the columns of the grid
            if (grid.get(i).get(j).getCell() == 0) { // checks if the cell position is empty
               cellPosition.put(0, i); // if the cell position is empty, its row index is stored in the first index of cellPosition
               cellPosition.put(1, j); // if the cell position is empty, its column index is stored in the second index of cellPosition
               return cellPosition; // the cell position containing the row and column index will be returned
            }
         }
      }
      return cellPosition; // otherwise the cell positions with the values (-1,-1) will be returned
   }

   // method to check if there are any duplicated cells in the rows
   public static boolean rowDuplicates(int i, int cell) {
      for (int j = 0; j < getGrid().size(); j++) { // iterates through the cells
         if (getGrid().get(i).get(j).getCell() == cell) { // checks if the cells rows already contains the number that is being validated
            return true; // if they do, the method will return true to show that there are duplicates
         }
      }
      return false; // otherwise, the method will return false to show that there are no duplicate values in the rows
   }

   // method to check if there are any duplicated cells in the columns
   public static boolean columnDuplicates(int j, int cell) {
      for (int i = 0; i < getGrid().size(); i++) { // iterates through the cells
         if (getGrid().get(i).get(j).getCell() == cell) { // checks if the cells columns already contains the number that is being validated
            return true; // if they do, the method will return true to show that there are duplicate values in the rows
         }
      }
      return false; // otherwise, the method will return false to show that there are no duplicates values in the columns
   }

   // method to check if there are any duplicated cells in the inner 3x3 sub-grids
   public static boolean innerGridDuplicates(int i, int j, int cell) {
      int squareRoot = (int)Math.sqrt(getGrid().size()); // gets the square root of the length of the grid
      int subRows = i - i % squareRoot; // sets the index of the set of 3 rows
      int subCols = j - j % squareRoot; // sets the index of the set of 3 columns

      for (int row = subRows; row < subRows + squareRoot; row++) { // iterates through the set of 3 rows
         for (int col = subCols; col < subCols + squareRoot; col++) { // iterates through the set of 3 columns
            if (getGrid().get(row).get(col).getCell() == cell) { // checks if the sub-grid indexes already contain the number that is being validated
               return true; // if they do, the method will return true to show that there are duplicate values in the inner 3x3 sub-grids
            }
         }
      }
      return false; // otherwise, the method will return false to show that there are no duplicate values in the inner 3x3 sub-grids
   }

   // method to check if all constraints are met to give a valid number
   public static boolean cellIsValid(int i, int j, int cell) {
      return (!rowDuplicates(i, cell) && // returns true if there are no duplicates in the rows
              !columnDuplicates(j, cell) && // returns true if there are no duplicates in the columns
              !innerGridDuplicates(i, j, cell)); // returns true if there are no duplicates in the 3x3 sub-grids
   }

   // method to display the sudoku grid to the console
   public String toString() {
      StringBuilder visualise = new StringBuilder(); // string builder is the ideal object to use to build a sequence of strings for the sudoku output
      for (int i = 0; i < getGrid().size(); i++) { // iterates through the rows of the grid
         visualise.append("\n");
         if(i % 3 == 0) visualise.append(" +-----------+-----------+----------+\n"); // checks each row of 3, and outputs horizontal lines every 3 rows
         for (int j = 0; j < getGrid().size(); j++) { // iterates through the columns
            if (j % 3 == 0) visualise.append(" | "); // checks each column of 3, and outputs vertical lines every 3 columns
            String cell = String.valueOf(getGrid().get(i).get(j).getCell() == 0 ? " " : getGrid().get(i).get(j).getCell()); // gets a cell located in the grid, and converts it to a string
            // sets the cell colour based on a condition - if the cell is editable its colour will be green, otherwise its colour will be red
            setCellColour(getGrid().get(i).get(j).isEditable() ? GREEN + " " + cell + " " + WHITE : RED + " " + cell + " " + WHITE);
            visualise.append(getCellColour()); // outputs the cells along with their colours
         }
         visualise.append("|"); // closes the column of the grid with a vertical line
      }
      visualise.append("\n +-----------+-----------+----------+\n"); // closes the row of the grid with a horizontal line
      return visualise.toString(); // returns the visualise string builder as a string
   }
}