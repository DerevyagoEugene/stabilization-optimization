package utility;

import java.util.Random;

public class RandomUtility {
    private static Integer randomNumber = 0;

    public static Integer randomIntegerFrom5To15() {
        if (randomNumber != 0)
            randomNumber = new Random().nextInt(11) + 5;
        return randomNumber;
    }
}
