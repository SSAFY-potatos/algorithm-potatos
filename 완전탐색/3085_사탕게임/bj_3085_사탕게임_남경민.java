import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[N][N];

        int[] dx = {0,1};
        int[] dy = {1,0};

        int maxNum = 1;

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<N;j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        // 완전 탐색
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                for(int p=0;p<2;p++) {
                    int idx = i+dx[p];
                    int idy = j+dy[p];

                    if(idx>=0 && idx<N && idy>=0 && idy<N) {

                        if(arr[idx][idy] != (arr[i][j])){ // 같은 색상 아니면 서로 변경
                            //swap
                            char temp = arr[i][j];
                            arr[i][j] = arr[idx][idy];
                            arr[idx][idy] = temp;
                            int cntR = 1;
                            int cntR2 =1;
                            int cntL = 1;
                            int cntL2 = 1;

                            for(int k=0;k<N-1;k++) {
                                // 연속인 경우
                                if(arr[i][k] == arr[i][k+1]) {
                                    cntR++;
                                }else {
                                    maxNum = Math.max(maxNum, cntR);
                                    cntR=1;
                                }

                                if(dx[p]==1 && arr[idx][k] == arr[idx][k+1]) {
                                    cntR2++;
                                }else {
                                    maxNum = Math.max(maxNum, cntR2);
                                    cntR2=1;
                                }

                                if(arr[k][j] == arr[k+1][j]) {
                                    cntL++;
                                }else {
                                    maxNum = Math.max(maxNum, cntL);
                                    cntL=1;
                                }

                                if(dy[p]==1 && arr[k][idy] == arr[k+1][idy]) {
                                    cntL2++;
                                }else {
                                    maxNum = Math.max(maxNum, cntL2);
                                    cntL2=1;
                                }
                            }
                            maxNum = Math.max(Math.max(maxNum, (cntL2>cntR2)? cntL2:cntR2), (cntL>cntR)? cntL:cntR);
                            //swap
                            arr[idx][idy] = arr[i][j];
                            arr[i][j] = temp;
                        }
                    }
                }
            }

            int cntR = 1;
            int cntL = 1;
            for(int k=0;k<N-1;k++) {
                if(arr[i][k] == arr[i][k+1]) {
                    cntR++;
                }else {
                    maxNum = Math.max(maxNum, cntR);
                    cntR=1;
                }

                if(arr[k][i] == arr[k+1][i]) {
                    cntL++;
                }else {
                    maxNum = Math.max(maxNum, cntL);
                    cntL=1;
                }
            }
            maxNum = Math.max(maxNum, (cntL>cntR)? cntL:cntR);
        }

        System.out.println(maxNum);
    }
}
