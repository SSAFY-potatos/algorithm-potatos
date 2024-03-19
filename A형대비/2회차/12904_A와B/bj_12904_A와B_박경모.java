import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < T.length(); i++) {
			stack.push(T.charAt(i));
		}

		while (stack.size() > S.length()) {
			char c = stack.pop();
			if (c == 'A') {
				continue;
			} else if (c == 'B') {
				Stack<Character> temp = new Stack<Character>(); // 뒤집기 용 스택
				while (!stack.isEmpty()) {
					temp.push(stack.pop());
				}
				stack = temp;
			}
		}

		StringBuffer sb = new StringBuffer();
		Stack<Character> temp = new Stack<Character>(); // 뒤집기 용 스택
		while (!stack.isEmpty()) {
			temp.push(stack.pop());
		}
		stack = temp;
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		if (sb.toString().equals(S)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}
}
