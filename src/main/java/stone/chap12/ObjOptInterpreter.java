package stone.chap12;

import stone.ClassParser;
import stone.ParseException;
import stone.chap11.EnvOptInterpreter;
import stone.chap11.ResizableArrayEnv;
import stone.chap8.Natives;

/**
 * @Author ada
 * @Date 2022/6/7 11:36 PM
 * @Version 1.0
 */
public class ObjOptInterpreter extends EnvOptInterpreter {

    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new ResizableArrayEnv()));
    }
}
