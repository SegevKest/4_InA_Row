
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
		countStrike = 0;
		currTurn = true;
	}
	
	public boolean foundWin() {
	
		
		return true;
	}
	
	
	public boolean checkWin(int stepToCheck, char playerToCheckStrike) {
	
		
		if (countStrike == 4 )
			return true;
		else {
			
			if(countStrike > 0) {
				
			}
		}
			
		return false;
	}
	
	

	public int addDiskToBoard(int columnToInsert) {
		
		int rowToInsertDisk = getNextRowToInsertDisk(columnToInsert);
		
		if (currTurn)	//Player A
			boardOfGame[rowToInsertDisk][columnToInsert] = 'A';
		else
			boardOfGame[rowToInsertDisk][columnToInsert] = 'B';
		
		
		
		printMatrix();
		
		
		currTurn = !currTurn;
				
		
		return 0;
		
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
		
		System.out.println(currColumn);
		int i=ROWS-1;
		
		while( boardOfGame[i][currColumn] != 0) {
			i--;
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
