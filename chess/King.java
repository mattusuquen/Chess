package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class King extends Piece {

    public King(Player color){
        super(color);
    }

    public Player getColor() {
        return color;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        if (moves == 0){ // check if castle
            if (color == Player.white){
                if (endRow == 0 && endCol == 2){
                    if (board.getPiece(0, 0) == null) return false;
                    if (board.getPiece(0, 0).getMoves() != 0) return false;
                    if (board.getPiece(0, 0).getPieceType() != PieceType.WR) return false;
                    if (pathIsBlocked(0, 0, 0, 4, board)) return false;
                    board.getPiece(0, 0).move();
                    board.movePiece(0, 0, 0, 3,"",false);
                    board.setTurn(Player.black);
                    return true;
                }
                if (endRow == 0 && endCol == 6){
                    if (board.getPiece(0, 7) == null) return false;
                    if (board.getPiece(0, 7).getMoves() != 0) return false;
                    if (board.getPiece(0, 7).getPieceType() != PieceType.WR) return false;
                    if (pathIsBlocked(0, 7, 0, 4, board)) return false;
                    board.getPiece(0, 7).move();
                    board.movePiece(0, 7, 0, 5,"",false);
                    board.setTurn(Player.black);
                    return true;
                }
            } else {
                if (endRow == 7 && endCol == 2){
                    if (board.getPiece(7, 0) == null) return false;
                    if (board.getPiece(7, 0).getMoves() != 0) return false;
                    if (board.getPiece(7, 0).getPieceType() != PieceType.BR) return false;
                    if (pathIsBlocked(7, 0, 7, 4, board)) return false;
                    board.getPiece(7, 0).move();
                    board.movePiece(7, 0, 7, 3,"",false);
                    board.setTurn(Player.white);
                    return true;
                }
                if (endRow == 7 && endCol == 6){
                    if (board.getPiece(7, 7) == null) return false;
                    if (board.getPiece(7, 7).getMoves() != 0) return false;
                    if (board.getPiece(7, 7).getPieceType() != PieceType.BR) return false;
                    if (pathIsBlocked(7, 7, 7, 4, board)) return false;
                    board.getPiece(7, 7).move();
                    board.movePiece(7, 7, 7, 5,"",false);
                    board.setTurn(Player.white);
                    return true;
                }
            }
        }

        if (Math.abs(startRow - endRow) > 1 || Math.abs(startCol - endCol) > 1) return false;
        return true;
        
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WK : PieceType.BK;
    }

    @Override
    protected boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        if (startRow == endRow) {
            int min = Math.min(startCol, endCol);
            int max = Math.max(startCol, endCol);
            for (int i = min + 1; i < max; i++) {
                if (board.getPiece(startRow, i) != null) return true;
            }
        }
        return false;
    }
    
}
