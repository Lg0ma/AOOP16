/** Class for the chesspiece queen that extends from the rook class
 *  @author Andre Melendez
 *  @version 1.0 (04/06/24)
 */
public class queen extends rook {

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
     * A default constructor for the queen object
     * sets the piece to a white queen in coords A,0
     */
    public queen() {
        // default values
        this.piece_name = chess_piece_type.QUEEN;
        this.color = chess_piece_color.WHITE;
        this.column = chess_piece_columns.A;
        this.row = 0;
    }

    /**
     * A constructor for the queen object that sets the
     * initial piece info like name , color, x_coord, and y_coord
     * @param name a chess piece name that is in the chess_piece_type enum
     * @param color a chess piece color that is in the chess_piece_color enum
     * @param x_coord the initial x coordinate for the piece that is in the chess_piece_columns enum
     * @param y_coord the initial y coordinate for the piece that is an int
     */
    public queen(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
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
     * and if its within the bounds of the chessboard
     * @see chessPiece
     * @param column a new column which the piece will try to move to
     * @param row a new row which the piece will try to move to
     * @return true if the piece is able to move to the new position passed in according to its set rules, false otherwise
     */
    @Override
    public Boolean verifyTarget(chess_piece_columns column, int row) {

        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // find the current y position of the rook
        int old_row = getRow();
        // if statement to check if the user input is within the chessboard range
        if (!chessboard.withinChessboard(column, row)) {
            // print statement and return
            System.out.println("Input is out of range ... ");
            return false;
        }
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // initialize a Boolean for rook validation
        Boolean rook_movement = super.verifyTarget(column, row);
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
