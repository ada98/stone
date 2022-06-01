package stone.chap11;

import stone.chap6.Environment;

import java.util.Arrays;

/**
 * 全局变量.
 *
 * @Author ada
 * @Date 2022/5/31 9:29 PM
 * @Version 1.0
 */
public class ResizableArrayEnv extends ArrayEnv {
    protected Symbols names;

    public ResizableArrayEnv() {
        super(10, null);
        names = new Symbols();
    }

    @Override
    public Symbols symbols() {
        return names;
    }

    @Override
    public Object get(String name) {
        Integer i = names.find(name);
        if (i == null) {
            if (outer == null) {
                return null;
            } else {
                return outer.get(name);
            }
        } else {
            return values[i];
        }
    }

    @Override
    public void put(int nest, int index, Object value) {
        if (nest == 0) {
            assign(index, value);
        } else {
            super.put(nest, index, value);
        }
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        ((EnvOptimizer.EnvEx2) e).putNew(name, value);
    }

    @Override
    public void putNew(String name, Object value) {
        assign(names.putNew(name), value);
    }

    @Override
    public Environment where(String name) {
        if (names.find(name) != null) {
            return this;
        } else if (outer == null) {
            return null;
        } else {
            return ((EnvOptimizer.EnvEx2) outer).where(name);
        }
    }

    protected void assign(int index, Object value) {
        if (index >= values.length) {
            int newLen = values.length * 2;
            if (index >= newLen) {
                newLen = index + 1;
            }
            values = Arrays.copyOf(values, newLen);
        }
        values[index] = value;
    }
}
