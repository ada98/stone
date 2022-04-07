package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/1 10:57 PM
 * @Version 1.0
 */
public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) {
        super(c);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree thenBlock() {
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
    }
}
