package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BK_1043 {
    static int person, party, result;

    static List<Integer> infected;
    static List<List<Integer>> partypeople, partys;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        person = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());

        //진실을 아는사람 수
        st = new StringTokenizer(bf.readLine());
        int xManNum = Integer.parseInt(st.nextToken());

        //최종적으로 진실을 알 수 있는 사람의 리스트
        infected = new ArrayList<>();
        visited = new boolean[person + 1];

        for (int i = 0; i < xManNum; i++) {
            int x = Integer.parseInt(st.nextToken());
            infected.add(x);
            visited[x] = true;
        }

        //파티별 참가 인원 명단
        partypeople = new ArrayList<>();
        partys = new ArrayList<>();

        for (int i = 0; i <= person; i++) {
            //파티 수 만큼 인접 리스트 생성
            partypeople.add(new ArrayList<>());
        }

        //파티 별로 오는 사람을 체크해 만나는 사람 체크하는 인접리스트 생성
        for (int i = 0; i < party; i++) {
            st = new StringTokenizer(bf.readLine());
            //i번째 파티에 참가하는 사람 수
            int ppNum = Integer.parseInt(st.nextToken());

            List<Integer> pp = new ArrayList<>();
            for (int j = 0; j < ppNum; j++) {
                //파티에 오는 사람 목록
                pp.add(Integer.parseInt(st.nextToken()));
            }

            partys.add(pp);

            for (int j = 0; j < pp.size(); j++) {
                int x = pp.get(j);

                for (int k = 0; k < pp.size(); k++) {
                    int y = pp.get(k);

                    if(j != k){
                        partypeople.get(x).add(y);
                    }
                }
            }
        }

        if (xManNum == 0) {
            System.out.println(party);
        } else {
           bfs();
        }
    }

    private static void bfs() {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < infected.size(); i++) {
            int x = infected.get(i);

            q.offer(x);
        }

        while (!q.isEmpty()){
            int x = q.poll();

            List<Integer> temp = partypeople.get(x);

            for (int i = 0; i < temp.size(); i++) {
                if(!visited[temp.get(i)]){
                    visited[temp.get(i)] = true;
                    q.offer(temp.get(i));
                }
            }
        }

        checkParty();
        System.out.println(result);
    }

    private static void checkParty() {
        for (int i = 0; i < party; i++) {
            List<Integer> pt = partys.get(i);
            boolean isSafe = true;

            for (int j = 0; j < pt.size(); j++) {
                int x = pt.get(j);

                if (visited[x]) {
                    isSafe = false;
                    break;
                }
            }

            if (isSafe) {
                result++;
            }
        }
    }

    private static void ladella() {
        for (int i = 0; i < party; i++) {
            List<Integer> pt = partypeople.get(i);

            //감염자가 현재 파티에 있는지 검사(두 리스트에 중복된 값이 있는지 확인)
            List<Integer> match = pt.stream().filter(o -> infected.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());

            if (!match.isEmpty()) {
                // pt 리스트에서 infected 리스트에 없는값들 뽑아서 추가
                List<Integer> nInfeted = pt.stream().filter(o -> infected.stream().noneMatch(Predicate.isEqual(o))).collect(Collectors.toList());

                infected.addAll(nInfeted);
            }
        }
    }
}
