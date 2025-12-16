package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QueenMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();
        RookMovesCalculator rookMoveCalc = new RookMovesCalculator();
        BishopMovesCalculator bishopMovesCalc = new BishopMovesCalculator();

        moves.addAll(rookMoveCalc.pieceMoves(board,position));
        moves.addAll(bishopMovesCalc.pieceMoves(board,position));

        return moves;
    }
}