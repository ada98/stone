package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/1 10:52 PM
 * @Version 1.0
 */
public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> c) {
        super(c);
    }

    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }

}
