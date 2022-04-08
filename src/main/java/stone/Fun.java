package stone;

import stone.ast.ASTList;
import stone.ast.ASTree;
import stone.ast.BlockStmnt;
import stone.ast.ParameterList;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/8 12:01 AM
 * @Version 1.0
 */
public class Fun extends ASTList {
    public Fun(List<ASTree> c) {
        super(c);
    }

    public ParameterList parameters() {
        return (ParameterList) child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(1);
    }

    public String string() {
        return "(fun " + parameters() + " " + body() + ")";
    }
}
