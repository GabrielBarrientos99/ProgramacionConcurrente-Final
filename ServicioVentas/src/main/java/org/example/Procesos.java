package org.example;

import org.json.JSONObject;

public interface Procesos {
    public void CREATE(JSONObject a);
    public String READ(JSONObject a);
    public String READALL(JSONObject a);
    public void UPDATE(JSONObject a);
    public void DELETE(JSONObject a);
}
