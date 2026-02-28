package chess.pieceMovesCalculators;

import chess.*;
import chess.ChessPiece.PieceType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PawnMovesCalculator implements PieceMovesCalculator {

    private final PieceType[] allowedPromotionTypes = {
            PieceType.QUEEN,
            PieceType.BISHOP,
            PieceType.KNIGHT,
            PieceType.ROOK};


    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Set<ChessMove> moves = new HashSet<>();


        ChessGame.TeamColor pieceColor = board.getPiece((position)).getTeamColor();
        int forwardDirection = pieceColor == ChessGame.TeamColor.WHITE ? 1 : -1;
        int promoRow = 1+ (pieceColor == ChessGame.TeamColor.WHITE ?
                ChessBoard.blackBackLineRowIndex : ChessBoard.whiteBackLineRowIndex);

        int homeRow = 1 + (pieceColor == ChessGame.TeamColor.WHITE ?
                ChessBoard.whitePawnsRowIndex : ChessBoard.blackPawnsRowIndex);

        moves.addAll(getForwardMoves(board, position, forwardDirection, promoRow, homeRow));
        moves.addAll(getDiagonalCaptureMoves(board, position, pieceColor, forwardDirection, promoRow));


        return moves;
    }

    private boolean isEmpty(final ChessBoard board, final ChessPosition pos) {
        return board.getPiece(pos) == null;
    }

    private Collection<ChessMove> getForwardMoves(ChessBoard board,
                                                  ChessPosition startPosition,
                                                  int forwardDirection, int promoRow, int homeRow) {

        Collection<ChessMove> forwardMoves = new HashSet<>();

        // move 1, promote if results in end row
        int stepSize = 1;

        boolean canMove1Step = false; //Used to check path clear for 2 step initial move
        ChessPosition endPosition = new ChessPosition(
                startPosition.getRow() + (stepSize * forwardDirection), startPosition.getColumn());



        if (endPosition.isInBounds() && isEmpty(board, endPosition)) {
            canMove1Step = true;
            if (endPosition.getRow() == promoRow) {
                forwardMoves.addAll(createPromotionMoves(startPosition, endPosition));

            } else {
                forwardMoves.add(new ChessMove(startPosition, endPosition, null));
            }
        }

        //move 2 if start
        stepSize = 2;
        if (startPosition.getRow() == homeRow && canMove1Step) {
            endPosition = new ChessPosition(startPosition.getRow() + (stepSize * forwardDirection), startPosition.getColumn());

            if (endPosition.isInBounds() &&  isEmpty(board, endPosition)) {
                forwardMoves.add(new ChessMove(startPosition, endPosition, null));
            }
        }
        return forwardMoves;
    }


    private Collection<ChessMove> getDiagonalCaptureMoves(final ChessBoard board,
                                                          final ChessPosition startPosition,
                                                          final ChessGame.TeamColor pieceColor,
                                                          int forwardDirection, int promoRow) {

        int[] columnOffsets = {-1, 1};
        HashSet<ChessMove> captureMoves = new HashSet<>();

        for (int colOffset : columnOffsets) {
            ChessPosition endPosition = new ChessPosition(
                    startPosition.getRow() + forwardDirection,
                    startPosition.getColumn() + colOffset
            );

            if (!endPosition.isInBounds()){
                continue;
            }


            ChessPiece occupyingPiece = board.getPiece(endPosition);
            if (board.getPiece(endPosition) != null && occupyingPiece.getTeamColor() != pieceColor) {
                if (endPosition.getRow() == promoRow) {
                    captureMoves.addAll(createPromotionMoves(startPosition,endPosition));
                } else {
                    captureMoves.add(new ChessMove(startPosition, endPosition, null));
                }
            }

        }

        return captureMoves;
    }

    private Collection<ChessMove> createPromotionMoves(ChessPosition startPosition, ChessPosition endPosition) {
        var promotionMoves = new HashSet<ChessMove>();
        for (var pieceType : allowedPromotionTypes) {
            promotionMoves.add(new ChessMove(startPosition, endPosition, pieceType));
        }
        return promotionMoves;
    }
}

