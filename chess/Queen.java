package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class Queen extends Piece{

    public Queen(Player color){
        super(color);
    }

    public Player getColor() {
        return color;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        if((Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) && (startRow != endRow && startCol != endCol)) return false;
        if (isBlocked(startRow, startCol, endRow, endCol, board)) return false;
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WQ : PieceType.BQ;
    }

    @Override
    protected boolean isBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        
        if (startRow == endRow) {
            int min = Math.min(startCol, endCol);
            int max = Math.max(startCol, endCol);
            for (int i = min + 1; i < max; i++) {
                if (board.getPiece(startRow, i) != null) return true;
            }
        } else if (startCol == endCol) {
            int min = Math.min(startRow, endRow);
            int max = Math.max(startRow, endRow);
            for (int i = min + 1; i < max; i++) {
                if (board.getPiece(i, startCol) != null) return true;
            }
        } else if(Math.abs(startCol - endCol) == Math.abs(startRow - endRow)){
            int rowDirection = (endRow > startRow) ? 1 : -1;
            int colDirection = (endCol > startCol) ? 1 : -1;

            int row = startRow + rowDirection;
            int col = startCol + colDirection;

            while (row != endRow && col != endCol) {
                if (board.getPiece(row, col) != null) return true;
                row += rowDirection;
                col += colDirection;
            }

        }
        return false;

    }
    
}
