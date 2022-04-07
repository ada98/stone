package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/1 11:00 PM
 * @Version 1.0
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c) {
        super(c);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }


}
