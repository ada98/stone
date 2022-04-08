package stone.ast;

import java.util.List;

/**
 * @Author ada
 * @Date 2022/4/7 11:38 PM
 * @Version 1.0
 */
public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) {
        super(c);
    }

    public int size() {
        return numChildren();
    }
}
