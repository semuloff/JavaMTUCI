import java.util.ArrayList;
import java.util.LinkedList;

public class Lesson_7 {

    // Collection.
    public static void main(String[] args) {
        // ArrayList. ------------------------------------

        ArrayList<Integer> numbers = new ArrayList<>();

        // methods.
        numbers.add(1);
        numbers.add(1);

        System.out.println(numbers);
        numbers.add(1, 2);

        System.out.println(numbers);

        System.out.println(numbers.size());
        System.out.println(numbers.isEmpty());
        System.out.println(numbers.get(1));

        System.out.println(numbers.remove(0));
        System.out.println(numbers + "\n");

        // add values in numbers.
        for (int number = 1; number < 12; number += 2) {
            numbers.add(number);
        }

        // new method for "for" cycle.
        for (Integer el : numbers) {
            System.out.println("Element: " + el);
        }


        // LinkedList. ------------------------------------

        LinkedList<Float> floats = new LinkedList<>();

        // the principle of operation is similar to the previous type of collection.
        floats.add(0.3f);
        floats.add((float) 9.32);

        System.out.println(floats);
        System.out.println(floats.size());

        for (Float el : floats)
            System.out.println(el);
    }
}
