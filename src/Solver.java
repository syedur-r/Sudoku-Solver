import java.util.*; // imports java.util utilities

public class Solver {

   static int tally = 0; // this integer keeps track of the number of operations required to solve the puzzle

   // backtracking algorithm
   static boolean backtrack(ArrayList<ArrayList<Cell>> grid) {
      HashMap<Integer, Integer> cellPosition = Grid.getEmptyCellPosition(grid); // stores the empty cell positions of the grid inside a hashmap, using the helper method
      int rowPos = cellPosition.get(0); // stores the row index inside an integer
      int colPos = cellPosition.get(1); // stores the column index inside an integer
      if (rowPos == -1) return true; // sets the base case of the recursion call by checking if the row is not empty e.g. == -1
      // cellPosition.get(1) can also be checked for the column index, but we only need only to check the rowPos to know that the colPos is also -1
      // if it doesn't then there are no more empty cells and the method will return true

      // traverses through possible numbers that may be valid for the puzzle - number starts from 1 and ends in 9, since 0 means empty
      for (int cell = 1; cell <= grid.size(); cell++) {
         if (Grid.cellIsValid(rowPos, colPos, cell)) { // checks if the has no duplicate numbers in the rows, columns and the sub-grids
            grid.get(rowPos).get(colPos).setCell(cell); // if the number is valid, it is assigned to the index in the grid
            /* /// RECURSION /// */
            if (backtrack(grid)) { // checks if the puzzle has a valid solution
               return true; // returns true if the condition is met
            } else {
               grid.get(rowPos).get(colPos).setCell(0); // resets the cell as 0 so backtracking can continue
               tally++; // increments the tally points for the number of operations
               // backtracking will continue until the base case is met
            }
         }
      }
      return false; // returns false if the sudoku puzzle is unsolvable
   }
}