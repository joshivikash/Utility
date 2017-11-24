import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BigAndSmallLettersCombination {
    public static void main(String[] args) {
        System.out.println(getListOfBigAndSmallLettersCombinations("Abcd"));
    }

    private static Set<String> getListOfBigAndSmallLettersCombinations(String string) {
        List<Integer[]> combinations = new ArrayList<Integer[]>();
        Integer[] inputArr = new Integer[string.length()];
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = i;
        }
        generateDifferentCombinations(inputArr, combinations);
        Set<String> combinationsOfBigAndSmallLetters = new HashSet<String>();
        combinations.forEach((arr) -> {
            String s = string.toLowerCase();
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j];
                s = s.replace(s.charAt(index), Character.toUpperCase(s.charAt(index)));
            }
            combinationsOfBigAndSmallLetters.add(s);
        });
        combinationsOfBigAndSmallLetters.add(string.toLowerCase());
        return combinationsOfBigAndSmallLetters;
    }

    private static void generateDifferentCombinations(Integer[] indexesArr, List<Integer[]> combinations) {
        // Iterate through the Input String's Index Array
        for (int i = 0; i < indexesArr.length; i++) {
            // For each index create the array of remaining indexes
            Integer[] remainingIndexesArr = new Integer[indexesArr.length - 1];
            int firstIndex = -1;
            int incrementingIndex = 0;
            boolean isRemainingIndexArrEmpty = true;

            // Populating "remainingIndexesArr" with elements that reside on the
            // RHS
            // of ith element of indexesArr
            for (int j = i + 1; j < indexesArr.length; j++) {
                firstIndex = (firstIndex == -1) ? j : firstIndex;
                remainingIndexesArr[incrementingIndex++] = indexesArr[j];
                isRemainingIndexArrEmpty = (isRemainingIndexArrEmpty) ? false : isRemainingIndexArrEmpty;
            }

            // Assigning the last element of indexesArr to "firstIndex"
            firstIndex = (firstIndex == -1 && i == (indexesArr.length - 1)) ? i : firstIndex;

            // Populating "remainingIndexesArr" with elements that reside on the
            // LHS
            // of ith element of indexesArr
            for (int k = 0; k != i && k < firstIndex; k++) {
                remainingIndexesArr[incrementingIndex++] = indexesArr[k];
                isRemainingIndexArrEmpty = (isRemainingIndexArrEmpty) ? false : isRemainingIndexArrEmpty;
            }

            if (!isRemainingIndexArrEmpty) {
                generateDifferentCombinations(remainingIndexesArr, combinations);
            }
            // If it is the last index add all indexes for this string , to the
            // list of
            // other combination string list
            if (i == (indexesArr.length - 1)) {
                combinations.add(indexesArr);
            }
        }
    }

}
