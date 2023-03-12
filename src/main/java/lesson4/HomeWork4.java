package lesson4;

import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {

    public static char[][] field;
    public static int size = 3;
    public static final char empty_space = '♦';
    public static final char dot_x = 'X';
    public static final char dot_0 = 'O';
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите размер поля");
        size = scanner.nextInt();
        createField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(dot_x)) {
                System.out.println("Победил человек");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
            compTurn();
            printField();
            if (checkWin(dot_0)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Конец");
    }

    public static void createField() {
        field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = empty_space;
            }
        }
    }

    public static void printField() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (!isVacant(x, y)) System.out.println("Уже занято, введите еще раз");
        } while (!isVacant(x, y));
        field[y][x] = dot_x;
    }

    public static void compTurn() {
        int x=0, y=0;
        int []array = {0,0};
        if (checkBlock(array)){
            x = array[0];
            y = array[1];
            System.out.println("Блокирующий ход компьютера:" +
                    " " + (x + 1) + " " + (y + 1));
        }
        else {
            do {
                x = random.nextInt(size)-1;
                y = random.nextInt(size)-1;
            } while (!isVacant(x, y));
        System.out.println("Ход компьютера:" +
                " " + (x + 1) + " " + (y + 1));
        }
        field[y][x] = dot_0;
    }

    public static boolean isVacant(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        return field[y][x] == empty_space;
    }
    public static boolean isFieldFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == empty_space) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        boolean[] check = new boolean[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                check[j]= (field[i][j] == c);
            }
            if (checkArray(check)) return true;
            for (int j = 0; j < size; j++) {
                check[j]= (field[j][i] == c);
            }
            if (checkArray(check)) return true;
        }

        //if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        for (int i = 0; i < size; i++) {
            check[i] = (field[i][i] == c);
        }
        if (checkArray(check)) return true;

        //if (field[2][0] == c && field[1][1] == c && field[0][2] == c) return true;
        for (int i = 0; i < size; i++) {
            check[i] = (field[size-i-1][i] == c);
        }
        if (checkArray(check)) return true;

        return false;
    }
    public static boolean checkArray(boolean[] array) {
        boolean answer = true;
        boolean sequence = false;
        if (array.length==5){
            sequence = true;
        }
        boolean a=false,b=false,c=false,d=false,e=false;
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) answer = false;
            if (sequence){
                if (i==0) {a = array[i];}
                if (i==1) {b = array[i];}
                if (i==2) {c = array[i];}
                if (i==3) {d = array[i];}
                if (i==4) {e = array[i];}
            }
        }
        if(sequence && b && c && d && (a||e)) answer=true;

        return answer;
    }

    public static boolean checkBlock(int array[]) {
        boolean answer = false;
        boolean bField[][]=new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bField[i][j]= false;
                if (!isVacant(i, j)) bField[i][j] = true;
            }
        }

        //строки
        for (int i = 0; i < size; i++) {
            if (bField[0][i]&&bField[1][i]) {array[0]=2;array[1]=i; if(isVacant(2, i)){if(field[i][0]==field[i][1]) return true;}}
            if (bField[0][i]&&bField[2][i]) {array[0]=1;array[1]=i; if(isVacant(1, i)){if(field[i][0]==field[i][2]) return true;}}
            if (bField[1][i]&&bField[2][i]) {array[0]=0;array[1]=i; if(isVacant(0, i)){if(field[i][1]==field[i][2]) return true;}}
        }
        //if (bField[0][0]&&bField[1][0]) {array[0]=2;array[1]=0; if(isVacant(2, 0)){if(field[0][0]==field[0][1]) return true;}}
        //if (bField[0][0]&&bField[2][0]) {array[0]=1;array[1]=0; if(isVacant(1, 0)){if(field[0][0]==field[0][2]) return true;}}
        //if (bField[1][0]&&bField[2][0]) {array[0]=0;array[1]=0; if(isVacant(0, 0)){if(field[0][1]==field[0][2]) return true;}}

        //if (bField[0][1]&&bField[1][1]) {array[0]=2;array[1]=1; if(isVacant(2, 1)){if(field[1][0]==field[1][1]) return true;}}
        //if (bField[0][1]&&bField[2][1]) {array[0]=1;array[1]=1; if(isVacant(1, 1)){if(field[1][0]==field[1][2]) return true;}}
        //if (bField[1][1]&&bField[2][1]) {array[0]=0;array[1]=1; if(isVacant(0, 1)){if(field[1][1]==field[1][2]) return true;}}

        //if (bField[0][2]&&bField[1][2]) {array[0]=2;array[1]=2; if(isVacant(2, 2)){if(field[2][0]==field[2][1]) return true;}}
        //if (bField[0][2]&&bField[2][2]) {array[0]=1;array[1]=2; if(isVacant(1, 2)){if(field[2][0]==field[2][2]) return true;}}
        //if (bField[1][2]&&bField[2][2]) {array[0]=0;array[1]=2; if(isVacant(0, 2)){if(field[2][1]==field[2][2]) return true;}}

        //столбцы
        for (int i = 0; i < size; i++) {
            if (bField[i][0]&&bField[i][1]) {array[0]=i;array[1]=2; if(isVacant(i, 2)){if(field[0][i]==field[1][i]) return true;}}
            if (bField[i][0]&&bField[i][2]) {array[0]=i;array[1]=1; if(isVacant(i, 1)){if(field[0][i]==field[2][i]) return true;}}
            if (bField[i][1]&&bField[i][2]) {array[0]=i;array[1]=0; if(isVacant(i, 0)){if(field[1][i]==field[2][i]) return true;}}
        }
        //if (bField[0][0]&&bField[0][1]) {array[0]=0;array[1]=2; if(isVacant(0, 2)){if(field[0][0]==field[1][0]) return true;}}
        //if (bField[0][0]&&bField[0][2]) {array[0]=0;array[1]=1; if(isVacant(0, 1)){if(field[0][0]==field[2][0]) return true;}}
        //if (bField[0][1]&&bField[0][2]) {array[0]=0;array[1]=0; if(isVacant(0, 0)){if(field[1][0]==field[2][0]) return true;}}

        //if (bField[1][0]&&bField[1][1]) {array[0]=1;array[1]=2; if(isVacant(1, 2)){if(field[0][1]==field[1][1]) return true;}}
        //if (bField[1][0]&&bField[1][2]) {array[0]=1;array[1]=1; if(isVacant(1, 1)){if(field[0][1]==field[2][1]) return true;}}
        //if (bField[1][1]&&bField[1][2]) {array[0]=1;array[1]=0; if(isVacant(1, 0)){if(field[1][1]==field[2][1]) return true;}}

        //if (bField[2][0]&&bField[2][1]) {array[0]=2;array[1]=2; if(isVacant(2, 2)){if(field[0][2]==field[1][2]) return true;}}
        //if (bField[2][0]&&bField[2][2]) {array[0]=2;array[1]=1; if(isVacant(2, 1)){if(field[0][2]==field[2][2]) return true;}}
        //if (bField[2][1]&&bField[2][2]) {array[0]=2;array[1]=0; if(isVacant(2, 0)){if(field[1][2]==field[2][2]) return true;}}

        //диагональ 1
        if (bField[0][0]&&bField[1][1]) {array[0]=2;array[1]=2; if(isVacant(2, 2)){if(field[0][0]==field[1][1]) return true;}}
        if (bField[0][0]&&bField[2][2]) {array[0]=1;array[1]=1; if(isVacant(1, 1)){if(field[0][0]==field[2][2]) return true;}}
        if (bField[1][1]&&bField[2][2]) {array[0]=0;array[1]=0; if(isVacant(0, 0)){if(field[1][1]==field[2][2]) return true;}}

        //диагональ 2
        if (bField[2][0]&&bField[1][1]) {array[0]=0;array[1]=2; if(isVacant(0, 0)){if(field[0][2]==field[1][1]) return true;}}
        if (bField[2][0]&&bField[0][2]) {array[0]=1;array[1]=1; if(isVacant(1, 1)){if(field[0][2]==field[2][0]) return true;}}
        if (bField[1][1]&&bField[0][2]) {array[0]=2;array[1]=0; if(isVacant(2, 2)){if(field[1][1]==field[2][0]) return true;}}


        return answer;
    }
}
