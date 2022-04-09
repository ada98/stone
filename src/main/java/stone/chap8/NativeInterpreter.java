package stone.chap8;

import stone.ClosureParser;
import stone.ParseException;
import stone.chap7.NestedEnv;

import static stone.chap6.BasicInterpreter.run;

/**
 * @Author ada
 * @Date 2022/4/9 1:24 AM
 * @Version 1.0
 */
public class NativeInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new Natives().environment(new NestedEnv()));
    }
}
