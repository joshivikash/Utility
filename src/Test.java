import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String string = "ABC";
        List<Integer[]> combinations = new ArrayList<Integer[]>();
        Integer[] inputArr = new Integer[string.length()];
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = i;
        }
        generateDifferentCombinations(inputArr, combinations);
        combinations.forEach((arr) -> {
            String s = string.toLowerCase();
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j];
                s = s.replace(s.charAt(index), Character.toUpperCase(s.charAt(index)));
            }
            System.out.println(s);
        });

    }

    private static void generateDifferentCombinations(Integer[] indexesArr, List<Integer[]> ipAddressCombinations) {
        // Iterate through the input string index Array
        for (int i = 0; i < indexesArr.length; i++) {
            // For each index get the array of remaining indexes
            Integer[] remainingIndexes = new Integer[indexesArr.length - 1];
            int firstIndexInRemainingIndexesArr = -1;
            int index = 0;
            boolean isRemainingIndexArrEmpty = true;
            for (int j = i + 1; j < indexesArr.length; j++) {
                firstIndexInRemainingIndexesArr = (firstIndexInRemainingIndexesArr == -1) ? j
                        : firstIndexInRemainingIndexesArr;
                remainingIndexes[index++] = indexesArr[j];
                isRemainingIndexArrEmpty = (isRemainingIndexArrEmpty) ? false : isRemainingIndexArrEmpty;
            }
            firstIndexInRemainingIndexesArr = (firstIndexInRemainingIndexesArr == -1 && indexesArr.length > 2
                    && i == (indexesArr.length - 1)) ? i : firstIndexInRemainingIndexesArr;
            for (int k = 0; k != i && k < firstIndexInRemainingIndexesArr; k++) {
                remainingIndexes[index++] = indexesArr[k];
                isRemainingIndexArrEmpty = (isRemainingIndexArrEmpty) ? false : isRemainingIndexArrEmpty;
            }
            if (!isRemainingIndexArrEmpty) {
                generateDifferentCombinations(remainingIndexes, ipAddressCombinations);
            }
            // If it is the last index add all indexes for this string , to the
            // list of
            // other combination string list
            if (i == (indexesArr.length - 1)) {
                ipAddressCombinations.add(indexesArr);
            }
        }
    }

}
