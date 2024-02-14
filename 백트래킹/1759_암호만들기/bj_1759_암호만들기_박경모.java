import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] alphabet;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alphabet = new char[C];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphabet);
//		System.out.println(Arrays.toString(alphabet));
		printComb(0, new char[L], 0);
		System.out.print(sb);
	}
	
	static void printComb(int toSelect, char[] Selected, int startIdx) {
		int cnt1 = 0, cnt2 =0; // 모음, 자음
		if(toSelect == L) {
			for(int i=0; i<L; i++) {
				if(Selected[i] == 'a' || Selected[i] == 'e' || Selected[i] == 'i' ||
						Selected[i] == 'o' || Selected[i] == 'u') {
					cnt1++;
				}else {
					cnt2++;
				}
			}
			if(cnt1 >=1 && cnt2>=2) {
				for(int i=0; i<L; i++) {
					sb.append(Selected[i]);
				}
				sb.append('\n');
			}
			return;
		}
		
		for(int i = startIdx; i <C; i++) {
			Selected[toSelect] = alphabet[i];
			printComb(toSelect+1, Selected, i+1);
		}
	}

}
