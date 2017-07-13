package demos;

public class TypeTime {
    static int KEYPAD_COLUMNS = 3;

    static int entryTime(String s, String keypad) {
        char firstKey = s.charAt (0);
        int firstPosition = keypad.indexOf (firstKey);
        int currentColumn = firstPosition % KEYPAD_COLUMNS;
        int currentRow = firstPosition / KEYPAD_COLUMNS;
        int currentTime = 0;

        final char[] remainingKeys = s.substring (1).toCharArray ();
        for (char typedKey : remainingKeys) {
            int newPosition = keypad.indexOf (typedKey);
            int newColumn = newPosition % KEYPAD_COLUMNS;
            int newRow = newPosition / KEYPAD_COLUMNS;

            // Increment is the same as max distance
            currentTime += Math.max (
                Math.abs (currentColumn - newColumn),
                Math.abs (currentRow - newRow)
            );

            // Update current values
            currentColumn = newColumn;
            currentRow = newRow;
        }

        return currentTime;
    }

//    public static void main (String... args) {
//        System.out.println (0 % 3);
//        System.out.println (1 % 3);
//        System.out.println (2 % 3);
//        System.out.println (3 % 3);
//
//        System.out.println (0 / 3);
//        System.out.println (1 / 3);
//        System.out.println (2 / 3);
//        System.out.println (3 / 3);
//
//        System.out.println (entryTime("423692", "923857614"));
//
//        assert entryTime("423692", "923857614") == 8;
//    }




    static long journeyStep(int[] path, int k, int total, int max, int index, int jump) {
        if (index == path.length - 1) { // At the end return total
            return total > max? total : max;
        }
        else if (jump > 1) {
            return journeyStep (path, k, total + path[index + jump], max, index, jump - 1);
        }
        else {
            return journeyStep (path, k, total + path[index + jump], max, index + jump, k);
        }
    }

    static long journey(int[] path, int k) {
        return journeyStep (path, k, path[0], 0, 0, k);
    }

    public static void main (String... args) {
        assert entryTime("423692", "923857614") == 8;
    }
}
