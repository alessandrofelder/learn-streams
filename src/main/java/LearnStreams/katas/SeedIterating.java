package LearnStreams.katas;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SeedIterating {
    public static void main(String[] args) {
        DragonsCurve dragonsCurve = new DragonsCurve();
        System.out.println(dragonsCurve.createCurve(3));
    }


}


class DragonsCurve {
    public IntFunction<String> mapFunction = inputChar -> {
        //Make the function; map the chars to Strings
        //a -> aRbFR, b -> LFaLb, otherwise -> itself
        if (inputChar == 'a') {
            return "aRbFR";
        } else if (inputChar == 'b') {
            return "LFaLb";
        } else {
            return String.valueOf((char) inputChar);
        }
    };

    /**
     * Make the curve; stream the chars repeatedly (starting with Fa) through the mapFunction n times
     * Then remove the a and b (createFilter function is useful for that)
     */
    public String createCurve(int n) {
        if (n == 0) {
            return "F";
        }
        return Stream.iterate(
                "Fa",
                seed -> seed.chars()
                        .mapToObj(mapFunction)
                        .collect(Collectors.joining()))
                .limit(n + 1)
                .skip(n)
                .flatMapToInt(String::chars)
                .filter(createFilter('a', false))
                .filter(createFilter('b', false))
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }

    /**
     * How many of the specified char are in the given curve?
     * Hint: createFilter could be useful for this
     */
    public long howMany(char c, String curve) {
        return curve.chars().filter(createFilter(c, true)).count();
    }

    /**
     * Create a predicate to filter the specified char; keep or remove based on keep variable
     */
    public IntPredicate createFilter(char filterWhat, boolean keep) {
        return value -> {
            if (filterWhat == value) {
                return keep;
            }
            return !keep;
        };
    }
}