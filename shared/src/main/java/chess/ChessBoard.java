package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] board = new ChessPiece[8][8];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        int whitePawnsRowIndex = 1;
        int whiteBackLineRowIndex = 0;
        int blackPawnsRowIndex = 6;
        int blackBackLineRowIndex = 7;

        ChessPiece.PieceType[] backlineOrder = {
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.KING,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.ROOK
        };

        for (var i=0; i < board.length; i++) {
            ChessGame.TeamColor color = ChessGame.TeamColor.WHITE;
            if (i == blackPawnsRowIndex || i == blackBackLineRowIndex) {
                color = ChessGame.TeamColor.BLACK;
            }

            new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            for (var j = 0; j < board[i].length; j++) {
                if (i == blackPawnsRowIndex || i == whitePawnsRowIndex) {
                    board[i][j] = new ChessPiece(color, ChessPiece.PieceType.PAWN);
                } else if (i == blackBackLineRowIndex || i == whiteBackLineRowIndex) {
                    board[i][j] = new ChessPiece(color, backlineOrder[j]);
                } else {
                    board[i][j] = null;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int i=board.length-1; i >= 0; i--){
            //rows
            boardStringBuilder.append("|");
            for (int j=0; j < board[i].length; j++){
                String pieceString = " ";
                if(board[i][j] != null){
                    pieceString = board[i][j].toString();
                }

                boardStringBuilder.append(pieceString).append("|");
            }
            boardStringBuilder.append("\n");

        }

        return boardStringBuilder.toString();
    }
}
