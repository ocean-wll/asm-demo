package pers.ocean;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Description 适配器
 * ClassReader classReader = new ClassReader("pers/ocean/OceanInterface");
 * ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
 * //处理
 * ClassVisitor classVisitor = new AddFieldAdapter(classWriter...);
 * classReader.accept(classVisitor, 0);
 * <p>
 * 根据ClassVisitor的每个方法被调用的顺序，如果类中有多个属性，那么visitField会被调用多次，每次都会检查要添加的字段是否已经有了，然后保存在isFieldPresent标识中，这样在访问最后的visitEnd中判断是否需要添加新属性；
 * @Author ocean_wll
 * @Date 2021/8/10 4:01 下午
 */
public class AddFieldAdapter extends ClassVisitor implements Opcodes {

    private int fAcc;
    private String fName;
    private String fDesc;
    //是否已经有相同名称的属性
    private boolean isFieldPresent;

    public AddFieldAdapter(ClassVisitor cv, int fAcc, String fName, String fDesc) {
        super(ASM4, cv);
        this.fAcc = fAcc;
        this.fName = fName;
        this.fDesc = fDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        //判断是否有相同名称的字段，不存在才会在visitEnd中添加
        if (name.equals(fName)) {
            isFieldPresent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
