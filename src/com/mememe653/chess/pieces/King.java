package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class King implements Piece {
	
	private final Color color;
	private final ImageIcon ii;
	
	private boolean hasMoved = false;
	
	public King(Color color) {
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White King.png");
		} else {
			ii = new ImageIcon("src/resources/Black King.png");
		}
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col) {
		List<ArrayList<int[]>> returnList = new ArrayList<>();
		
		if (row > 0 && col > 0) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row - 1, col - 1 });
			returnList.add(threatenedSquares);
		}
		if (row > 0) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row - 1, col });
			returnList.add(threatenedSquares);
		}
		if (row > 0 && col < 7) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row - 1, col + 1 });
			returnList.add(threatenedSquares);
		}
		if (col < 7) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row, col + 1 });
			returnList.add(threatenedSquares);
		}
		if (row < 7 && col < 7) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row + 1, col + 1 });
			returnList.add(threatenedSquares);
		}
		if (row < 7) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row + 1, col });
			returnList.add(threatenedSquares);
		}
		if (row < 7 && col > 0) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row + 1, col - 1 });
			returnList.add(threatenedSquares);
		}
		if (col > 0) {
			ArrayList<int[]> threatenedSquares = new ArrayList<>();
			threatenedSquares.add(new int[] { row, col - 1 });
			returnList.add(threatenedSquares);
		}
		
		return returnList;
	}

	@Override
	public List<ArrayList<int[]>> getCandidateMoves(int row, int col) {
		return getThreatenedSquares(row, col);
	}

	@Override
	public Image getImage() {
		return ii.getImage();
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void move() {
		hasMoved = true;
	}

}
