package JavaChess;

public class Square {
	
	private int xCoordinate;
	private int yCoordinate;
	private Piece p;
	private boolean isOccupied;
	
	public Square(int xCoordinate, int yCoordinate, Piece p, boolean isOccupied) {
		this.isOccupied = isOccupied;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.p = p;
	}
	
	protected void setPiece(Piece p) {
		if (getPiece() == p) {
			throw new IllegalArgumentException("Cannot set piece that is already there.");
		}
		this.p = p;
		this.isOccupied = true;
	}
	
	protected Piece freeSquare() {
		Piece p = getPiece();
		this.p = null;
		this.isOccupied = false;
		return p;
	}
	
	protected int getX() {
		return this.xCoordinate;
	}
	
	protected int getY() {
		return this.yCoordinate;
	}
	
	protected boolean isOccupied() {
		return this.isOccupied;
	}
	
	protected Piece getPiece() {
		return this.p;
	}

	

}
