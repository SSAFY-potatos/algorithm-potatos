import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = 1;
		StringBuilder sb = new StringBuilder();
		
		while(true) {

			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			int maxDay;
			if(L==0 && P==0 && V==0)
				break;
//			System.out.println(L + " " + P + " " + V);
			sb.append("Case ").append(tc).append(": ");
			if(V%P > L) {
				maxDay = L + (V/P*L);
			}else {
				maxDay = (V%P) + (V/P*L);
			}
			
			sb.append(maxDay).append("\n");
			tc++;
		}
		System.out.println(sb);
	}

}
