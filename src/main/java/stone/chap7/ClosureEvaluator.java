package stone.chap7;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.Fun;
import stone.ast.ASTree;
import stone.chap6.Environment;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/8 12:02 AM
 * @Version 1.0
 */
@Require(FuncEvaluator.class)
@Reviser
public class ClosureEvaluator {
    @Reviser
    public static class FunEx extends Fun {

        public FunEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment env) {
            return new Function(parameters(), body(), env);
        }
    }
}
