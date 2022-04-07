package stone.ast;

import stone.Token;

/**
 * @Author ada
 * @Date 2022/4/1 11:02 PM
 * @Version 1.0
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }

    public String value() {
        return token().getText();
    }
}
