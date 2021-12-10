package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Knight implements Piece {
	
	private final Color color;
	private final ImageIcon ii;
	
	public Knight(Color color) {
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White Knight.png");
		} else {
			ii = new ImageIcon("src/resources/Black Knight.png");
		}
	}

	@Override
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArrayList<int[]>> getCandidateMoves(int row, int col) {
		// TODO Auto-generated method stub
		return null;
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
