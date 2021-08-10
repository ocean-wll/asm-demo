package pers.ocean;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description ClassWriter，其继承于ClassVisitor，主要用来生成类，可以单独使用
 * @Author ocean_wll
 * @Date 2021/8/10 3:04 下午
 */
public class ClassWriterDemo implements Opcodes {

    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pers/ocean/OceanTestDemo", null, "java/lang/Object", new String[]{"pers/ocean/OceanInterface"});
        // desc 表示的是类型，signature表示的是泛型
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "pers/ocean/OceanInterface", null, null).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", "这是泛型", null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("OceanTestDemo.class");
        fileOutputStream.write(b);
        fileOutputStream.close();
    }
}
