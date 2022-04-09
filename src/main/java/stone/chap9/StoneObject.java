package stone.chap9;

import stone.chap6.Environment;
import stone.chap7.FuncEvaluator;

/**
 * @Author ada
 * @Date 2022/4/9 11:23 PM
 * @Version 1.0
 */
public class StoneObject {
    public static class AccessException extends Exception {
    }

    protected Environment env;

    public StoneObject(Environment e) {
        env = e;
    }

    @Override
    public String toString() {
        return "<Object:" + hashCode() + ">";
    }

    public Object read(String member) throws AccessException {
        return getEnv(member).get(member);
    }

    public void write(String member, Object value) throws AccessException {
        ((FuncEvaluator.EnvEx) getEnv(member)).putNew(member, value);
    }

    protected Environment getEnv(String member) throws AccessException {
        Environment e = ((FuncEvaluator.EnvEx) env).where(member);
        if (e != null && e == env) {
            return e;
        } else {
            throw new AccessException();
        }
    }
}
