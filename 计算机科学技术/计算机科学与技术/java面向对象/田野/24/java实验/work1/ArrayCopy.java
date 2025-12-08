package study.javase;

import java.util.Arrays;

public class ArrayCopy {

    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        return copy;
    }

    public static int[][] copy2DArray(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] copy1 = copyArray(arr1);

        System.out.println("Original 1D array:");
        printArray(arr1);
        System.out.println("Copied 1D array:");
        printArray(copy1);

        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] copy2 = copy2DArray(arr2);

        System.out.println("Original 2D array:");
        print2DArray(arr2);
        System.out.println("Copied 2D array:");
        print2DArray(copy2);

        arr1[0] = 99;
        arr2[0][0] = 99;

        System.out.println("After change original 1D:");
        printArray(arr1);
        System.out.println("Copied 1D unchanged:");
        printArray(copy1);

        System.out.println("After change original 2D:");
        print2DArray(arr2);
        System.out.println("Copied 2D unchanged:");
        print2DArray(copy2);
    }
}