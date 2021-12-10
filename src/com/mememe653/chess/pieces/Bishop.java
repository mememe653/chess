package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Bishop implements Piece {
	
	private final Color color;
	private final ImageIcon ii;
	
	public Bishop(Color color) {
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White Bishop.png");
		} else {
			ii = new ImageIcon("src/resources/Black Bishop.png");
		}
	}

	@Override
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col) {
		List<ArrayList<int[]>> returnList = new ArrayList<>();
		
		ArrayList<int[]> threatenedSquares = new ArrayList<>();
		int i = row;
		int j = col;
		while (i >= 0 && i < 8 && j >= 0 && j < 8) {
			i--;
			j--;
			threatenedSquares.add(new int[] { i, j });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		i = row;
		j = col;
		while (i >= 0 && i < 8 && j >= 0 && j < 8) {
			i++;
			j++;
			threatenedSquares.add(new int[] { i, j });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		i = row;
		j = col;
		while (i >= 0 && i < 8 && j >= 0 && j < 8) {
			i--;
			j++;
			threatenedSquares.add(new int[] { i, j });
		}
		returnList.add(threatenedSquares);
		
		threatenedSquares = new ArrayList<>();
		i = row;
		j = col;
		while (i >= 0 && i < 8 && j >= 0 && j < 8) {
			i++;
			j--;
			threatenedSquares.add(new int[] { i, j });
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

}
