package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class KnightMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();

        int[][] shapeL_offsets = {{1,2},{-1,2},{1,-2},{-1,-2},
                {2,1},{-2,1},{2,-1},{-2,-1}};

        for (int[] offset: shapeL_offsets){
            ChessPosition endPosition = new ChessPosition(position.getRow()+offset[0], position.getColumn()+offset[1]);

            if(!endPosition.isInBounds()){
                continue;
            }

            if(board.getPiece(endPosition) == null || board.getPiece(endPosition).getTeamColor() != board.getPiece(position).getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
        }


        return moves;
    }
}