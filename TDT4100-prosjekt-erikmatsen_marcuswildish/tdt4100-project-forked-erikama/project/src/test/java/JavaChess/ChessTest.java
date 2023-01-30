package JavaChess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import JavaChess.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


class ChessTest {


	@Test
	public void testInitialGameState() {
		int squareSize = 50;
		ChessController cont = new ChessController();
		GameState g = new GameState(cont);
		Piece blackBishop1 = new Bishop(2*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_bdt60.png"))));
		g.addPiece(blackBishop1);
		try {
			g.getPieces();
			fail("Should throw an illegal state exception, only one piece in play.");
		}
		catch (IllegalStateException e) {
			System.out.println("We expect to have an illegalStateException here.");
		}
		Piece whiteQueen = new Queen(3*squareSize, 7*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_qlt60.png"))));
		g.addPiece(whiteQueen);
		assertEquals(2, g.getPieces().size());
		try {
			g.InitializeGameState();
			fail("Should throw an illegal state exception, cannot have that many pieces in play");
		}
		catch (IllegalStateException e){
			System.out.println("We expect to have an illegalStateException here.");
		}
		g.removePiece(whiteQueen);
		g.removePiece(blackBishop1);
		g.InitializeGameState();
		assertEquals(32, g.getPieces().size());
		for (Piece p: g.getPieces()) {
			p.updateBoard(g.getSquares());
		}
		for (Piece p: g.getPieces()) {
			assertFalse(g.isPawnQueening(p));
		}
		assertFalse(g.isCheck(0));
		assertFalse(g.isCheck(1));
	}
	
	@Test
	public void testPawn() {
		int squareSize = 50;
		ChessController cont = new ChessController();
		GameState g = new GameState(cont);
		g.InitializeGameState();
		for (Piece p: g.getPieces()) {
			p.updateBoard(g.getSquares());
		}
		Piece pawn1 = g.getSquares()[1][1].getPiece();
		assertTrue(pawn1.isLegalMove(1, 1, 1, 3));
		assertTrue(pawn1.isLegalMove(1, 1, 1, 2));
		assertFalse(pawn1.isLegalMove(1, 1, 1, 5));
		assertFalse(pawn1.isLegalMove(1, 1, 2, 1));
	}
	
	@Test
	public void testBishop() {
		int squareSize = 50;
		ChessController cont = new ChessController();
		GameState g = new GameState(cont);
		g.InitializeGameState();
		for (Piece p: g.getPieces()) {
			p.updateBoard(g.getSquares());
		}
		Piece bishop = g.getSquares()[2][0].getPiece();
		assertFalse(bishop.isLegalMove(2, 0, 4, 2)); //Bishop should be blocked
		Piece pawn = g.getSquares()[3][1].getPiece();
		pawn.setY(200);
		g.getSquares()[3][1].freeSquare();
		bishop.updateBoard(g.getSquares());
		assertTrue(bishop.isLegalMove(2, 0, 4, 2)); //Bishop should not be blocked
	}
	
	@Test
	public void testCheckingKing() {
		int squareSize = 50;
		ChessController cont = new ChessController();
		GameState g = new GameState(cont);
		g.InitializeGameState();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.getSquare(i, j).freeSquare();
			}
		}
		Piece blackRook1 = new Rook(0, 6*squareSize, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_rdt60.png"))));
		Piece whiteKing = new King(3*squareSize, 7*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_klt60.png"))));
		Piece blackRook2 = new Rook(0, 7*squareSize, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_rdt60.png"))));
		g.getSquare(0, 6).setPiece(blackRook1);
		g.getSquare(3, 7).setPiece(whiteKing);
		g.getSquare(0, 6).getPiece().updateBoard(g.getSquares());
		g.getSquare(3, 7).getPiece().updateBoard(g.getSquares());
		assertTrue(g.getSquare(3, 7).getPiece().isLegalMove(3, 7, 4, 7)); //King is not in check when moving horizontally
		g.getSquare(0, 7).setPiece(blackRook2);
		g.getPieces().clear();
		g.addPiece(blackRook2); //Creating makeshift position with king on the last rank, and two rooks on the last two ranks
		g.addPiece(blackRook1);
		g.addPiece(whiteKing);
		g.getSquare(0, 6).getPiece().updateBoard(g.getSquares());
		g.getSquare(3, 7).getPiece().updateBoard(g.getSquares());
		g.getSquare(0, 7).getPiece().updateBoard(g.getSquares());
		assertTrue(g.isCheck(0));
	}
	
	@Test
	public void testFileSaving() throws IOException{
		
		GameWriter gameWriter= new GameWriter();
		try {
			gameWriter.exportGame("SomeText");
			fail("This will give us an illegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			
		}
		int currentID = gameWriter.getGameID();
		gameWriter.updateGame("e4");
		gameWriter.updateGame("e5");
		gameWriter.exportGame("Chessgame.txt");
		GameWriter nextGameWriter = new GameWriter();
		int nextID = nextGameWriter.getGameID();
		assertEquals(currentID + 1, nextID);
	}
	
	@Test
	public void testSquare() {
		int squareSize = 50;
		Piece p = new Queen(3*squareSize, 7*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_qlt60.png"))));
		Square s = new Square(2, 2, p, true);
		try {
			s.setPiece(p);
			fail("Cannot set same piece to square");
		}
		catch (IllegalArgumentException e) {
			
		}
		assertEquals(s.getX(), 2);
		assertEquals(s.getY(), 2);
		s.freeSquare();
		assertFalse(s.isOccupied());
		s.setPiece(p);
		assertTrue(s.isOccupied());
	}
}
