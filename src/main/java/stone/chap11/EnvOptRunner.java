package stone.chap11;

import javassist.gluonj.util.Loader;
import stone.chap8.NativeEvaluator;

/**
 * @Author ada
 * @Date 2022/5/31 10:47 PM
 * @Version 1.0
 */
public class EnvOptRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(EnvOptInterpreter.class, args, EnvOptimizer.class, NativeEvaluator.class);
    }
}
