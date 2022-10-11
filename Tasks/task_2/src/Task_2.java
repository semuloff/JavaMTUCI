public class Task_2 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "repeat");
        System.out.println(repeat("mice", 5)); // ("mice", 5) -> "mmmmmiiiiiccccceeeee"
        System.out.println(repeat("hello", 3)); // ("hello", 3) ➞ "hhheeellllllooo"
        System.out.println(repeat("stop", 1)); // ("stop", 1) ➞ "stop"

        // (2/10)
        // prettyPrint("(1/10)", "differenceMaxMin");
        // System.out.println(differenceMaxMin());
    }

    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static String repeat(String arg, int conut) {
        String answer = "";

        for (int i = 0; i < arg.length(); i++) {
            answer += (arg.charAt(i) + "").repeat(conut);
        }

        return answer;
    }

    // (2/10)
    // public static void differenceMaxMin(int[] args) {
    //    int maxNumber, minNumber;
    //}
}

