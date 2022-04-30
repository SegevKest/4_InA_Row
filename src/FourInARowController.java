import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;import sun.java2d.DefaultDisposerRecord;
import javafx.scene.layout.GridPane;

// The class will represent the Game board
// It contains all methods and properties of the game, the players and the buttons 
// Thru interacting with the UI the user will be able to play the game.
public class FourInARowController {

    private final double DISKRADIUS = 20, DISKXCOORD = 29, DISKYCOORD = 25;
    private final int COLUMNS = 7, ROWS = 6;
    
    @FXML private GridPane btnsGrid;

    @FXML private Button clearBtn;

    @FXML private GridPane gameBoard;
        
    private FourInRowLogic gameLogic;
    
    private Button[] btnsArrayGrid;
    private Pane[][] board;
    
    private int selectedColumToInsert;
    private boolean colorOfCircle;		// If true - will be red, false - yellow
    
    
    // Initialize method - intialize the  buttons, the grid and logic part and set the first turn
    public void initialize() {
	
    	initButtons();
    	
    	initGrid();
    	
    	gameLogic = new FourInRowLogic();
    	
    	// Set the first player to play
    	colorOfCircle = true;
    	
    	System.out.println("\nPlayer 1 starts the game ");
    }

    
    // the method will be invoked after clicking on the clear button.
    // release all insert buttons that were disable after the win announced
    @FXML
    void clearGame(ActionEvent event) {

    	gameLogic.restartGame();
    	
    	for (int i= ROWS - 1; i>=0; i--) {
    		for (int j =0; j<COLUMNS; j++) {
    			
    			if (board[i][j].getChildren().size()>0)	
    				board[i][j].getChildren().remove(0);
    		}
    	} 
    	
    	for (int i = 0; i<COLUMNS; i++)
    		btnsArrayGrid[i].setDisable(false);
    	
		if( colorOfCircle ) 
			System.out.println("\nPlayer 1 starts the game ");
		else
			System.out.println("\nPlayer 2 starts the game ");
    }
    
    
    // the method will initialize the buttons for each column and will add the function that will invoke on every click
    private void initButtons() {
    	
    	btnsArrayGrid = new Button[COLUMNS];
    	// Add the first row of the buttons + their text
    	for (int i = 0; i< COLUMNS; i++) {
    		
    		btnsArrayGrid[i] = new Button(""+(i+1));	
    		btnsArrayGrid[i].setPrefSize(btnsGrid.getPrefWidth() / COLUMNS, btnsGrid.getPrefHeight());
    		btnsGrid.add( btnsArrayGrid[i], i, 0);
    		
    		btnsArrayGrid[i].setOnAction(new EventHandler<ActionEvent>() {
				
    			// the handleInsertButtonEvent will return true if there was a win- false if not.
				@Override
				public void handle(ActionEvent event) {
					boolean resultOfGame = handleInsertButtonEvent(event);
					
					if (resultOfGame) 
						handleWin(); 
				}
			});
    	}
    }
    
    
    // the method will initialize the grid - adding the Pane to every cell + adding its border
    private void initGrid() {
    	
    	board = new Pane[ROWS][COLUMNS];
    	
    	for (int i=0;i<ROWS; i++) {
    		for( int j=0;j<COLUMNS; j++) {
    			
    			board[i][j] = new Pane();
    			board[i][j].setStyle("-fx-border-color: black");
    			board[i][j].setPrefSize((gameBoard.getPrefWidth() / COLUMNS), (gameBoard.getPrefWidth() / ROWS));
    		
    			gameBoard.add(board[i][j], j, i);
    		}
    	}
    }
    
    // the handle of each button - when we initialize the buttons array
	private boolean handleInsertButtonEvent(ActionEvent event) {
		
		boolean resultOfInsert;
		Button clickedBtn = (Button)event.getSource();
		
		selectedColumToInsert = Integer.parseInt(clickedBtn.getText());

		insertNewDisk(selectedColumToInsert - 1 , clickedBtn);
		
		resultOfInsert = gameLogic.addDiskToBoard( selectedColumToInsert - 1); 
		
		colorOfCircle = !colorOfCircle;
			
		return resultOfInsert;
	}
	
	// The method will get the next row to insert the disk to
	private int getNextRowToInsertDisk(int currColumn) {
		
		int i=ROWS-1;
		
		while( board[ i][currColumn].getChildren().size() != 0) {
			i--;
		}

		return i;
	}
	
	
	// The method will insert the new Disk (Circle object) with its color to the selected column. 
	// Also will modify the color itself for next turn  
	private void insertNewDisk(int currColumn, Button clickedBtn) {
	
		Circle newDisk = new Circle(DISKXCOORD, DISKYCOORD, DISKRADIUS);
		
		int rowToInsert = getNextRowToInsertDisk(currColumn);
		
		if( colorOfCircle ) 
			newDisk.setFill(Color.RED);
		else
			newDisk.setFill(Color.YELLOW);
			
		board[ rowToInsert][currColumn].getChildren().add(newDisk);
			
		// if the column is full - disable this button
		if (rowToInsert == 0)
			clickedBtn.setDisable(true);
	}
	
	
	// The method handles the winning scenario. it output to console a message and disabling all buttons
	private void handleWin() {
		
		String winner = "";
		
		if( !colorOfCircle ) 
			winner = "Player 1";
		else
			winner = "Player 2";
		
		System.out.println(winner + " has won the game! \nYou can clear to board to start a new game!");
	
    	for (int i = 0; i<COLUMNS; i++)
    		btnsArrayGrid[i].setDisable(true);
	}
}
