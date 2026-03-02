package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard gameBoard;
    private TeamColor curTurnTeam;


    public ChessGame() {
        gameBoard = new ChessBoard();
        gameBoard.resetBoard();
        curTurnTeam = TeamColor.WHITE;

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return curTurnTeam;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        this.curTurnTeam = team;
    }

    private void toggleTeamTurn() {
        this.curTurnTeam = this.curTurnTeam == TeamColor.BLACK ? TeamColor.WHITE : TeamColor.BLACK;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece startPiece = gameBoard.getPiece(startPosition);
        Collection<ChessMove> validMoves =  new HashSet<>();


        if (startPiece == null){
            return validMoves;
        }

        TeamColor teamColor = startPiece.getTeamColor();
        Collection<ChessMove> possibleMoves =  startPiece.pieceMoves(gameBoard,startPosition);


        //remove startPiece to test all possible end positions to see if it puts team in check
        gameBoard.addPiece(startPosition,null);
        for (ChessMove m: possibleMoves){
            ChessPiece endPiece = m.getPromotionPiece() == null ? startPiece : new ChessPiece(teamColor,m.getPromotionPiece());

            ChessPiece temp = gameBoard.getPiece(m.getEndPosition());
            gameBoard.addPiece(m.getEndPosition(),endPiece);
            if (!isInCheck(teamColor)){
                validMoves.add(m);
            }
            gameBoard.addPiece(m.getEndPosition(), temp);
        }

        // replace piece at start pos
        gameBoard.addPiece(startPosition, startPiece);

        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPosition = move.getStartPosition();
        ChessPiece startPiece = gameBoard.getPiece(startPosition);

        if (startPiece == null){
            throw new InvalidMoveException("No piece at start pos");
        }
        else if (curTurnTeam != startPiece.getTeamColor()){
            throw new InvalidMoveException("Not team's turn");
        }
        Collection<ChessMove> validMoves = validMoves(startPosition);

        if  (validMoves.isEmpty()){
            throw new InvalidMoveException("Piece has no valid moves");
        }
        else if  (!validMoves.contains(move)){
            throw new InvalidMoveException("Move is invalid");
        }

        ChessPiece.PieceType promoPiece = move.getPromotionPiece();
        ChessPiece endPiece =  promoPiece != null ? new ChessPiece(startPiece.getTeamColor(), promoPiece) :
                startPiece;

        gameBoard.addPiece(move.getStartPosition(), null);
        gameBoard.addPiece(move.getEndPosition(), endPiece);
        toggleTeamTurn();

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos = findKing(teamColor);
        TeamColor enemyColor = teamColor == TeamColor.BLACK ? TeamColor.WHITE : TeamColor.BLACK;

        for (int i =1; i <= ChessBoard.boardRowColDimension; i++){
            for (int j =1; j <= ChessBoard.boardRowColDimension; j++) {

                ChessPosition pos = new ChessPosition(i,j);
                ChessPiece piece = gameBoard.getPiece(pos);

                if (piece == null || !piece.getTeamColor().equals(enemyColor)) {
                    continue;
                }


                for (ChessMove enemyMove: piece.pieceMoves(gameBoard,pos)){

                    int enemyEndRow = enemyMove.getEndPosition().getRow();
                    int enemyEndCol = enemyMove.getEndPosition().getColumn();
                    if (enemyEndRow == kingPos.getRow() && enemyEndCol == kingPos.getColumn()){
                        return true;
                    }

                }
            }
        }

        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        // in checkmate if other team has a move to get king

        return isInCheck(teamColor) && teamHasNoValidMoves(teamColor);

    }

    private ChessPosition findKing(TeamColor teamColor){
        for (int i =1; i <= ChessBoard.boardRowColDimension; i++){
            for (int j =1; j <= ChessBoard.boardRowColDimension; j++) {
                ChessPosition pos = new ChessPosition(i,j);
                ChessPiece piece = gameBoard.getPiece(pos);
                if (piece != null
                        && piece.getPieceType().equals(ChessPiece.PieceType.KING)
                        && piece.getTeamColor().equals(teamColor)) {
                    return pos;
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (this.isInCheck(teamColor)){
            return false;
        }

        return teamHasNoValidMoves(teamColor);

    }

    private boolean teamHasNoValidMoves(TeamColor teamColor){
        for (int i =1; i <= ChessBoard.boardRowColDimension; i++){
            for (int j =1; j <= ChessBoard.boardRowColDimension; j++) {

                ChessPosition pos = new ChessPosition(i,j);
                ChessPiece piece = gameBoard.getPiece(pos);

                if (piece == null || ! piece.getTeamColor().equals(teamColor)) { // or not same color
                    continue;
                }

                if(!validMoves(pos).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {

        this.gameBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.gameBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame otherChessGame = (ChessGame) o;
        return gameBoard.equals(otherChessGame.gameBoard) && this.curTurnTeam == otherChessGame.curTurnTeam ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(curTurnTeam, Objects.hashCode(gameBoard));
    }
}
