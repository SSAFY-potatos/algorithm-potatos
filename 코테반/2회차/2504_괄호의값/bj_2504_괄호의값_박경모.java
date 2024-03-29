import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        int Ans = 0;
        int cnt = 1; 
        
        Loop1:
        for(int i=0; i<s.length(); i++) {
            char temp = s.charAt(i);
                switch(temp) {
                case '(':
                    stack.push(temp);
                    cnt *= 2;
                    break;
                case ')':
                    if(stack.isEmpty() || stack.peek() != '(') {
                        Ans = 0;
                        break Loop1;
                    }
                    
                    if(s.charAt(i-1) == '(') {
                    	Ans += cnt;
                    }
                    stack.pop();
                    cnt /= 2;
                    break;
                case '[':
                    stack.push(temp);
                    cnt *= 3;
                    break;
                case ']':
                    if(stack.isEmpty() || stack.peek()!= '[') {
                        Ans = 0;
                        break Loop1;
                    }
                    
                    if(s.charAt(i-1) == '[') {
                    	Ans += cnt;
                    }
                    stack.pop();
                    cnt /=3;
                    break;
                }
            } 
            System.out.println(!stack.isEmpty() ? 0 :Ans);
        }

 
    
}