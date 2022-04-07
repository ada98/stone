package stone.chap6;

import java.util.HashMap;

/**
 * @Author ada
 * @Date 2022/4/6 10:51 PM
 * @Version 1.0
 */
public class BasicEnv implements Environment {

    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<>();
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }
}
