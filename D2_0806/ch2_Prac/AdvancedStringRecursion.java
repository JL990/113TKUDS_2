package D2_0806.ch2_Prac;

import java.util.*;

public class AdvancedStringRecursion {

    // 1. 遞迴產生字串的所有排列組合
    public static void generatePermutations(String str) {
        generatePermutationsHelper("", str);
    }

    private static void generatePermutationsHelper(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                generatePermutationsHelper(
                    prefix + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1)
                );
            }
        }
    }

    // 2. 遞迴實作字串匹配演算法（類似 strStr）
    public static int recursiveStrStr(String haystack, String needle) {
        return strStrHelper(haystack, needle, 0);
    }

    private static int strStrHelper(String haystack, String needle, int index) {
        if (index + needle.length() > haystack.length()) return -1;
        if (haystack.substring(index, index + needle.length()).equals(needle)) {
            return index;
        }
        return strStrHelper(haystack, needle, index + 1);
    }

    // 3. 遞迴移除字串中的重複字符（只保留第一次出現）
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, 0, new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, int index, Set<Character> seen) {
        if (index == str.length()) return "";

        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDuplicatesHelper(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDuplicatesHelper(str, index + 1, seen);
        }
    }

    // 4. 遞迴計算所有子字串組合（不重複的所有子字串）
    public static void printAllSubstrings(String str) {
        printSubstringsHelper(str, 0, 0);
    }

    private static void printSubstringsHelper(String str, int start, int end) {
        if (start == str.length()) return;
        if (end > str.length()) {
            printSubstringsHelper(str, start + 1, start + 1);
        } else {
            if (start < end) {
                System.out.println(str.substring(start, end));
            }
            printSubstringsHelper(str, start, end + 1);
        }
    }

    // 主程式測試
    public static void main(String[] args) {
        System.out.println("【1】字串排列組合（abc）：");
        generatePermutations("abc");

        System.out.println("\n【2】字串匹配（找 \"lo\" in \"hello\"）：");
        int index = recursiveStrStr("hello", "lo");
        System.out.println("結果索引：" + index);

        System.out.println("\n【3】移除重複字元（aabbccddeeff）：");
        String noDup = removeDuplicates("aabbccddeeff");
        System.out.println("結果：" + noDup);

        System.out.println("\n【4】所有子字串組合（abc）：");
        printAllSubstrings("abc");
    }
}
