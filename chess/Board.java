package chess;

import java.util.ArrayList;

import chess.Chess.Player;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Board {

    private Player turn = Player.white;
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
        // Change the ordering on this
        // Just general idea
        if (pieceToMove == null) return Message.ILLEGAL_MOVE;                                               // check if there is a piece to move
        if (!pieceToMove.isTurn(this)) return Message.ILLEGAL_MOVE;                                         // check if it is the turn of the piece
        if (!pieceToMove.isValidMove(startRow, startCol, endRow, endCol, this)) return Message.ILLEGAL_MOVE;// check if move is valid
        if (board[endRow][endCol] != null &&                                                                // check if the piece is trying to take its own piece
            board[endRow][endCol].getColor() == pieceToMove.getColor()) return Message.ILLEGAL_MOVE;

        // if not, move piece
        board[startRow][startCol] = null;
        board[endRow][endCol] = pieceToMove;
        pieceToMove.move(); // increment move counter

        // Turn successful, change turn
        turn = turn == Player.white ? Player.black : Player.white;
        
        if (isInCheck()) return Message.CHECK;
        

        // Piece was moved successfully
        return null;
    }

    public boolean isInCheck(){
        int kingRow = -1;
        int kingCol = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) continue;
                if (piece.getColor() != turn) continue;
                if (turn == Player.white) {
                    if (piece.getPieceType() == PieceType.WK){
                        kingRow = i;
                        kingCol = j;
                        break;
                    }
                } else {
                    if (piece.getPieceType() == PieceType.BK){
                        kingRow = i;
                        kingCol = j;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) continue;
                if (piece.getColor() == turn) continue;
                if (piece.isValidMove(i, j, kingRow, kingCol, this)) return true;
            }
        }
        return false;
    }
}
