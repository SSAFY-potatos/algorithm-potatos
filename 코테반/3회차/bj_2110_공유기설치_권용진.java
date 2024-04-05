import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int C;
	
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = arr[N - 1] + 1;
		
		int mid;
		while (left + 1 < right) {
			mid = (left + right) / 2;
			
			if (isValid(mid)) {
				left = mid;
				
			} else {
				right = mid;
			}
		}
		System.out.println(left);
	}
	
	static boolean isValid(int dist) {
		int cnt = 1;
		
		int left = arr[0];
		for (int i = 1; i < N; i++) {
			int right = arr[i];
			
			if (right - left >= dist) {
				if (++cnt == C) {
					return true;
				}
				left = right;
			}
		}
		return false;
	}
}