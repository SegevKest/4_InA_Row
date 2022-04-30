
public class FourInRowLogic {

	// The matrix will be char type - player A - player B.
	// We will check if the there is a strike with those letters.
	private char[][] boardOfGame;	
	
	private int countStrike;
	
    private final int COLUMNS = 7, ROWS = 6;
   
    // The turn will be also boolean - true - Player A, false- Player B
    private boolean currTurn;
    
	public FourInRowLogic() {
		
		boardOfGame = new char[ROWS][COLUMNS];
		countStrike = 1;
		currTurn = true;
	}
	
	/*
	 * public boolean foundWin( int colToCheck, int rowToCheck, char
	 * playerToCheckStrike) {
	 * 
	 * String directionCheck = checkStrikeDirection(colToCheck, rowToCheck,
	 * playerToCheckStrike);
	 * 
	 * if (directionCheck!=null) { return checkWin(colToCheck, rowToCheck,
	 * playerToCheckStrike, directionCheck); } else return false; }
	 * 
	 * public String checkStrikeDirection(int colToCheck, int rowToCheck, char
	 * playerToCheckStrike) {
	 * 
	 * String stepToReturn = null;
	 * 
	 * if ( (colToCheck - 1 >= 0) && boardOfGame[rowToCheck ][colToCheck - 1] ==
	 * playerToCheckStrike) { stepToReturn = "LR"; // Left Row - continue check to
	 * left in the row (j-1) } else if ( (colToCheck - 1 >= 0 && rowToCheck + 1 <
	 * ROWS) && boardOfGame[rowToCheck + 1 ][colToCheck - 1] == playerToCheckStrike)
	 * { stepToReturn = "LD"; // Left Diagonal - continue check to left in diagonal
	 * (i+1)(j-1) } else if ( (rowToCheck + 1 < ROWS) && boardOfGame[rowToCheck + 1
	 * ][colToCheck] == playerToCheckStrike) { stepToReturn = "C"; // Column -
	 * continue check in the column (i+1)(j) } else if ( (colToCheck + 1 < COLUMNS
	 * && rowToCheck + 1 < ROWS) && boardOfGame[rowToCheck + 1 ][colToCheck + 1] ==
	 * playerToCheckStrike) { stepToReturn = "RD"; // Right Diagonal - continue
	 * check to Right in diagonal (i+1)(j+1) } else if ( (colToCheck + 1 < COLUMNS )
	 * && boardOfGame[rowToCheck ][colToCheck + 1] == playerToCheckStrike) {
	 * stepToReturn = "RR"; // Right Row - continue check to Right in row (i)(j+1) }
	 * 
	 * return stepToReturn; }
	 * 
	 * public boolean checkWin(int colToCheck, int rowToCheck, char
	 * playerToCheckStrike, String direction) {
	 * 
	 * 
	 * if (countStrike == 4 ) { System.out.println("Done!!!"); return true; } else {
	 * 
	 * if (boardOfGame[rowToCheck ][colToCheck] == playerToCheckStrike) {
	 * countStrike++;
	 * 
	 * switch(direction){
	 * 
	 * case ("LR"):{ colToCheck = colToCheck - 1; break; } case ("LD"):{ colToCheck
	 * = colToCheck - 1; rowToCheck = rowToCheck + 1; break; } case ("C"):{
	 * rowToCheck = rowToCheck + 1; break; } case ("RD"):{ colToCheck = colToCheck +
	 * 1; rowToCheck = rowToCheck + 1; break; } case ("RR"):{ colToCheck =
	 * colToCheck + 1; break; } }
	 * 
	 * return checkWin(colToCheck, rowToCheck, playerToCheckStrike, direction); }
	 * else return false; }
	 * 
	 * }
	 */
	
	public boolean checkForWin(char playerToCheckStrike ) {
		
		// Vertical check
		for( int i =0; i<ROWS;i++) {
			for (int j =0; j<COLUMNS - 3; j++) {
				if ( boardOfGame[i][j] == playerToCheckStrike && boardOfGame[i][j+1] == playerToCheckStrike  && boardOfGame[i][j+2] == playerToCheckStrike  && boardOfGame[i][j+3] == playerToCheckStrike)
					return true;
			}
		}
		
		// Horizontal check
		for( int i =0; i<ROWS - 3;i++) {
			for (int j =0; j<COLUMNS; j++) {
				if ( boardOfGame[i][j] == playerToCheckStrike && boardOfGame[i+1][j] == playerToCheckStrike  && boardOfGame[i+2][j] == playerToCheckStrike  && boardOfGame[i+3][j] == playerToCheckStrike)
					return true;
			}
		}
		
		// left to right diagonal descending
		for( int i = 3; i < ROWS; i++) {
			for (int j =0; j < COLUMNS - 3; j++) {
				if ( boardOfGame[i][j] == playerToCheckStrike && boardOfGame[i-1][j+1] == playerToCheckStrike  && boardOfGame[i-2][j+2] == playerToCheckStrike  && boardOfGame[i-3][j+3] == playerToCheckStrike)
					return true;
			}
		}
		
		// left to right diagonal ascending
		for( int i = 3; i < ROWS; i++) {
			for (int j =3; j < COLUMNS; j++) {
				if ( boardOfGame[i][j] == playerToCheckStrike && boardOfGame[i-1][j-1] == playerToCheckStrike  && boardOfGame[i-2][j-2] == playerToCheckStrike  && boardOfGame[i-3][j-3] == playerToCheckStrike)
					return true;
			}
		}
				
		return false;
	}

	public boolean addDiskToBoard(int columnToInsert) {
		
		int rowToInsertDisk = getNextRowToInsertDisk(columnToInsert);
		boolean foundWin;
		
		
		if (currTurn)	{
			//Player A	
			boardOfGame[rowToInsertDisk][columnToInsert] = 'A';
//			foundWin = foundWin(columnToInsert, rowToInsertDisk, 'A');
		}
		else	{
			boardOfGame[rowToInsertDisk][columnToInsert] = 'B';
//			foundWin = foundWin(columnToInsert, rowToInsertDisk, 'B');
		}
		
		printMatrix();
		
		if (currTurn)
			foundWin = checkForWin('A');
		else
			foundWin = checkForWin('B');
		
		currTurn = !currTurn;
		
		return foundWin;
	}
	
	// Method that will restart the matrix of the games
	public void restartGame() {
		
		for (int i = 0; i < boardOfGame.length; i++) {
			for (int j = 0; j < boardOfGame[i].length; j++) {
				
				boardOfGame[i][j] = 0;
			}
		}
		currTurn = true;
	}
	
	// Method to find the next row to insert the disk to
	private int getNextRowToInsertDisk(int currColumn) {
		
		/*
		 * int i= ROWS - 1;
		 * 
		 * while( boardOfGame[i][currColumn] != 0) { i--; }
		 */
		
		int i= 0;
		
		while( boardOfGame[i][currColumn] != 0) {
			i++;
		}


		return i;
	}
	
	
	private void printMatrix() {
		
		System.out.println("---------------");
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				
				System.out.print(boardOfGame[i][j] + " ");
			}
			System.out.println();
		}
	}

}
