package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 게리맨더링
public class BK_17471 {
    static int N, result;

    static List<List<Integer>> list;
    static int[] arr, status;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(bf.readLine());

        list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(st.nextToken());
            list.add(new ArrayList<>());
        }

        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(!list.get(i + 1).contains(temp))
                    list.get(i + 1).add(temp);
                if (!list.get(temp).contains(i + 1))
                    list.get(temp).add(i + 1);
            }
        }

        status = new int[N + 1];
        status[0] = -1;

        dfs(1);

        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(result);
        }

    }

    //구역 선택하기
    private static void dfs(int cnt) {
        if(cnt == N  + 1){
            if(!cheak(1) || !cheak(0)){
                return;
            }

            int A = 0;
            int B = 0;

            for (int i = 1; i <= N; i++) {
                if (status[i] == 1){
                    A += arr[i];
                }else {
                    B += arr[i];
                }
            }

            if(A == 0 || B == 0){
                return;
            }

            result = Math.min(result, Math.abs(A - B));

            return;
        }

        dfs(cnt + 1);
        status[cnt] = 1;
        dfs(cnt + 1);
        status[cnt] = 0;
    }

    //선거구 유효성 검사
    //구역이 2개인지, 구역끼리 연결되어 있는지
    private static boolean cheak(int color) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q1 = new LinkedList<>();

        //구역 유효성 검사
        for (int i = 1; i <= N; i++) {
            if(status[i] == color){
                //젤 처음 정점 1개 추가
                q1.add(i);
                visited[i] = true;
                break;
            }
        }

        while (!q1.isEmpty()){
            int temp = q1.poll();
            List<Integer> tmpeList =  list.get(temp);

            for (int next : tmpeList) {
                if (!visited[next] && status[next] == color) {
                    q1.add(next);
                    visited[next] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            //같은 구역중 방문안한거 있음 false 반환
            if (status[i] == color  && !visited[i])
                return false;
        }

        return true;
    }
}
