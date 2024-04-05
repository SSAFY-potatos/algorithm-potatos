import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2110_공유기설치_남경민 {
    static int N,C;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        System.out.println(upper_bound(1,arr[N-1]-arr[0]+1,C)-1);
    }
    static int upper_bound(int min,int max,int key) {
        int mid = 0;
        while(min < max) {
            mid = (min+max)/2;
            if(count(mid) >= key) {
                min = mid+1;
            }else {
                max = mid;
            }
        }
        return min;
    }
    static int count(int mid) {
        int cnt = 1;
        int current = 0;
        int next = 0;

        for (int i = current+1; i < arr.length; i++) {
            next = i;

            if(arr[next] - arr[current] < mid) continue;
            cnt++;
            current = next;
            i = current;
        }

        return cnt;
    }
}
