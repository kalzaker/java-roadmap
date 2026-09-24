package m01.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        System.out.print("Проверка на палиндром a{b}a, гойда, Уж редко рукою окурок держу: ");
        System.out.println(isPalindrome("a{b}a") + ", " + isPalindrome("гойда") + ", " + isPalindrome("Уж редко рукою окурок держу"));

        System.out.print("Проверка на анограмму Рапид/пидар, Кот/кто, Папа/инвалид: ");
        System.out.println(isAnagram("Рапид", "пидар") + ", " + isAnagram("Кот", "кто") + ", " + isAnagram("Папа", "инвалид"));

        System.out.println("Вывод частоты символов в словах жопа, рука, нигермания");
        charFrequency("жопа"); charFrequency("рука"); charFrequency("нигермания");

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        int binaryIndex = binarySearch(arr, 7);
        System.out.print("Бинарный поиск индекса числа 7 в массиве 1, 3, 5, 7, 9, 11, 13, 15: ");
        System.out.println(binaryIndex);
        binaryIndex = binarySearch(arr, 11);
        System.out.print("Бинарный поиск индекса числа 11 в массиве 1, 3, 5, 7, 9, 11, 13, 15: ");
        System.out.println(binaryIndex);
        binaryIndex = binarySearch(arr, -1);
        System.out.print("Бинарный поиск индекса числа -1 в массиве 1, 3, 5, 7, 9, 11, 13, 15: ");
        System.out.println(binaryIndex);

        int[] arr2 = {2, 4, 6, 8, 10, 12, 14};
        System.out.print("Слияние двух массивов: ");
        System.out.println(Arrays.toString(mergeArrays(arr, arr2)));

        System.out.print("Второй максимум массива: ");
        System.out.println(findSecondMax(arr));
    }

    public static boolean isPalindrome(String s){
        if (s.isEmpty()) return false;
        var clean = s.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();
        var reversed = new StringBuilder(clean).reverse().toString();
        return clean.equals(reversed);
    }

    public static boolean isAnagram(String s1, String s2){
        if(s1.isEmpty() || s2.isEmpty()) return false;

        String clean1 = s1.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();
        String clean2 = s2.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();

        if(clean1.length() != clean2.length()) return false;

        int[] charCounts = new int[65536];

        for (int i = 0; i < clean1.length(); i++){
            charCounts[clean1.charAt(i)]++;
            charCounts[clean2.charAt(i)]--;
        }

        for(int count : charCounts) {
            if(count != 0) return false;
        }

        return true;
    }

    public static void charFrequency(String s){
        if(s.isEmpty()) return;

        String clean = s.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();

        Map<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < clean.length(); i++) {
            char c = clean.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static int binarySearch(int[] array, int target){
        int left = 0;
        int right = array.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(array[mid] == target) return mid;

            if(array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    public static int[] mergeArrays(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < arr1.length && j < arr2.length){
            if(arr1[i] <= arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
            k++;
        }

        while(i < arr1.length) {
            result[k] = arr1[i];
            i++;
            k++;
        }

        while(j < arr2.length) {
            result[k] = arr2[j];
            j++;
            k++;
        }

        return result;
    }

    public static int findSecondMax(int[] arr){
        if(arr.length < 2){
            throw new IllegalArgumentException("Массив должен содержать больше одного элемента");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for(int num : arr){
            if(num > max){
                secondMax = max;
                max = num;
            } else if(num > secondMax && num != max) {
                secondMax = num;
            }
        }

        if(secondMax == Integer.MIN_VALUE){
            throw new RuntimeException("Второго максимума не существует");
        }

        return secondMax;
    }
}
