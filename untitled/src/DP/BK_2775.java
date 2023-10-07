package DP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 부녀회장이 될테야
public class BK_2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int Tc = 0; Tc < T; Tc++) {
            st = new StringTokenizer(bf.readLine());
            //k층
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            //n호
            int n = Integer.parseInt(st.nextToken());

            //2차원 DP
            int[][] dp = new int[k + 1][n];
            //1층 1로 초기화
            for (int i = 0; i <= k; i++) {
                dp[i][0] = 1;
            }
            //1호들 i층으로 초기화
            for (int i = 0; i < n; i++) {
                dp[0][i] = i + 1;
            }

            for (int i = 1; i <= k; i++) {
                for (int j = 1; j < n; j++) {
                    //i층 j호는 내 아래층과 왼쪽 호의 합이다
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            System.out.println(dp[k][n - 1]);
        }

    }
}