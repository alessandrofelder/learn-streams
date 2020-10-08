package LearnStreams.katas;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.stream.IntStream;

class SimpleConsecutivePairs {
    public static void main(String[] args) {
        System.out.println(Solution.solve(new int[]{1, 2, 5, 8, -4, -3, 7, 6, 5}));
    }


}

class Solution {
    public static int solve(int[] arr) {
        return (int) IntStream.range(0, arr.length / 2)
                .mapToObj(index -> new ImmutablePair<>(arr[2 * index], arr[(2 * index) + 1]))
                .filter(pair -> Math.abs(pair.left - pair.right) == 1)
                .count();
    }
}