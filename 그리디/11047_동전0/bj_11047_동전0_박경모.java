import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] coinArr = new int[N];
		for(int i =0; i<N; i++) {
			coinArr[i] = Integer.parseInt(br.readLine());
		}
		int cnt = 0;
		for(int i=N-1; i>=0; i--) {
			if(coinArr[i] > K)
				continue; 
			
			cnt += K / coinArr[i];
			K = K % coinArr[i];
		}
		
		System.out.println(cnt);
		
		
		
		
	}

}
