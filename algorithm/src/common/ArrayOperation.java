package common;

/**
 * @Author: suntong
 * @Date:Created in 15:50 2019/7/3
 * 数组的操作
 */
public class ArrayOperation {

    /**
     * 将数组的奇数放前半部分，偶数放在后半部分，且奇奇和偶偶的相对位置保持不变
     * @param array
     */
    public static void reorderArray1(int[] array){
        int i;
        int j;
        //利用冒泡排序，前偶后奇就交换O(n^2)

        for(i = 0;i < array.length;++i){
            for(j = array.length - 1;j > i;--j){
                if(array[j] % 2 == 1 && array[j - 1] % 2 == 0){
                    swap(array,j,j - 1);
                }
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //第二种:再创建一个数组,现将一种存进去，然后在把剩下的存进去。
    public static void reorderArray2(int [] array) {
        if(array.length == 0 || array.length == 1)
            return;
        //oddCount：保存奇数个数
        //oddBegin：奇数从数组头部开始添加
        int oddCount=0,oddBegin=0;
        //新建一个数组
        int[] newArray=new int[array.length];
        //计算出（数组中的奇数个数）开始添加元素
        for(int i=0;i<array.length;i++){
            if((array[i] & 1) == 1) {
                oddCount++;
            }
        }
        for(int i = 0;i < array.length;i++){
            //如果数为基数新数组从头开始添加元素
            //如果为偶数就从oddCount（数组中的奇数个数）开始添加元素
            if((array[i] & 1) == 1){
                newArray[oddBegin++]=array[i];
            }else {
                newArray[oddCount++]=array[i];
            }
        }
        for(int i = 0;i < array.length;i++){
            array[i] = newArray[i];
        }
    }


    /**
     * 计算连续子向量的最大和
     * @param array
     * @return
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
        //用temp记录累计值，sum记录和最大基于思想：
        // 对于一个数A，若是A的左边累计数非负，那么加上A能使得值不小于A，认为累计值对整体和是有贡献的。
        // 如果前几项累计值负数，则认为有害于总和，total记录当前值
        if(array.length == 0){
            return 0;
        }
        int sum = array[0];
        int tmp = array[0];
        for(int i = 1;i < array.length;++i){
            tmp = (tmp < 0)?array[i]:tmp + array[i];
            sum = (tmp > sum)?tmp:sum;
        }
        return sum;
    }


}

