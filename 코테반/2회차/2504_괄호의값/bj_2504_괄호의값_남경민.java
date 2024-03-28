import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj_2504_괄호의값_남경민 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Character> stack = new Stack<>();

        int temp = 1;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 괄호 시작
            if(c == '(') {
                temp*=2; //2곱하고 스택넣어줌
                stack.add(c);
            }
            else if(c=='[') {
                temp*=3; //3곱하고 스택넣어줌
                stack.add(c);
            }else {
                if(stack.isEmpty()) { //스택에 아무값도 없는경우, 잘못된 문자열임
                    result = 0;
                    break;
                }
                char out = stack.pop();
                if(c==')') {
                    if(out != '(') { //종료 조건
                        result = 0; break;
                    }
                    if(s.charAt(i-1) =='(') result+=temp;
                    temp/=2;

                }else if(c==']') {
                    if(out != '[') { //종료 조건
                        result = 0; break;
                    }
                    if(s.charAt(i-1) =='[') result+=temp;
                    temp/=3;
                }
            }

        }
        if(!stack.isEmpty()) result = 0;
        System.out.println(result);
    }
}
