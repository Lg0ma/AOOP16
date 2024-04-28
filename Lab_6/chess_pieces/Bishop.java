/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;

// Import necessary packages and classes
import board.ChessBoard;
import enums.*;

/**
 * Class representing a Bishop chess piece, it implements interface IntBishop.
 * <p>
 * Authors: Ivan Armenta, Andre Melendez, Luis Gomez
 */
public class Bishop implements IntBishop {

    // Attributes for Bishop piece
    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected enums.chess_piece_columns column;
    protected int row;
    protected ChessBoard chessBoard = new ChessBoard();

    /**
     * Default constructor initializing the Bishop with default values.
     */
    public Bishop() {
        this.piece_name = chess_piece_type.BISHOP;
        this.color = chess_piece_color.WHITE;
        this.column = enums.chess_piece_columns.A;
        this.row = 0;
    }

/**
     * Constructor with parameters to initialize the Bishop with specified values.
     *
     * @param name   The type of the Bishop piece.
     * @param color  The color of the Bishop piece.
     * @param x_coord The column coordinate of the Bishop piece.
     * @param y_coord The row coordinate of the Bishop piece.
     */
    public Bishop(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        this.piece_name = name;
        this.color = color;
        this.column = x_coord;
        this.row = y_coord;
    }

    // Getter and setter methods for Bishop attributes...

    /**
     * Method to obtain the type of the Bishop piece.
     *
     * @return The type of the Bishop piece.
     */
    public chess_piece_type getType(){
        return piece_name;
    }

    /**
     * Method to obtain the color of the Bishop piece
     * 
     * @return The color of the Bishop piece
     */
    public chess_piece_color getColor() {
        return color;
    }

    /**
     * Method to obtain the column of the Bishop piece
     * 
     * @return The column of the Bishop piece
     */   
    public enums.chess_piece_columns getColumn() {
        return column;
    }

/**
     * Method to obtain the row of the Bishop piece
     * 
     * @return The row of the Bishop piece
     */  
    public int getRow() {
        return row;
    }

    /**
     * Method that sets the column of the Bishop piece
     * 
     * @param x_coord Column coordinate of Bishop piece
     */
    public void setColumn(enums.chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    /**
     * Method that sets the row of the Bishop piece
     * 
     * @param x_coord Row coordinate of Bishop piece
     */    
    public void setRow(int y_coord) {
        this.row = y_coord;
    }


    /**
     * Method that returns a string representation of the Bishop piece.
     *
     * @return A string containing the Bishop's type, color, and position.
     */    
    public String toString() {
        return getType() + " (" + getColor() + ") current position: " + getColumn() + ", " + getRow();
    }

    /**
     * Method that returns a boolean value that represents if the Bishop can move to that position following his movement rules
     * 
     * @param column Column which the bishop is going to move to.
     * @param row Row which the bishop is going to move to.
     * @return true if the movement is valid and the Bishop is successfully moved, false otherwise. 
     */
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        //get current piece position
        chess_piece_columns old_column = getColumn();
        int old_row = getRow();
        // if the default method return true
        if(moveToBishop( old_column, old_row, column, row) == true) {
            // update the attributes
            setColumn(column);
            setRow(row);
            return true;
        }
        // If movement is invalid, return false
        return false;
    }
}