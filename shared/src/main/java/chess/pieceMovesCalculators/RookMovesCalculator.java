package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RookMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();

        //move up diag up+R
        int i = position.getRow() + 1;
        int j = position.getColumn();
        while(i <= 8){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i++;
        }

        //move down
        i = position.getRow() -1;
        j = position.getColumn();
        while(i >= 1){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i--;
        }

        //move left
        i = position.getRow();
        j = position.getColumn() -1;
        while(j >=1){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            j--;
        }

        //move right
        i = position.getRow();
        j = position.getColumn() +1;
        while(j <=8){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            j++;
        }


        return moves;
    }
}