import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, arr[], result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = 0;

        for(int i = 0; i < n; i++){
            arr[i] = i;
        }

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a, b) && result == 0){
                result = i;
            }
        }
        System.out.println(result);
    }
    
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return true;
        
        if(a <= b) arr[b] = a;
        else arr[a] = b;
        return false;
    }

    static int find(int x) {
        if(arr[x] == x) return x;
        return find(arr[x]);
    }
}