package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BK_14568 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int resutl = 0;
        //택
        for (int i = 1; i < N; i++) {
            // 남
            for (int j = 1; j < N; j++) {
                //영
                for (int k = 1; k < N; k++) {
                    if (i + j + k == N && j - k >= 2 &&  i % 2 == 0) {
                        resutl++;
                    }
                }
            }
        }

        System.out.println(resutl);
    }

}
