package com.mememe653.chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mememe653.chess.Board.BoardNum;

public class Chess extends JFrame {

	public Chess() {
		initUI();
	}
	
	private void initUI() {
		JPanel container = new JPanel();
		
		container.setLayout(new GridLayout(1, 2, 20, 20));
		container.add(new Board(BoardNum.One));
		container.add(new Board(BoardNum.Two));
		add(container);
		
		setResizable(false);
		pack();
		setTitle("Chess");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new Chess();
			frame.setVisible(true);
		});
	}
}
