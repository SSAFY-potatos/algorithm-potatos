import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String output = br.readLine();

        int result = 0;

        while (true) {
            int idx = output.length();

            if (idx == input.length()) {
                if (output.equals(input))
                    result = 1;
                break;
            }
            if (output.charAt(idx - 1) == 'A') {
                output = split(output);
            } else {
                output = re(split(output));
            }
        }

        System.out.println(result);
    }

    static String split(String str) {
        StringBuilder re = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            re.append(str.charAt(i));
        }
        return re.toString();
    }

    static String re(String str) {
        StringBuilder reverse = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        return reverse.toString();
    }
}