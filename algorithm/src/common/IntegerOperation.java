package common;

import java.util.Arrays;

/**
 * @Author: suntong
 * @Date:Created in 11:01 2019/7/3
 * 整数操作算法
 */
public class IntegerOperation {

    /**
     * 整数中二进制中1的个数（负数用补码表示）
     * @param n
     * @return
     */
    public static int getBinaryOneCount(int n){//支持负数
        if(n == 0){
            return 0;
        }
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n - 1);//清除二进制低位的1
        }
        return count;
    }
    public static int BinaryNumOf1(int num) {//不支持负数
        int count = 0;
        while (num != 0){
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }



    /**
     * 求m的n次方
     * @param m
     * @param n
     * @return
     */
    public static int calPow1(int m, int n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return m;
        }
        //判断奇偶数，进行递归
        int res = 0;
        res = calPow1(m, n);
        res *= res;
        if(n % 2 != 0){
            res *= m;
        }
        return res;
    }
    //第二种方法(转次方为2进制)
    public static int calPow2(int m, int n){
        int res = 1;
        String binary = Integer.toBinaryString(n);
        for(int idx = 0;idx < binary.length();idx++)
        {
            //	System.out.println(ab.length());
            int s = Integer.parseInt(String.valueOf(binary.charAt(idx)));//char类型转化为int
            //		System.out.println(s);
            res *= res;
            if(s == 1){
                res *= m;
            }
        }
        System.out.println(binary);
        return res;
    }

    /**
     * 整数的任意的进制之间转换
     * @param num
     * @param fromRadix
     * @param toRadix
     * @return
     */
    private static char chs[] = new char[36];
    static {
        for(int i = 0; i < 10 ; i++) {
            chs[i] = (char)('0' + i);
        }
        for(int i = 10; i < chs.length; i++) {
            chs[i] = (char)('A' + (i - 10));
        }
    }
    //转为10进制
    public static int strToInt (String resStr, int radix){
        char[] res = resStr.toCharArray();
        int sum = 0;
        for (int i = res.length - 1; i >= 0 ; i--) {
            sum += (res[i] - '0') * Math.pow(radix, res.length - i - 1);
        }
        return sum;
    }
    public static String transRadix(String num, int fromRadix, int toRadix){
        int number = strToInt(num, fromRadix);
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            int index = number % toRadix;
            sb.append(chs[index]);
            number = number / toRadix;
        }
        return sb.reverse().toString();
    }

    /**
     * 求一个正整数数最少可以由几个数的平房组成
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int idx = 1;
            while (i - idx * idx >= 0) {
                min = Math.min(dp[i - idx * idx] + 1, min);
                idx++;
            }
            dp[i] = min;
        }
        return dp[n];
    }
    public static Integer powerOfcombine(int n){
        if(n == 0 || n == 1){
            return n;
        }
        int min = n;
        for(int i = 0;i * i <= n ;i++){
            n = powerOfcombine(n - i * i) +1;
            if(n < min){
                min = n;
            }
        }
        return min;
    }
}
