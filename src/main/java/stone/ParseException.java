package stone;

import java.io.IOException;

/**
 * @Author ada
 * @Date 2022/4/1 12:06 AM
 * @Version 1.0
 */
public class ParseException extends Exception {

    public ParseException(Token t) {
        this("", t);
    }

    public ParseException(String msg, Token t) {
        super("syntax error around " + location(t) + ". " + msg);
    }

    public ParseException(IOException e) {
        super(e);
    }

    public ParseException(String msg) {
        super(msg);
    }

    private static String location(Token t) {
        if (t == Token.EOF) {
            return "the last line";
        } else {
            return "\"" + t.getText() + "\" at line " + t.getLineNumber();
        }
    }
}
