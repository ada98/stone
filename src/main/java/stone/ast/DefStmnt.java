package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/7 11:24 PM
 * @Version 1.0
 */
public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList) child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }
}
