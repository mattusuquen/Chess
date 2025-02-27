package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class Bishop extends Piece {
    
    public Bishop(Player color){
        super(color);
    }

    public Player getColor() {
        return color;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

       
        if (rowDiff == colDiff && rowDiff != 0) 
        {
            
            if (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).getColor() != this.color) 
            {
                return !pathIsBlocked(startRow, startCol, endRow, endCol, board);
            }
        }
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WB : PieceType.BB;
    }

    @Override
    protected boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        
        int rowDirection = (endRow > startRow) ? 1 : -1;
        int colDirection = (endCol > startCol) ? 1 : -1;

        int currentRow = startRow + rowDirection;
        int currentCol = startCol + colDirection;

        while (currentRow != endRow && currentCol != endCol) 
        {
            if (board.getPiece(currentRow, currentCol) != null) 
            {
                return true;
            }
            
            currentRow += rowDirection;
            currentCol += colDirection;
        
        }

        return false;
    }
}