package stone.chap7;

import javassist.gluonj.util.Loader;

/**
 * @Author ada
 * @Date 2022/4/8 12:03 AM
 * @Version 1.0
 */
public class ClosureRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ClosureInterpreter.class, args, ClosureEvaluator.class);
    }
}
