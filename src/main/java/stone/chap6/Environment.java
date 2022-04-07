package stone.chap6;

/**
 * 环境对象接口.
 *
 * @Author ada
 * @Date 2022/4/6 10:48 PM
 * @Version 1.0
 */
public interface Environment {
    void put(String name, Object value);

    Object get(String name);
}
