
public class FourInRowLogic {

	
	
	
	private char[][] boardOfGame;
	
	private int countStrike;
	
    private final int COLUMNS = 7, ROWS = 6;
    
    
	public FourInRowLogic() {
		
		boardOfGame = new char[6][7];
		countStrike = 0;
		
	}
	
	public boolean foundWin() {
			return true;
	}
	public boolean checkWin(int stepToCheck) {
	
		
		if (countStrike == 4 )
			return true;
		else {
			
			if(countStrike > 0) {
				
			}
		}
			
		return false;
	}
	
	
	public int addDiskToBoard() {
		
		return 0;
		
	}
	
	public void restartGame() {
		
		for (int i = 0; i < boardOfGame.length; i++) {
			for (int j = 0; j < boardOfGame[i].length; j++) {
				
				boardOfGame[i][j] = 0;
			}
		}
	}
	
	
}
