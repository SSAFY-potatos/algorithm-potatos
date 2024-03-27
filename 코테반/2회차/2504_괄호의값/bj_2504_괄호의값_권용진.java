import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int answer = 0;
        int m = 1;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];

            if (curr == '(') {
                stack.add(curr);
                m *= 2;
                flag = true;

            } else if (curr == '[') {
                stack.add(curr);
                m *= 3;
                flag = true;

            } else if (curr == ')') {
                if (stack.isEmpty() || stack.pollLast() != '(') {
                    System.out.println(0);
                    return;
                }
                if (flag) {
                    answer += m;
                    flag = false;
                }
                m /= 2;

            } else if (curr == ']') {
                if (stack.isEmpty() || stack.pollLast() != '[') {
                    System.out.println(0);
                    return;
                }
                if (flag) {
                    answer += m;
                    flag = false;
                }
                m /= 3;
            }
        }
        if (stack.isEmpty()) {
            System.out.println(answer);
        } else {
            System.out.println(0);
        }
    }
}