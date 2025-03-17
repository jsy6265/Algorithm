package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정수 수열 x[0], x[1], ..., x[N-1]이 볼록이 되려면 모든 1 ≤ i ≤ N-2에 대해서, x[i-1]+x[i+1] ≥ 2*x[i]를 만족해야 한다. 또, 길이가 1, 2인 수열은 항상 볼록하다.
//
//예를 들어, 7, 3, 4, 5, 7과 4, 2, 1, 3은 볼록하지만, 4, 3, 1, 2와 5, 7, 3은 볼록하지 않다.
//
//수열 A가 주어졌을 때, 수열을 볼록하게 만드는 최소 연산 횟수를 구하는 프로그램을 작성하시오. 연산은 인덱스 i를 고르고, a[i]를 a[i]-1로 바꾸는 연산만 허용된다.
public class BK_14281 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine());
        long result = 0;
        long[] arr = new long[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        boolean isBlock = true;
        while (isBlock){
            isBlock = false;

            for (int i = 1; i < N - 1; i++) {
                // 현재 인덱스가 조건에 안맞으면
                if(arr[i - 1] + arr[i + 1] < arr[i] * 2){
                    // i 번쨰 인덱스가 (i - 1) + (i + 1) / 2만큼 작아야됨
                    long temp = arr[i] - (arr[i - 1] + arr[i + 1]) / 2;
                    arr[i] -= temp;
                    result += temp;
                    isBlock = true;
                }
            }
        }

        System.out.println(result);
    }
}
