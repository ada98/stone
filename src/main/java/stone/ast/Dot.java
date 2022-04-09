package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/9 10:46 PM
 * @Version 1.0
 */
public class Dot extends Postfix {
    public Dot(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public String toString() {
        return "." + name();
    }
}
