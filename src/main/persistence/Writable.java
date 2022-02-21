// this class is based on the writable class in the provided EdX JsonSerializationDemo

package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
