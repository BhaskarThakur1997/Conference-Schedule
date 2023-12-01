import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'maxPresentations' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY scheduleStart
     *  2. INTEGER_ARRAY scheduleEnd
     */

    public static int maxPresentations(List<Integer> scheduleStart, List<Integer> scheduleEnd) {
    // Write your code here
        int n = scheduleStart.size();
        List<int[]> presentations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            presentations.add(new int[]{scheduleStart.get(i), scheduleEnd.get(i)});
        }

        presentations.sort((a, b) -> Integer.compare(a[1], b[1])); // Sort by end times

        int maxAttended = 0;
        int endTime = 0;

        for (int[] presentation : presentations) {
            int start = presentation[0];
            int end = presentation[1];

            if (start >= endTime) {
                maxAttended++;
                endTime = end;
            }
        }

        return maxAttended;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scheduleStartCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> scheduleStart = IntStream.range(0, scheduleStartCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int scheduleEndCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> scheduleEnd = IntStream.range(0, scheduleEndCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maxPresentations(scheduleStart, scheduleEnd);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
