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
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WN : PieceType.BN;
    }
    
}
