package stone.ast;

import stone.Token;

/**
 * @Author ada
 * @Date 2022/4/1 10:45 PM
 * @Version 1.0
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token().getNumber();
    }
}
