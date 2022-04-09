package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/9 10:42 PM
 * @Version 1.0
 */
public class ClassStmnt extends ASTList {
    public ClassStmnt(List<ASTree> list) {
        super(list);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public String superClass() {
        if (numChildren() < 3) {
            return null;
        } else {
            return ((ASTLeaf) child(1)).token().getText();
        }
    }

    public ClassBody body() {
        return (ClassBody) child(numChildren() - 1);
    }

    public String toString() {
        String parent = superClass();
        if (parent == null) {
            parent = "*";
        }
        return "(class " + name() + " " + parent + " " + body() + ")";
    }
}
