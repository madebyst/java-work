package common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: suntong
 * @Date:Created in 10:59 2019/7/3
 * 字符串操作的算法
 */
public class StringOperation {
    /**
     * 字符串反转
     * @param str
     * @return
     */
    public static String reverse(String str){
        char[] strArr = str.toCharArray();
        int len = strArr.length;
        for(int i = 0;i < len / 2;i++){
            char c = strArr[i];
            strArr[i] = strArr[len - i -1];
            strArr[len - i -1] = c;
        }
        return new String(strArr);
    }

    /**
     * 替换字符串中的空格为“%20”
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        //遍历空格数量
        int spaceNum = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        //原有空格1个位置+2个位置  预留3
        int newLength = str.length() + 2 * spaceNum;
        int oldIndex = str.length() - 1;
        int newIndex = newLength - 1;
        str.setLength(newLength);
        while (oldIndex >= 0 && oldIndex < newIndex) {
            if (str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
            oldIndex--;
        }
        return str.toString();
    }

    /**
     * 找出字符串中第一个出现且不重复的字符
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str){
        if(str.length() == 0){
            return -1;
        }
        char c = 'A';
        //z - A +1 = 57,要申请57个单元格
        int[] count = new int['z' - 'A'+1];
        for(int i = 0;i < str.length();++i){
            count[str.charAt(i) - c]++;
        }
        for(int i = 0;i < str.length();++i){
            if(count[str.charAt(i) - c] == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 找出一个字符串中的最长的且不重复子串
     * @param string
     * @return
     */
    //时间复杂度o(N)
    public static String maxUniqueSubstring(String string) {
        int begin = 0;
        int maxLength = 0;
        int curLength = 0;
        int[] positions = new int[26];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = -1; //初始化为-1，负数表示没出现过
        }
        for (int i = 0; i < string.length(); i++) {
            int curChar = string.charAt(i) - 'a';
            int prePosition = positions[curChar];
            //当前字符与它上次出现位置之间的距离
            int distance = i - prePosition;
            //当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
            if (prePosition < 0 || distance > curLength) {
                curLength++;
            } else {
                //更新最长非重复子字符串的长度
                if (curLength > maxLength){
                    maxLength = curLength;
                    begin = i - curLength;
                }
                curLength = distance;
            }
            positions[curChar] = i; //更新字符出现的位置
        }
        if (curLength > maxLength) {
            maxLength = curLength;
        }

        StringBuilder tmp = new StringBuilder();
        for (int i = begin; i < begin + maxLength; ++i) {
            tmp.append(string.charAt(i));
        }
        return tmp.toString();
    }
    //时间复杂度o(n^2)
    public static String findSubString(String str) {
        int i, j;
        char c = '0';
        int begin = 0;   //最长不重复子串起始下标
        int maxlen = 0;  //最大长度
        int length = str.length();
        char[] arr = str.toCharArray();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (i = 0; i < length; ++i) {
            map.put(arr[i] - c, 1);
            for (j = i + 1; j < length; ++j) {
                if (map.get(arr[j] - c) == null)
                    map.put(arr[j] - c, 1);
                else
                    break;
            }
            if (j - i > maxlen) {
                maxlen = j - i;
                begin = i;
            }
            map.clear();
        }

        StringBuilder tmp = new StringBuilder();
        for (i = begin; i < begin + maxlen; ++i) {
            tmp.append(arr[i]);
        }
        return tmp.toString();
    }

    /**
     * 找出字符串中最长的回文子“串” （子串是连续的）
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() < 2) { // 单个字符肯定是回文串，直接返回s
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];  // 初始化一个二维数组，值默认是false
        String result = s.substring(0,1);
        for (int j = 0; j < s.length(); j++){
            for (int i = 0; i <= j; i++){
                if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                }else {
                    dp[i][j] = false;
                }
                if (dp[i][j]){
                    if (j - i + 1 > result.length()){
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://blog.csdn.net/sugarbliss/article/details/80730929
     * 找出字符串中最长的回文子序列的长度（子序列相对位置不变，可以非连续）
     *当i>j时，f(i,j)=0。
     *当i=j时，f(i,j)=1。
     *当i<j并且s[i]=s[j]时，f(i,j)=f(i+1,j-1)+2。
     *当i<j并且s[i]≠s[j]时，f(i,j)=max( f(i,j-1), f(i+1,j) )
     * @param s
     * @return
     */
    public static int getLongestPalindromeSubStr(String s){
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][len-1];
    }


    /**
     * 递增排序的数组和数字S，数组中查找两个数，使得和是S，如果有多对数字的和等于S，输出两个数的乘积最小的
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> findNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int len = array.length;
        int i = 0;
        int j = len - 1;
        while(i < j){
            if(array[i] + array[j] == sum){
                list.add(array[i]);
                list.add(array[j]);
                break;
            }
            while(i < j && array[i] + array[j] > sum){
                --j;
            }
            while(i < j && array[i] + array[j] < sum){
                ++i;
            }
        }
        return list;
    }

}
