package stone.chap12;

import stone.ast.BlockStmnt;
import stone.ast.ParameterList;
import stone.chap11.ArrayEnv;
import stone.chap11.OptFunction;
import stone.chap6.Environment;

/**
 * @Author ada
 * @Date 2022/6/7 11:34 PM
 * @Version 1.0
 */
public class OptMethod extends OptFunction {
    OptStoneObject self;

    public OptMethod(ParameterList parameterList, BlockStmnt body, Environment env, int memorySize, OptStoneObject self) {
        super(parameterList, body, env, memorySize);
        this.self = self;
    }

    @Override
    public Environment makeEnv() {
        ArrayEnv e = new ArrayEnv(size, env);
        e.put(0, 0, self);
        return e;
    }
}
