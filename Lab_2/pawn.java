// package
package Lab_2;
// import
import Lab_2.Lab_2_Chess_and_Objects.chess_piece_type;

// ANDRE MELENDEZ
// pawn class
class pawn {

    // initialize a private chess_piece_type for the name
    private chess_piece_type piece_name;
    // initialize a private String for the color
    private String color;
    // initialize a private int for the x-coord
    private char column;
    // initialize a private int for the y-coord
    private int row;
    
    // empty constructor
    public pawn() {
        // default values
        this.piece_name = chess_piece_type.PAWN;
        this.color = "Unknown";
        this.column = ' ';
        this.row = 0;
    }

    // constructor with parameters for each class field
    public pawn(chess_piece_type name, String color, char x_coord, int y_coord) {
        // set values
        this.piece_name = name;
        this.color = color;
        this.column = x_coord;
        this.row = y_coord;
    }

    // getter method for color
    public String getColor() {
        return color;
    }

    // getter method for column
    public char getColumn() {
        return column;
    }

    // getter method for row
    public int getRow() {
        return row;
    }

    // setter method for column
    public void setColumn(char x_coord) {
        this.column = x_coord;
    }

    // setter method for row
    public void setRow(int y_coord) {
        this.row = y_coord;
    }

    // method to verify its piece movement 
    public Boolean verifyTarget(char column, int row) {
        // initialize a boolean
        boolean valid = true;
        // turn the char into an int
        int new_column = Lab_2_Chess_and_Objects.turn_x_to_int(column);
        int old_column = Lab_2_Chess_and_Objects.turn_x_to_int(getColumn());
        // if statement to check if the user input is within the chessboard range
        if (!chessboard.withinChessboard(new_column, row)) {
            // print statement and return
            System.out.println("Input is out of range ... ");
            valid = false;
        }
        // if the team of the pawn is white
        if (getColor().equalsIgnoreCase("white")) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if ((old_column != new_column) || ((getRow() + 1) != row)) {
                // set valid to false if the move is not valid'
                valid = false;
            }
        }
        // if the team of the pawn is black
        if (getColor().equalsIgnoreCase("black")) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if (old_column != new_column || (getRow() - 1) != row) {
                // set valid to false if the move is not valid
                valid = false; 
            }
        }
        // if valid is true up to this point
        if (valid == true) {
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
        }
        // return the validity of the move
        return valid; 
    }
}