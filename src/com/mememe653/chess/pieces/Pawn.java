package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

// TODO Set hasMoved to true when piece is moved.
// Could have move() function for each piece which is generally
// not implemented except by pawn, or maybe pawn should not inherit
// from piece.
// Also, pawn promotion, and en passant.

public class Pawn implements Piece {
	
	public enum Direction {
		Up,
		Down
	}
	
	private final Color color;
	private final ImageIcon ii;
	
	private final Direction direction;
	private boolean hasMoved = false;
	
	public Pawn(Color color, Direction direction) {
		this.direction = direction;
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White Pawn.png");
		} else {
			ii = new ImageIcon("src/resources/Black Pawn.png");
		}
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col) {
		List<ArrayList<int[]>> returnList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			returnList.add(new ArrayList<>());
		}
		if (direction.equals(Direction.Up)) {
			if (row > 0 && col > 0) {
				returnList.get(0).add(new int[] { row - 1, col - 1 });
			}
			if (row > 0 && col < 7) {
				returnList.get(1).add(new int[] { row - 1, col + 1 });
			}
		} else if (direction.equals(Direction.Down)) {
			if (row < 7 && col > 0) {
				returnList.get(0).add(new int[] { row + 1, col - 1 });
			}
			if (row < 7 && col < 7) {
				returnList.get(1).add(new int[] { row + 1, col + 1 });
			}
		}
		
		return returnList;
	}

	@Override
	public List<ArrayList<int[]>> getCandidateMoves(int row, int col) {
		List<ArrayList<int[]>> returnList = new ArrayList<>();
		returnList.add(new ArrayList<>());
		if (direction.equals(Direction.Up)) {
			if (row > 0) {
				returnList.get(0).add(new int[] { row - 1, col });
			}
		} else if (direction.equals(Direction.Down)) {
			if (row < 7) {
				returnList.get(0).add(new int[] { row + 1, col });
			}
		}
		if (!hasMoved) {
			if (direction.equals(Direction.Up)) {
				if (row > 1) {
					returnList.get(0).add(new int[] { row - 2, col });
				}
			} else if (direction.equals(direction.Down)) {
				if (row < 6) {
					returnList.get(0).add(new int[] { row + 2, col });
				}
			}
		}
		
		return returnList;
	}

	@Override
	public Image getImage() {
		return ii.getImage();
	}

	@Override
	public Color getColor() {
		return color;
	}

}
