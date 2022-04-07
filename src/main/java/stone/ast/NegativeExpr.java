package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/1 10:54 PM
 * @Version 1.0
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) {
        super(c);
    }

    public ASTree operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }

}
