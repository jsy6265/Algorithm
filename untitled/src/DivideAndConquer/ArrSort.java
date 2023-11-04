package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//int 배열을 입력받아 반으로 나누고 정렬 후 다시 합쳤을 때 오름차순으로 정렬되는지 체크
public class ArrSort {
    static int N, result;
    static int[] arr, list;
    static boolean isSort;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = 0;
        divide(2);
        System.out.println(result);
    }

    //배열을 cnt개의 그룹으로 나눈다
    private static void divide(int cnt) {
        //그룹에 몇명있는지 담길 배열
        list = new int[cnt];
        isSort = false;
        //cnt개의 그룹으로 나누는 조합 짜기
        comp(cnt, 0, 0);

        //N개 이상의 그룹은 만들 수 없으니 종료
        if (cnt + 1 == N) {
            return;
        }

        //cnt개의 그룹으로 정렬 했으면 더 나눠보고 정렬 안되면 cnt - 1이 답이다
        if (isSort) {
            divide(cnt + 1);
        } else {
            result = cnt - 1;
        }
    }

    private static void comp(int cnt, int sum, int depth) {
        //sum이 N보다 크면 종료
        if (sum > N) {
            return;
        }
        //조합 다 짰을 때 유효성 검사
        if (depth == cnt) {
            //조합 짜면서 더한 사람 수가 N이 아니면 검사할 필요 없음
            if (sum != N) {
                return;
            }
            //시작점
            int start = 0;
            //배열 복사
            int[] temp = Arrays.copyOf(arr, N);
            for (int i = 0; i < cnt; i++) {
                if (start > N) {
                    return;
                }
                //부분 정렬 temp 배열의 start부터 start + list배열의 i 번째 인덱스(그룹에 몇명인지는) - 1(그룹원이 1명이고 start가 0이면 0번쨰 인덱스 까지 정렬해야 함으로 1을 뺀다)
                Arrays.sort(temp, start, start + list[i]);
                //다음 그룹이 시작할 인덱스
                start += list[i];
            }

            //정렬 됬는지 검사
            for (int i = 1; i < N; i++) {
                if (temp[i] < temp[i - 1]) {
                    return;
                }
            }
            isSort = true;
            return;

        }
        //depth번째 그룹에 몇명이 들어가는지
        for (int i = 1; i < N; i++) {
            list[depth] = i;
            comp(cnt, sum + i, depth + 1);
        }
    }
}
