package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BishopMovesCalculator extends LinearMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();

        int[][] directions = {{1,1},{-1,-1},{-1,1},{1,-1}};

        for (int[] d : directions){
            moves.addAll(getLinearMoves(board,position,d[0],d[1]));
        }
        return moves;
    }

}