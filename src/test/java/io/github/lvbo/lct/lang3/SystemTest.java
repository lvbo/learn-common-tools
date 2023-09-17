package io.github.lvbo.lct.lang3;

import java.io.File;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-09 10:19
 */
public class SystemTest {

    /**
     * 获取操作系统和JVM一些信息
     */
    @Test
    public void testGetSystemInfo() {
        // 判断操作系统类型
        boolean isWin = SystemUtils.IS_OS_WINDOWS;
        boolean isWin10 = SystemUtils.IS_OS_WINDOWS_10;
        boolean isWin2012 = SystemUtils.IS_OS_WINDOWS_2012;
        boolean isMac = SystemUtils.IS_OS_MAC;
        boolean isLinux = SystemUtils.IS_OS_LINUX;
        boolean isUnix = SystemUtils.IS_OS_UNIX;
        boolean isSolaris = SystemUtils.IS_OS_SOLARIS;
        System.out.println("isWin:" + isWin);
        System.out.println("isMac:" + isMac);

        // 判断java版本
        boolean isJava6 = SystemUtils.IS_JAVA_1_6;
        boolean isJava8 = SystemUtils.IS_JAVA_1_8;
        boolean isJava11 = SystemUtils.IS_JAVA_11;
        boolean isJava14 = SystemUtils.IS_JAVA_14;
        System.out.println("isJava6:" + isJava6);
        System.out.println("isJava14:" + isJava14);

        // 获取java相关目录
        File javaHome = SystemUtils.getJavaHome();
        File userHome = SystemUtils.getUserHome(); // 操作系统用户目录
        File userDir = SystemUtils.getUserDir(); // 项目所在路径
        File tmpDir = SystemUtils.getJavaIoTmpDir();

        System.out.println("javaHome:" + javaHome);
        System.out.println("userHome:" + userHome);
        System.out.println("userDir:" + userDir);
        System.out.println("tmpDir:" + tmpDir);
    }
}
