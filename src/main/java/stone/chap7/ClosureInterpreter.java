package stone.chap7;

import stone.ClosureParser;
import stone.ParseException;
import stone.chap6.BasicInterpreter;

/**
 * @Author ada
 * @Date 2022/4/8 12:02 AM
 * @Version 1.0
 */
public class ClosureInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new NestedEnv());
    }
}
