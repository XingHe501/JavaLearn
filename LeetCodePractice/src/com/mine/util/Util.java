package com.mine.util;

import java.nio.ByteBuffer;

/**
 * Created: 2021/05/31 18:05
 * <p>
 * FileName: Util
 * <p>
 * What's the daily question: 2021/05/31
 *
 * @author Admin
 * @version jdk
 */
public class Util {

    /**
     * lowbit 例如,Lowbit(34)的返回值将是2；而Lowbit(12)返回4；Lowbit(8)返回8。 将34转为二进制,为0010
     * 0010,这里的"最后一个1"指的是从2^{0}位往前数,见到的第一个1,也就是2^{1}位上的1.
     *
     * @param n
     * @return 返回参数转为二进制后,最后一个1的位置所代表的数值.
     */
    public int lowbit(int n) {
        return n & -n;
    }

    public String floatToBinary(float f) {
        StringBuilder sb = new StringBuilder();
        ByteBuffer bbf = ByteBuffer.allocate(4);
        bbf.putFloat(f);
        byte[] bytes = bbf.array();
        for (var b : bytes) {
            for (int i = 0; i < 8; i++) {
                sb.insert(0, ((b >> i) & 1) == 1 ? "1" : "0");
            }
        }
        String s = sb.toString();
        return s.charAt(0) + " " + s.substring(1, 8) + " " + s.substring(9);

    }

    public void EquipResult(Object a, Object b) throws Exception {
        if (a == b)
            System.out.println("Right");
        else
            throw new Exception("不一致");
    }
}