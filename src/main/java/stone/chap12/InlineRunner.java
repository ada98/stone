package stone.chap12;

import javassist.gluonj.util.Loader;

import java.lang.annotation.Native;

/**
 * @Author ada
 * @Date 2022/6/7 11:37 PM
 * @Version 1.0
 */
public class InlineRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ObjOptInterpreter.class, args, InlineCache.class, Native.class);
    }
}
