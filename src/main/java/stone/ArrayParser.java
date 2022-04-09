package stone;

import javassist.gluonj.Reviser;
import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;

import static stone.Parser.rule;

/**
 * @Author ada
 * @Date 2022/4/10 12:26 AM
 * @Version 1.0
 */
@Reviser
public class ArrayParser extends FuncParser{
    Parser elements  = rule(ArrayLiteral.class).ast(expr).repeat(rule().sep(",").ast(expr));

    public ArrayParser(){
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
