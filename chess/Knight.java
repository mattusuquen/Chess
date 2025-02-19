package chess;

import chess.ReturnPiece.PieceType;

public class Knight extends Piece {

    public Knight(Color color){
        super(color);
    }

    public Color getColor() {
        return super.getColor();
    }

    public boolean isValidMove(String move, Board board) {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceType'");
    }
    
}
