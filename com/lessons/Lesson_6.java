import java.util.Scanner;

public class Lesson_6 {
    public static void main(String[] args) {
        // array.
        int lenght_row = 3;
        int lenght_column = 3;
        int[][] arr = new int[lenght_row][lenght_column];
        Scanner scan = new Scanner(System.in);

        // array filling.
        for (int index = 0; index < arr.length; index++) {
            for (int index_2 = 0; index_2 < lenght_column; index_2++) {
                System.out.print("Index [" + index + "][" + index_2 + "] Value: ");
                arr[index][index_2] = scan.nextInt();
            }
        }

        System.out.println("-------------- Result --------------");

        // value output.
        for (int index = 0; index < arr.length; index++) {
            for (int index_2 = 0; index_2 < lenght_column; index_2++) {
                System.out.println("Index [" + index + "][" + index_2 + "] Value: " + arr[index][index_2]);
            }
        }
    }
}
