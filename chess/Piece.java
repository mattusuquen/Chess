package chess;

import chess.ReturnPiece.PieceType;

public abstract class Piece {
    private Color color;

    public Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract PieceType getPieceType();
}
