package com.sabahtalateh.j4j.arrays;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 */
public class ArrayDuplicate {
    /**
     * We should have `duplications` counter that will contain total number of duplications.
     *
     * Iteration description.
     * For current word in range from array start to array length minus duplications number
     * we should check if every forward standing word duplicates current word.
     * If it so we should move it to the end of words array and increase `duplications` counter.
     * If not so we should increase next index variable to have ability to access next word.
     * When we arrive to the end of duplication-free range we should check next word in words array.
     *
     * Do the iteration for every word in the duplication-free range.
     * Duplication-free range is array length minus duplications count.
     *
     * @param words input words array.
     * @return deduplicated words array.
     */
    public String[] deduplicate(String[] words) {
        int duplications = 0;

        for (int i = 0; i < words.length - duplications; i++) {
            int nextIdx = i + 1;
            String currentWord = words[i];

            while (nextIdx < words.length - duplications) {
                String nextWord = words[nextIdx];
                if (nextWord.equals(currentWord)) {
                    for (int j = nextIdx; j < words.length - duplications - 1; j++) {
                        String tmp = words[j];
                        words[j] = words[j + 1];
                        words[j + 1] = tmp;
                    }
                    duplications++;
                } else {
                    nextIdx++;
                }
            }
        }

        return Arrays.copyOf(words, words.length - duplications);
    }
}
