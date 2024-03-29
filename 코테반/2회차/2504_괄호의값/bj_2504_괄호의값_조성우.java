import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj_2504_괄호의값_조성우 {

  static Stack<Character> bracketStack = new Stack<>();
  static Stack<Integer> valueStack = new Stack<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    boolean isValid = true;

    for (int i = 0; i < str.length(); i++) {
      char curChar = str.charAt(i);

      if (curChar == '(' || curChar == '[') {
        bracketStack.push(curChar);
        valueStack.push(-1);
      } else {
        int tempNum = 0;

        if (bracketStack.isEmpty()) {
          isValid = false;
          break;
        }

        while (!valueStack.isEmpty() && valueStack.peek() != -1) {
          tempNum += valueStack.pop();
        }

        if (valueStack.isEmpty()) {
          isValid = false;
          break;
        }

        valueStack.pop();
        char openChar = bracketStack.pop();

        if ((openChar == '(' && curChar == ')') || (openChar == '[' && curChar == ']')) {
          tempNum = (tempNum == 0) ? 1 : tempNum;
          int valueToAdd = (curChar == ')') ? tempNum * 2 : tempNum * 3;
          valueStack.push(valueToAdd);
        } else {
          isValid = false;
          break;
        }
      }
    }

    if (!isValid || !bracketStack.isEmpty()) {
      System.out.println(0);
    } else {
      int result = 0;
      while (!valueStack.isEmpty()) {
        result += valueStack.pop();
      }
      System.out.println(result);
    }
  }
}