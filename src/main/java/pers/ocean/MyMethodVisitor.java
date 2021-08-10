package pers.ocean;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Description MethodVIsitor一般通过实现visitCode visitInsan visitMaxs方法来实现类的修改。
 * visitCode是方法的访问开始；
 * visitInsn可以访问方法的操作指令，一般应用于在return指令之前插入代码；
 * visitMax则用于复写操作数栈和局部变量表的大小，因为类被修改，所以所需的栈和变量表大小可能会增加。
 * @Author ocean_wll
 * @Date 2021/8/10 4:55 下午
 */
public class MyMethodVisitor extends MethodVisitor implements Opcodes {

    public MyMethodVisitor(MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("start");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                || opcode == Opcodes.ATHROW) {
            //方法在返回之前打印"end"
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("end");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }
}
