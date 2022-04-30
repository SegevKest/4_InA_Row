import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;import sun.java2d.DefaultDisposerRecord;
import javafx.scene.layout.GridPane;



public class FourInARowController {

    @FXML
    private GridPane btnsGrid;

    @FXML
    private Button clearBtn;

    @FXML
    private GridPane gameBoard;

    private final double DISKRADIUS = 20, DISKXCOORD = 29, DISKYCOORD = 25;
    private final int COLUMNS = 7, ROWS = 6;
    
    
    private FourInRowLogic gameLogic;
    
    private Button[] btnsArrayGrid;
    private Pane[][] board;
    
    private int selectedColumToInsert;
    private boolean colorOfCircle;		// If true - will be red, false - yellow
    
    public void initialize() {
	
    	initButtons();
    	
    	initGrid();
    	
    	gameLogic = new FourInRowLogic();
    	
    	// Set the first player to play
    	colorOfCircle = true;
    }

    
    @FXML
    void clearGame(ActionEvent event) {

    	gameLogic.restartGame();
    	
    	for (int i= ROWS - 1; i>=0; i--) {
    		for (int j =0; j<COLUMNS; j++) {
    			
    			if (board[i][j].getChildren().size()>0)	
    				board[i][j].getChildren().remove(0);
    		}
    	} 	
    }
    
    
    private void initButtons() {
    	
    	btnsArrayGrid = new Button[COLUMNS];
    	
    	// Add the first row of the buttons + their text
    	for (int i = 0; i< COLUMNS; i++) {
    		
    		btnsArrayGrid[i] = new Button(""+(i+1));	
    		btnsArrayGrid[i].setPrefSize(btnsGrid.getPrefWidth() / COLUMNS, btnsGrid.getPrefHeight());
    		btnsGrid.add( btnsArrayGrid[i], i, 0);
    		
    		btnsArrayGrid[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					handleInsertButtonEvent(event);
				}

			});
    	}
    }
    
    
    private void initGrid() {
    	
    	board = new Pane[ROWS][COLUMNS];
    	
    	for (int i=0;i<ROWS; i++) {
    		for( int j=0;j<COLUMNS; j++) {
    			
    			board[i][j] = new Pane();
    			board[i][j].setStyle("-fx-border-color: black");
    			//board[i][j].setBorder(new Border(new BorderStroke(Color.BLACK, 
    					//BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    			board[i][j].setPrefSize((gameBoard.getPrefWidth() / COLUMNS), (gameBoard.getPrefWidth() / ROWS));
    			gameBoard.add(board[i][j], j, i);
    		}
    	}
    }
    

	private void handleInsertButtonEvent(ActionEvent event) {
		
		int resultOfInsert;
		Button clickedBtn = (Button)event.getSource();
		System.out.println();
		
		selectedColumToInsert = Integer.parseInt(clickedBtn.getText());

		insertNewDisk(selectedColumToInsert - 1);
		
		
		
		// call to the logic method to enter the new disk in the logic class
		// return the status that will continue
		// 4 - WIN , 2-3 continue, 1 found single, 0 - ended  
		
		resultOfInsert = gameLogic.addDiskToBoard( selectedColumToInsert - 1); 
		
		//Send POPUP message on WINNER.
		
		
	}
	
	
	private int getNextRowToInsertDisk(int currColumn) {
		
		int i=ROWS-1;
		
		while( board[ i][currColumn].getChildren().size() != 0) {
			i--;
		}

		return i;
	}
	
	
	// The method will insert the new Disk (Circle object) with its color to the selected column. Also will modify the color itself for next turn 
	private void insertNewDisk(int currColumn) {
	
		Circle newDisk = new Circle(DISKXCOORD, DISKYCOORD, DISKRADIUS);
		
		int rowToInsert = getNextRowToInsertDisk(currColumn);
		
		if( colorOfCircle ) 
			newDisk.setFill(Color.RED);
		else
			newDisk.setFill(Color.YELLOW);
		
		board[ rowToInsert][currColumn].getChildren().add(newDisk);
			
		colorOfCircle = !colorOfCircle;
	}

}
