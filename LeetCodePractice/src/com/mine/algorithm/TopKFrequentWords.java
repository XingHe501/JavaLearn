package com.mine.algorithm;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created: 2021/05/20 15:45
 *
 * https://leetcode-cn.com/problems/top-k-frequent-words
 */
public class TopKFrequentWords {
    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了 15.55% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了 49.01% 的用户
     *
     * @param words String[]
     * @param k int
     * @return ans List<String>
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> times =new HashMap<>();
        for (String val : words) times.put(val, times.getOrDefault(val, 0) + 1);

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>(times.entrySet());
        Collections.sort(sortList, (o1, o2) -> {
            int re = o2.getValue().compareTo(o1.getValue());
            return re == 0 ? o1.getKey().compareTo(o2.getKey()) : re; // CompareTo  > => 1  = => 0 < => -1
        });

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) ans.add(sortList.get(i).getKey());
        return ans;
    }

    /**
     * 一句话
     * 执行用时:14 ms ,在所有Java提交中击败了5.35%的用户
     * 内存消耗:38.7 MB ，在所有Java提交中击败了43.91%的用户
     *
     * @param words String []
     * @param k int
     * @return ans List<String>
     */
    public List<String> topKFrequentAnswerTwo(String[] words, int k){
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().sorted(((o1, o2) -> {
                    int re = o2.getValue().compareTo(o1.getValue());
                    return re == 0 ? o1.getKey().compareTo(o2.getKey()) : re;
                }))
                .map(Map.Entry::getKey)
                .limit(k).collect(Collectors.toList());
    }

    /**
     * 哈希表 & 优先队列（堆） by 宫水三叶
     *
     * 时间复杂度:使用哈希表统计词频，复杂度为О(n);使用最多n个元素维护一个大小为k的堆，复杂度为O(n log k);输出答案复杂度为O(k)(同时k≤n)。整体复杂度为O(n log k)
     * 空间复杂度:O(n)
     *
     * @param words String[]
     * @param k int
     * @return ans List<String>
     */
    public List<String> topKFrequentAnswerThree(String[] words, int k){
        Map<String, Integer> map = new HashMap<>();
        for (String val : words) map.put(val, map.getOrDefault(val, 0) + 1);
        //  优先级队列
        PriorityQueue<Object[]> q = new PriorityQueue<>(k, (a, b) -> {// 如果词频不同，根据词频升序
            int c1 = (Integer) a[0], c2 = (Integer) b[0];
            if (c1 != c2) return c1 - c2;
            // 如果词频相同，根据字典序倒序
            String s1 = (String) a[1], s2 = (String) b[1];
            return s2.compareTo(s1);
        });
        for (String s : map.keySet()){
            int cnt = map.get(s);
            // 不足 k 个，直接入堆
            if (q.size() < k) {
                q.add(new Object[]{cnt, s});
            } else {
                Object[] peek = q.peek(); // peek 返回堆第一个元素，不删除该元素
                if (cnt > (Integer)peek[0]) { // 词频比堆顶元素大，弹出堆顶元素，入堆
                    q.poll();
                    q.add(new Object[]{cnt, s});
                } else if (cnt == (Integer)peek[0]) { // 词频与堆顶元素相同
                    String top = (String)peek[1];
                    if (s.compareTo(top) < 0) { // 且字典序大小比堆顶元素小，弹出堆顶元素，入堆
                        q.poll();
                        q.add(new Object[]{cnt, s});
                    }
                }
            }
        }
        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) ans.add((String) q.poll()[1]); // poll 返回第一个元素并删除该元素
        Collections.reverse(ans);
        return ans;
    }
}

class TopKFrequentWordsRun {
    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        System.out.println(topKFrequentWords.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 2));
        System.out.println(topKFrequentWords.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequentWords.topKFrequentAnswerThree(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 20));
    }
}