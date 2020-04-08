package com.my.arithmetic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @author: Ma Xiangguang
 * @date: 2020/4/8 15:06
 * @version: 1.0
 */
public class ArithmeticDemo {

    public static void main(String[] args) {
        int[] a = new int[]{12, 1, 4, 432, 3, 23, 13, 43};
        int[] b = {12, 1, 4, 432, 3, 23, 13, 43};


        int[] ints = Arrays.stream(a).sorted().filter(value -> value > 10).toArray();

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(ints));

        int i = biSearch(ints, 22);
        System.out.println("i = " + i);

        bubbleSort1(b);
        System.out.println(Arrays.toString(b));


        HashMap<String, Object> objectObjectHashMap = new HashMap<>();



    }

    /**
     * 二分法
     *
     * @param array
     * @param a
     * @return
     */
    public static int biSearch(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            //中间位置
            mid = (lo + hi) / 2;
            if (array[mid] == a) {
                return mid + 1;
                //向右查找
            } else if (array[mid] < a) {
                lo = mid + 1;
            } else { //向左查找
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
    public static void bubbleSort1(int[] a) {
        int i, j;
        int n = a.length;
        for (i = 0; i < n; i++) {
            for (j = 1; j < n; j++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序 时间复杂度  n2
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        for (int i = 1, length = a.length; i < length; i++) {
            //插入的数据
            int insertVal = a[i];

            //被插入的位置(准备和前一个数比较)
            int index = i - 1;

            //如果插入的数比被插入的数小
            while (index > 0 && insertVal < a[index]) {
                //将把a[index] 向后移动
                a[index + 1] = a[index];
                //让index向前移动
                index--;
            }

            //把插入的数放入合适位置
            a[index + 1] = insertVal;
        }
    }

    /**
     * 快速排序
     *
     * @param a
     * @param low
     * @param high
     */
    public static void quickSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key) {
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            }
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key) {
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
                if (a[start] >= key) {
                    int temp = a[start];
                    a[start] = a[end];
                    a[end] = temp;
                }
                //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
            }
            //递归
            if (start > low) quickSort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
            if (end < high) quickSort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
        }

    }

}
