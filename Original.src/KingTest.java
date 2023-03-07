import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    // Calcular los posibles movimientos del rey negro
    @Test
    public void testCalculatePossibleMoves(){
        ChessGameBoard board = new ChessGameBoard();
        King king = new King (board,1,1,ChessGamePiece.BLACK);
        ArrayList<String> expectedMoves = new ArrayList<>();

        //Posibles movimientos del rey
        expectedMoves.add("2,0");
        expectedMoves.add("2,2");
        expectedMoves.add("2,1");

        assertEquals(expectedMoves, king.calculatePossibleMoves(board));
    }

    // Crear la imagen de la pieza deL Rey
    @Test
    public void testCreateImageByPiece(){
        ChessGameBoard board = new ChessGameBoard();

        King whiteQueen = new King(board,0,0,ChessGamePiece.WHITE);
        King blackQueen = new King(board,0,0,ChessGamePiece.BLACK);
        King unassigned = new King(board,0,0,ChessGamePiece.UNASSIGNED);

        assertNotNull(ChessGamePiece.BLACK);
        assertNotNull(ChessGamePiece.WHITE);
        assertNotNull(ChessGamePiece.UNASSIGNED);

        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/WhiteKing.gif",
                whiteQueen.createImageByPieceType().getDescription());
        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/BlackKing.gif",
                blackQueen.createImageByPieceType().getDescription());
        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/default-Unassigned.gif",
                unassigned.createImageByPieceType().getDescription());

    }
}

