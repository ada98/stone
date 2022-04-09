package stone.chap8;

import javassist.gluonj.util.Loader;
import stone.chap7.ClosureEvaluator;

/**
 * @Author ada
 * @Date 2022/4/9 1:24 AM
 * @Version 1.0
 */
public class NativeRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(NativeInterpreter.class, args, NativeEvaluator.class, ClosureEvaluator.class);
    }
}
