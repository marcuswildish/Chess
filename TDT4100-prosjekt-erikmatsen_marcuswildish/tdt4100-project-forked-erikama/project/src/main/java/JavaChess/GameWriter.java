package JavaChess;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameWriter implements fileHandler{
	
	List<String> movesPlayed = new ArrayList<String>();
	
	private int GameID;
	
	private static final Map<Integer, String> columnToFile = new HashMap<Integer, String>() {{
        put(0, "a");
        put(1, "b");
        put(2, "c");
        put(3, "d");
        put(4, "e");
        put(5, "f");
        put(6, "g");
        put(7, "h");
    }};
    
	private static final Map<String, String> pieceToLetter = new HashMap<String, String>() {{
        put("JavaChess.Queen", "Q");
        put("JavaChess.King", "K");
        put("JavaChess.Knight", "N");
        put("JavaChess.Bishop", "B");
        put("JavaChess.Rook", "R");
    }};
    
    public GameWriter() {
    	this.GameID=Integer.parseInt(readFromFile("ChessGame.txt"));
    }
	
    protected String getFile(int key) {
    	return columnToFile.get(key);
    }
    
    protected String getPieceLetter(String key) {
    	return pieceToLetter.get(key);
    }
    
    protected void updateGame(String move) {
    	this.movesPlayed.add(move);
    }
    
    //Writer the moves in the game to file
    public void exportGame(String file) throws IOException {
    	if (file != "Chessgame.txt") {
    		throw new IllegalArgumentException("Do not create a new file, but rather write to existing file Chessgame.txt");
    	}
    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
    	String log ="gameID: " + (GameID+1)+ "\n" +"White          Black \n\n";
    	int alternate = 0;
    	for (String move : this.movesPlayed) {
    		if(alternate % 2 == 0) {
    			int spacelength = 15 - move.length();
    			String space="";
    			for (int i=0;i<spacelength;i++) {
    				space+=" ";
    			}
    			log += move+space;
    		}
    		else {
    			log += move + "\n";
    		}
    		alternate++;
    		
    	}
    	writer.write(log);
    	System.out.println("Game exported!");
    	writer.close();
    }
    
    protected String getMoveString(int gridx, int gridy, Piece p, boolean takes) {
    	String file = getFile(gridx);
		String pieceLetter;
		if (p.getClass().getName() == "JavaChess.Pawn") {
			pieceLetter = "";
		}
		else {
			pieceLetter = getPieceLetter(p.getClass().getName());
		}
		
		if (takes == false) {
			return pieceLetter + file + (8-gridy);
		}
		else {
			return pieceLetter +"x"+file + (8-gridy);
		}
		
		
		
		
    }
//Reads the GameID from ChessGame.txt file and updates the variable GameID to this int value
	@Override
	public String readFromFile(String file) {
		String id="";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
				String FirstLine=String.valueOf(reader.readLine());
				int LineSize=FirstLine.length();
				for (int i=8; i<LineSize; i++) {
					id+=String.valueOf(FirstLine.charAt(i));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	protected int getGameID() {
		return this.GameID;
	}

	
	
	

	
    
    

}
