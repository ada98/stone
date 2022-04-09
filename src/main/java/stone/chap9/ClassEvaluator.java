package stone.chap9;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.StoneException;
import stone.ast.ASTree;
import stone.ast.ClassBody;
import stone.ast.ClassStmnt;
import stone.ast.Dot;
import stone.ast.PrimaryExpr;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;
import stone.chap7.FuncEvaluator;
import stone.chap7.NestedEnv;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/9 11:39 PM
 * @Version 1.0
 */
@Require(FuncEvaluator.class)
@Reviser
public class ClassEvaluator {
    @Reviser
    public static class ClassStmntEx extends ClassStmnt {
        public ClassStmntEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment env) {
            ClassInfo ci = new ClassInfo(this, env);
            env.put(name(), ci);
            return name();
        }
    }

    @Reviser
    public static class ClassBodyEx extends ClassBody {

        public ClassBodyEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            for (ASTree t : this) {
                ((BasicEvaluator.ASTreeEx) t).eval(env);
            }
            return null;
        }
    }

    @Reviser
    public static class DotEx extends Dot {

        public DotEx(List<ASTree> c) {
            super(c);
        }

        public Object eval(Environment env, Object value) {
            String member = name();
            if (value instanceof ClassInfo) {
                if ("new".equals(member)) {
                    ClassInfo ci = (ClassInfo) value;
                    NestedEnv e = new NestedEnv(ci.environment());
                    StoneObject so = new StoneObject(e);
                    e.putNew("this", so);
                    initObject(ci, e);
                    return so;
                }
            } else if (value instanceof StoneObject) {
                try {
                    return ((StoneObject) value).read(member);
                } catch (StoneObject.AccessException e) {
                    e.printStackTrace();
                }
            }
            throw new StoneException("bad member class: " + member, this);
        }

        protected void initObject(ClassInfo ci, Environment env) {
            if (ci.superClass() != null) {
                initObject(ci.superClass(), env);
            }
            ((ClassBodyEx) ci.body()).eval(env);
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
                if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) {
                    Object t = ((FuncEvaluator.PrimaryEx) le).evalSubExpr(env, 1);
                    if (t instanceof StoneObject) {
                        return setField((StoneObject) t, (Dot) p.postfix(0), rvalue);
                    }
                }
            }
            return super.computeAssign(env, rvalue);
        }

        protected Object setField(StoneObject obj, Dot expr, Object rvalue) {
            String name = expr.name();
            try {
                obj.write(name, rvalue);
                return rvalue;
            } catch (StoneObject.AccessException e) {
                throw new StoneException("bad member access " + location() + ":" + name);
            }
        }
    }

}
