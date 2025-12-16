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

        //move up 2, then left, right
        int long_up = position.getRow() + 2;
        if (long_up <= 8) {
            int short_left = position.getColumn() - 1;
            int short_right = position.getColumn() + 1;
            if (short_left >= 1) {
                ChessPosition endPosition = new ChessPosition(long_up, short_left);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
            if (short_right <= 8) {
                ChessPosition endPosition = new ChessPosition(long_up, short_right);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
        }

        //move down 2, then left, right
        int long_down = position.getRow() - 2;
        if (long_down >= 1) {
            int short_left = position.getColumn() - 1;
            int short_right = position.getColumn() + 1;
            if (short_left >= 1) {
                ChessPosition endPosition = new ChessPosition(long_down, short_left);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
            if (short_right <= 8) {
                ChessPosition endPosition = new ChessPosition(long_down, short_right);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
        }

        //move left 2, then up or down
        int long_left = position.getColumn() - 2;
        if (long_left >= 1) {
            int short_down = position.getRow() - 1;
            int short_up = position.getRow() + 1;
            if (short_down >= 1) {
                ChessPosition endPosition = new ChessPosition(short_down, long_left);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
            if (short_up <= 8) {
                ChessPosition endPosition = new ChessPosition(short_up, long_left);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
        }

        //move right 2, then up or down
        int long_right = position.getColumn() + 2;
        if (long_right <= 8) {
            int short_down = position.getRow() - 1;
            int short_up = position.getRow() + 1;
            if (short_down >= 1) {
                ChessPosition endPosition = new ChessPosition(short_down, long_right);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
            if (short_up <= 8) {
                ChessPosition endPosition = new ChessPosition(short_up, long_right);
                ChessPiece endPosPiece = board.getPiece(endPosition);
                if (endPosPiece == null || board.getPiece(position).getTeamColor() != endPosPiece.getTeamColor()) {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
        }



        return moves;
    }
}