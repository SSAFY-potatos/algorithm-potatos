import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1463_1로만들기_남경민 {
    static int[] arr;
    static int result1,result2, result;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int x = Integer.parseInt(br.readLine());

        arr = new int[x+1];
        sb.append(dp(x));
        System.out.println(sb);

    }

    static int dp(int x) {

        if(x<=1) return 0;

        //메모이제이션 테이블에 값이 저장되어 있는지 확인
        if(arr[x]!=0) {
            return arr[x];
        }

        if(x%6==0) {
            result = Math.min(dp(x-1),Math.min(dp(x/2),dp(x/3)))+1;
        }else if(x%2==0) {
            result = Math.min(dp(x/2),dp(x-1))+1;
        }else if(x%3==0) {
            result = Math.min(dp(x/3),dp(x-1))+1;
        }else {
            result = dp(x-1)+1;
        }

        arr[x] = result;
        return result;
    }
}
