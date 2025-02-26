package chess;

import chess.ReturnPiece.PieceType;

public abstract class Piece {
    protected Chess.Player color;
    protected int moves;

    public Piece(Chess.Player color){
        this.color = color;
        this.moves = 0;
    }

    public Chess.Player getColor() {
        return color;
    }

    public boolean isTurn(Board board) {
        return board.getTurn() == color;
    }

    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board);

    protected abstract boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board);

    public abstract PieceType getPieceType();

    public void move(){ moves++; }
    public int getMoves(){ return moves; }
}
