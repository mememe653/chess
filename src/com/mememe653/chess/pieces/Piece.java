package com.mememe653.chess.pieces;

import java.awt.Image;

public interface Piece {

	Image getImage();
	int[][][] getThreatenedSquares(int row, int col);
	int[][][] getCandidateMoves(int row, int col);
}
