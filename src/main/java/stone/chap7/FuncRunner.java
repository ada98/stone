package stone.chap7;


import javassist.gluonj.util.Loader;

/**
 * @Author ada
 * @Date 2022/4/7 11:57 PM
 * @Version 1.0
 */
public class FuncRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(FuncInterpreter.class, args, FuncEvaluator.class);
    }
}
