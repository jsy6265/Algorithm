package BruteForce;

import java.lang.*;
import java.util.*;

class PM_PCCP4 {
    public static void main(String[] args) {
        String[] ex1 = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
        String[] ex2 = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
        String[] ex3 = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
        String[] ex4 = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"};
        String[] ex5 = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"};

        //System.out.println(Arrays.toString(solution(ex1)));
        //System.out.println(Arrays.toString(solution(ex2)));
        System.out.println(Arrays.toString(solution(ex3)));
        //System.out.println(Arrays.toString(solution(ex4)));
        //System.out.println(Arrays.toString(solution(ex5)));
    }

    static List<String> xList = new ArrayList<>();
    static List<String> aList = new ArrayList<>();
    static int maxIndex;

    public static String[] solution(String[] expressions) {
        String[] answer = {};
        xList = new ArrayList<>();
        aList = new ArrayList<>();
        maxIndex = 0;

        for (int i = 0; i < expressions.length; i++) {
            if (expressions[i].contains("X")) {
                xList.add(expressions[i]);
            } else {
                aList.add(expressions[i]);
            }
        }

        boolean[] checkArr = checkNum(expressions);

        answer = checkX(checkArr, xList);

        return answer;
    }

    // 사칙연산도 n진법으로 해야됨
    // 수들 최대값을 찾고
    // maxIndex 부터 9진법 까지 aList 값들 계산해서 가능한 진법 뽑기
    public static String[] checkX(boolean[] checkArr, List<String> xList) {
        String[] result = new String[xList.size()];

        for (int i = 0; i < xList.size(); i++) {
            String[] temp = xList.get(i).split(" ");

            Set<String> set = new HashSet<>();
            for (int j = 2; j < 10; j++) {
                if (checkArr[j]) {
                    set.add(fourBasicOperations(temp, j) + "");
                }
            }

            if (set.size() == 1) {
                temp[4] = set.iterator().next();
            } else {
                temp[4] = "?";
            }
            result[i] = temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[3] + " " + temp[4];
        }
        return result;
    }

    public static boolean[] checkNum(String[] ex) {
        boolean[] result = new boolean[10];

        // 최대값 찾기
        for (int j = 0; j < ex.length; j++) {
            String[] temp = ex[j].split(" ");

            for (int i = 0; i < temp[0].length(); i++) {
                char cTemp = temp[0].charAt(i);
                maxIndex = Math.max(Character.getNumericValue(cTemp), maxIndex);
            }

            for (int i = 0; i < temp[2].length(); i++) {
                char cTemp = temp[2].charAt(i);
                maxIndex = Math.max(Character.getNumericValue(cTemp), maxIndex);
            }

            if (!temp[4].equals("X")) {
                for (int i = 0; i < temp[4].length(); i++) {
                    char cTemp = temp[4].charAt(i);
                    maxIndex = Math.max(Character.getNumericValue(cTemp), maxIndex);
                }
            }
        }

        // aList 뒤져서 가능한 n진법 모두 찾기
        maxIndex++;

        for (int i = maxIndex; i < 10; i++) {
            result[i] = notation(i);
        }

        return result;
    }

    // n 진법으로 가능한 수식인지 확인
    private static boolean notation(int i) {
        int cnt = 0;
        for (int j = 0; j < aList.size(); j++) {
            String[] formula = aList.get(j).split(" ");
            int answer = fourBasicOperations(formula, i);
            if (answer == Integer.parseInt(formula[4])) {
                cnt++;
            }
        }

        if (cnt == aList.size()) {
            return true;
        }

        return false;
    }

    public static int fourBasicOperations(String[] formula, int i){
        int answer = 0;
        int multiple = 1;
        int carry = 0;
        String[] copyFormula = formula.clone();
        int alength = copyFormula[0].length();
        int blength = copyFormula[2].length();

        if (alength == 1) {
            copyFormula[0] = "00" + copyFormula[0];
        }else {
            copyFormula[0] = "0" + copyFormula[0];
        }

        if (blength == 1) {
            copyFormula[2] = "00" + copyFormula[2];
        }else{
            copyFormula[2] = "0" + copyFormula[2];
        }

        for (int k = 2; k >= 0; k--) {
            int sum = 0;
            char a = copyFormula[0].charAt(k);
            char b = copyFormula[2].charAt(k);

            if (copyFormula[1].equals("+")) {
                sum = Character.getNumericValue(a) + Character.getNumericValue(b);

                sum += carry;
                carry = 0;
                while (sum >= i) {
                    sum = sum - i;
                    carry++;
                }
            } else {
                sum = Character.getNumericValue(a) - Character.getNumericValue(b);

                sum += carry;
                carry = 0;
                while (0 > sum) {
                    sum = sum + i;
                    carry--;
                }
            }


            answer += sum * multiple;
            multiple *= 10;
        }
        return answer;
    }

}