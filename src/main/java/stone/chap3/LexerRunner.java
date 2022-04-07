package stone.chap3;

import stone.CodeDialog;
import stone.Lexer;
import stone.ParseException;
import stone.Token;

/**
 * @Author ada
 * @Date 2022/4/1 10:15 PM
 * @Version 1.0
 */
public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; ((t = l.read()) != Token.EOF); ) {
            System.out.println("==> " + t.getText());
        }
    }
}
