package com.mine.practice;

import com.mine.util.SignUtil;

import java.util.*;

public class MarkSign {
    private static final String K_SIGN = "sign";

    /**
     * 计算sign
     */
    public static String markSign(String appKey, String... key_val) {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 1; i < key_val.length; i += 2)
            if (key_val[i] != null && key_val[i - 1] != null)
                map.put(key_val[i - 1], key_val[i]);
        return markSignFlag(appKey, map);
    }

    /**
     * 具体执行计算sign
     */
    private static String markSignFlag(String appKey, Map<String, String> params) {
        ArrayList<String> keys = new ArrayList<String>(params.keySet());
        keys.remove(K_SIGN);
        Collections.sort(keys);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            final String key = keys.get(i);
            final String value = params.get(key);
            if (key != null && key.length() > 0) {
                builder.append(key).append("=").append(value).append('&');
            }
        }
        builder.append(appKey);
        String srcData = builder.toString();
        return SignUtil.encrypt(SignUtil.MD5, srcData);
    }

    public static void main(String[] args) throws Exception {
        String timestamp = "1502186851";//String.valueOf(System.currentTimeMillis() / 1000);
        String data = "eyJrZXkyIjoyLCJrZXkxIjoxfQ=="; //markData.markData("key1", 1, "key2", 2);
        String productKey = "12345678";
        String productCode = "12345678";
        String sign = markSign(productKey, "pcode", productCode, "data", data, "timestamp", timestamp);
        System.out.println(sign); // 1f03d3a85ad79e364f81b855e91f69bd
    }
}
