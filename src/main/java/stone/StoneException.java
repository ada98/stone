package stone;

import stone.ast.ASTree;

/**
 * @Author ada
 * @Date 2022/3/31 11:45 PM
 * @Version 1.0
 */
public class StoneException extends RuntimeException {
    public StoneException(String m) {
        super(m);
    }

    public StoneException(String m, ASTree t) {
        super(m + " " + t.location());
    }

}
