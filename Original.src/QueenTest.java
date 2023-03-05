import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QueenTest {
    //Prueba unitaria 1
    //Crear la pieza de la reina blanca
    @Test
    public void testQueenConstructor(){
        ChessGameBoard board = new ChessGameBoard();

        int row = 7;  //ubicación de la fila de la reina
        int col = 4;  //ubicación de la columna de la reina
        int color = ChessGamePiece.WHITE; //Color de la pieza reina

        Queen queen = new Queen(board, row, col, color);
      //assertEquals(board, queen.getBoard());
        assertEquals(row, queen.getRow());
        assertEquals(col, queen.getColumn());
        assertEquals(color, queen.getColorOfPiece());
    }
}