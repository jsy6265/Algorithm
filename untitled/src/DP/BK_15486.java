package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BK_15486 {
    static int N;
    static int[] dpArr;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        dpArr = new int[N];
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dpArr, -1);

        System.out.println(dp(0));
    }

    private static int dp(int d) {
        if(d > N){
            return Integer.MIN_VALUE;
        }

        if(d == N){
            return 0;
        }

        if(dpArr[d] == -1){
            dpArr[d] = Integer.max(dp(d + 1), dp(d + arr[d][0]) + arr[d][1]);
        }

        return dpArr[d];
    }
}
