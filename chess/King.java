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
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WK : PieceType.BK;
    }
    
}
