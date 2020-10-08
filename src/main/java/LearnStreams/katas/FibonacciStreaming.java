package LearnStreams.katas;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FibonacciStreaming {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Utility.generateFibonacciSequence().limit(5).toArray()));
    }

}


class Utility {

    public static IntStream generateFibonacciSequence() {
        AtomicInteger nMinusOne = new AtomicInteger(1);
        AtomicInteger nMinusTwo = new AtomicInteger(1);
        // To be implemented: Proper implementation.
        return IntStream.concat(
                IntStream.of(nMinusOne.get(), nMinusTwo.get()),
                IntStream.generate(() -> {
                    int sum = nMinusOne.get() + nMinusTwo.getAndSet(nMinusOne.get());
                    nMinusOne.set(sum);
                    return nMinusOne.get();
                })

        );
    }

}