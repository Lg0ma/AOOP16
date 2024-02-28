// initialize a Queen class that extends to Rook and implemented the IntBishop interface

import board.ChessBoard;
import board.IntChessBoard;
import enums.chess_piece_columns;

public class Queen extends Rook implements IntBishop {
        // empty constructor
        public Queen() {
            // default values
            super();
        }
    
        // constructor with parameters for each class field
        public Queen(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
            // set values
            super(name, color, x_coord, y_coord);
        }
    
        // method to verify its piece movement
        @Override
        public Boolean moveTo(chess_piece_columns column, int row) {
    
            // turn the enum into an int
            int new_column = column.ordinal() + 1;
            int old_column = getColumn().ordinal() + 1;
            // find the current y position of the rook
            int old_row = getRow();
            // check if the target position is within the Chess Board boundaries
            IntChessBoard chessBoard = new ChessBoard(); // create an instance of ChessBoard
            // if statement to check if the user input is within the chessboard range
            if (!chessBoard.verifyCoordinate(column, row)) {
                // print statement and return
                System.out.println("Input is out of range ... ");
                return false;
            }
            // find the absolute difference in the x and y coordinates
            int deltax = Math.abs(old_column - new_column);
            int deltay = Math.abs(old_row - row);
            // initialize a Boolean for rook validation
            Boolean rook_movement = super.verifyCoordinate(column, row);
            // initialize a Boolean for bishop validation
            Boolean bishop_movement = false;
            // check if the move is a valid diagonal
            if ((deltax == deltay) && (deltax > 0)) {
                // true if the move is valid
                bishop_movement = true;
            }
            // if both Booleans are true
            if (rook_movement == true || bishop_movement == true) {
                // set the valid new coordinates to the object
                setColumn(column);
                setRow(row);
                // return true
                return true;
            }
            // return false if the move is not valid
            return false;
        }
    }
    // implement a method
    public Boolean moveToBishop(chess_piece_columns X, int Y) {
        return false;
    }