package stone.chap7;

import stone.chap6.Environment;

import java.util.HashMap;

/**
 * @Author ada
 * @Date 2022/4/7 11:45 PM
 * @Version 1.0
 */
public class NestedEnv implements Environment {
    protected HashMap<String, Object> values;
    protected Environment outer;

    public NestedEnv() {
        this(null);
    }

    public NestedEnv(Environment e) {
        values = new HashMap<>();
        outer = e;
    }

    public void setOuter(Environment e) {
        outer = e;
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        ((FuncEvaluator.EnvEx) e).putNew(name, value);
    }

    @Override
    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null) {
            return outer.get(name);
        } else {
            return v;
        }
    }

    public void putNew(String name, Object value) {
        values.put(name, value);
    }

    public Environment where(String name) {
        if (values.get(name) != null) {
            return this;
        } else if (outer == null) {
            return null;
        } else {
            return ((FuncEvaluator.EnvEx) outer).where(name);
        }
    }
}
