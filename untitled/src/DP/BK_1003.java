package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 피보니치 함수
public class BK_1003 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int Tc = 0; Tc < T; Tc++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n > 1) {
                int[] dp = new int[n + 1];
                //피보나치 1, 0을 1로 초기화
                dp[n] = 1;
                dp[n - 1] = 1;

                //f(N) = f(N - 1) + f(N - 2)
                for (int i = n - 2; i >= 0; i--) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                }
                dp[0] = dp[2];
                System.out.println(dp[0] + " " + dp[1]);
            } else if (n == 0) {
                System.out.println(1 + " " + 0);
            } else if (n == 1) {
                System.out.println(0 + " " + 1);
            }

        }
    }
}