import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

public class Main {
	static int N, ANS;
	static int[] qArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		qArr = new int[N]; // Queen의 ROW 위치를 저장하는 배열
		dfs(0);
		System.out.println(ANS);
		
	}
	
	static void dfs(int cidx) {
		if(cidx == N) {
			ANS++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			qArr[cidx] = i;
			if(isPromising(cidx)) {
				dfs(cidx+1);
			}
		}
	}
	
	static boolean isPromising(int cidx) {
		for(int i=0; i<cidx; i++) {
			if(qArr[i] == qArr[cidx] || Math.abs(qArr[i] - qArr[cidx]) == cidx - i) {
				return false;
			}
		}
		return true;
	}
	

}
