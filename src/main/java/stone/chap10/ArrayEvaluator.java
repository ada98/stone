package stone.chap10;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.ArrayParser;
import stone.StoneException;
import stone.ast.ASTree;
import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;
import stone.ast.PrimaryExpr;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;
import stone.chap7.FuncEvaluator;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/10 12:51 AM
 * @Version 1.0
 */
@Require({FuncEvaluator.class, ArrayParser.class})
@Reviser
public class ArrayEvaluator {
    @Reviser
    public static class ArrayLitEx extends ArrayLiteral {

        public ArrayLitEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            int s = numChildren();
            Object[] res = new Object[s];
            int i = 0;
            for (ASTree t : this) {
                res[i++] = ((BasicEvaluator.ASTreeEx) t).eval(env);
            }
            return res;
        }
    }

    @Reviser
    public static class ArrayRefEx extends ArrayRef {

        public ArrayRefEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment env, Object value) {
            if (value instanceof Object[]) {
                Object index = ((BasicEvaluator.ASTreeEx) index()).eval(env);
                if (index instanceof Integer) {
                    return ((Object[]) value)[(Integer) index];
                }
            }
            throw new StoneException("bad array access", this);
        }
    }

    @Reviser
    public static class AssignEx extends BasicEvaluator.BinaryEx {

        public AssignEx(List<ASTree> c) {
            super(c);
        }

        @Override
        protected Object computeAssign(Environment env, Object rvalue) {
            ASTree le = left();
            if (le instanceof PrimaryExpr) {
                FuncEvaluator.PrimaryEx p = (FuncEvaluator.PrimaryEx) le;
                if (p.hasPostfix(0) && p.postfix(0) instanceof ArrayRef) {
                    Object a = ((FuncEvaluator.PrimaryEx) le).evalSubExpr(env, 1);
                    if (a instanceof Object[]) {
                        ArrayRef aref = (ArrayRef) p.postfix(0);
                        Object index = ((BasicEvaluator.ASTreeEx) aref.index()).eval(env);
                        if (index instanceof Integer) {
                            ((Object[]) a)[(Integer) index] = rvalue;
                            return rvalue;
                        }
                    }
                    throw new StoneException("bad array access", this);
                }
            }
            return super.computeAssign(env, rvalue);
        }
    }
}
