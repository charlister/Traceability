package profiling.analysis;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import profiling.model.Profile;
import profiling.model.ProfileProductPrice;

import java.io.FileInputStream;
import java.util.*;

public class AutoAnalysis {
    private List<Profile> profiles;

    public AutoAnalysis(String logPath) {
        this.profiles = new ArrayList<>();
        this.generateProfiles(logPath);
    }

    private JSONObject parseXMLToJSONObject(String xmlFilePath) {
        JSONObject jsonObject = null;
        try {
            FileInputStream fis = null;
            fis = new FileInputStream(xmlFilePath);
            String xmlContent = IOUtils.toString(fis, "UTF-8");
            jsonObject = XML.toJSONObject(xmlContent);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return jsonObject;
    }

    private void displayJSONObject(JSONObject jsonObject) {
        String jsonString = jsonObject.toString(4);
        System.out.println(jsonString);
    }

    private JSONArray getRecords(JSONObject jsonObject) {
        JSONObject log = jsonObject.optJSONObject("log");
        if (log == null) {
            System.err.println("log key is unavailable.");
            return null;
        }
        JSONArray records = log.optJSONArray("record");
        if (log == null) {
            System.err.println("record key is unavailable.");
            return null;
        }
        return records;
    }

    private Profile generateProfileByJSONObject(JSONObject record) {
//        System.out.println(record);
        Profile result = new Profile();
        JSONObject message = new JSONObject(record.optString("message"));
        result.setUserId(message.optLong("user_id"));
        result.setTimestamp(new Date(record.optLong("millis")));
        result.setAction(record.optString("class")+'.'+record.optString("method"));
        result.setEvent(""+message.optString("msg"));
        return result;
    }

    private ProfileProductPrice generateProfilePPByJSONObject(JSONObject record) {
//        System.out.println(record);
        ProfileProductPrice result = new ProfileProductPrice();
        JSONObject message = new JSONObject(record.optString("message"));
        result.setUserId(message.optLong("user_id"));
        result.setTimestamp(new Date(record.optLong("millis")));
        result.setAction(record.optString("class")+'.'+record.optString("method"));
        result.setEvent(""+message.optString("msg"));
        result.setProductId(message.optLong("product_id"));
        result.setProductPrice(Float.parseFloat(message.optString("product_price")));
        return result;
    }

    private void generateProfiles(String logPath) {
        JSONObject jsonObject = this.parseXMLToJSONObject(logPath);
//        this.displayJSONObject(jsonObject);
        JSONArray records = this.getRecords(jsonObject);
        if (records == null) {
            System.err.println("There is no log recorded");
        }
        else {
            for (Object object : records) {
                JSONObject record = (JSONObject) object;
                String opName = record.get("method").toString();
                String level = record.get("level").toString();
                if(!level.equals("INFO"))
                    continue;
                if (opName.equals("getProducts")) {
                    this.profiles.add(generateProfileByJSONObject(record));
                }
                else if(opName.startsWith("add") || opName.startsWith("delete") || opName.startsWith("update")) {
                    this.profiles.add(generateProfileByJSONObject(record));
                }
                if (opName.equals("getProductById")) {
                    this.profiles.add(generateProfilePPByJSONObject(record));
                }
            }
        }
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
