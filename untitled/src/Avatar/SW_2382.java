package Avatar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//SW 미생물 처리
public class SW_2382 {
    static int res;
    static int N, M, K; //맵 사이즈, 이동횟수, 군집수

    static ArrayList<Data> list;
    //0 1  2  3
    static int[] dx = {0, 0, -1, 1}; //(상: 1, 하: 2, 좌: 3, 우: 4)
    static int[] dy = {-1, 1, 0, 0};

    static class Data implements Comparable<Data> {
        int num; // 군집번호(행첨자, 열첨자의 정보를 가지고 있는 군집번호
        int x, y;
        int cnt;
        int dir;

        public Data(int num, int x, int y, int cnt, int dir) {
            super();
            this.num = num;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Data o) {
            // TODO Auto-generated method stub
            if (num == o.num) {
                return -(cnt - o.cnt); //내림차순
            }
            return num - o.num;
//            return num = o.num; //오름차순
//            return -(num = o.num); //내림차순
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int t = 1; t <= TC; t++) {
            res = 0; // 합
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            list = new ArrayList<>();
            int x, y, cnt, dir, num;
            for (int i = 0; i < K; i++) {
                y = sc.nextInt();
                x = sc.nextInt();
                cnt = sc.nextInt();
                dir = sc.nextInt() - 1;
                num = y * N + x;
                list.add(new Data(num, x, y, cnt, dir));
            }//입력 완료

            //M 시간 만큼 시뮬레이션 구현
            Data cur;
            for (int m = 0; m < M; m++) {
                //모든 리스트 이동하기
                for (int i = 0; i < list.size(); i++) {
                    cur = list.get(i);
                    cur.x = cur.x + dx[cur.dir];
                    cur.y = cur.x + dy[cur.dir];
                    cur.num = cur.y * N + cur.x;
                    //약품영역을 검사
                    if (cur.x == 0 || cur.x == N - 1 || cur.y == 0 || cur.y == N - 1) { //약품영역안에들어간경우
                        //미생물갯수 반으로 설정

                        cur.cnt = cur.cnt / 2;
                        //방향바꾸기
                        if (cur.dir % 2 == 0) {
                            cur.dir += 1;
                        } else {
                            cur.dir -= 1;
                        }
                        //군집의 갯수가 0이면 군집제거
                        if (cur.cnt == 0) {
                            list.remove(i);
                            i--;
                        }
                    }
                }
                //모여진 군집번호별 정렬 처리하기
                //  1,3,4,4,4
                Collections.sort(list);
                for (int i = 0; i < list.size() - 1; i++) {
                    Data a = list.get(i);
                    Data b = list.get(i + 1);
                    if (a.num == b.num) { //같은 x,y 좌표안에 있음
                        a.cnt += b.cnt;
                        list.remove(i + 1);
                        i--;
                    }
                }

            }
            //미생물의 합구하기
            res = 0;
            for (Data d : list) {
                res += d.cnt;
            }
            System.out.println("#" + t + " " + res);
        }

    }

}