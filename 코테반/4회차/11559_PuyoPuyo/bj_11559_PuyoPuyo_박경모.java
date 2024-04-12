import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static class Point{
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[12][6];
		int ANS = 0;
		for(int i=0; i<12; i++) {
			String s = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			// 출력용
//			for(int i= 0; i<12; i++) { 
//				for(int j=0; j<6; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			int flag = 0;
			boolean[][] visited = new boolean[12][6];
			boolean[] toPullDown = new boolean[6];
			
			for(int i= 11; i>=0; i--) { // 밑에서부터 탐색
				for(int j=0; j<6; j++) {
					if(!visited[i][j] && map[i][j] != '.') {
						flag += bfs(map, visited, new Point(i,j), toPullDown);
					}
				}
			}
			if(flag == 0) {
				break;
			}else {
				pullDown(map, toPullDown);
				ANS++;
			}
		}

		// 출력용
//		for(int i= 0; i<12; i++) { 
//			for(int j=0; j<6; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(ANS);
	}
	
	static int bfs(char[][] map, boolean[][] visited, Point start, boolean[] toPullDown) {
		Queue<Point> queue = new ArrayDeque<>();
		List<Point> toDelList = new ArrayList<>();
		queue.offer(start);
		toDelList.add(start);
		visited[start.y][start.x] = true;
		char initColor = map[start.y][start.x];
		int cnt = 1;
		while(!queue.isEmpty()) {
			Point temp = queue.poll();
			int cy = temp.y;
			int cx = temp.x;
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny>11 || nx<0 || nx>5)
					continue;
				
				if(!visited[ny][nx] && map[ny][nx] == initColor) {
					Point nextPoint = new Point(ny, nx);
					queue.offer(nextPoint);
					toDelList.add(nextPoint);
					visited[ny][nx] = true; 
					cnt++;
				}
			}
		}
		// 4개 이상 블럭은 터트리고 끄집어내리기
		if(cnt >=4 ) {
			for(Point ob: toDelList) {
				map[ob.y][ob.x] = '.';
				toPullDown[ob.x]= true; 
			}
		}
		
		return cnt>=4 ? 1 : 0;
	}
	
	static void pullDown(char[][] map, boolean[] toPullDown) {
		for(int i=0; i<6; i++) {
			if(toPullDown[i]) {
				for(int ground= 11; ground>=1; ground--) {
					for(int j=ground-1; j>=0; j--) {
						if(map[ground][i] == '.' && map[j][i] != '.') {
							map[ground][i] = map[j][i];
							map[j][i] = '.';
							ground--;
						}
						
					}
				}
			}
		}
	}
	

}
