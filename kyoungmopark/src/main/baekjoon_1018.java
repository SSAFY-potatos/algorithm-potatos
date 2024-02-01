package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1018 {
	
	public static int checkChess(int rowStart, int colStart, char[][] chess) {
		int error1 = 0;
		int error2 = 0;
		String B = "BWBWBWBW";
		String W = "WBWBWBWB"; 
		char startChar = chess[rowStart][colStart];
		switch(startChar) {
		case 'B':
			for(int i = rowStart; i < rowStart+8; i++) {
				for(int j = colStart; j < colStart+8; j++) {
					if(rowStart%2 == i%2) {
						if(chess[i][j] != B.charAt(j-colStart))
							error1++;		
					}
					else {
						if(chess[i][j] != W.charAt(j-colStart))
							error1++;
					}
				}
			}
			break;
		case 'W':
			for(int i = rowStart; i < rowStart+8; i++) {
				for(int j = colStart; j < colStart+8; j++) {
					if(rowStart%2 == i%2) {
						if(chess[i][j] != W.charAt(j-colStart))
							error1++;		
					}
					else {
						if(chess[i][j] != B.charAt(j-colStart))
							error1++;
					}
				}
			}
			break;
		}
		switch(startChar) {
		case 'W':
			for(int i = rowStart; i < rowStart+8; i++) {
				for(int j = colStart; j < colStart+8; j++) {
					if(rowStart%2 == i%2) {
						if(chess[i][j] != B.charAt(j-colStart))
							error2++;		
					}
					else {
						if(chess[i][j] != W.charAt(j-colStart))
							error2++;
					}
				}
			}
			break;
		case 'B':
			for(int i = rowStart; i < rowStart+8; i++) {
				for(int j = colStart; j < colStart+8; j++) {
					if(rowStart%2 == i%2) {
						if(chess[i][j] != W.charAt(j-colStart))
							error2++;		
					}
					else {
						if(chess[i][j] != B.charAt(j-colStart))
							error2++;
					}
				}
			}
			break;
		}
		return Math.min(error1, error2);
	}
	

	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int error = 0;
		
		char[][] chessInput = new char[row][col];
		
		for(int i=0; i < row; i++) {
			String input = br.readLine();
			for(int j=0; j < col; j++) {
				chessInput[i][j] = input.charAt(j);
			}
		}
		int answer = checkChess(0, 0, chessInput);
		for(int i = 0; i<= row - 8; i++) {
			for(int j =0; j <=col -8; j++) {
				error = checkChess(i, j, chessInput);
				if(error < answer) {
					answer = error;
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
