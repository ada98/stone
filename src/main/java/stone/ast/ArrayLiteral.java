package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/10 12:28 AM
 * @Version 1.0
 */
public class ArrayLiteral extends ASTList{
    public ArrayLiteral(List<ASTree> list) {
        super(list);
    }
    public int size(){return numChildren();}
}
