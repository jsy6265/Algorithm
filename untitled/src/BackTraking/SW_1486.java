package BackTraking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW 장훈이의 높은 선반
public class SW_1486 {
    static int N, S, result;
    static int[] peple;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            peple = new int[N];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                peple[i] = Integer.parseInt(st.nextToken());
            }

            result = Integer.MAX_VALUE;

            dfs(0, 0);

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static void dfs(int cnt, int index) {
        if (S < cnt) {
            result = Math.min(result, cnt - S);
            return;
        }
        if (index == N) {
            if (cnt - S >= 0) {
                result = Math.min(result, cnt - S);
            }

            return;
        }

        dfs(cnt, index + 1);
        dfs(cnt + peple[index], index + 1);

    }
}