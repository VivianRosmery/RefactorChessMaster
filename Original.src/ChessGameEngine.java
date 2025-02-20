import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
// -------------------------------------------------------------------------
/**
 * This is the backend behind the Chess game. Handles the turn-based aspects of
 * the game, click events, and determines win/lose conditions.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessGameEngine {
    private ChessGamePiece currentPiece;
    private boolean firstClick;
    private int currentPlayer;
    private ChessGameBoard board;
    private King king1;
    private King king2;
    // ----------------------------------------------------------

    /**
     * Create a new ChessGameEngine object. Accepts a fully-created
     * ChessGameBoard. (i.e. all components rendered)
     *
     * @param board the reference ChessGameBoard
     */
    public ChessGameEngine(ChessGameBoard board) {
        firstClick = true;
        currentPlayer = 1;
        this.board = board;
        this.king1 = (King) board.getCell(7, 3).getPieceOnSquare();
        this.king2 = (King) board.getCell(0, 3).getPieceOnSquare();
        ((ChessPanel) board.getParent()).getGameLog().clearLog();
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!");
    }
    // ----------------------------------------------------------

    /**
     * Resets the game to its original state.
     */
    public void reset() {
        firstClick = true;
        currentPlayer = 1;
        ((ChessPanel) board.getParent()).getGraveyard(1).clearGraveyard();
        ((ChessPanel) board.getParent()).getGraveyard(2).clearGraveyard();
        ((ChessPanel) board.getParent()).getGameBoard().initializeBoard();
        ((ChessPanel) board.getParent()).revalidate();
        this.king1 = (King) board.getCell(7, 3).getPieceOnSquare();
        this.king2 = (King) board.getCell(0, 3).getPieceOnSquare();
        ((ChessPanel) board.getParent()).getGameLog().clearLog();
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!");
    }

    /**
     * Switches the turn to be the next player's turn.
     */
    private void nextTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "It is now Player " + currentPlayer + "'s turn.");
    }
    // ----------------------------------------------------------

    /**
     * Gets the current player. Used for determining the turn.
     *
     * @return int the current player (1 or 2)
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Determines if the requested player has legal moves.
     *
     * @param playerNum the player to check
     * @return boolean true if the player does have legal moves, false otherwise
     */
    public boolean playerHasLegalMoves(int playerNum) {
        ArrayList<ChessGamePiece> pieces;
        if (playerNum == 1) {
            pieces = board.getAllWhitePieces();
        } else if (playerNum == 2) {
            pieces = board.getAllBlackPieces();
        } else {
            return false;
        }
        for (ChessGamePiece currPiece : pieces) {
            if (currPiece.hasLegalMoves(board)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the last-clicked piece is a valid piece (i.e. if it is
     * the correct color and if the user actually clicked ON a piece.)
     *
     * @return boolean true if the piece is valid, false otherwise
     */
    /*private boolean selectedPieceIsValid() {
        if (currentPiece == null) // user tried to select an empty square
        {
            return false;
        }
        if (currentPlayer == 2) // black player
        {
            if (currentPiece.getColorOfPiece() == ChessGamePiece.BLACK) {
                return true;
            }
            return false;
        } else
        // white player
        {
            if (currentPiece.getColorOfPiece() == ChessGamePiece.WHITE) {
                return true;
            }
            return false;
        }
    }*/
    //Tercera Refactorización de Codigo Critico - Segundo Avance
    //Se usa los patrones de Singleton, Brigde y Builder
    private boolean selectedPieceIsValid() {
        if (currentPiece == null) {
            return false;
        } else if (currentPlayer == 2 && currentPiece.getColorOfPiece() == ChessGamePiece.BLACK) {
            return true;
        } else if (currentPlayer == 1 && currentPiece.getColorOfPiece() == ChessGamePiece.WHITE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines if the requested King is in check.
     *
     * @param checkCurrent if true, will check if the current king is in check if false,
     *                     will check if the other player's king is in check.
     * @return true if the king is in check, false otherwise
     */
    public boolean isKingInCheck(boolean checkCurrent) {
        if (checkCurrent) {
            if (currentPlayer == 1) {
                return king1.isChecked(board);
            }
            return king2.isChecked(board);
        } else {
            if (currentPlayer == 2) {
                return king1.isChecked(board);
            }
            return king2.isChecked(board);
        }
    }

    /**
     * Asks the user if they want to play again - if they don't, the game exits.
     *
     * @param endGameStr the string to display to the user (i.e. stalemate, checkmate,
     *                   etc)
     */
    private void askUserToPlayAgain(String endGameStr) {
        int resp =
                JOptionPane.showConfirmDialog(board.getParent(), endGameStr
                        + " Do you want to play again?");
        if (resp == JOptionPane.YES_OPTION) {
            reset();
        } else {
            board.resetBoard(false);
            // System.exit(0);
        }
    }

    /**
     * Determines if the game should continue (i.e. game is in check or is
     * 'normal'). If it should not, the user is asked to play again (see above
     * method).
     */
    /*private void checkGameConditions(){
        int origPlayer = currentPlayer;
        for ( int i = 0; i < 2; i++ ){
            int gameLostRetVal = determineGameLost();
            if ( gameLostRetVal < 0 ){
                askUserToPlayAgain( "Game over - STALEMATE. You should both go"
                    + " cry in a corner!" );
                return;
            }
            else if ( gameLostRetVal > 0 ){
                askUserToPlayAgain( "Game over - CHECKMATE. " + "Player "
                    + gameLostRetVal + " loses and should go"
                    + " cry in a corner!" );
                return;
            }
            else if ( isKingInCheck( true ) ){
                JOptionPane.showMessageDialog(
                    board.getParent(),
                    "Be careful player " + currentPlayer + ", " +
                    "your king is in check! Your next move must get " +
                    "him out of check or you're screwed.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE );
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;
            // check the next player's conditions as well.
        }
        currentPlayer = origPlayer;
        nextTurn();
    }*/
    //Cuarta refactorización - Patron de simplificación
    //patron de eliminación de duplicación
    private void checkGameConditions() {
        int origPlayer = currentPlayer;
        for (int i = 0; i < 2; i++) {
            int gameLostRetVal = determineGameLost();
            if (gameLostRetVal < 0) {
                askUserToPlayAgain("Game over - STALEMATE. You should both go cry in a corner!");
                return;
            } else if (gameLostRetVal > 0) {
                askUserToPlayAgain("Game over - CHECKMATE. Player " + gameLostRetVal + " loses and should go cry in a corner!");
                return;
            } else if (isKingInCheck(true)) {
                JOptionPane.showMessageDialog(board.getParent(), "Be careful player " + currentPlayer + ", your king is in check! Your next move must get him out of check or you're screwed.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
        currentPlayer = origPlayer;
        nextTurn();
    }

    /**
     * Determines if the game is lost. Returns 1 or 2 for the losing player, -1
     * for stalemate, or 0 for a still valid game.
     *
     * @return int 1 or 2 for the losing play, -1 for stalemate, or 0 for a
     * still valid game.
     */
    /*public int determineGameLost() {
        if (king1.isChecked(board) && !playerHasLegalMoves(1)) // player 1
        // loss
        {
            return 1;
        }
        if (king2.isChecked(board) && !playerHasLegalMoves(2)) // player 2
        // loss
        {
            return 2;
        }
        if ((!king1.isChecked(board) && !playerHasLegalMoves(1))
                || (!king2.isChecked(board) && !playerHasLegalMoves(2))
                || (board.getAllWhitePieces().size() == 1 &&
                board.getAllBlackPieces().size() == 1)) // stalemate
        {
            return -1;
        }
        return 0; // game is still in play
    }*/

    //Segunda Refactorización de Codigo Critico - Segundo Avance
    // Se usa los patrones de Singleton, Brigde y Builder

    public int determineGameLost() {
        boolean player1Lost = king1.isChecked(board) && !playerHasLegalMoves(1);
        boolean player2Lost = king2.isChecked(board) && !playerHasLegalMoves(2);
        boolean isStalemate = (!king1.isChecked(board) && !playerHasLegalMoves(1)) ||
                (!king2.isChecked(board) && !playerHasLegalMoves(2)) ||
                (board.getAllWhitePieces().size() == 1 && board.getAllBlackPieces().size() == 1);
        if (player1Lost) {
            return 1;
        } else if (player2Lost) {
            return 2;
        } else if (isStalemate) {
            return -1;
        } else {
            return 0;
        }
    }

    // ----------------------------------------------------------

    /**
     * Given a MouseEvent from a user clicking on a square, the appropriate
     * action is determined. Actions include: moving a piece, showing the possi
     * ble moves of a piece, or ending the game after checking game conditions.
     *
     * @param e the mouse event from the listener
     */
    /*public void determineActionFromSquareClick(MouseEvent e) {
        BoardSquare squareClicked = (BoardSquare) e.getSource();
        ChessGamePiece pieceOnSquare = squareClicked.getPieceOnSquare();
        board.clearColorsOnBoard();
        if (firstClick) {
            currentPiece = squareClicked.getPieceOnSquare();
            if (selectedPieceIsValid()) {
                currentPiece.showLegalMoves(board);
                squareClicked.setBackground(Color.GREEN);
                firstClick = false;
            } else {
                if (currentPiece != null) {
                    JOptionPane.showMessageDialog(squareClicked, "You tried to pick up the other player's piece! " + "Get some glasses and pick a valid square.", "Illegal move", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(squareClicked, "You tried to pick up an empty square! " + "Get some glasses and pick a valid square.", "Illegal move", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            if (pieceOnSquare == null ||
                    !pieceOnSquare.equals(currentPiece)) // moving
            {
                boolean moveSuccessful =
                        currentPiece.move(
                                board,
                                squareClicked.getRow(),
                                squareClicked.getColumn());
                if (moveSuccessful) {
                    checkGameConditions();
                } else {
                    int row = squareClicked.getRow();
                    int col = squareClicked.getColumn();
                    JOptionPane.showMessageDialog(
                            squareClicked,
                            "The move to row " + (row + 1) + " and column "
                                    + (col + 1)
                                    + " is either not valid or not legal "
                                    + "for this piece. Choose another move location, "
                                    + "and try using your brain this time!",
                            "Invalid move",
                            JOptionPane.ERROR_MESSAGE);
                }
                firstClick = true;
            } else
            // user is just unselecting the current piece
            {
                firstClick = true;
            }
        }
    }*/

    //Primera Refactorización de Codigo Critico - Segundo Avance
    // Se usa los patrones de Singleton, Brigde y Builder
    public void determineActionFromSquareClick(MouseEvent e) {
        BoardSquare squareClicked = (BoardSquare) e.getSource();
        ChessGamePiece pieceOnSquare = squareClicked.getPieceOnSquare();
        board.clearColorsOnBoard();
        if (firstClick) {
            handleFirstClick(squareClicked);
        } else {
            handleSecondClick(squareClicked, pieceOnSquare);
        }
    }

    private void handleFirstClick(BoardSquare squareClicked) {
        currentPiece = squareClicked.getPieceOnSquare();
        if (selectedPieceIsValid()) {
            currentPiece.showLegalMoves(board);
            squareClicked.setBackground(Color.GREEN);
            firstClick = false;
        } else {
            handleInvalidSelection(squareClicked);
        }
    }

    private void handleInvalidSelection(BoardSquare squareClicked) {
        if (currentPiece != null) {
            showMessage(squareClicked, "You tried to pick up the other player's piece! "
                    + "Get some glasses and pick a valid square.", "Illegal move");
        } else {
            showMessage(squareClicked, "You tried to pick up an empty square! "
                    + "Get some glasses and pick a valid square.", "Illegal move");
        }
    }

    private void handleSecondClick(BoardSquare squareClicked, ChessGamePiece pieceOnSquare) {
        if (pieceOnSquare == null || !pieceOnSquare.equals(currentPiece)) {
            handleValidMove(squareClicked);
        } else {
            firstClick = true;
        }
    }

    private void handleValidMove(BoardSquare squareClicked) {
        boolean moveSuccessful = currentPiece.move(board, squareClicked.getRow(), squareClicked.getColumn());
        if (moveSuccessful) {
            checkGameConditions();
        } else {
            showMessage(squareClicked, "The move is either not valid or not legal for this piece. " +
                    "Choose another move location, and try using your brain this time!", "Invalid move");
        }
        firstClick = true;
    }

    private void showMessage(BoardSquare squareClicked, String message, String title) {
        JOptionPane.showMessageDialog(squareClicked, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
    //Primer intento de refactorización - Error
    //Primera Refactorización de Codigo Critico - Segundo Avance
    // Se usa los patrones de Singleton, Brigde y Builder
    /*public void determineActionFromSquareClick(MouseEvent e) {
        BoardSquare clickedSquare = (BoardSquare) e.getSource();
        ChessGamePiece pieceOnSquare = clickedSquare.getPieceOnSquare();
        board.clearColorsOnBoard();

        if (firstClick) {
            handleFirstClick(clickedSquare, pieceOnSquare);
        } else {
            handleSecondClick(clickedSquare, pieceOnSquare);
        }
    }

    private void handleFirstClick(BoardSquare clickedSquare, ChessGamePiece pieceOnSquare) {
        currentPiece = clickedSquare.getPieceOnSquare();

        if (selectedPieceIsValid()) {
            currentPiece.showLegalMoves(board);
            clickedSquare.setBackground(Color.GREEN);
            firstClick = false;
        } else {
            showMessageDialog(clickedSquare, MESSAGE_INVALID_SELECTION, MESSAGE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSecondClick(BoardSquare clickedSquare, ChessGamePiece pieceOnSquare) {
        if (pieceOnSquare != null && !pieceOnSquare.equals(currentPiece)) {
            firstClick = true;
        } else {
            boolean moveSuccessful = currentPiece.move(board, clickedSquare.getRow(), clickedSquare.getColumn());
            if (moveSuccessful) {
                checkGameConditions();
            } else {
                showMessageDialog(clickedSquare, buildInvalidMoveMessage(clickedSquare), MESSAGE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }

            firstClick = true;
        }
    }

    private void showMessageDialog(BoardSquare parent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }

    private String buildInvalidMoveMessage(BoardSquare clickedSquare) {
        int row = clickedSquare.getRow();
        int column = clickedSquare.getColumn();
        return String.format(MESSAGE_INVALID_MOVE_FORMAT, row + 1, column + 1);
    }

    private static final String MESSAGE_INVALID_SELECTION = "You tried to pick up the other player's piece! Get some glasses and pick a valid square.";
    private static final String MESSAGE_INVALID_MOVE_FORMAT = "The move to row %d and column %d is either not valid or not legal for this piece. Choose another move location, and try using your brain this time!";
    private static final String MESSAGE_ERROR_TITLE = "Illegal move";

}*/