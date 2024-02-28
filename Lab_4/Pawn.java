// package 
/* */
// imports 
import enums.chess_piece_color;
import enums.chess_piece_columns;
import enums.chess_piece_type;
import board.ChessBoard;
import board.IntChessBoard;

// ANDRE MELENDEZ
// Pawn class that extends to Figure
public class Pawn extends Figure {
    // empty constructor
    public Pawn() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public Pawn(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        super(name, color, x_coord, y_coord);
    }

    // method to verify its piece movement
    @Override
    public Boolean moveTo(chess_piece_columns column, int row) {

        // initialize a boolean
        boolean valid = true;
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // check if the target position is within the Chess Board boundaries
        IntChessBoard chessBoard = new ChessBoard(); // create an instance of ChessBoard
        // if statement to check if the user input is within the chessboard range
        if (!chessBoard.verifyCoordinate(column, row)) {
            // print statement and return
            System.out.println("Input is out of range ... ");
            valid = false;
        }
        // if the team of the pawn is white
        if (getColor().ordinal() == 1) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if ((old_column != new_column) || ((getRow() + 1) != row)) {
                // set valid to false if the move is not valid'
                valid = false;
            }
        }
        // if the team of the pawn is black
        if (getColor().ordinal() == 0) {
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
    // method that prints the current coordinates of the Chess Piece
    public String toString() {

    }