package io.github.lvbo.lct.commonscollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.SetUniqueList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.map.AbstractReferenceMap;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.collections4.map.ReferenceMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.junit.Test;

public class CollectionExpandTest {

    /**
     * FixedSizeList 用于装饰另一个 List 以阻止修改其大小。不支持添加、删除、清除等操作
     */
    @Test
    public void fixedSizeListTest() {
        List<String> sourceList = new ArrayList<>();
        sourceList.add("1");
        // 装饰一下原list
        List<String> list = FixedSizeList.fixedSizeList(sourceList);
        list.set(0, "11");
        System.out.println(list);
        // 以下改变容器size的操作会抛出异常
        list.add("4"); // UnsupportedOperationException("List is fixed size")
        list.remove("5"); // UnsupportedOperationException("List is fixed size")
        list.clear(); // UnsupportedOperationException("List is fixed size")
    }

    /**
     * SetUniqueList 用来装饰另一个 List 以确保不存在重复元素，内部使用了 Set 来判断重复问题
     */
    @Test
    public void setUniqueListTest() {
        List<String> sourceList = new ArrayList<>();
        sourceList.add("1");
        sourceList.add("2");
        // 元素不重复的list
        SetUniqueList<String> list = SetUniqueList.setUniqueList(sourceList);
        // 存在则不处理，不会影响原来顺序
        list.add("2");
        System.out.println(list); // [1,2]
    }

    /**
     * TransformedList 装饰另一个 List 以转换添加的对象。add 和 set 方法受此类影响。
     */
    @Test
    public void transformedListTest() {
        List<String> sourceList = new ArrayList<>();
        sourceList.add("1");
        sourceList.add("2");
        // 转换list,在添加元素的时候会通过第二个参数Transformer转换一下
        // （Transformer接口只有一个抽象方法可以使用lambda表达式）
        // transformingList不会对原list的已有元素做转换
        TransformedList<String> list = TransformedList.transformingList(sourceList, e -> e.concat("_"));
        list.add("a");
        System.out.println("list:" + list);
        System.out.println("sourceList:" + sourceList);

        // transformedList会对原list的已有元素做转换
        list = TransformedList.transformedList(sourceList, e -> e.concat("_"));
        list.add("a");
        System.out.println(list);
    }

    /**
     * PredicatedList 装饰另一个 List ，装饰后的 List 在添加元素的时候会调用 Predicate 接口来判断元素，匹配通过才会被添加到集合中。
     */
    @Test
    public void predicatedListTest() {
        List<String> sourceList = new ArrayList<>();
        // 在添加元素的时候会通过第二个参数Predicate判断一下是否符合要求，符合要求才添加进来
        PredicatedList<String> list = PredicatedList.predicatedList(sourceList, e -> e.startsWith("_"));
        list.add("_4");
        System.out.println("list:" + list);
        System.out.println("sourceList:" + sourceList);

        // 以下会抛异常：java.lang.IllegalArgumentException: Cannot add Object '4'
        list.add("4");
    }

    /**
     * ListOrderedSet 有序的Set，顺序按照元素添加顺序排列，类似 List
     */
    @Test
    public void listOrderedSetTest() {
        // 有序的set，按照插入顺序排序
        Set<String> set = new ListOrderedSet<>();
        set.add("aa");
        set.add("11");
        set.add("哈哈");
        System.out.println(set);
    }

    /**
     * Bag 接口是带计数功能的集合扩展，它继承了 Collection 接口，可以当做集合来使用
     */
    @Test
    public void bagTest() {
        // bag 带计数功能的集合
        Bag<String> bag = new HashBag<>();
        bag.add("a");
        bag.add("b");
        bag.add("a");
        System.out.println(bag.size());
        System.out.println(bag.getCount("a"));
    }

    /**
     * MultiValuedMap 和正常的 Map 有点区别，同一个 key 允许存放多个 value，这些 value 会放到一个 List 中。
     * 这个功能如果用 Java 的 Map 我们需要构造一个 Map<String, List> 加个各种操作来实现
     */
    @Test
    public void multiValuedMap() {
        // list实现，允许value重复
        ListValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("user", "张三");
        map.put("user", "李四");
        map.put("user", "张三");
        map.put("age", "12");
        // 注意：value的泛型是String, 但是get方法返回的是List<String>
        List<String> users2 = map.get("user"); // [张三,李四,张三]
        System.out.println(users2);

        // multiMap的其他方法
        map.containsKey("user"); // true
        map.containsValue("张三"); // true
        map.containsMapping("user", "张三"); // true

        int size = map.size(); // 4

        Collection<String> ss = map.values();// [张三,李四,张三,12]
        map.remove("user"); // 清空user的所有value
        // 转换为原生map
        Map<String, Collection<String>> jMap = map.asMap();
        System.out.println(jMap);
    }

    /**
     * key大小写不敏感的Map
     */
    @Test
    public void caseInsensitiveMap() {
        // key大小写不敏感
        Map<String, Integer> map = new CaseInsensitiveMap<>();
        map.put("one", 1);
        map.put("two", 2);
        Integer o = map.get("ONE");
        System.out.println(o);
    }

    /**
     * 有序的Map
     */
    @Test
    public void orderedMapTest() {
        // key有序：按照插入顺序
        OrderedMap<String, String> map = new ListOrderedMap<>();
        map.put("哈哈", "1");
        map.put("此处", "2");
        map.put("cc", "3");
        map.put("dd", "4");
        // 得到的keySet有序
        Set<String> set = map.keySet(); // 哈哈,此处,cc,dd
        String nk = map.nextKey("此处"); // cc
        System.out.println(nk);
        String pk = map.previousKey("此处"); // 哈哈
        System.out.println(pk);
    }

    /**
     * LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。
     */
    @Test
    public void lRUMapTest() {
        LRUMap<String, String> map = new LRUMap<>(2);
        map.put("aa", "1");
        map.put("bb", "2");
        map.put("cc", "3");
        // 最早没有被使用的aa将被移出
        System.out.println(map);
        // 访问一次bb，此时在put的话将会移出最早没有被访问的cc
        map.get("bb");
        map.put("dd", "4");
        System.out.println(map);
    }

    /**
     * 装饰一个 Map 以在达到过期时间时删除过期条目。
     * 当在 Map 中放置键值对时，此装饰器使用 ExpirationPolicy 来确定条目应保持多长时间，由到期时间值定义。
     * 当对 Map 做操作的时候才会检查元素是否过期并触发删除操作。
     */
    @Test
    public void passiveExpiringMapTest() throws InterruptedException {
        // 存活一秒钟
        int ttlMillis = 1000;
        PassiveExpiringMap.ExpirationPolicy<String, String> ep = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(ttlMillis);
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(ep);
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        // 等待一秒后在获取
        Thread.sleep(1000);
        String vc = map.get("c");
        System.out.println(vc);
    }

    /**
     * ReferenceMap 允许垃圾收集器删除映射。可以指定使用什么类型的引用来存储映射的键和值。
     * 如果使用的不是强引用，则垃圾收集器可以在键或值变得不可访问，或者 JVM 内存不足时删除映射。
     * 用它做一个简易的缓存不会导致存放内容过多导致内存溢出。
     */
    @Test
    public void referenceMapTest() {
        // key value全部使用软引用，再JVM内存不足的情况下GC会将软引用的键值对回收
        ReferenceMap<String, String> map = new ReferenceMap<>(AbstractReferenceMap.ReferenceStrength.SOFT,
                AbstractReferenceMap.ReferenceStrength.SOFT);
    }

    /**
     * BidiMap 允许在 key 和 value 之间进行双向查找。
     * 其中一个键可以查找一个值，一个值可以同样轻松地查找一个键。这个接口扩展了 Map，value 不允许重复，如果重复将同时覆盖旧的键值对。
     */
    @Test
    public void bidiMapTest() {
        BidiMap<String, String> map = new DualHashBidiMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        // 根据key获取value
        String v = map.get("a");
        System.out.println(v);
        // 根据value获取key
        String k = map.getKey("2");
        System.out.println(k);
        // 根据value移除
        map.removeValue("3");
        System.out.println(map);
    }
}
