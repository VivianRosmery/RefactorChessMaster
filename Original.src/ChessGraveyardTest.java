import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

class ChessGraveyardTest {
    // Prueba unitaria 3
    // Verificar si la piezas se agregan correctamente
    // al cementeerio de piezas
    @Test
    void testAddPiece() {
        ChessGamePiece piece = new Pawn(new ChessGameBoard(), 0, 0, ChessGamePiece.WHITE);
        ChessGraveyard graveyard = new ChessGraveyard("Graveyard");

        graveyard.addPiece(piece);

        assertEquals(2, graveyard.getComponentCount());
        assertTrue(graveyard.getComponent(0) instanceof JLabel);
    }

    //Verificar si la piezas se eliminan correctamente
    // del cementeerio de piezas
    @Test
    void testClearGraveyard() {
        ChessGamePiece piece = new Pawn(new ChessGameBoard(), 0, 0, ChessGamePiece.WHITE);
        ChessGraveyard graveyard = new ChessGraveyard("Graveyard");

        graveyard.addPiece(piece);
        graveyard.clearGraveyard();

        assertEquals(1, graveyard.getComponentCount());
        assertTrue(graveyard.getComponent(0) instanceof JLabel);
        assertEquals("Graveyard", ((JLabel) graveyard.getComponent(0)).getText());
    }
}
