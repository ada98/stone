package stone.chap12;

import javassist.gluonj.util.Loader;
import stone.chap8.NativeEvaluator;

/**
 * @Author ada
 * @Date 2022/6/7 11:36 PM
 * @Version 1.0
 */
public class ObjOptRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ObjOptInterpreter.class, args, ObjOptimizer.class, NativeEvaluator.class);
    }
}
