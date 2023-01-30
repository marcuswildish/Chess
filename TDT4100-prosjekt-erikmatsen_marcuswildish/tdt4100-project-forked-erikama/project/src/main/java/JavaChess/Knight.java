package JavaChess;
import javafx.scene.image.ImageView;

public class Knight extends Piece {
	
	
	public Knight(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);		
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		if (prevX == newX + 1 || prevX == newX - 1) {
			if (prevY == newY - 2 || prevY == newY + 2) {
				return true;
			}
		}
		if (prevX == newX + 2 || prevX == newX - 2) {
			if (prevY == newY - 1 || prevY == newY + 1) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		if (currentX == kingX + 1 || currentX == kingX - 1) {
			if (currentY == kingY - 2 || currentY == kingY + 2) {
				return true;
			}
		}
		if (currentX == kingX + 2 || currentX == kingX - 2) {
			if (currentY == kingY - 1 || currentY == kingY + 1) {
				return true;
			}
		}
		return false;
	}
}
