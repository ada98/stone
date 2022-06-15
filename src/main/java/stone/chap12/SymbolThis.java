package stone.chap12;

import stone.StoneException;
import stone.chap11.Symbols;

/**
 * @Author ada
 * @Date 2022/6/7 11:34 PM
 * @Version 1.0
 */
public class SymbolThis extends Symbols {
    public static final String NAME = "this";

    public SymbolThis(Symbols outer) {
        super(outer);
        add(NAME);
    }

    @Override
    public int putNew(String key) {
        throw new StoneException("fatal");
    }

    @Override
    public Location put(String key) {
        Location loc = outer.put(key);
        if (loc.nest >= 0) {
            loc.nest++;
        }
        return loc;
    }

}
