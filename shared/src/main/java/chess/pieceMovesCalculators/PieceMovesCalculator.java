package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public interface PieceMovesCalculator {
    abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);
}
