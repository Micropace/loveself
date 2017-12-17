package com.weibu.loveself.utils;


import java.util.ArrayList;
import java.util.List;

public class MathUtil {

    /**
     * 求一组字符串的组合
     * @param source 字符串列表
     * @return 组合后的结果列表
     */
    public static List<String> combination(List<String> source) {
        List<String> result = new ArrayList<>();

        int size = source.size();
        String str[] = source.toArray(new String[source.size()]);

        int nbit = (0xFFFFFFFF >>> (32 - size));

        for (int i = 1; i <= nbit; i++) {
            StringBuilder res = new StringBuilder();
            for (int j = 0; j < size; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    res.append(str[j]);
                }
            }
            result.add(res.toString());
        }
        // 最后的结果排序
        result.sort((a, b) -> {
            if(a.length() < b.length()) return -1;
            else if(a.length() == b.length()) {
                return a.compareTo(b);
            }
            else {
                return 1;
            }
        });

        return result;
    }
}
