package stone;

import stone.ast.Arguments;
import stone.ast.DefStmnt;
import stone.ast.ParameterList;

import static stone.Parser.rule;

/**
 * 支持函数功能的语法分析器.
 *
 * @Author ada
 * @Date 2022/4/7 11:18 PM
 * @Version 1.0
 */
public class FuncParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class).ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class).sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Arguments.class).ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }

}
