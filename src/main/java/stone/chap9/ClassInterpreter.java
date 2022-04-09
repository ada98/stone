package stone.chap9;

import stone.ClassParser;
import stone.ParseException;
import stone.chap6.BasicInterpreter;
import stone.chap7.NestedEnv;
import stone.chap8.Natives;

/**
 * @Author ada
 * @Date 2022/4/9 11:36 PM
 * @Version 1.0
 */
public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new NestedEnv()));
    }
}
