import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Vector<Integer> pay = new Vector<>();

        for(int i = 0; i < N; i++){
            int v = sc.nextInt();
            pay.add(v);
        }

        pay.sort((o1, o2) -> o2-o1);

        int check = 0;

        for(int i = 0; i < N; i++){
            if(K / pay.get(i) != 0){
                check += K / pay.get(i);
                K = K % pay.get(i);
            }
        }
        System.out.println(check);
    }
}
