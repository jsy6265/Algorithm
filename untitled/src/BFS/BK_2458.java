package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//백준 키순서
public class BK_2458 {
    static int N, M, result;
    static List<List<Integer>> in;
    static List<List<Integer>> out;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        in = new ArrayList<>();
        out = new ArrayList<>();
        for (int i = 0; i <= N; i++){
            in.add(new ArrayList<>());
            out.add(new ArrayList<>());
        }
        //진입 차수와 진출 차수를 각각 리스트에 저장
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            in.get(x).add(y);
            out.get(y).add(x);
        }
        result = 0;
        //모든 정점에 대해 진입 차수와 진출 차수가 연결되있는 정점의 수가 N개이면 순서가 정해진 정점이다.
        for (int i = 1; i <= N; i++){
            bfs(i);
        }

        System.out.println(result);
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N+1];
        int cnt = 0;

        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> tempIn = in.get(start);
        List<Integer> tempOut = out.get(start);

        //시작 정점과 연결된 정점들을 큐에추가(진출)
        for (int i = 0; i < tempIn.size(); i++){
            int x = tempIn.get(i);
            q.add(x);
            visited[x] = true;
            cnt++;
        }

        //BFS로 연결된 모든 정점 확인하면서 숫자 체크
        while (!q.isEmpty()){
            int x = q.poll();
            List<Integer> temp = in.get(x);

            for (int i =0; i < temp.size(); i++){
                int y = temp.get(i);
                if(!visited[y]){
                    q.add(y);
                    visited[y] = true;
                    cnt++;
                }
            }
        }

        //시작 정점과 연결된 정점들을 큐에추가(진입)
        for (int i = 0; i < tempOut.size(); i++){
            int x = tempOut.get(i);
            q.add(x);
            visited[x] = true;
            cnt++;
        }

        //BFS로 연결된 모든 정점 확인하면서 숫자 체크
        while (!q.isEmpty()){
            int x = q.poll();
            List<Integer> temp = out.get(x);

            for (int i =0; i < temp.size(); i++){
                int y = temp.get(i);
                if(!visited[y]){
                    q.add(y);
                    visited[y] = true;
                    cnt++;
                }
            }
        }

        //start에서 출발하여 추가된 정점들의 수가 N-1(시작 정점 제외)이면 조건 성립
        if (cnt == N - 1){
            result++;
        }
    }
}