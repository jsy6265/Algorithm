package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_2112_Test {
    static int D, W, K, result;
    static int[][] map;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            arr = new int[D];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            Arrays.fill(arr, -1);
            result = Integer.MAX_VALUE;

            dfs(0, 0);

            System.out.println("#" + tc + " " + result);
        }
    }

    private static void dfs(int index, int cnt) {
        //현재 넣은 약품 수가 결과 값보다 크거나 같으면 더이상 볼 필요 없음
        if (cnt >= result) {
            return;
        }

        if (index == D) {
            if (cheak()) {
                result = Math.min(result, cnt);
            }
            return;
        }

        //약품 않넣는 경우
        arr[index] = -1;
        dfs(index + 1, cnt);
        //A 약품 넣는 경우
        arr[index] = 1;
        dfs(index + 1, cnt + 1);
        //B 약품 넣는 경우
        arr[index] = 0;
        dfs(index + 1, cnt + 1);
    }

    private static boolean cheak() {
        int A = 0, B = 0, cnt = 0;

        for (int i = 0; i < W; i++) {
            cnt = 1;
            for (int j = 0; j < D - 1; j++) {
                A = map[j][i];
                B = map[j + 1][i];

                if(arr[j] != -1){
                    A = arr[j];
                }
                if(arr[j + 1] != -1){
                    B = arr[j + 1];
                }

                if(A == B){
                    cnt++;
                    if(cnt >= K){
                        break;
                    }
                }else {
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
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int D, W, K;
    static int[][] film;
    //조합 구할 때 쓸 배열(약품 넣는 포인트, AB중 뭐 넣을지)
    static int[] point, ABComp;
    static  int result;
    //AB중 뭐 넣을지 조합 구해서 리스트엠 담아서 꺼내먹을거임
    static List<int[]>  AB ;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(bf.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];

            for (int i = 0; i < D; i++){
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < W; j++){
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;

            //먼저 입력값 그대로 유효성 검사 한번해보고 실패하면 완탐
            if(CheakFilm(film)){
                result = 0;
            }else{
                PushMedicine(1);
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    //cnt개의 약품 넣는 조합 구할거임
    private static void PushMedicine(int cnt) {
        point = new int[cnt];
        AB = new ArrayList<>();
        ABComp = new int[cnt];

        //cnt의 개의 약품을 넣는 경우의 수 구하기 A, B 넣는 경우의 수 구하기
        CombinationAB(cnt, 0);

        //약품 넣는 경우의 수 구하기 (list에 추가)
        Combination(cnt, 0);

        //유효성 검사 실패하면 약품 1개 더 넣는 경우 구함
        if(result == Integer.MAX_VALUE){
            PushMedicine(cnt+1);
        }
    }

    //원봄 배열에 약품 넣기
    private static int[][] PushAB(int[] temp, int[] abcomp) {
        int[][] tempFilm = new int[D][W];

        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                tempFilm[i][j] = film[i][j];
            }
        }

        int index = 0;

        while (index < temp.length){
            for (int i = 0; i < W; i++){
                tempFilm[temp[index]][i] = abcomp[index];
            }
            index++;
        }

        return tempFilm;
    }

    //약품 넣는 경우의 수
    private static void CombinationAB(int death, int index) {
        if(index == death){
            AB.add(ABComp);
            return;
        }
        for (int i = 0; i < 2; i++){
            ABComp[index] = i;
            CombinationAB(death, index + 1);
        }
    }

    //약품 넣는 위치 경우의 수
    private static void Combination(int death, int index) {
        if(index == death){
            for (int j = 0; j < AB.size(); j ++){
                int[] tempAB = AB.get(j);
                int[][] tempFilm = PushAB(point, tempAB);

                if(CheakFilm(tempFilm)){
                    result = death;
                }
            }
            return;
        }

        for (int i = 0; i < D; i++){
            point[index] = i;
            Combination(death, index + 1);
        }
    }

    //유효성 검사
    public static boolean CheakFilm(int[][] arr){

        int lastFilm = 0;
        int continuity = 1;
        int cnt = 0;

        //행검사
        for (int i = 0; i < W; i++){
            for (int j = 0; j < D; j++){
                //연속하는 조건을 만족하면 cnt + 1
                if (continuity >= K){
                    cnt++;
                    lastFilm = 0;
                    continuity = 0;
                    continue;
                }

                //전에 나온 필름과 같은 타입이면 연속 + 1
                if(arr[j][i] == lastFilm){
                    continuity++;
                }else {//서로 다른 필름이면 연속을 1로 초기화
                    continuity = 1;
                }
                //비교할 필름을 현재 필름으로 교체
                lastFilm = arr[j][i];

                if(j == D- 1 && continuity < K){
                    return false;
                }
            }
            lastFilm = 0;
            continuity = 0;
        }

        if (cnt == W - 1){
            return  true;
        }else {
            return false;
        }
    }
}
 */