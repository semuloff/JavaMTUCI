public class Palindrome {
    // checks if a word belongs to a palindrome.
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (isPalindrome(string)) {
                System.out.println(string + " - is palindrome!");
            } else {
                System.out.println(string + " - is not palindrome!");
            }
        }
    }

    // this method checks if a word belongs to a palindrome.
    public static boolean isPalindrome(String string) {
        return string.equals(reverseString(string));
    }

    // this method returns the input parameter in reverse order.
    private static String reverseString(String string) {
        String output_word = "";
        for (int i = 0; i < string.length(); i++) {
            output_word += string.charAt(string.length() - 1 - i);
        }
        return output_word;
    }
}
