package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    Map<String , Json> pairs;
    public JsonObject(JsonPair... jsonPairs) {
        pairs = new HashMap<>();
        for (JsonPair pr : jsonPairs) {
            pairs.put(pr.key, pr.value);
        }

    }

    @Override
    public String toJson() {
        String output = "{";
        if (pairs.size() == 0){
            return output + "}";
        }
        for (String k : pairs.keySet()){
            output += "'" + k + "': " + pairs.get(k).toJson() + ", ";
        }
        output = output.substring(0, output.length() - 2);
        return output + "}";
    }

    public void add(JsonPair jsonPair) {
        pairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return pairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject output = new JsonObject();
        for (String n : names){
            if (pairs.get(n) != null){
                output.add(new JsonPair(n, pairs.get(n)));
            }
        }
        return output;
    }

}
