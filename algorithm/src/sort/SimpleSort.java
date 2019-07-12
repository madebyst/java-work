package sort;

import java.util.Arrays;

/**
 * @Author: suntong
 * @Date:Created in 17:13 2019/7/12
 * 冒泡、选择、插入、快排、归并、堆排
 */
public class SimpleSort {
    /**
     * 冒泡排序  (时间复杂度:最好O(n),最坏O(n^2)，空间O(1)，稳定)）
     * @param arr
     */
    public static void sort(int[] arr){
        for(int i = 0;i < arr.length - 1;++i){
            for(int j = 0;j < arr.length - i - 1;++j){
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序   （平均时间复杂度（O^2）
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for(int i = 0;i < arr.length;++i){
            for(int j = i+1;j < arr.length;++j){
                if(arr[i] > arr[j]){
                    int tmp  = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序  O(n^2)
     * @param arr
     */
    public static  void insertSort(int[] arr){
        int i,j;
        for(i = 0;i < arr.length;i++){
            int tmp = arr[i];
            for(j = i - 1;j >= 0;j--){
                if(arr[j] >= tmp){
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j +1] = tmp;
        }
    }

    /**
     * 快速排序  平均时间复杂度是O(nlogn) 最差时间复杂度是O(n^2)
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int Partition(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
    // O(nlogn),O(logn),不稳定
    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {
            int par = Partition(arr, low, high);
            quickSort(arr, low, par - 1);
            quickSort(arr, par + 1, high);
        }
    }

    public static void merge(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序 (递归) 时间复杂度 NlogN
     * @param arr
     * @param l
     * @param r
     */
    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // int mid = (l + r) / 2;
        // 等同于以上写法，这样的好处是防止溢出
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /// 合并两个有序数组为新的有序数组
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        // 逐一判断左指针指向的数和右指针指向的数
        // 小的加入到数组中
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 将剩余的数加入到数组中
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        // 将临时数组中的内容拷贝回原数组中
        // （原left-right范围的内容被复制回原数组）
        for(i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    /**
     * 归并排序 （非递归）
     * @param arr
     * @param brr
     * @param gap
     */
    public static void Merge(int[] arr, int[] brr, int gap) {
        int len = arr.length;
        int low1 = 0;// 第一个归并段的起始下标
        int high1 = low1 + gap - 1;// 第一个归并的结束下标
        int low2 = high1 + 1;// 第二个归并段的起始下标
        int high2 = low2 + gap - 1 < len - 1 ? low2 + gap - 1 : len - 1;// 第二个归并																段的结束下标
        int i = 0;// brr下标

        // 有两个归并段
        while (low2 < len) {
            // 两个归并段都有数据
            while ((low1 <= high1) && (low2 <= high2)) {
                if (arr[low1] <= arr[low2]) {
                    brr[i++] = arr[low1++];
                } else {
                    brr[i++] = arr[low2++];
                }
            }

            // 一个归并段的数据已归并完成，另一个还有数据
            while (low1 <= high1) {
                brr[i++] = arr[low1++];
            }

            while (low2 <= high2) {
                brr[i++] = arr[low2++];
            }

            low1 = high2 + 1;
            high1 = low1 + gap - 1;
            low2 = high1 + 1;
            high2 = low2 + gap - 1 < len - 1 ? low2 + gap - 1 : len - 1;
        }

        // 不足两个归并段
        while (low1 < len) {
            brr[i++] = arr[low1++];
        }

        for (i = 0; i < len; i++) {
            arr[i] = brr[i];
        }
    }

    public static void MergeSort(int[] arr)// O(nlogn),O(n),稳定
    {
        int[] brr = new int[arr.length];
        for (int i = 1; i < arr.length; i *= 2) {
            Merge(arr, brr, i);
        }
    }

    /**
     * 堆排序
     * @param data
     * @param lastIndex
     */
    // 对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex) {
        // 从（lastIndex - 1） /2处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // k保存正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                // 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    // 若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        // biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                // 如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    // 交换他们
                    swap(data, k, biggerIndex);
                    // 将biggerIndex赋予k，开始while循环的下一次循环,重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    // 交换
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 7, 6 };
        int arrayLength = a.length;
        // 循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            // 建堆
            buildMaxHeap(a, arrayLength - 1 - i);
            // 交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }
}

