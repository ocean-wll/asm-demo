package pers.ocean;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2021/8/10 5:08 下午
 */
public class MethodVisitorDemo {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("pers/ocean/TestService");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, 0);

        byte[] b = classWriter.toByteArray();

        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("target/classes/pers/ocean/TestService.class");
        fileOutputStream.write(b);
        fileOutputStream.close();
    }
}
