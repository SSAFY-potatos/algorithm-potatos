import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarf = new int[9];
        int sum =0;

        for (int i = 0; i < 9; i++) {
            int p = Integer.parseInt(br.readLine());
            dwarf[i] = p;
            sum+=p;
        }

        int keys = (sum - 100);
        Arrays.sort(dwarf);

        for(int i=0;i<9;i++){
            for(int j=(i+1);j<9;j++){
                if((keys-dwarf[i]) == dwarf[j]){
                    for (int k = 0; k < 9; k++) {
                        if(k!=i && k!=j){
                            System.out.println(dwarf[k]);
                        }
                    }
                    return;
                }
            }
        }
    }
}
