package stone.chap6;


import javassist.gluonj.util.Loader;

/**
 * 解释器启动程序.
 * @Author ada
 * @Date 2022/4/6 11:50 PM
 * @Version 1.0
 */
public class Runner {
    public static void main(String[] args) throws Throwable {
        Loader.run(BasicInterpreter.class,args,BasicEvaluator.class);
    }
}
