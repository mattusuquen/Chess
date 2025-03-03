package chess;

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

			if (move.equals("resign"))
			{
				ReturnPlay returnPlay = new ReturnPlay();
				returnPlay.message = board.getTurn() == Player.white ? ReturnPlay.Message.RESIGN_BLACK_WINS : ReturnPlay.Message.RESIGN_WHITE_WINS;
				returnPlay.piecesOnBoard = board.getPieces();
				return returnPlay;
			}
			int startRow = getMove(move)[1];
			int startCol = getMove(move)[0];
			int endRow = getMove(move)[3];
			int endCol = getMove(move)[2];

			boolean drawRequest = false; 
			String promotion = "";

			if((endRow == 0 || endRow == 7) && move.length() == 7)
			{
				promotion = move.substring(6).toUpperCase();
				System.err.println("Promotion:" + promotion);
			}

			if (move.endsWith(" draw?"))
			{
				drawRequest = true; 
			}
			
			ReturnPlay returnPlay = new ReturnPlay();
			returnPlay.message = board.movePiece(startRow, startCol, endRow, endCol, promotion,drawRequest);
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
		public String getPromotion(String move){

			if(move.length() == 7)
			{
				return move.charAt(6) + ""; 
			}
			
			return "Q";
		}

		public boolean isDraw(){
			return false;
		}
		
		/**
		 * This method should reset the game, and start from scratch.
		 */
		public static void start() {
			board = new Board();
	}
}
