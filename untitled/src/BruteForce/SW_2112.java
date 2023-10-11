package BruteForce;

import java.util.Arrays;
import java.util.Scanner;

public class SW_2112 {

    static int res;
    static int D, W, K;  // 두께 D, 가로크기 W, 합격기준 K
    static int[][] map;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int t = 1; t <= TC; t++) {

            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();

            res = D; //Integer.MAX_VALUE; // ?  위치 수정함 , K로 초기값으로 수정해도 될것 같아요

            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            } //입력완료
            arr = new int[D]; //0
//			for(int i =0; i < D; i++) {
//				arr[i] = -1;
//			}
            Arrays.fill(arr, -1);
            //구현
            //막의 정보를 시뮬레이션
            // 막의 정보를 X, 막의 정보 0, 막의 정보를 1
            // 현재 몇번째의 막의 정보를 수정하고 있는지, 현재까지 몇번 막의 정보를 수정했는지
            dfs(0, 0);
            System.out.println("#" + t + " " + res);
        }

    }

    static void dfs(int idx, int cnt) {  //매개변수
        if (cnt >= res) { //최적화
            return;
        }

        //종료
        if (idx == D) {
            //전체막이 안전한지 검사해서
            //안전하면 cnt와 최종횟수를 비교해서 최소값으로 변경
            if (check()) {
                res = Math.min(res, cnt);
            }
            //종료시 return return 구문이 빠짐
            return;
        }
        //실행하면서 재귀호출
        // 막의 정보를 X, 막의 정보 0, 막의 정보를 1
        arr[idx] = -1;
        dfs(idx + 1, cnt);
//		막의 정보 0,
        arr[idx] = 0;
        dfs(idx + 1, cnt + 1);
//		막의 정보를 1
        arr[idx] = 1;
        dfs(idx + 1, cnt + 1);
    }

    private static boolean check() {
        int cnt = 0;
        int a, b;
        for (int j = 0; j < W; j++) {
            cnt = 1;
            for (int i = 0; i < D - 1; i++) {
                a = map[i][j];
                b = map[i + 1][j];
                //막이 변경되었으면 변경된값을 확인하기
                if (arr[i] != -1) {
                    a = arr[i];
                }
                if (arr[i + 1] != -1) {
                    b = arr[i + 1];
                }

                //같냐 비교
                if (a == b) {
                    cnt++;
                    //현재위치에서 이미안전하다고 판단된 결과 이니 다음열로 넘어가기
                    if (cnt >= K) {
                        break;
                    }
                } else {
                    cnt = 1;
                }
            }
            if (cnt < K) {
                return false;
            }
        }
        return true;
    }

}
