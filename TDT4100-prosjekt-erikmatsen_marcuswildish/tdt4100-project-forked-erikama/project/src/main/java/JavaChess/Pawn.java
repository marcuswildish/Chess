package JavaChess;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {

	public Pawn(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		boolean isFirstMove;
		if ((prevY == 1 && this.getColor() == 1) || (prevY == 6 && this.getColor() == 0)) {
			isFirstMove = true;
		}
		else {
			isFirstMove = false;
		}
		Square[][] currentPosition = this.getPosition();
		if (newX == prevX && newY == prevY + 2 && this.getColor() == 1) { //Trying to push 2 squares on first move for black
			if (isFirstMove && !currentPosition[newX][newY].isOccupied()) {
				return true;
			}
			return false;
		}
		if (newX == prevX && newY == prevY - 2 && this.getColor() == 0) { //Trying to push 2 squares on first move for white
			if (isFirstMove && !currentPosition[newX][newY].isOccupied()) {
				return true;
			}
			return false;
		}
		if (newY == prevY + 1 && this.getColor() == 1) {
			if (newX == prevX + 1 || newX == prevX - 1) { //Trying to capture diagonally for black.
				if (currentPosition[newX][newY].isOccupied()) {
					if (currentPosition[newX][newY].getPiece().getColor() == 0) { //Can only capture diagonally if opposite piece color.
						return true;
					}
					return false;
				}
			}
			if (newX == prevX) { //Trying to push pawn forward
				if (currentPosition[newX][newY].isOccupied()) {
					return false;
				}
				else {
					return true;
				}
			}
			
		}
		else if (newY == prevY - 1 && this.getColor() == 0) {
			if (newX == prevX + 1 || newX == prevX - 1) { //Trying to capture diagonally for white.
				if (currentPosition[newX][newY].isOccupied()) {
					if (currentPosition[newX][newY].getPiece().getColor() == 1) { //Can only capture diagonally if opposite piece color.
						return true;
					}
					else {
						return false;
					}
				}
			}
			if (newX == prevX) { //Trying to push pawn forward
				if (currentPosition[newX][newY].isOccupied()) {
					return false;
				}
				else {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	@Override
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		Square[][] currentPosition = this.getPosition();
		if (kingY == currentY + 1 && this.getColor() == 1) {
			if (kingX == currentX + 1 || kingX == currentX - 1) { //Trying to capture diagonally for black.
				if (currentPosition[kingX][kingY].isOccupied()) {
					if (currentPosition[kingX][kingY].getPiece().getColor() == 0) { //Can only capture diagonally if opposite piece color.
						return true;
					}
					return false;
				}
			}
			else if (kingY == currentY - 1 && this.getColor() == 0) {
				if (kingX == currentX + 1 || kingX == currentX - 1) { //Trying to capture diagonally for white.
					if (currentPosition[kingX][kingY].isOccupied()) {
						if (currentPosition[kingX][kingY].getPiece().getColor() == 1) { //Can only capture diagonally if opposite piece color.
							return true;
						}
						else {
							return false;
						}
					}
				}
				if (kingX == currentX) { //Trying to push pawn forward
					if (currentPosition[kingX][kingY].isOccupied()) {
						return false;
					}
					else {
						return true;
					}
				}
				
			}
		}
		return false;
	}

}
