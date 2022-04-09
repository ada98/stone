package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/10 12:33 AM
 * @Version 1.0
 */
public class ArrayRef extends Postfix {
    public ArrayRef(List<ASTree> c) {
        super(c);
    }

    public ASTree index() {
        return child(0);
    }

    @Override
    public String toString() {
        return "[" + index() + "]";
    }
}
