import static org.junit.Assert.*;
import org.junit.*;

/*
 Use JUnit to verify that initial X and Y positions given by user have the correct type, & both are within chess board limits
Use JUnit to verify that final X and Y positions given by user have the correct type, both are within chess board limits, &
that Initial and final positions are different 
 */
public class chessboardTest extends chessboard {

    public boolean isValidRow(String input){
        return input.matches("[A-H]");
    }

    public boolean isValidCol(String input){
        return input.matches("[1-8]");
    }

    @org.junit.jupiter.api.Test
    void testGetInitialCol() {
        assertEquals(true, isValidCol("3"));
        assertEquals(false, isValidCol("-"));
    }
    
    @org.junit.jupiter.api.Test
    void testGetInitialRow() {
        assertEquals(false, isValidRow("-"));
        assertEquals(true, isValidRow("A"));
    }

    @org.junit.jupiter.api.Test
    void testGetFinalRow() {
        assertEquals(true, isValidRow("H"));
        assertEquals(false, isValidRow("1"));
    }
    
    @org.junit.jupiter.api.Test
    void testGetFinalCol() {
        assertEquals(true, isValidCol("4"));
        assertEquals(false, isValidCol(""));
    }
}
