package demoPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DT_HashMap {

    public static void main(String[] args) {
        HashMap<Object,Object> map = new HashMap<>();
        map.put("a","错误1");
        map.put("b","错误2");
        map.put("c","错误3");
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getValue());
        }

        System.out.println(map.containsKey("a"));
        System.out.println(map.containsKey(null));

        map.put("a","222");
        System.out.println(map.get("a"));
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getValue());
        }
    }
}
