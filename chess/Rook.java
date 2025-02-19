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

    public boolean isValidMove(String move, Board board) {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WR : PieceType.BR;
    }

}
