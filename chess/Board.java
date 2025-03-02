package chess;

import chess.Chess.Player;
import java.util.ArrayList;

import chess.Chess.Player;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;
import java.lang.annotation.Target;

public class Board {

    private Player turn = Player.white;
    private static final int NOT_FOUND = -1;
    Piece[][] board;
    
    public Board(){
        board = new Piece[8][8];
        initializeBoard();
    }

    public void initializeBoard(){
        board = new Piece[8][8];
        board[0][0] = new Rook(Player.white);
        board[0][1] = new Knight(Player.white);
        board[0][2] = new Bishop(Player.white);
        board[0][3] = new Queen(Player.white);
        board[0][4] = new King(Player.white);
        board[0][5] = new Bishop(Player.white);
        board[0][6] = new Knight(Player.white);
        board[0][7] = new Rook(Player.white);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.white);
        }

        board[7][0] = new Rook(Player.black);
        board[7][1] = new Knight(Player.black);
        board[7][2] = new Bishop(Player.black);
        board[7][3] = new Queen(Player.black);
        board[7][4] = new King(Player.black);
        board[7][5] = new Bishop(Player.black);
        board[7][6] = new Knight(Player.black);
        board[7][7] = new Rook(Player.black);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Player.black);
        }
    }

    public Piece getPiece(int row, int col){
        return board[row][col];
    }

    public void setTurn(Player player){
        turn = player;
    }

    public ArrayList<ReturnPiece> getPieces(){
        ArrayList<ReturnPiece> pieces = new ArrayList<ReturnPiece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) continue;
                Piece piece = board[i][j];
                PieceType pieceType = piece.getPieceType();
                PieceFile pieceFile = getFile(j);
                int pieceRank = i+1;
                ReturnPiece returnPiece = new ReturnPiece();
                returnPiece.pieceType = pieceType;
                returnPiece.pieceFile = pieceFile;
                returnPiece.pieceRank = pieceRank;
                pieces.add(returnPiece);
                
            }
        }

        return pieces;
    }

    public ReturnPiece.PieceFile getFile(int col){
        switch(col){
            case 0:
                return ReturnPiece.PieceFile.a;
            case 1:
                return ReturnPiece.PieceFile.b;
            case 2:
                return ReturnPiece.PieceFile.c;
            case 3:
                return ReturnPiece.PieceFile.d;
            case 4:
                return ReturnPiece.PieceFile.e;
            case 5:
                return ReturnPiece.PieceFile.f;
            case 6:
                return ReturnPiece.PieceFile.g;
            case 7:
                return ReturnPiece.PieceFile.h;
            default:
                return null;
        }
    }

    public Player getTurn(){
        return turn;
    }

    public void setPiece(int row, int col, Piece piece){
        board[row][col] = piece;
    }

    public Message movePiece(int startRow, int startCol, int endRow, int endCol){
        Piece pieceToMove = getPiece(startRow, startCol);

        //1. Check if piece exists
        if(pieceToMove == null) return Message.ILLEGAL_MOVE; 

        //2. Check PLayer Turn 
        if(!pieceToMove.isTurn(this)) return  Message.ILLEGAL_MOVE; 

        //3. Check move validity 
        if(!pieceToMove.isValidMove(startRow, startCol, endRow, endCol, this)) return  Message.ILLEGAL_MOVE;

        //4. Check if Piece is attacking its own 
        Piece target = getPiece(endRow, endCol);
        if(target != null && target.getColor() == pieceToMove.getColor())  return Message.ILLEGAL_MOVE; 

        //5. En Passant

        
        //6. Castling 


        //7. Move piece 
        board[startRow][startCol] = null; 
        board[endRow][endCol] = pieceToMove;
        pieceToMove.move();

        //8. Pawn Promotion 
        // Check if we have input from Chess 
        // Chess Returns letter 
        // cast letter into piece 
         
        if (pieceToMove instanceof Pawn && (endRow == 0 || endRow == 7)) 
        {
            board[endRow][endCol] = new Queen(pieceToMove.getColor()); 
        }

        //9. Change turn
        turn = (turn == Player.white) ? Player.black : Player.white; 

        //10. Check Game End 
        if (isCheckmate()) return (turn == Player.white) ? Message.CHECKMATE_BLACK_WINS : Message.CHECKMATE_WHITE_WINS;
        if (isStalemate()) return Message.STALEMATE;
        
        if (isDraw()) return Message.DRAW;
        
        if (isInCheck()) return Message.CHECK;
        
        //11. Move Complete 
        return  null; 


        
        

    }

    private int[] findPiece(PieceType pieceType){
        int[] location = new int[2];

        location[0] = NOT_FOUND;
        location[1] = NOT_FOUND;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) continue;
                if (piece.getPieceType() == pieceType){
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }

    public boolean isCheckmate() {
        if (!isInCheck()) return false;
    
        PieceType kingType = (turn == Player.white) ? PieceType.WK : PieceType.BK;
        int[] kingPos = findPiece(kingType);
        
        int kingRow = kingPos[0];
        int kingCol = kingPos[1];
    
        if (kingRow == NOT_FOUND || kingCol == NOT_FOUND) return false; // Should not happen
    
        // Iterate over all the current player's pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null || piece.getColor() != turn) continue;
    
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (piece.isValidMove(i, j, x, y, this)) {
                            Piece original = board[x][y]; // Store captured piece if any
                            board[x][y] = piece;
                            board[i][j] = null; // Move piece temporarily
    
                            boolean stillInCheck = isInCheck();
    
                            // Undo move
                            board[i][j] = piece;
                            board[x][y] = original;
    
                            if (!stillInCheck) return false; // Found a legal escape move
                        }
                    }
                }
            }
        }
        return true; // No legal escape moves → Checkmate
    }
    
    public boolean isStalemate() {
        if (isInCheck()) return false; // Stalemate cannot happen if in check
    
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null || piece.getColor() != turn) continue;
    
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (piece.isValidMove(i, j, x, y, this)) {
                            return false; // Found at least one legal move
                        }
                    }
                }
            }
        }
        return true; // No legal moves → stalemate
    }
    
    private boolean isDraw(){
        return false;
    }

    public boolean isInCheck(){
        int[] kingLocation = findPiece(turn == Player.white ? PieceType.WK : PieceType.BK);
        int kingRow = kingLocation[0];
        int kingCol = kingLocation[1];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) continue;
                if (piece.getColor() == turn) continue;
                if (piece.isValidMove(i, j, kingRow, kingCol, this)) {
                    // Get piece that puts king in check
                    return true;
                }
            }
        }
        return false;
    }
}
