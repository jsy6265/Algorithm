package Avatar;

import java.util.ArrayList;
import java.util.Scanner;

//SW 무선충전
public class SW_5644 {
    static int res;
    static int M, A; // 이동횟, BC 갯수
    static int[] PA, PB; //사용자의 이동방향 배열
    static BC[] bc;

    //이동하지 않음, 상 (UP), 우 (RIGHT), 하 (DOWN), 좌 (LEFT)
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static class BC {
        int x, y; //위치정보
        int C; //충전 범위
        int P; //충전량

        public BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            C = c;
            P = p;
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int t = 1; t <= TC; t++) {
            res = 0; //가장 작을 수 있는 값 => 무충전경우  0
            M = sc.nextInt();
            A = sc.nextInt();
            PA = new int[M];
            PB = new int[M];
            for (int i = 0; i < M; i++) {
                PA[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                PB[i] = sc.nextInt();
            }
            //충전소 입력받기
            bc = new BC[A];
            for (int i = 0; i < A; i++) {
                bc[i] = new BC(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt());
            } //입력 완료
            // M 시간만큼이동하면서 충전량 최대값 구하기
            //
            solve();

            System.out.println("#" + t + " " + res);
        }

    }

    private static void solve() {
        Pos pa = new Pos(0, 0);
        Pos pb = new Pos(9, 9);
        //그냥 충전되나 먼저 해보기

        //이동회수 만큼 충전해보기
        for (int m = 0; m <= M; m++) {  // 처음부터 가능하다. m
            ArrayList<Integer> bca = new ArrayList<>();
            ArrayList<Integer> bcb = new ArrayList<>();
            for (int i = 0; i < A; i++) { //배터리충전소 갯수만큼 충전 범위 안에 있는지 체크해서 충전이 가능하면 리스트에추가한다.
                if (getD(pa, bc[i]) <= bc[i].C) { //pa 사용자 충전 가능한가
                    bca.add(i);
                }
                if (getD(pb, bc[i]) <= bc[i].C) { //pb 사용자 충전 가능한가
                    bcb.add(i);
                }
            }
            res += calc(bca, bcb);
            if (m == M) {
                break;
            }
            //이동하기
            pa.x = pa.x + dx[PA[m]];
            pa.y = pa.y + dy[PA[m]];
            pb.x = pb.x + dx[PB[m]];
            pb.y = pb.y + dy[PB[m]];
        }

    }

    private static int calc(ArrayList<Integer> bca, ArrayList<Integer> bcb) {
//        둘다 충전이 안될수 도 있음
        if (bca.size() == 0 && bcb.size() == 0) {
            return 0;
        }
        int sum = 0;
//        pa 충전 가능함
        if (bcb.size() == 0) {
            for (Integer idx : bca) {
                sum = Math.max(sum, bc[idx].P);
            }
            return sum;
        }
        sum = 0;
//        pb 충전 가능함
        if (bca.size() == 0) {
            for (Integer idx : bcb) {
                sum = Math.max(sum, bc[idx].P);
            }
            return sum;
        }
        sum = 0;
//        둘다 가능함
        for (int idxa : bca) {
            for (int idxb : bcb) {
                int temp = 0;
                //내부 최대값 구하기
                if (idxa == idxb) { //같은 충전소에서 충전한다.
                    temp = bc[idxb].P;
                } else { //서로다른 충전소에서 충전한다.
                    temp = bc[idxa].P + bc[idxb].P;
                }
                sum = Math.max(sum, temp);
            }
        }
        return sum;
    }

    //D = |XA – XB| + |YA – YB|
    static int getD(Pos p, BC bc) { //사람의 위치, 충전소 정보
        return Math.abs(p.x - bc.x) + Math.abs(p.y - bc.y);
    }

}