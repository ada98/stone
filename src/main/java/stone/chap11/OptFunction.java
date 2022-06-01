package stone.chap11;

import stone.ast.BlockStmnt;
import stone.ast.ParameterList;
import stone.chap6.Environment;
import stone.chap7.Function;

/**
 * @Author ada
 * @Date 2022/5/31 10:39 PM
 * @Version 1.0
 */
public class OptFunction extends Function {
    protected int size;
    protected OptFunction(ParameterList parameterList, BlockStmnt body, Environment env,int memorySize) {
        super(parameterList, body, env);
        size = memorySize;
    }

    public  Environment makeEnv(){
        return new ArrayEnv(size,env);
    }



}
