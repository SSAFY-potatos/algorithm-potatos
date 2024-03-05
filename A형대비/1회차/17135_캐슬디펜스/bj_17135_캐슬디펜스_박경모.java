import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int[] archor;
	static int Ans = 0;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ArrayList<int[]> enemyArr = new ArrayList<int[]>();
		archor = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					enemyArr.add(new int[] { i, j });
				}
			}
		}

		comb(0, 0, enemyArr);
		System.out.println(Ans);

	}

	static void comb(int cnt, int start, ArrayList<int[]> enemy) {
		if (cnt == 3) {
//			System.out.println("Case : " + Arrays.toString(archor));
			Ans = Math.max(Ans, game(enemy));
			return;
		}

		for (int i = start; i < M; i++) {
			archor[cnt] = i;
			comb(cnt + 1, i + 1, enemy);
		}
	}

	static int game(ArrayList<int[]> Enemy) {
		ArrayList<int[]> enemy = new ArrayList<>();
		for (int[] arr : Enemy) {
		    int[] newArr = Arrays.copyOf(arr, arr.length);
		    enemy.add(newArr);
		}
		int cnt = 0;
		while(!enemy.isEmpty()) {
//			System.out.println("enemy before");
//			for(int[] e : enemy) {
//				System.out.print("["+e[0]+","+e[1]+"]");
//			}
//			System.out.println();
			int[][] toDel = new int[3][2];
			for(int i=0; i<3; i++) {
				Arrays.fill(toDel[i], -1);
			}
			for(int i=0; i<3; i++) { // 궁수
				int min = Integer.MAX_VALUE;
				for(int j=0; j<enemy.size(); j++) {
					int dist = Math.abs(enemy.get(j)[0] - N) +
							Math.abs(enemy.get(j)[1] - archor[i]);
					if(dist < min && dist <= D) { 
						min = dist; 
						toDel[i] = new int[] {enemy.get(j)[0], enemy.get(j)[1]};
					}else if(dist == min && dist <= D) {// 같으면 왼쪽 인덱스 
						if(toDel[i][1] > enemy.get(j)[1]) {
							toDel[i][1] = enemy.get(j)[1];
							toDel[i][0] = enemy.get(j)[0];
						}
					}
				}	
			}
//			System.out.println("toDel : "+Arrays.deepToString(toDel));
			for(int i=0; i<3; i++) {
				for(Iterator<int[]> iter = enemy.iterator(); iter.hasNext();) {
					int[] temp = iter.next();
					if(toDel[i][0] == temp[0] && toDel[i][1] == temp[1]) {
						iter.remove();
						cnt++;
					}
				}
			}
//			System.out.println("enemy after");
//			for(int[] e : enemy) {
//				System.out.print("["+e[0]+","+e[1]+"]");
//			}
//			System.out.println();
			for(int i=0; i<enemy.size(); i++) {
				enemy.get(i)[0] += 1;
			}
			
			Iterator<int[]> enemyiter = enemy.iterator();
			while(enemyiter.hasNext()) {
				int[] obj = enemyiter.next();
				if(obj[0] == N) {
					enemyiter.remove();
				}
			}
//			System.out.println("enemy after2");
//			for(int[] e : enemy) {
//				System.out.print("["+e[0]+","+e[1]+"]");
//			}
//			System.out.println();
//			System.out.println("cnt : "+ cnt);
		}
		
		return cnt; 
	}
}
