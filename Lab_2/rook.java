// package
package Lab_2;
// import
import Lab_2.Lab_2_Chess_and_Objects.chess_piece_type;

// ANDRE MELENDEZ
// rook class
class rook {
    
    // initialize a private chess_piece_type for the name
    private chess_piece_type piece_name;
    // initialize a private String for the color
    private String color;
    // initialize a private int for the x-coord
    private char column;
    // initialize a private int for the y-coord
    private int row;

    // empty constructor
    public rook() {
        // default values
        this.piece_name = chess_piece_type.ROOK;
        this.color = "Unknown";
        this.column = ' ';
        this.row = 0;
    }

    // constructor with parameters for each class field
    public rook(chess_piece_type name, String color, char x_coord, int y_coord) {
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
        // turn the char into an int
        int new_column = Lab_2_Chess_and_Objects.turn_x_to_int(column);
        int old_column = Lab_2_Chess_and_Objects.turn_x_to_int(getColumn());
        // find the current y position of the rook
        int old_row = getRow();
        // if statement to check if the user input is within the chessboard range
        if (!chessboard.withinChessboard(new_column, row)) {
            // print statement and return
            System.out.println("Input is out of range ... ");
            return false;
        }
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // check if the move is valid along the y-axis or x-axis
        if (deltax == 0 && deltay != 0) {
            // return true if the move is valid along the y-axis
            setColumn(column);
            setRow(row);
            return true;
        } 
        else if (deltax != 0 && deltay == 0) {
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // return true if the move is valid along the x-axis
            return true;
        }
        // return false if the move is not valid
        return false;
    }
}