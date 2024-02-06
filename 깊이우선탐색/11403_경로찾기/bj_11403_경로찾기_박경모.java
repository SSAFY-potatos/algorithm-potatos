import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer> search = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int[][] adjArr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(adjArr[j][i] == 1 && adjArr[i][k] == 1) {
						adjArr[j][k] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(adjArr[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		
	}

}
