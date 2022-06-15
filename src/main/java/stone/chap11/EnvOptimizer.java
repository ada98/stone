package stone.chap11;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stone.Fun;
import stone.StoneException;
import stone.Token;
import stone.ast.ASTList;
import stone.ast.ASTree;
import stone.ast.BlockStmnt;
import stone.ast.DefStmnt;
import stone.ast.Name;
import stone.ast.ParameterList;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;
import stone.chap7.ClosureEvaluator;

import java.util.List;

import static javassist.gluonj.GluonJ.revise;

/**
 * @Author ada
 * @Date 2022/5/31 10:02 PM
 * @Version 1.0
 */
@Require(ClosureEvaluator.class)
@Reviser
public class EnvOptimizer {

    @Reviser
    public interface EnvEx2 extends Environment {
        Symbols symbols();

        void put(int nest, int index, Object value);

        Object get(int nest, int index);

        void putNew(String name, Object value);

        Environment where(String name);
    }

    @Reviser
    public static abstract class ASTreeOptEx extends ASTree {
        public void lookup(Symbols syms) {
        }
    }

    @Reviser
    public static abstract class ASTListEx extends ASTList {
        public ASTListEx(List<ASTree> list) {
            super(list);
        }

        public void lookup(Symbols syms) {
            for (ASTree t : this) {
                ((ASTreeOptEx) t).lookup(syms);
            }
        }
    }

    @Reviser
    public static class DefStmntEx extends DefStmnt {
        protected int index, size;

        public DefStmntEx(List<ASTree> c) {
            super(c);
        }

        public void lookup(Symbols syms) {
            index = syms.putNew(name());
            size = FunEx.lookup(syms, parameters(), body());
        }

        public Object eval(Environment env) {
            ((EnvEx2) env).put(0, index, new OptFunction(parameters(), body(), env, size));
            return name();
        }
    }

    @Reviser
    public static class FunEx extends Fun {
        protected int size = -1;

        public FunEx(List<ASTree> c) {
            super(c);
        }

        public void lookup(Symbols syms) {
            size = lookup(syms, parameters(), body());
        }

        public Object eval(Environment env) {
            return new OptFunction(parameters(), body(), env, size);
        }

        public static int lookup(Symbols syms, ParameterList params, BlockStmnt body) {
            Symbols newSyms = new Symbols(syms);
            ((ParamsEx) params).lookup(newSyms);
            ((ASTreeOptEx) revise(body)).lookup(newSyms);
            return newSyms.size();
        }

    }

    @Reviser
    public static class ParamsEx extends ParameterList {
        protected int[] offsets = null;

        public ParamsEx(List<ASTree> c) {
            super(c);
        }

        public void lookup(Symbols syms) {
            int s = size();
            offsets = new int[s];
            for (int i = 0; i < s; i++) {
                offsets[i] = syms.putNew(name(i));
            }
        }

        public void eval(Environment env, int index, Object value) {
            ((EnvEx2) env).put(0, offsets[index], value);
        }

    }

    @Reviser
    public static class NameEx extends Name {
        protected static final int UNKONWN = -1;
        protected int nest, index;

        public NameEx(Token t) {
            super(t);
            index = UNKONWN;
        }

        public void lookup(Symbols syms) {
            Symbols.Location loc = syms.get(name());
            if (loc == null) {
                throw new StoneException("undefined name:" + name(), this);
            } else {
                nest = loc.nest;
                index = loc.index;
            }
        }

        public void lookupForAssign(Symbols syms) {
            Symbols.Location loc = syms.put(name());
            nest = loc.nest;
            index = loc.index;
        }

        public Object eval(Environment env) {
            if (index == UNKONWN) {
                return env.get(name());
            } else {
                return ((EnvEx2) env).get(nest, index);
            }
        }

        public void evalForAssign(Environment env, Object value) {
            if (index == UNKONWN) {
                env.put(name(), value);
            } else {
                ((EnvEx2) env).put(nest, index, value);
            }
        }
    }

    @Reviser
    public static class BinaryEx2 extends BasicEvaluator.BinaryEx {

        public BinaryEx2(List<ASTree> c) {
            super(c);
        }

        public void lookup(Symbols syms) {
            ASTree left = left();
            if ("=".equals(operator())) {
                if (left instanceof Name) {
                    ((NameEx) left).lookupForAssign(syms);
                    ((ASTreeOptEx) right()).lookup(syms);
                    return;
                }
            }
            ((ASTreeOptEx) left).lookup(syms);
            ((ASTreeOptEx) right()).lookup(syms);
        }
    }


}
