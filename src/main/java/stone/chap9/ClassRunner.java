package stone.chap9;

import javassist.gluonj.util.Loader;
import stone.chap7.ClosureEvaluator;
import stone.chap8.NativeEvaluator;

/**
 * @Author ada
 * @Date 2022/4/9 11:37 PM
 * @Version 1.0
 */
public class ClassRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ClassInterpreter.class, args, ClassEvaluator.class, NativeEvaluator.class, ClosureEvaluator.class);
    }
}
