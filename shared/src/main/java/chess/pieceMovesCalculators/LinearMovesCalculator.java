package chess.pieceMovesCalculators;

import chess.*;

import java.util.Collection;
import java.util.HashSet;

public abstract class LinearMovesCalculator {

    public Collection<ChessMove> getLinearMoves(ChessBoard board, ChessPosition startPosition, int rowDirection, int colDirection){
        if (rowDirection ==0 && colDirection ==0){
            return null;
        }

        var myColor = board.getPiece(startPosition).getTeamColor();
        int curRow = startPosition.getRow() + rowDirection;
        int curCol = startPosition.getColumn() + colDirection;
        ChessPosition curPosition = new ChessPosition(curRow, curCol);

        Collection<ChessMove> moves = new HashSet<ChessMove>();


        while (curPosition.isInBounds()){

            ChessPiece occupyingPiece = board.getPiece(curPosition);
            if (occupyingPiece != null )
            {
                if(board.getPiece(curPosition).getTeamColor() != myColor) {
                    moves.add(new ChessMove(startPosition, curPosition, null));
                }
                break;
            }

            moves.add(new ChessMove(startPosition, curPosition, null));

            curRow = curPosition.getRow() + rowDirection;
            curCol = curPosition.getColumn() + colDirection;
            curPosition = new ChessPosition(curRow, curCol);
        }

        return moves;
    }


}
