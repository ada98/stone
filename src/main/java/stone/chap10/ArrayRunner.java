package stone.chap10;

import javassist.gluonj.util.Loader;
import stone.chap7.ClosureEvaluator;
import stone.chap8.NativeEvaluator;
import stone.chap9.ClassEvaluator;
import stone.chap9.ClassInterpreter;

/**
 * @Author ada
 * @Date 2022/4/10 12:53 AM
 * @Version 1.0
 */
public class ArrayRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ClassInterpreter.class, args, ClassEvaluator.class, ArrayEvaluator.class, NativeEvaluator.class, ClosureEvaluator.class);
    }
}
