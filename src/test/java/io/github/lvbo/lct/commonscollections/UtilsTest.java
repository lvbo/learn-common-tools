package io.github.lvbo.lct.commonscollections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void testCollectionUtils() {
        String str = null;
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("1", "2", "4");
        // 判断是否为空（null或空list都为true）
        Assert.assertFalse(CollectionUtils.isEmpty(list1));
        // 添加元素（忽略null元素）
        CollectionUtils.addIgnoreNull(list1, str);
        System.out.println("ignoreNull: " + list1);
        // list是否包含subList中的所有元素
        Assert.assertFalse(CollectionUtils.containsAll(list1, list2));
        // list是否包含subList中的任意一个元素
        Assert.assertTrue(CollectionUtils.containsAny(list1, list2));
        // list1 减去 list2
        Collection<String> subtract = CollectionUtils.subtract(list1, list2);
        System.out.println("subtract: " + subtract);
        // 合并两个list并去重
        Collection<String> union = CollectionUtils.union(list1, list2);
        System.out.println("union: " + union);
        // 取两个list同时存在的元素
        Collection<String> intersection = CollectionUtils.intersection(list1, list2);
        System.out.println("intersection: " + intersection);
    }

    @Test
    public void testListUtils() {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("1", "2", "4");
        // 同CollectionUtils, 返回结果为List
        List<String> subtract = ListUtils.subtract(list1, list2);
        System.out.println("subtract: " + subtract);
        List<String> union = ListUtils.union(list1, list2);
        System.out.println("union: " + union);
        List<String> intersection = ListUtils.intersection(list1, list2);
        System.out.println("intersection: " + intersection);
        // 判断两个集合中的内容是否完全相同（顺序也一致）
        Assert.assertFalse(ListUtils.isEqualList(list1, list2));
        // list1如果为null则转换为空List
        ListUtils.emptyIfNull(list1);
        // list1中所有元素做Hash
        int i = ListUtils.hashCodeForList(list1);
        System.out.println("hashCodeForList: " + i);
    }

    @Test
    public void testMapUtils() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("sex", true);
        map.put("age", 34);
        map.put("money", null);
        // 获取Map中指定key的value
        // 使用getString(final Map map, final Object key)方法
        // 当然，也可使用getString( Map map, Object key, String defaultValue)方法
        String name = MapUtils.getString(map, "name");
        System.out.println("name: " + name);
        String address = MapUtils.getString(map, "address");
        System.out.println("address: " + address);
        name = MapUtils.getString(map, "name1", "lisi");
        System.out.println(name);
        Integer money = MapUtils.getInteger(map, "money");
        System.out.println(money);
        money = MapUtils.getInteger(map,"money",23);
        System.out.println(money);
        // Map判空
        Assert.assertFalse(MapUtils.isEmpty(map));
        Assert.assertTrue(MapUtils.isNotEmpty(map));
        // 将二维数组放入Map中
        String[][] user = {{"names","zhangfsan"},{"sexs","1f"}};
        Map<String, Object> map1 = MapUtils.putAll(map, user);
        System.out.println(map1);
    }

}
