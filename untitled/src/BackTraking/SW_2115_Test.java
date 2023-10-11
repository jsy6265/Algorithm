package BackTraking;

import java.util.Scanner;

public class SW_2115_Test {
    static int res;
    static int N, M, C; //맵사이즈, 벌통개수, 벌꿀채취량
    static int[][] map;
    static int[] sel;
    static int[] max; //각 일꾼마다의 최대수익
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for(int t = 1; t <= TC ; t++) {
            res = 0; // 초기화 필요할지 몰라
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            map = new int[N][N];
            sel = new int[2]; //일꾼이 2로 고정되어 있음

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }//입력 완료

            //구현
            //2마리의 일꾼을 선택하고, 선택된 일꾼의 위치에서 벌통의 부분집합의 최대값을 구하고
            //그 두마리의 이득의 합을 전체 최대값으로 업데이트 하기
            dfs(0, 0); //부분집합, 모든 2개를 선택하면 가지치기
            //출력
            System.out.println("#"+ t +" " + res);
        }

    }
    //2마리의 일꾼을 선택하고, 선택된 일꾼의 위치에서 벌통의 부분집합의 최대값을 구하고
    //그 두마리의 이득의 합을 전체 최대값으로 업데이트 하기
    static void dfs(int cnt, int num) {
        //2마리의 일꾼이 선택되면(기저조건)
        //부분집합구하기
        if(cnt == 2) {
            //부분집합구하기
            int[] honey = new int[M]; //실제 선택할 맴에서 1차원 배열로 뽑아내기
            max = new int[2]; //0으로 초기화 되어 있어도 가능

            //1번째 일꾼
            int start = sel[0];
            for(int i = 0; i < M; i++) {
                honey[i] = map[start/N][start%N + i];
            }
            //부분집합구하기
            subSet(0,0,0,0,honey);
            //2번째일군
            start = sel[1];
            for(int i = 0; i < M; i++) {
                honey[i] = map[start/N][start%N + i];
            }
            //부분집합구하기
            subSet(1,0,0,0,honey);
            //최대값 업데이트 하기
            res = Math.max(res, max[0] + max[1]);
            return;
        }
        //선택되지 않는마지막 번호들은 제거하기
        if(num > N*N - M) {
            return;
        }
        //현재 번호 선택하기
        if(num % N <= N-M) { //선택할 공간이 있을때 선택
            sel[cnt] = num;
            dfs(cnt+1, num + M);
        }

        //현재 번호 선택안하기
        dfs(cnt, num+1);
    }
    //num:일꾼 번호, idx : 현재벌통위치, sW; 벌통의 꿀의 합, sB : 수익의 합, honey : 선택된 벌통 배열
    static void subSet(int num, int idx, int sW, int sB, int[] honey) {
        if(sW > C) { //선택된 벌통의 꿀의 합이 C 한계치가 넘어가경우는 최대값 변경불가
            return;
        }
        //종료
        if(idx == M) {
            //if(sW <= C) { //선택된 벌통의 꿀의 합이 C 한계치를 넘지 않을경우에만 최대값 업데이트하기
            max[num] = Math.max(max[num], sB);
            //}
            return;
        }

        //선택함
        subSet(num,idx+1, sW + honey[idx], sB+(honey[idx]*honey[idx]), honey);
        //선택안함
        subSet(num,idx+1, sW , sB, honey);
    }
//    static void subSet(int num, int idx, int sW, int sB, int[] honey) {
//        //종료
//        if(idx == M) {
//            if(sW <= C) { //선택된 벌통의 꿀의 합이 C 한계치를 넘지 않을경우에만 최대값 업데이트하기
//                max[num] = Math.max(max[num], sB);
//            }
//            return;
//        }
//
//        //선택함
//        subSet(num,idx+1, sW + honey[idx], sB+(honey[idx]*honey[idx]), honey);
//        //선택안함
//        subSet(num,idx+1, sW , sB, honey);
//    }

}