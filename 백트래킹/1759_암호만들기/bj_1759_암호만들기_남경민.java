import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L,C;
    static List<Character> arr;
    static boolean[] isSelected;
    static char[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        isSelected = new boolean[C];
        answer = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr.add(st.nextToken().charAt(0));
        }
        // 오름차순 정렬
        Collections.sort(arr);

        cipher(0,0);
    }
    private static void cipher(int cnt,int lcnt){
        if(cnt == C ){
            if(lcnt == L){
                int idx = 0;
                int consonants = 0; //자음
                int vowel = 0; //모음
                for (int i = 0; i < C; i++) {
                    if(isSelected[i]) {
                        answer[idx++] = arr.get(i);
                        if(arr.get(i) == 'a' || arr.get(i) == 'e'||arr.get(i) == 'i'||arr.get(i) == 'o'||arr.get(i) == 'u'){
                            vowel++;
                        }else consonants++;
                    }
                }
                if(vowel>=1 && consonants>=2){
                    for (int i = 0; i < L; i++) {
                        System.out.print(answer[i]);
                    }
                    System.out.println();
                }
            }
            return;
        }

        isSelected[cnt] = true;
        cipher(cnt+1,lcnt+1);
        isSelected[cnt] = false;
        cipher(cnt+1,lcnt);
    }
}
