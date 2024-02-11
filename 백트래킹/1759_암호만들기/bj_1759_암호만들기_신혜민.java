import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] arr, alphabet;
    static StringBuilder sb = new StringBuilder();

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[L];

        String str = br.readLine();
        str = str.replaceAll("\\s", "");
        alphabet = str.toCharArray();

        Arrays.sort(alphabet);

        re(0, 0);
        System.out.println(sb);
    }

    static void re(int cnt, int idx){
        if(cnt == L){
            int moum = 0;
            for(int i = 0; i < L; i++){
                if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u'){
                    moum++;
                }
            }
            if(L - moum >= 2 && moum >= 1){
                for(int j = 0; j < L; j++){
                    sb.append(arr[j]);
                }
                sb.append("\n");
            }
            return;
        }

        for(int i = idx; i < C; i++){
            arr[cnt] = alphabet[i];
            
            re(cnt+1, i+1);
        }
    }
}