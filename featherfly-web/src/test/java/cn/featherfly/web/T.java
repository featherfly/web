package cn.featherfly.web;

import java.util.List;

/**
 * The type T.
 *
 * @author zhongj
 */
public class T {
    public static void main(String[] args) {
//        System.out.println(SystemPropertyUtils.getJavaVmName());
        System.out.println(System.getProperty("java.vm.version"));
        #if JAVA_8
        System.out.println("JAVA_8");
        #elif JAVA_11_OR_LATER
        System.out.println("JAVA_11_OR_LATER");
        #else
        #error "不支持的jvm"
        #endif

        System.out.println();
        System.out.println();

        #if JAVA_11_OR_LATER
        System.out.println(jakarta.servlet.ServletRequest.class);
        #else
        System.out.println(javax.servlet.ServletRequest.class);
        #endif

        // jdk9
        System.out.println(List.of("jdk9", "api", "List.of()"));
    }
}
