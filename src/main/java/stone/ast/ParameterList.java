package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/7 11:22 PM
 * @Version 1.0
 */
public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c) {
        super(c);
    }

    public String name(int i) {
        return ((ASTLeaf) child(i)).token().getText();
    }

    public int size() {
        return numChildren();
    }
}
