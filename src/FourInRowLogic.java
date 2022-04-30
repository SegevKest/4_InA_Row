// The class will be responsible for the Logic in this game.
// In shortly, it will handle the matrix, that is built as the UI matrix
// And will check after every insert if there is a win.
// The method will activate thru the UI of the Program.


public class FourInRowLogic {

	// The matrix will be char type - player A - player B.
	// We will check if the there is a strike with those letters.
	private char[][] boardOfGame;	
	
    private final int COLUMNS = 7, ROWS = 6;
   
    // The turn will be also boolean - true - Player A, false- Player B
    private boolean currTurn;
    
    
    // the constructor for this class - will create the matrix and turn the currTurn to the player
	public FourInRowLogic() {
		
		boardOfGame = new char[ROWS][COLUMNS];
		currTurn = true;
	}
	
	
	// The method will check every time for a winning strike.
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

	
	// the method will insert the new disk to the relevant cell in the matrix and check for wining strike.
	public boolean addDiskToBoard(int columnToInsert) {
		
		int rowToInsertDisk = getNextRowToInsertDisk(columnToInsert);
		boolean foundWin;
		
		if (currTurn)	{
			//Player A	
			boardOfGame[rowToInsertDisk][columnToInsert] = 'A';
			foundWin = checkForWin('A');
		}
		else	{
			boardOfGame[rowToInsertDisk][columnToInsert] = 'B';
			foundWin = checkForWin('B');
		}
		
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
	}
	
	
	// Method to find the next row to insert the disk to
	private int getNextRowToInsertDisk(int currColumn) {
		
		int i= 0;
		
		while( boardOfGame[i][currColumn] != 0) {
			i++;
		}

		return i;
	}
}
