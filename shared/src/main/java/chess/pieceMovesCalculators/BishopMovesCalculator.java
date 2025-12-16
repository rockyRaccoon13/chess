package chess.pieceMovesCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BishopMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();

        //step diag up+R
        int i = position.getRow();
        int j = position.getColumn();
        i++;
        j++;
        while(i <= 8 && j <=8){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i++;
            j++;
        }

        //step diag down+R
        i = position.getRow();
        j = position.getColumn();
        i--;
        j++;
        while(i >= 1 && j <=8){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i--;
            j++;
        }

        //step diag left+up
        i = position.getRow();
        j = position.getColumn();
        i++;
        j--;
        while(i <= 8 && j >=1){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i++;
            j--;
        }

        //step diag left+down
        i = position.getRow();
        j = position.getColumn();
        i--;
        j--;
        while(i >= 1 && j >=1){
            ChessPosition endPosition = new ChessPosition(i, j);
            ChessPiece endPosPiece = board.getPiece(endPosition);
            if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                moves.add(new ChessMove(position, endPosition, null));
            }
            if(endPosPiece != null){
                break;
            }
            i--;
            j--;
        }


        return moves;
    }
}