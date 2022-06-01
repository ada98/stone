package stone.chap11;

import stone.BasicParser;
import stone.ClosureParser;
import stone.CodeDialog;
import stone.Lexer;
import stone.ParseException;
import stone.Token;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import stone.chap6.BasicEvaluator;
import stone.chap6.Environment;
import stone.chap8.Natives;

/**
 * @Author ada
 * @Date 2022/5/31 10:42 PM
 * @Version 1.0
 */
public class EnvOptInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new Natives().environment(new ResizableArrayEnv()));
    }

    public static void run(BasicParser bp, Environment env) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                ((EnvOptimizer.ASTreeOptEx) t).lookup(((EnvOptimizer.EnvEx2) env).symbols());
                Object r = ((BasicEvaluator.ASTreeEx) t).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
