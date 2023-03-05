import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.GridLayout;
import javax.swing.*;

class ChessGraveyardTest {

    @Test
    void testAddPiece() {
        ChessGamePiece piece = new Pawn(new ChessGameBoard(), 0, 0, ChessGamePiece.WHITE);
        ChessGraveyard graveyard = new ChessGraveyard("Graveyard");

        graveyard.addPiece(piece);

        assertEquals(2, graveyard.getComponentCount());
        assertTrue(graveyard.getComponent(0) instanceof JLabel);
    }

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
