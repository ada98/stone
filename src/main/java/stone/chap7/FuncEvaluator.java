package stone.chap7;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.StoneException;
import stone.ast.ASTree;
import stone.ast.Arguments;
import stone.ast.DefStmnt;
import stone.ast.ParameterList;
import stone.ast.Postfix;
import stone.ast.PrimaryExpr;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/7 11:56 PM
 * @Version 1.0
 */
@Require(BasicEvaluator.class)
@Reviser
public class FuncEvaluator {

    @Reviser
    public interface EnvEx extends Environment {
        void putNew(String name, Object value);

        Environment where(String name);

        void setOuter(Environment e);
    }

    @Reviser
    public static class DefStmntEx extends DefStmnt {

        public DefStmntEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment env) {
            ((EnvEx) env).putNew(name(), new Function(parameters(), body(), env));
            return name();
        }
    }

    @Reviser
    public static class primaryEx extends PrimaryExpr {

        public primaryEx(List<ASTree> c) {
            super(c);
        }

        public ASTree operand() {
            return child(0);
        }

        public Postfix postfix(int nest) {
            return (Postfix) child(numChildren() - nest - 1);
        }

        public boolean hasPostfix(int nest) {
            return numChildren() - nest > 1;
        }

        public Object eval(Environment env) {
            return evalSubExpr(env, 0);
        }

        public Object evalSubExpr(Environment env, int nest) {
            if (hasPostfix(nest)) {
                Object target = evalSubExpr(env, nest + 1);
                return ((PostfixEx) postfix(nest)).eval(env, target);
            } else {
                return ((BasicEvaluator.ASTreeEx) operand()).eval(env);
            }
        }
    }

    @Reviser
    public static abstract class PostfixEx extends Postfix {

        public PostfixEx(List<ASTree> c) {
            super(c);
        }

        public abstract Object eval(Environment env, Object value);

    }

    @Reviser
    public static class ArgumentsEx extends Arguments {

        public ArgumentsEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof Function)) {
                throw new StoneException("bad function", this);
            }
            Function func = (Function) value;
            ParameterList params = func.parameters();
            if (size() != params.size()) {
                throw new StoneException("bad number of arguments", this);
            }
            Environment newEnv = func.makeEnv();
            int num = 0;
            for (ASTree a : this) {
                ((ParamsEx) params).eval(newEnv, num++, ((BasicEvaluator.ASTreeEx) a).eval(callerEnv));
            }
            return ((BasicEvaluator.BlockEx) func.body()).eval(newEnv);
        }
    }

    @Reviser
    public static class ParamsEx extends ParameterList {

        public ParamsEx(List<ASTree> c) {
            super(c);
        }

        public void eval(Environment env, int index, Object value) {
            ((EnvEx) env).putNew(name(index), value);
        }
    }
}
