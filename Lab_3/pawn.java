/** Class for the chesspiece pawn that extends from the chessPiece class
 * @author Andre Melendez
 * @version 1.0 (04/06/24)
 */
public class pawn extends chessPiece {

    /** Chesspiece name/type that is in the chess_piece_type
     */
    private chess_piece_type piece_name;

    /** Chesspiece color that is in the chess_piece_color enum
     */
    private chess_piece_color color;

    /** Chesspiece column that is in the chess_piece_columns enum
     */
    private chess_piece_columns column;

    /** Chesspiece row that is an integer
     */
    private int row;

    /**
     * A default constructor for the pawn object
     * sets the piece to a white pawn in coords A,0
     */
    public pawn() {
        // default values
        this.piece_name = chess_piece_type.PAWN;
        this.color = chess_piece_color.WHITE;
        this.column = chess_piece_columns.A; 
        this.row = 0;
    }

    /**
     * A constructor for the pawn object that sets the
     * initial piece info like name , color, x_coord, and y_coord
     * @param name a chess piece name that is in the chess_piece_type enum
     * @param color a chess piece color that is in the chess_piece_color enum
     * @param x_coord the initial x coordinate for the piece that is in the chess_piece_columns enum
     * @param y_coord the initial y coordinate for the piece that is an int
     */
    public pawn(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        this.piece_name = name;
        this.color = color;
        this.column = x_coord;
        this.row = y_coord;
    }


    /**
     * getter method that returns the chess piece type
     * @return piece_name a chess piece type that is in the chess_piece_type enum
     */
    public chess_piece_type getType(){
        return piece_name;
    }


    /**
     * getter method that returns the chess piece color
     * @return color a chess piece color that is in the chess_piece_color enum
     */
    public chess_piece_color getColor() {
        return color;
    }


    /**
     * getter method that returns the chess piece column
     * @return column a column that is in the chess_piece_columns enum
     */
    public chess_piece_columns getColumn() {
        return column;
    }


    /**
     * getter method that returns the chess piece row
     * @return row an int variable that represents the chess piece row
     */
    public int getRow() {
        return row;
    }


    /** setter method for column data in a chesspiece
     * sets the column data to the data passed in the x_coord param
     * @param x_coord
     */
    public void setColumn(chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    /** setter method for row data in a chesspiece
     * sets the row data to the data passed in the y_coord param
     * @param y_coord
     */
    public void setRow(int y_coord) {
        this.row = y_coord;
    }

    /**
     * Overrides the verifyTarget method in the chessPiece class.
     * Verification method that verifies if a piece can move to a new position according to its ruleset
     * and for the pawn class according to its color and if its within the bounds of the chessboard
     * @see chessPiece
     * @param column a new column which the piece will try to move to
     * @param row a new row which the piece will try to move to
     * @return true if the piece is able to move to the new position passed in according to its set rules, false otherwise
     */
    @Override
    public Boolean verifyTarget(chess_piece_columns column, int row) {

        // initialize a boolean
        boolean valid = true;
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // if statement to check if the user input is within the chessboard range
        if (!chessboard.withinChessboard(column, row)) {
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
