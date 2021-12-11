package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Rook implements Piece {
	
	private final Color color;
	private final ImageIcon ii;
	
	private boolean hasMoved = false;
	
	public Rook(Color color) {
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White Rook.png");
		} else {
			ii = new ImageIcon("src/resources/Black Rook.png");
		}
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col) {
		List<ArrayList<int[]>> returnList = new ArrayList<>();
		
		ArrayList<int[]> threatenedSquares = new ArrayList<>();
		int i = row;
		while (i > 0) {
			i--;
			threatenedSquares.add(new int[] { i, col });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		int j = col;
		while (j < 7) {
			j++;
			threatenedSquares.add(new int[] { row, j });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		i = row;
		while (i < 7) {
			i++;
			threatenedSquares.add(new int[] { i, col });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		j = col;
		while (j > 0) {
			j--;
			threatenedSquares.add(new int[] { row, j });
		}
		returnList.add(threatenedSquares);
		
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
