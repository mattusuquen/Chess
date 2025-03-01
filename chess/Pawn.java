package chess;

import chess.Chess.Player;
import chess.ReturnPiece.PieceType;

public class Pawn extends Piece {

    private boolean enpassant = false;

    public Pawn(Player color) {
        super(color);
    }

    public Player getColor() {
        return color;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
        
        int rowDiff = endRow - startRow;
        int colDiff = endCol - startCol;

        if (color == Player.white) 
        {
            // Move straight
            if (colDiff == 0) 
            { 
                // 1 move forward
                if (rowDiff == 1 && board.getPiece(endRow, endCol) == null) 
                {
                    return true;
                } 
                
                // 2 moves from starting position
                else if (rowDiff == 2 && startRow == 1 && board.getPiece(endRow, endCol) == null && board.getPiece(endRow - 1, endCol) == null) 
                {
                    return true;
                }
            } 
            // Capture
            else if (Math.abs(colDiff) == 1 && rowDiff == 1) 
            { 
            
                if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getColor() == Player.black) 
                {
                    return true;
                }

                // En Passant for White Pawn
                if (board.getPiece(endRow - 1, endCol) instanceof Pawn 
                        && board.getPiece(endRow - 1, endCol).getColor() == Player.black
                        && board.getPiece(endRow - 1, endCol) == board.getPrevPiece()
                        && board.getPiece(endRow - 1, endCol).getMoves() == 1)
                {
                    board.setPiece(endRow - 1, endCol, null);
                    return true;
                }
            }
        } 
        
        // Black pawn
        else 
        { 
            if (colDiff == 0) 
            { 
                if (rowDiff == -1 && board.getPiece(endRow, endCol) == null) 
                { 
                    return true;
                } 
                
                else if (rowDiff == -2 && startRow == 6 && board.getPiece(endRow, endCol) == null && board.getPiece(endRow + 1, endCol) == null)
                { 
                    return true;
                }

            }
            
            else if (Math.abs(colDiff) == 1 && rowDiff == -1) 
            { 
            
                if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getColor() == Player.white) 
                {
                    return true;
                }
                
                
                if (board.getPiece(endRow + 1, endCol) instanceof Pawn 
                        && board.getPiece(endRow + 1, endCol).getColor() == Player.white
                        && board.getPiece(endRow + 1, endCol) == board.getPrevPiece()
                        && board.getPiece(endRow + 1, endCol).getMoves() == 1)
                {
                    board.setPiece(endRow + 1, endCol, null);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return color == Player.white ? PieceType.WP : PieceType.BP;
    }

    @Override
    protected boolean pathIsBlocked(int startRow, int startCol, int endRow, int endCol, Board board) {
        
        if (endCol == startCol) 
        {
            int rowDirection = (endRow > startRow) ? 1 : -1;
        
            for (int i = startRow + rowDirection; i != endRow; i += rowDirection) 
            {
                if (board.getPiece(i, startCol) != null) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void setEnpassant(boolean enpassant) {
        this.enpassant = enpassant;
    }

    public boolean getEnpassant() {
        return this.enpassant;
    }
}
