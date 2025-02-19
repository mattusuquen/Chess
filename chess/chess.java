package chess;

import chess.ReturnPlay.Message;

// @author Matthew Usuquen, 

public class Chess {

    enum Player { white, black }
    private static Board board;
	
		/**
		 * Plays the next move for whichever player has the turn.
		 * 
		 * @param move String for next move, e.g. "a2 a3"
		 * 
		 * @return A ReturnPlay instance that contains the result of the move.
		 *         See the section "The Chess class" in the assignment description for details of
		 *         the contents of the returned ReturnPlay instance.
		 */
		public static ReturnPlay play(String move) {
	
			/* FILL IN THIS METHOD */
			
			/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
			/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
			ReturnPlay returnPlay = new ReturnPlay();
			int startRow = getMove(move)[1];
			int startCol = getMove(move)[0];
			int endRow = getMove(move)[3];
			int endCol = getMove(move)[2];
			System.out.println(startRow + " " + startCol + " " + endRow + " " + endCol);
			board.movePiece(startRow, startCol, endRow, endCol);
			returnPlay.message = null;
			returnPlay.piecesOnBoard = board.getPieces();
			return returnPlay;
		}
		
		public static int[] getMove(String move){
			int[] moveArray = new int[4];
			moveArray[0] = move.charAt(0) - 'a';
			moveArray[1] = move.charAt(1) - '1';
			moveArray[2] = move.charAt(3) - 'a';
			moveArray[3] = move.charAt(4) - '1';
			return moveArray;
		}
		
		/**
		 * This method should reset the game, and start from scratch.
		 */
		public static void start() {
			board = new Board();
	}
}
