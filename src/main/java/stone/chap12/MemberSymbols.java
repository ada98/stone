package stone.chap12;

import stone.chap11.Symbols;

/**
 * @Author ada
 * @Date 2022/6/7 11:35 PM
 * @Version 1.0
 */
public class MemberSymbols extends Symbols {

    public static int METHOD = -1;
    public static int FIELD = -2;
    protected int type;

    public MemberSymbols(Symbols outer, int type) {
        super(outer);
        this.type = type;
    }

    @Override
    public Location get(String key, int nest) {
        Integer index = table.get(key);
        if (index == null) {
            if (outer == null) {
                return null;
            } else {
                return outer.get(key, nest);
            }
        } else {
            return new Location(type, index.intValue());
        }
    }

    @Override
    public Location put(String key) {
        Location loc = get(key, 0);
        if (loc == null) {
            return new Location(type, add(key));
        } else {
            return loc;
        }
    }
}
