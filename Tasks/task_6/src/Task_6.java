package Tasks.task_6.src;

public class Task_6 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(10/10)", "palindromedescendant");
        System.out.println(palindromeDescendant(11211230)); // 11211230 -> true
        System.out.println(palindromeDescendant(13001120)); // 13001120 -> true
        System.out.println(palindromeDescendant(23336014)); // 23336014 -> true
        System.out.println(palindromeDescendant(11)); // 11 -> true
    }
    
    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (10/10)
    public static boolean palindromeDescendant(int number) {
        // assert: number's lenght is even.
        while (number / 10 > 0) {
            StringBuilder builder = new StringBuilder();

            while (number > 0) {
                int firstPart = number % 10;
                number /= 10;
                builder.append(firstPart + number % 10);
                number /= 10;
            }

            boolean quess = true;
            String line = builder.toString();

            for (int middle = line.length() / 2 - 1, balance = 0; middle >= 0; middle--, balance++) {
                if (!(line.charAt(middle) == line.charAt(middle + 2 * balance + 1))) {
                    quess = false;
                    number = Integer.valueOf(builder.toString());
                }
            }

            return true;
        }

        return false;
    }
}

