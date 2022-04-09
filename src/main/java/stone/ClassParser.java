package stone;

import stone.ast.ClassBody;
import stone.ast.ClassStmnt;
import stone.ast.Dot;

import static stone.Parser.rule;

/**
 * 支持类的语法分析器.
 *
 * @Author ada
 * @Date 2022/4/9 10:28 PM
 * @Version 1.0
 */
public class ClassParser extends ClosureParser {

    Parser member = rule().or(def, simple);
    Parser class_body = rule(ClassBody.class).sep("{")
            .option(member)
            .repeat(
                    rule().sep(";", Token.EOL)
                            .option(member)
            )
            .sep("}");
    Parser defclass = rule(ClassStmnt.class).sep("class")
            .identifier(reserved)
            .option(
                    rule().sep("extends")
                            .identifier(reserved)
            )
            .ast(class_body);

    public ClassParser() {
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
