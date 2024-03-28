import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> s = new Stack<>();

        int result = 0;
        int sum = 1;

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {
                s.push('(');

                sum *= 2;
            }
            else if(str.charAt(i) == '[') {
                s.push('[');

                sum *= 3;
            }
            else if(str.charAt(i) == ')') {
                if(s.isEmpty() || !s.contains('(')) {
                    result = 0;
                    break;
                }
                else if(str.charAt(i-1) == '(') {
                    result += sum;
                }
                sum /= 2;
                s.pop();
			}
			else if(str.charAt(i) == ']') {
				if(s.isEmpty() || !s.contains('[')) {
					result = 0;
					break;
				}
				else if(str.charAt(i-1) == '['){
					result += sum;
				}
				sum /= 3;
				s.pop();
			}
        }

        if(!s.isEmpty()) {
            result = 0;
        }
        
        System.out.println(result);
    }
}