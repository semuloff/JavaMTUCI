public class Lesson_8 {
    public static void main(String[] args) {
        // functions
        byeWorld("Mark");
        System.out.println(sum(4, 3));
    }

    public static void byeWorld(String item) {
        System.out.println("Bye, World and " + item + "!");
    }

    public static int sum(int firstNumb, int secondNumb) {
        return firstNumb + secondNumb;
    }
}
