package chess;

import chess.ReturnPiece.PieceType;

public abstract class Piece {
    protected Chess.Player color;

    public Piece(Chess.Player color){
        this.color = color;
    }

    public Chess.Player getColor() {
        return color;
    }

    public abstract PieceType getPieceType();
}
