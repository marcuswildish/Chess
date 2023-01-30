package JavaChess;

import java.io.IOException;

public interface fileHandler {
	
	public abstract String readFromFile(String file);
	public abstract void exportGame(String file) throws IOException;

}
