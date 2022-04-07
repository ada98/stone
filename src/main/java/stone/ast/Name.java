package stone.ast;

import stone.Token;

/**
 * @Author ada
 * @Date 2022/4/1 10:46 PM
 * @Version 1.0
 */
public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }
}
