package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Board {
    Piece[][] board;
    
    public Board(){
        board = new Piece[8][8];
        initializeBoard();
    }

    public void initializeBoard(){
        board = new Piece[8][8];
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Knight(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Knight(Color.WHITE);
        board[0][7] = new Rook(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.WHITE);
        }

        board[7][0] = new Rook(Color.BLACK);
        board[7][1] = new Knight(Color.BLACK);
        board[7][2] = new Bishop(Color.BLACK);
        board[7][3] = new Queen(Color.BLACK);
        board[7][4] = new King(Color.BLACK);
        board[7][5] = new Bishop(Color.BLACK);
        board[7][6] = new Knight(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.BLACK);
        }
    }

    public Piece getPiece(int row, int col){
        return board[row][col];
    }

    public ArrayList<ReturnPiece> getPieces(){
        ArrayList<ReturnPiece> pieces = new ArrayList<ReturnPiece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) continue;
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

    public void setPiece(int row, int col, Piece piece){
        board[row][col] = piece;
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol){

        board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = null;
    }
}
