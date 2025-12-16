package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KingMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();
        int posMarg_left = Math.max(position.getColumn() - 1, 1);
        int posMarg_right = Math.min(position.getColumn() + 1, 8);

        int posMarg_below = Math.max(position.getRow() - 1, 1);
        int posMarg_above = Math.min(position.getRow() + 1, 8);

        for (int i = posMarg_below; i <= posMarg_above; ++i) {
            for (int j = posMarg_left; j <= posMarg_right; ++j) {
                if (!(i == position.getRow() && j == position.getColumn())) {
                    ChessPosition endPosition = new ChessPosition(i, j);
                    ChessPiece endPosPiece = board.getPiece(endPosition);
                    if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                        moves.add(new ChessMove(position, endPosition, null));
                    }
                }
            }
        }


        return moves;
    }
}