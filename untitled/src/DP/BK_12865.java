package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BK_12865 {
    static int N, K;
    static int[][] arr;
    static int[][] dpArr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        dpArr = new int[N][K+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dpArr[i], -1);
        }

        System.out.println(dp(0, 0));
    }

    private static int dp(int now, int w) {
        if(w > K){
            return Integer.MIN_VALUE;
        }

        if(now == N){
            return 0;
        }

        if(dpArr[now][w] == -1){
            dpArr[now][w] = Integer.max(dp(now + 1, w + arr[now][0]) + arr[now][1], dp(now + 1, w));
        }
        return dpArr[now][w];
    }
}
