import javafx.scene.paint.Color;

public class Disk {

	
	private Color diskColor;
	private int diskRow;
	private int diskCol;
	
	
	
	public Disk(Color newDiskColor, int newDiskRow, int newDiskCol) {
		diskColor = newDiskColor;
		diskRow = newDiskRow;
		diskCol = newDiskCol;
	}
	
	public Color getColor(){
		return diskColor;
	}
}
