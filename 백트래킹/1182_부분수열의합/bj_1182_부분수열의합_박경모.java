import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S, result;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		boolean[] isSelected = new boolean[N]; 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		subSet(numbers, isSelected, 0);
		
		if(S == 0)
			System.out.println(result-1);
		else
			System.out.println(result);
		
		
	}
	
	static void subSet(int[] numbers, boolean[] isSelected, int count) {
		int sum = 0; 
		if(count == numbers.length) {
			for(int i=0; i<numbers.length; i++) {
				if(isSelected[i]) {
					sum+=numbers[i];
				}
			}
			if(S == sum)
				result++;
			return;
		}
		
		isSelected[count] = true;
		subSet(numbers, isSelected, count+1);
		isSelected[count] = false; 
		subSet(numbers, isSelected, count+1);
		
	}

}