package stone;

/**
 * 支持闭包的语法分析器.
 * @Author ada
 * @Date 2022/4/7 11:59 PM
 * @Version 1.0
 */
public class ClosureParser extends FuncParser{
    public ClosureParser(){
        primary.insertChoice(Parser.rule().sep("fun").ast(paramList).ast(block));
    }
}
