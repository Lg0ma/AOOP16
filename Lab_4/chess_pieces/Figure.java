// package
package chess_pieces;
// import packages
import enums.*;
import board.*;

// ANDRE MELENDEZ
// initialize an abstract class
public abstract class Figure implements IntFigure {

    // initialize protected attributes
    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected enums.chess_piece_columns column;
    protected int row;
    protected ChessBoard chessBoard = new ChessBoard();

    // constructor with no attributes
    protected Figure() {
        // default attributes
        this.piece_name = chess_piece_type.PAWN;
        this.color = chess_piece_color.WHITE;
        this.column = enums.chess_piece_columns.A;
        this.row = 0;
    }
    
    // constructor with attributes
    protected Figure(chess_piece_type name, chess_piece_color color2, enums.chess_piece_columns column, int row) {
        // set attributes
        this.piece_name = name;
        this.color = color2;
        this.column = column;
        this.row = row;
    }

    // method to get the type of object
    public chess_piece_type getType(){
        return piece_name;
    }
    
    // method to get color of object
    public chess_piece_color getColor() {
        return color;
    }

    // method to get column of object
    public enums.chess_piece_columns getColumn() {
        return column;
    }

    // method to get row of object
    public int getRow() {
        return row;
    }

    // setter method for column
    public void setColumn(enums.chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    // setter method for row
    public void setRow(int y_coord) {
        this.row = y_coord;
    }
    
    // method to create a chess piece
    public static Figure create_chess_piece(chess_piece_type type, chess_piece_color color, enums.chess_piece_columns column, int row) {
        // switch case block to create a specific chess piece
        switch (type) {
            // type bishop
            case BISHOP:
                return new Bishop(type, color, column, row);
            // type king
            case KING:
                return new King(type, color, column, row);
            // type knight
            case KNIGHT:
                return new Knight(type, color, column, row);
            // type pawn
            case PAWN:
                return new Pawn(type, color, column, row);
            // type queen
            case QUEEN:
                return new Queen(type, color, column, row);
            // type rook
            case ROOK:
                return new Rook(type, color, column, row);
            // otherwise the type is invalid
            default:
                throw new IllegalArgumentException("Invalid chess piece type");
        }
    }

    // method that prints the current attributes of the Chess Piece
    public String toString() {
        return getType() + " (" + getColor() + ") current position: " + getColumn() + ", " + getRow();
    }

    // partially implement the moveTo method
    public Boolean moveTo(enums.chess_piece_columns X, int Y) {
        // check if the input is within range
        return chessBoard.verifyCoordinate(X, Y);
    }
}