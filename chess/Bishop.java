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
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WB : PieceType.BB;
    }

    @Override
    protected boolean isBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBlocked'");
    }

}
