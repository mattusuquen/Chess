package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class Rook extends Piece {

    public Rook(Player color){
        super(color);
    }

    public Player getColor() {
        return color;
    }

    protected boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board){
        if (startRow == endRow) {
            int min = Math.min(startCol, endCol);
            int max = Math.max(startCol, endCol);
            for (int i = min + 1; i < max; i++) {
                if (board.getPiece(startRow, i) != null) return true;
            }
        } else if (startCol == endCol){
            int min = Math.min(startRow, endRow);
            int max = Math.max(startRow, endRow);
            for (int i = min + 1; i < max; i++) {
                if (board.getPiece(i, startCol) != null) return true;
            }
        }
        return false;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        if (startRow != endRow && startCol != endCol) {
            System.out.println("Rook can only move in straight lines");
            return false;             // Rook can only move in straight lines
        }
        if (pathIsBlocked(startRow, startCol, endRow, endCol, board)) return false; // Path is blocked by piece
        
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WR : PieceType.BR;
    }

}
