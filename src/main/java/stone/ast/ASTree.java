package stone.ast;

import java.util.Iterator;

/**
 * 抽象语法树.
 * @Author ada
 * @Date 2022/3/31 11:45 PM
 * @Version 1.0
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);

    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    public Iterator<ASTree> iterator() {
        return children();
    }
}
