package io.github.lvbo.lct.lang3;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author lvbo created on 2023-07-17 10:20
 */
public class StringTest {

    /**
     * 字符串判空
     */
    @Test
    public void testStringIsEmpty() {
        String str = "";
        // 原生写法
        if (str == null || str.length() == 0) {
            // Do something
        }
        // commons写法
        if (StringUtils.isEmpty(str)) {
            // Do something
        }

        /* StringUtils.isEmpty(null)      = true
         * StringUtils.isEmpty("")        = true
         * StringUtils.isEmpty(" ")       = false
         * StringUtils.isEmpty("bob")     = false
         * StringUtils.isEmpty("  bob  ") = false
        */
    }

    /**
     * 字符串去空格
     */
    @Test
    public void testStringTrim() {
        String str = "  trim  ";

        // 去除两端空格，不需要判断null
        String newStr = StringUtils.trim(str);

        /*
         * 去除两端空格，如果是null则转换为空字符串
         * StringUtils.trimToEmpty(null)          = ""
         * StringUtils.trimToEmpty("")            = ""
         * StringUtils.trimToEmpty("     ")       = ""
         * StringUtils.trimToEmpty("abc")         = "abc"
         * StringUtils.trimToEmpty("    abc    ") = "abc"
         */
        newStr = StringUtils.trimToEmpty(str);

        /*
         * 去除两端空格，如果结果是空串则转换为null
         * StringUtils.trimToNull(null)          = null
         * StringUtils.trimToNull("")            = null
         * StringUtils.trimToNull("     ")       = null
         * StringUtils.trimToNull("abc")         = "abc"
         * StringUtils.trimToNull("    abc    ") = "abc"
         */
        newStr = StringUtils.trimToNull(str);

        /*
         * 去两端 给定字符串中任意字符
         * StringUtils.strip(null, *)          = null
         * StringUtils.strip("", *)            = ""
         * StringUtils.strip("abc", null)      = "abc"
         * StringUtils.strip("  abc", null)    = "abc"
         * StringUtils.strip("abc  ", null)    = "abc"
         * StringUtils.strip(" abc ", null)    = "abc"
         * StringUtils.strip("  abcyx", "xyz") = "  abc"
         */
        newStr = StringUtils.strip(str, "stripChars");

        // 去左端 给定字符串中任意字符
        newStr = StringUtils.stripStart(str, "stripChars");
        // 去右端 给定字符串中任意字符
        newStr = StringUtils.stripEnd(str, "stripChars");
    }

    /**
     * 字符串分割
     */
    @Test
    public void testStringSplit() {
        String str = "a,b,c";

        // 原生写法
        String[] strArr1 = str.split(",");

        // commons写法
        String[] strArr2 = StringUtils.split(str, ",");
    }

    /**
     * 取子字符串
     */
    @Test
    public void testStringSubString() {
        // 获得"ab.cc.txt"中最后一个.之前的字符串
        StringUtils.substringBeforeLast("ab.cc.txt", "."); // ab.cc
        // 相似方法
        // 获得"ab.cc.txt"中最后一个.之后的字符串（常用于获取文件后缀名）
        StringUtils.substringAfterLast("ab.cc.txt", "."); // txt
        // 获得"ab.cc.txt"中第一个.之前的字符串
        StringUtils.substringBefore("ab.cc.txt", "."); // ab
        // 获得"ab.cc.txt"中第一个.之后的字符串
        StringUtils.substringAfter("ab.cc.txt", "."); // cc.txt
        // 获取"ab.cc.txt"中.之间的字符串
        StringUtils.substringBetween("ab.cc.txt", "."); // cc
        // 看名字和参数应该就知道干什么的了
        StringUtils.substringBetween("a(bb)c", "(", ")"); // bb
    }

    /**
     * 其他
     */
    @Test
    public void testOther() {
        // 首字母大写
        StringUtils.capitalize("test"); // Test
        // 字符串合并
        StringUtils.join(new int[]{1,2,3}, ","); // 1,2,3
        // 缩写
        StringUtils.abbreviate("abcdefg", 6); // "abc..."
        // 判断字符串是否是数字
        StringUtils.isNumeric("abc123");// false
        // 删除指定字符
        StringUtils.remove("abbc", "b"); // ac
    }

    /**
     * 随机字符串
     */
    @Test
    public void testRandomStr() {
        // 随机生成长度为5的字符串
        RandomStringUtils.random(5);
        // 随机生成长度为5的"只含大小写字母"字符串
        RandomStringUtils.randomAlphabetic(5);
        // 随机生成长度为5的"只含大小写字母和数字"字符串
        RandomStringUtils.randomAlphanumeric(5);
        // 随机生成长度为5的"只含数字"字符串
        RandomStringUtils.randomNumeric(5);
    }
}
