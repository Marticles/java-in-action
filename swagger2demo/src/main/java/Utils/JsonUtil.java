package Utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    public static String StringToJson(int code,String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }
}
