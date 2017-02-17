package com.help.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by houlongbin on 2017/2/14.
 */
public class MatchUtil {
    public static void main(String[] args) {
//        List<Integer> list1=new LinkedList<Integer>(Arrays.asList(800,1500,1600,200,200,600,800,1000,1000));
//        List<Integer> list2=new LinkedList<Integer>(Arrays.asList(10,10,10,30,40));
//        List<Integer> list3=match(list2,list1);
//        for(Integer in1:list3){
//            System.out.println("in1 = [" + in1 + "]");
//        }
        //list1.add(800);list1.add(1600);list1.add(1600);
        List<Integer> list1 = new LinkedList<Integer>(Arrays.asList(800, 200, 600, 600, 800, 1000, 300, 900, 900, 500, 100, 200, 300, 500, 1400, 1600, 1000, 600));
        int sum = 0;
        for (int m : list1) {
            sum += m;
        }
        System.out.println("in1 = [" + sum + "]");
        list1 = new LinkedList<Integer>(Arrays.asList(500, 500, 100, 500, 1000, 300, 800, 900, 900, 200, 500, 600, 600, 800));
        sum = 0;
        for (int m : list1) {
            sum += m;
        }
        System.out.println("in1 = [" + sum + "]");
        list1 = new LinkedList<Integer>(Arrays.asList(800, 1500, 1600, 200, 200, 600, 600, 800, 1000, 1000, 300, 900, 900, 500, 600, 800));
        sum = 0;
        for (int m : list1) {
            sum += m;
        }
        System.out.println("in1 = [" + sum + "]");
    }

    public static List<Integer> match(List<Integer> a, List<Integer> b) {
        List<List<Integer>> min = new ArrayList<List<Integer>>();
        match(a, b, new ArrayList<Integer>(), min);
        return min.get(0);
    }

    /**
     * @param a   未匹配部分。
     * @param b   未匹配部分。
     * @param c   已匹配部分。
     * @param min 碎片最小的匹配结果。
     */
    private static void match(List<Integer> a, List<Integer> b, List<Integer> c,
                              List<List<Integer>> min) {
        if (a.isEmpty() || b.isEmpty()) {
            min(c, min);
            return;
        }

        if (a.size() == 1) {
            c.addAll(b);
            min(c, min);
        } else if (b.size() == 1) {
            c.addAll(a);
            min(c, min);
        } else {
            // a 和 b 中相等的元素先进行匹配。
            matchEqual(a, b, c);

            if (a.isEmpty() || b.isEmpty()) {
                min(c, min);
            } else if (a.size() == 1 || b.size() == 1) {
                match(a, b, c, min);
            } else {
                // 如果当前匹配的结果碎片化已经大于等于最小碎片化，则不再进行后面的匹配。
                if (!min.isEmpty() && c.size() >= min.get(0).size()) {
                    return;
                }

                // a 和 b 的最小的元素肯定不能拆分，必定包含在最终的匹配结果中。
                Integer aMin = Collections.min(a);
                Integer bMin = Collections.min(b);
                if (aMin < bMin) {
                    c.add(aMin);
                    removeFirst(a, aMin);
                    // b 中的每个元素减掉最小元素，递归进行剩余部分匹配。
                    for (int i = 0; i < b.size(); i++) {
                        List<Integer> bCopy = new ArrayList<Integer>(b);
                        bCopy.set(i, b.get(i) - aMin);
                        match(new ArrayList<Integer>(a), bCopy, new ArrayList<Integer>(c),
                                min);
                    }
                } else {
                    c.add(bMin);
                    removeFirst(b, bMin);
                    // a 中的每个元素减掉最小元素，递归进行剩余部分匹配。
                    for (int i = 0; i < a.size(); i++) {
                        List<Integer> aCopy = new ArrayList<Integer>(a);
                        aCopy.set(i, a.get(i) - bMin);
                        match(aCopy, new ArrayList<Integer>(b), new ArrayList<Integer>(c),
                                min);
                    }
                }
            }
        }
    }

    private static void removeFirst(List<Integer> list, Integer e) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer v = iterator.next();
            if (v.equals(e)) {
                iterator.remove();
                break;
            }
        }
    }

    private static void min(List<Integer> c, List<List<Integer>> min) {
        if (min.isEmpty()) {
            min.add(c);
        } else if (c.size() < min.get(0).size()) {
            min.set(0, c);
        }
    }

    /**
     * 匹配列表 a 和 b 中相同的元素，添加到列表 c 中，并从列表 a 和 b 中删除。
     *
     * @param a
     * @param b
     * @param c
     */
    private static void matchEqual(List<Integer> a, List<Integer> b,
                                   List<Integer> c) {
        Iterator<Integer> aIterator = a.iterator();
        while (aIterator.hasNext()) {
            Integer aElement = aIterator.next();
            Iterator<Integer> bIterator = b.iterator();
            boolean hasEqual = false;
            while (bIterator.hasNext()) {
                Integer bElement = bIterator.next();
                if (bElement.equals(aElement)) {
                    hasEqual = true;
                    c.add(bElement);
                    bIterator.remove();
                    break;
                }
            }
            if (hasEqual) {
                aIterator.remove();
            }
        }
    }

    /**
     * 匹配列表 a 和 b 中相同的元素，添加到列表 c 中，并从列表 a 和 b 中删除。
     */
    public static JSONArray matchEqualOffer(Map<String, Integer> mapb, Map<String, Integer> mapc) {
        Iterator<Map.Entry<String, Integer>> iterb = mapb.entrySet().iterator();
        JSONArray jsonArray = new JSONArray();
        while (iterb.hasNext()) {
            boolean hasEqual = false;
            Map.Entry<String, Integer> meb = iterb.next();
            Iterator<Map.Entry<String, Integer>> iterc =  mapc.entrySet().iterator();
            while (iterc.hasNext()) {
                Map.Entry<String, Integer> mec = iterc.next();
                if (meb.getValue().equals(mec.getValue())) {
                    hasEqual = true;
                    JSONObject json = new JSONObject();
                    json.put("forder", meb.getKey());
                    json.put("torder", mec.getKey());
                    json.put("num", mec.getValue());
                    jsonArray.add(json);
                    iterc.remove();
                    break;
                }
            }
            if (hasEqual) {
                iterb.remove();
            }

        }

        System.out.println("matchJson="+jsonArray.toJSONString());
        return jsonArray;
    }


}
