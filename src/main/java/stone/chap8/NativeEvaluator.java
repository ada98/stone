package stone.chap8;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.StoneException;
import stone.ast.ASTree;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;
import stone.chap7.FuncEvaluator;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/9 1:20 AM
 * @Version 1.0
 */
@Require(FuncEvaluator.class)
@Reviser
public class NativeEvaluator {
    @Reviser
    public static class NativeArgEx extends FuncEvaluator.ArgumentsEx {

        public NativeArgEx(List<ASTree> c) {
            super(c);
        }

        @Override
        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof NativeFunction)) {
                return super.eval(callerEnv, value);
            }
            NativeFunction func = (NativeFunction) value;
            int nparams = func.numOfParameters();
            if (size() != nparams) {
                throw new StoneException("bad number of arguments", this);
            }
            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a : this) {
                BasicEvaluator.ASTreeEx ae = (BasicEvaluator.ASTreeEx) a;
                args[num++] = ae.eval(callerEnv);
            }
            return func.invoke(args, this);
        }
    }
}
