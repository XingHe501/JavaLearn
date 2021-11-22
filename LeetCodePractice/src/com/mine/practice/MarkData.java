package com.mine.practice;

import org.json.JSONObject;
//import com.mine.util.Base64;
import java.util.*;

public class MarkData {
    private static final String CHAR_CODE = "UTF-8";

    /**
     * 计算Data
     */
    private static String markData(Object... key_val) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (int i = 1; i < key_val.length; i += 2)
            if (key_val[i] != null && key_val[i - 1] != null)
                map.put(key_val[i - 1] + "", key_val[i]);
        return markDataFlag(map);
    }

    /**
     * 具体执行计算Data
     */
    private static String markDataFlag(Map<String, Object> params) throws Exception {
        JSONObject nameValuePairs = new JSONObject();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            if (key == null) {
                throw new NullPointerException("key == null");
            }
            nameValuePairs.put(key, entry.getValue());
        }
        // 将数组转化为 json 字符串
        String jsonStr = nameValuePairs.toString();

        // 对 jsonStr 进行 base64 编码
        String tmpData = Base64.getEncoder().encodeToString(jsonStr.getBytes(CHAR_CODE));
        System.out.println("markDataFlag and json to base64 is " + tmpData);

        // 将 tmpData 进行对称换位 , 得到结果就是 data 密文数据
        String finalData;
        if (tmpData.length() > 51) {
            char[] chars = changeArray(tmpData.toCharArray(), 1, 33, 10, 42, 18, 50, 19, 51);
            finalData = new String(chars);
        } else {
            finalData = tmpData;
        }
        return finalData;
    }

    /**
     * 换位
     */
    public static char[] changeArray(char[] chars, int... index) {
        for (int i = 1; i < index.length; i += 2) {
            final int l = index[i - 1];
            final int r = index[i];
            char c = chars[l];
            chars[l] = chars[r];
            chars[r] = c;
        }
        return chars;
    }

    public static void main(String[] args) throws Exception {
//        var Data = markData("key2", 2, "key1", 1, "key3", 3, "key4", 4);
//        System.out.println(Data);
//         解码自己的
//        Encode(Data);
//         解码官方给的文档
//        Encode("eyJrZXkyIjoyLCJrZXkxIjoxfQ==");
        char[] encodeContent = "eyJrZXkxIjoxLCJrZXkyIjoyLCJrZXkzIjozLCJrZXk0Ijo0fQ==".toCharArray();
        var changedResult = changeArray(encodeContent, 1, 33, 10, 42, 18, 50, 19, 51);
        System.out.println(changedResult); // ejJrZXkxIjkxLCJrZX==IjoyLCJrZXkzIyozLCJrZXo0Ijo0fQky
    }

    private static void Encode(String a) {
        if (a.length() < 51) {
            byte[] by = a.getBytes();
            var encoded = Base64.getDecoder().decode(by);
            System.out.println(new String(encoded, 0, 0, encoded.length));
        } else {
            System.out.println();
        }
    }
}
