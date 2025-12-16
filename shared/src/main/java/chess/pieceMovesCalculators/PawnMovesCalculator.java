package chess.pieceMovesCalculators;

import chess.*;
import chess.ChessPiece.PieceType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PawnMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();

        PieceType[] allowedPromotionTypes = {
                PieceType.QUEEN,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK};

        ChessGame.TeamColor pieceColor = board.getPiece((position)).getTeamColor();
        ChessPosition endPosition;
        final int startPosRow = position.getRow();
        final int startPosCol = position.getColumn();

        int pawnHomeRow =2;
        int promoRow =8;

        int step =1;
        if(pieceColor == ChessGame.TeamColor.BLACK){
            pawnHomeRow = 7;
            step = -1;
            promoRow =1;

        }



        // move 1, promote if results in end row
        boolean canMove1Step = false;
        endPosition = new ChessPosition(startPosRow + step, startPosCol);
        if (board.getPiece(endPosition) == null) {
            canMove1Step =true;
            if(endPosition.getRow()==promoRow){
                for(var pieceType: allowedPromotionTypes){
                    moves.add(new ChessMove(position, endPosition, pieceType));
                }
            }
            else {
                moves.add(new ChessMove(position, endPosition, null));
            }
        }

        //move 2 if start
        if ( startPosRow == pawnHomeRow && canMove1Step) {
            endPosition = new ChessPosition(startPosRow + (step * 2), startPosCol);
            if (board.getPiece(endPosition) == null) {
                moves.add(new ChessMove(position, endPosition, null));
            }
        }

        //diag right -- move diagonal attack (in bounds and if piece)
        if(startPosCol +1 <=8) {
            endPosition = new ChessPosition(startPosRow + step, startPosCol +1);
            ChessPiece occupyingPiece = board.getPiece(endPosition);
            if (board.getPiece(endPosition) != null && occupyingPiece.getTeamColor()!=pieceColor) {
                if(endPosition.getRow()==promoRow){
                    for(var pieceType: allowedPromotionTypes){
                        moves.add(new ChessMove(position, endPosition, pieceType));
                    }
                }
                else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }


        }

        //diag left -- move diagonal attack (in bounds and if piece)
        if(startPosCol -1 >=1) {
            endPosition = new ChessPosition(startPosRow + step, startPosCol -1);
            ChessPiece occupyingPiece = board.getPiece(endPosition);
            if (board.getPiece(endPosition) != null && occupyingPiece.getTeamColor()!=pieceColor) {
                if(endPosition.getRow()==promoRow){
                    for(var pieceType: allowedPromotionTypes){
                        moves.add(new ChessMove(position, endPosition, pieceType));
                    }
                }
                else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
        }

        return moves;
    }
}