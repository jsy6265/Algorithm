package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BK_28286 {
    static int N, K, result;
    static int[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = 0;

        a = new int[N];
        b = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // 틀린 부분 찾아두고 틀린 부분에서만 밀고 당겨서 계속 답 체크?
        dfs(b, K, 0);

        System.out.println(result);
    }

    public static void dfs(int[] arr, int k, int index){
        result = Math.max(result, checkScore(arr));

        if(k > 0){
            for (int i = 0; i < N; i++) {
                dfs(push(arr, i), k - 1, index);
                dfs(pull(arr, i), k - 1, index);
            }
        }
    }

    public static int checkScore(int[] arr){
        int score = 0;

        for (int i = 0; i < N; i++) {
            if(a[i] == arr[i]){
                score++;
            }
        }

        return score;
    }

    public static int[] pull(int[] arr, int index){
        int[] pArr = arr.clone();

        for (int i = index; i < N - 1; i++) {
            pArr[i] = pArr[i + 1];
        }

        pArr[N - 1] = 0;

        return pArr;
    }

    public static int[] push(int[] arr, int index){
        int[] pArr = arr.clone();
        int temp1 = 0;
        int temp2 = 0;

        temp2 = pArr[index];
        pArr[index] = 0;

        for (int i = index + 1; i < N; i++) {
            // 현재 값 저장
            temp1 = pArr[i];
            // 앞에값 가져오기
            pArr[i] = temp2;

            temp2 = temp1;
        }

        return pArr;
    }
}
