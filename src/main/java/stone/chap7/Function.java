package stone.chap7;

import stone.ast.BlockStmnt;
import stone.ast.ParameterList;
import stone.chap6.Environment;

/**
 * @Author ada
 * @Date 2022/4/7 11:55 PM
 * @Version 1.0
 */
public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;

    protected Function(ParameterList parameterList, BlockStmnt body, Environment env) {
        this.parameters = parameterList;
        this.body = body;
        this.env = env;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmnt body() {
        return body;
    }

    public Environment makeEnv() {
        return new NestedEnv(env);
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
