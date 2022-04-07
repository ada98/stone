package stone.ast;

import stone.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author ada
 * @Date 2022/4/1 10:31 PM
 * @Version 1.0
 */
public class ASTLeaf extends ASTree {
    private static final ArrayList<ASTree> empty = new ArrayList<>();
    private final Token token;

    public ASTLeaf(Token t) {
        token = t;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token token() {
        return token;
    }
}
