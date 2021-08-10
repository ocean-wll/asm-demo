package pers.ocean;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

/**
 * @Description 此类主要功能就是读取字节码文件，然后把读取的数据通知ClassVisitor，字节码文件可以多种方式传入：
 * <p>
 * public ClassReader(final InputStream inputStream)：字节流的方式；
 * public ClassReader(final String className)：文件全路径；
 * public ClassReader(final byte[] classFile)：二进制文件；
 * @Author ocean_wll
 * @Date 2021/8/10 3:00 下午
 */
public class ClassReaderDemo {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("pers/ocean/OceanInterface");
        ClassWriter classVisitor = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        /*
        ClassReader的accept方法处理接收一个访问者，还包括另外一个parsingOptions参数，选项包括：
            SKIP_CODE：跳过已编译代码的访问（如果您只需要类结构，这可能很有用）；
            SKIP_DEBUG：不访问调试信息，也不为其创建人工标签；
            SKIP_FRAMES：跳过堆栈映射帧；
            EXPAND_FRAMES：解压缩这些帧；
         */
        classReader.accept(classVisitor, 0);
    }
}
