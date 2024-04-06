/** Class for the chesspiece knight that extends from the chessPiece class
 * @author Luis Gomez
 * @version 1.0 (04/06/24)
 */
public class chessboard {
    
    /** Method that will check if a specified set of coordinates is withing the bounds of the chessboard
     * @param enum_column a new column which the piece will try to move to
     * @param row a new row which the piece will try to move to
     * @return true if the coordinates passed in are within the chessboard bounds, false otherwise
     */
    public static boolean withinChessboard(chess_piece_columns enum_column, int row) {
        int CONSTANT_MAX_ROW_COL = 8;
        int MIN_ROW_COL = 1;
        // convert enum to an int
        int column = enum_column.ordinal() + 1;
        if (column <= CONSTANT_MAX_ROW_COL && column >= MIN_ROW_COL) {
            if (row <= CONSTANT_MAX_ROW_COL && row >= MIN_ROW_COL) {
                return true;
            }
        }
        return false;
    }
}
