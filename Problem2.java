import java.util.HashMap;
import java.util.Map;

public class DigitalRoot {
    
    static final Map<Character, int[]> SEGMENT_MAP = new HashMap<>();
    
    static {
        SEGMENT_MAP.put('0', new int[]{1, 1, 1, 1, 1, 1, 0});
        SEGMENT_MAP.put('1', new int[]{0, 1, 1, 0, 0, 0, 0});
        SEGMENT_MAP.put('2', new int[]{1, 1, 0, 1, 1, 0, 1});
        SEGMENT_MAP.put('3', new int[]{1, 1, 1, 1, 0, 0, 1});
        SEGMENT_MAP.put('4', new int[]{0, 1, 1, 0, 0, 1, 1});
        SEGMENT_MAP.put('5', new int[]{1, 0, 1, 1, 0, 1, 1});
        SEGMENT_MAP.put('6', new int[]{1, 0, 1, 1, 1, 1, 1});
        SEGMENT_MAP.put('7', new int[]{1, 1, 1, 0, 0, 0, 0});
        SEGMENT_MAP.put('8', new int[]{1, 1, 1, 1, 1, 1, 1});
        SEGMENT_MAP.put('9', new int[]{1, 1, 1, 1, 0, 1, 1});
    }

    public static int countTransitions(char from, char to) {
        int[] segFrom = SEGMENT_MAP.get(from);
        int[] segTo = SEGMENT_MAP.get(to);
        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (segFrom[i] != segTo[i]) count++;
        }
        return count;
    }

    public static int getSamTransitions(String[] numbers) {
        int transitions = 0;
        for (String num : numbers) {
            for (char c : num.toCharArray()) {
                transitions += getOnSegmentCount(c);
            }
        }
        for (int i = 0; i < numbers.length - 1; i++) {
            transitions += getTotalTransitions(numbers[i], numbers[i + 1]);
        }
        return transitions;
    }

    public static int getMaxTransitions(String[] numbers) {
        int transitions = getOnSegmentCount(numbers[0].charAt(0));
        for (int i = 1; i < numbers.length; i++) {
            transitions += getTotalTransitions(numbers[i - 1], numbers[i]);
        }
        return transitions;
    }

    public static int getOnSegmentCount(char c) {
        int count = 0;
        for (int s : SEGMENT_MAP.get(c)) {
            count += s;
        }
        return count;
    }

    public static int getTotalTransitions(String num1, String num2) {
        int total = 0;
        int len1 = num1.length(), len2 = num2.length();
        int maxLen = Math.max(len1, len2);
        
        num1 = String.format("%" + maxLen + "s", num1).replace(' ', '0');
        num2 = String.format("%" + maxLen + "s", num2).replace(' ', '0');

        for (int i = 0; i < maxLen; i++) {
            total += countTransitions(num1.charAt(i), num2.charAt(i));
        }
        return total;
    }

    public static void main(String[] args) {
        String[] numbers = {"137", "11", "2"};

        int samTransitions = getSamTransitions(numbers);
        int maxTransitions = getMaxTransitions(numbers);

        System.out.println("Sam's Total Transitions: " + samTransitions);
        System.out.println("Max's Total Transitions: " + maxTransitions);
        System.out.println("Difference: " + (samTransitions - maxTransitions));
    }
}
