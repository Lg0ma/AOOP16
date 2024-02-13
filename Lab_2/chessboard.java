// package // changed to foler name Team16_Lab2, if folder name is different for you, make sure to change it
package Lab_2;

public class chessboard {
    // VARS?
    // MANDATORY METHOD
    public static boolean withinChessboard(int column, int row) { //NEEDS TO FIGURE OUT PARAMETER VAR TYPE, I think int
        int CONSTANT_MAX_ROW_COL = 8;
        int MIN_ROW_COL = 1;
        if (column <= CONSTANT_MAX_ROW_COL && column >= MIN_ROW_COL){
            if(row <= CONSTANT_MAX_ROW_COL && row >= MIN_ROW_COL){
                return true;
            }
        }
        return false;
        }
}

