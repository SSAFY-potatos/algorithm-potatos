import java.util.Scanner;

public class 사탕게임 {
    static int N;
    static char[][] arr;
    static int max = 0;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
		N = scan.nextInt();

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = scan.next();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                //가로
                char tmp = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = tmp;
                max = Math.max(max, check());
                tmp = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = tmp;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
            //세로
            char tmp = arr[j][i];
            arr[j][i] = arr[j+1][i];
            arr[j+1][i] = tmp;        
            max = Math.max(max, check());
            tmp = arr[j][i];
            arr[j][i] = arr[j+1][i];
            arr[j+1][i] = tmp;   
            }
        }

        System.out.println(max);
    }

    static int check() {	
		// 가로
		for(int i=0; i<N; i++) {
			int count = 1;
			for(int j=0; j<N-1; j++) {
				if(arr[i][j] == arr[i][j+1]){
                    count ++;
                }
				else {
                    count = 1;
                }
				max = Math.max(max, count);
			}
		}
		
		// 세로
		for(int i=0; i<N; i++) {
			int count = 1;
			for(int j=0; j<N-1; j++) {
				if(arr[j][i] == arr[j+1][i]){
                    count++;
                }
				else {
					count = 1;
                }
				max = Math.max(max, count);
			}
		}
        return max;
    }
}
