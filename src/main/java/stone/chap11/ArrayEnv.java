package stone.chap11;

import stone.StoneException;
import stone.chap6.Environment;

/**
 * @Author ada
 * @Date 2022/5/31 9:16 PM
 * @Version 1.0
 */
public class ArrayEnv implements Environment {

    protected Object[] values;
    protected Environment outer;

    public ArrayEnv(int size, Environment outer) {
        this.values = new Object[size];
        this.outer = outer;
    }

    public Symbols symbols() {
        throw new StoneException("not symbols");
    }

    public Object get(int nest, int index) {
        if (nest == 0) {
            return values[index];
        } else if (outer == null) {
            return null;
        } else {
            return ((EnvOptimizer.EnvEx2) outer).get(nest - 1, index);
        }
    }

    public void put(int nest, int index, Object value) {
        if (nest == 0) {
            values[index] = value;
        } else if (outer == null) {
            throw new StoneException("no outer environment");
        } else {
            ((EnvOptimizer.EnvEx2) outer).put(nest - 1, index, value);
        }
    }

    @Override
    public void put(String name, Object value) {
        error(name);
    }

    @Override
    public Object get(String name) {
        error(name);
        return null;
    }

    public void putNew(String name,Object value){
        error(name);
    }

    public Environment where(String name){
        error(name);
        return null;
    }

    public void setOuter(Environment e){
        outer = e;
    }

    private void error(String name){
        throw new StoneException("cannot access by name:" + name);
    }

}
