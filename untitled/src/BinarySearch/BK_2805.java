package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 나무자르기
public class BK_2805 {
    static int N, M, result;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;

        st = new StringTokenizer(bf.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        CutTree();

        System.out.println(result);
    }

    private static void CutTree() {
        int min = 0;
        int max = arr[N - 1];
        while (min < max) {
            int cm = (max + min) / 2;
            long cnt = 0;

            for (int i = 0; i < N; i++) {
                cnt += Math.max(arr[i] - cm, 0);
            }

            if (cnt < M) {
                max = cm;
            } else {
                min = cm + 1;
            }
        }
        result = min - 1;
    }
}
