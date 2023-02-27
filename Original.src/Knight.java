import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;
// -------------------------------------------------------------------------
/**
 * Represents a Knight game piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Knight
    extends ChessGamePiece{
    /**
     * Knight constructor for gamePiece
     *
     * @param row
     *            the row to create the knight on
     * @param col
     *            the column to create the knight on
     * @param board
     *            the board to create the piece on
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Knight( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }

    //Septima refactorización: Patron de simplificación
    //patron de eliminación de duplicación

    /* Se agrego un nuevo metodo getStrings */

    /*private ArrayList<String> getStrings(ChessGameBoard board, int i2, int i3, int i4, int i5) {
        ArrayList<String> moves = new ArrayList<String>();
        for (int i = i2; i >= -i2; i -= i3){
            for (int j = i4; j >= -i4; j -= i5){
                if ( isOnScreen( pieceRow + i, pieceColumn + j )
                        && ( isEnemy( board, pieceRow + i, pieceColumn + j ) ||
                            board.getCell( pieceRow + i,pieceColumn + j )
                            .getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + j ) );
                }
            }
        }
        return moves;
    }*/
    //Disminuyendo su complejidad
    private ArrayList<String> getStrings(ChessGameBoard board, int i2, int i3, int i4, int i5) {
        ArrayList<String> moves = new ArrayList<String>();
        int row, col;
        BoardSquare cell;
        for (int i = i2; i >= -i2; i -= i3) {
            row = pieceRow + i;
            for (int j = i4; j >= -i4; j -= i5) {
                col = pieceColumn + j;
                if (isOnScreen(row, col)) {
                    cell = board.getCell(row, col);
                    if (isEnemy(board, row, col) || cell.getPieceOnSquare() == null) {
                        moves.add(row + "," + col);
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Calculates the moves of the knight in the north direction relative to the
     * location of the piece.
     *
     * @param board
     *            the board to check moves on
     * @return ArrayList<String> a list of the possible moves
     */

    //Octava Refactorización - Patron de simplificación
    private ArrayList<String> calculateNorthMoves( ChessGameBoard board ){
        /*ArrayList<String> moves = new ArrayList<String>();
        for ( int i = 2; i >= -2; i -= 4 ){
            for ( int j = 1; j >= -1; j -= 2 ){
                if ( isOnScreen( pieceRow + i, pieceColumn + j )
                    && ( isEnemy( board, pieceRow + i, pieceColumn + j ) ||
                        board.getCell(
                        pieceRow + i,
                        pieceColumn + j )
                        .getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + j ) );
                }
            }
        }
        return moves;*/
        return getStrings(board,2,4,1,2);
    }
    /**
     * Calculates the moves of the knight in the south direction relative to the
     * location of the piece.
     *
     * @param board
     *            the board to check moves on
     * @return ArrayList<String> a list of the possible moves
     */
    // Octava refactorización - Metod de simplificación
    private ArrayList<String> calculateSouthMoves( ChessGameBoard board ){
        /*ArrayList<String> moves = new ArrayList<String>();
        for ( int i = 1; i >= -1; i -= 2 ){
            for ( int j = 2; j >= -2; j -= 4 ){
                if ( isOnScreen( pieceRow + i, pieceColumn + j )
                    && ( isEnemy( board, pieceRow + i, pieceColumn + j ) ||
                        board.getCell(
                        pieceRow + i,
                        pieceColumn + j )
                        .getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + j ) );
                }
            }
        }
        return moves;*/
        return getStrings(board,1,2,2,4);
    }
    /**
     * Calculates the possible moves for this Knight.
     *
     * @param board
     *            the game board to check
     * @return ArrayList<String> the list of possible moves
     */
    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> moves = new ArrayList<String>();
        if ( isPieceOnScreen() ){
            moves.addAll( calculateNorthMoves( board ) );
            moves.addAll( calculateSouthMoves( board ) );
        }
        return moves;
    }
    /**
     * Creates an icon for this piece depending on the piece's color.
     *
     * @return ImageIcon the ImageIcon representation of this piece.
     */
    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == ChessGamePiece.WHITE ){
            return new ImageIcon(
                getClass().getResource("chessImages/WhiteKnight.gif")
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                getClass().getResource("chessImages/BlackKnight.gif")
            );            
        }
        else
        {
            return new ImageIcon(
                getClass().getResource("chessImages/default-Unassigned.gif")
            );            
        }
    }
}
