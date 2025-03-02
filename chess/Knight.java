package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class Knight extends Piece {



    public Knight(Player color){
        super(color);
    }



    public Player getColor() {
        return color;
    }



    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) 
        {
            if (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).getColor() != this.color) 
            {
                return true;
            }
        
        }

        return false;
    }



    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WN : PieceType.BN;
    }



    @Override
    protected boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        return false;
    }

}