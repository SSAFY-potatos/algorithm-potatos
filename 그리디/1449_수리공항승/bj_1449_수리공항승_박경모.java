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
		int N = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수 
		int L = Integer.parseInt(st.nextToken()); // 테이프의 길이 
		int[] toFix = new int[N]; 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			toFix[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(toFix);
			
		int idx = 0;
		int cnt = 1;
		float start = (float) (toFix[idx] - 0.5); 
		float end = (float) (start + L);
		while(idx < N-1) {
			if(toFix[idx+1] < end) {
				idx++;
			}else {
				start = (float) (toFix[idx+1] - 0.5); 
				end = (float) (start + L);
				cnt++;
				idx++;
			}
		}

		System.out.println(cnt);
		
		
	}

}
