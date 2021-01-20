public class Cell {

   private int cell; // creates a private instance of a cell
   private boolean isEditable; // creates a boolean to check whether a cell is editable or not

   // creates a constructor to set the class arguments, when passed as a parameter
   public Cell(int cell, boolean isEditable) {
      this.cell = cell; // sets the instance cell as the constructor cell
      this.isEditable = isEditable; // sets the instance isEditable as the constructor isEditable
   }

   // creates a getter method to retrieve a cell
   public int getCell() {
      return cell; // retrieves a cell
   }

   // creates a setter method to set the value of a cell
   public void setCell(int cell) {
      this.cell = cell; // sets the value of a cell
   }

   // getter method to check whether a cell is editable or not
   public boolean isEditable() {
      return isEditable; // returns the boolean to check whether a cell is editable or not
   }

   // setter method to assign a cell with true or false
   public void setEditable(boolean editable) {
      isEditable = editable; // assigns a true or false value to represent whether a cell is editable or not
   }
}