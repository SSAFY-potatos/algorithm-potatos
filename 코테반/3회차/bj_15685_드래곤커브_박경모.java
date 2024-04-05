//package baekjoon;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] grid = new boolean[101][101]; // 드래곤 커브를 그릴 2차원 boolean 배열
		//read input -> process
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			draw(grid, x, y, d, g); // 좌표(점) -> 배열로 변환하면서 +1씩 해주기

		}
		//정사각형 개수 체크 
		int Ans = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(grid[i][j] && grid[i+1][j]
						&&grid[i][j+1] && grid[i+1][j+1]) {
					Ans++;
				}	
			}
		}
		System.out.println(Ans);

	}
	
	static void draw(boolean[][] grid, int x, int y, int d, int g) {
		// 0 : →, 90도 회전해서 끝점에 붙이면 1 : ↑
		// 1 : ↑, 90도 회전해서 끝점에 붙이면 2 : ←
		// 2 : ←, 90도 회전해서 끝점에 붙이면 3 : ↓
		// 3 : ↓, 90도 회전해서 끝점에 붙이면 0 : →
		
		int arrSize = (int)Math.pow(2, g);
		int[] dirArr = new int[arrSize];
		int tempDir; // temp 변수
		dirArr[0] = d; // 초기 방향 삽입
		
		// dirArr에 값 넣기
		for(int i=0; i<g; i++) { // 세대 만큼 iteration
			int pushiter = (int)Math.pow(2, i); // 1, 2, 4... 
			for(int j=0; j<pushiter; j++) {
				tempDir = dirArr[pushiter-j-1]; // 직전 값 불러오기 
				if(tempDir<=2) {
					dirArr[pushiter+j] = tempDir + 1;
				}else if(tempDir == 3) {
					dirArr[pushiter+j] = 0;
				}else {
					System.out.println("error");
				}
			}	
		}
		
		//grid에 드래곤커브 점찍기
		grid[y][x] = true;
		for(int i=0; i<arrSize; i++) {
			tempDir = dirArr[i];
			switch(tempDir) {
			case 0:
				grid[y][x+1] = true;
				x++;
				break;
			case 1:
				grid[y-1][x] = true;
				y--;
				break;
			case 2:
				grid[y][x-1] = true;
				x--;
				break; 
			case 3: 
				grid[y+1][x] = true;
				y++;
				break; 
			}
		}
		
		
	}
}
