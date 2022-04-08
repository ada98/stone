package stone.chap7;

import stone.FuncParser;
import stone.ParseException;
import stone.chap6.BasicInterpreter;

/**
 * @Author ada
 * @Date 2022/4/7 11:57 PM
 * @Version 1.0
 */
public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new FuncParser(), new NestedEnv());
    }
}
