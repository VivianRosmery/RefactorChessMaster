import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    //Prueba unitaria 2
    //Crear la pieza del rey negro
    @Test
    public void testKingConstructor() {
        ChessGameBoard board = new ChessGameBoard();
        int row = 0;  //ubicación de la fila del rey
        int col = 3;  //ubicación de la columna del rey
        int color = ChessGamePiece.BLACK; //Color de la pieza rey

        King king = new King(board, row, col, color);
        assertEquals(row, king.getRow());
        assertEquals(col, king.getColumn());
        assertEquals(color, king.getColorOfPiece());
    }
    @Test
    public void testKingConstructor_AF() {
            ChessGameBoard board2 = new ChessGameBoard();
            ChessGameBoard board3 = new ChessGameBoard();

            int row2 = 0;  //ubicación de la fila del rey
            int col2 = 3;  //ubicación de la columna del rey
            int color2 = ChessGamePiece.BLACK; //Color de la pieza rey

        // Otro modo
        assertFalse(new King(board2, 0, 3, ChessGamePiece.BLACK) == new King(board3, row2, col2, color2));
    }

}

