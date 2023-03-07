import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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
        assertEquals(row, queen.getRow());
        assertEquals(col, queen.getColumn());
        assertEquals(color, queen.getColorOfPiece());
    }

    //Calcular los posibles movimientos de la reina blanca
    @Test
    public void testCalculatePossibleMoves(){
        ChessGameBoard board = new ChessGameBoard();
        Queen queen = new Queen (board,0,0,ChessGamePiece.WHITE);
        ArrayList<String>expectedMoves = new ArrayList<>();

        //Posibles movimientos de la reina
        expectedMoves.add("1,1");
        expectedMoves.add("1,0");
        expectedMoves.add("0,1");

        assertEquals(expectedMoves, queen.calculatePossibleMoves(board));
    }

    //Crear la imagen de la pieza de la reina
    @Test
    public void testCreateImageByPiece(){
        ChessGameBoard board = new ChessGameBoard();

        Queen whiteQueen = new Queen(board,0,0,ChessGamePiece.WHITE);
        Queen blackQueen = new Queen(board,0,0,ChessGamePiece.BLACK);
        Queen unassigned = new Queen(board,0,0,ChessGamePiece.UNASSIGNED);

        assertNotNull(ChessGamePiece.BLACK);
        assertNotNull(ChessGamePiece.WHITE);
        assertNotNull(ChessGamePiece.UNASSIGNED);

        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/WhiteQueen.gif",
                whiteQueen.createImageByPieceType().getDescription());
        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/BlackQueen.gif",
                blackQueen.createImageByPieceType().getDescription());
        assertEquals("file:/C:/Users/USUARIO/Desktop/RefactorChessMaster/Original.src/out/production/Original.src/chessImages/default-Unassigned.gif",
                unassigned.createImageByPieceType().getDescription());

    }

}