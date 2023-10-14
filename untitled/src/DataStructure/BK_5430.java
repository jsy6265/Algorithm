package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BK_5430 {
    public static void main(String[] args)  throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());
            String function = st.nextToken();

            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                System.out.println(st.nextToken().split(","));
            }
        }
    }
}
