package space.harbour.java.hw3;

import java.util.Arrays;

public class Generics {
    public static void main(String[] args) {
        int[] arr = null;
        System.out.println(Arrays.toString(arr));

        Integer[] arr2 = {3, 4, 5, 7, 10, 3, 1};
        System.out.println(getMaxG(arr2));
        }

    public Integer get_max_int(int[] a) {
        if (a == null || a.length == 0) {
            return null;
            }
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
                }
            }
        return max;
        }

    public static <T extends Number> T getMaxG(T[] a) {
        if (a == null || a.length == 0) {
            return null;
            }
        T max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max.doubleValue() < a[i].doubleValue()) {
                max = a[i];
                }
            }
        return max;
        }
    }
