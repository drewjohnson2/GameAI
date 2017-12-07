
package controller;

import model.HTTPGetInterface;
import org.json.JSONException;
import org.json.JSONObject;


public class GameClientID implements HTTPGetInterface {
    
    private String gameClientID;

    @Override
    public String get(JSONObject json) {
        try {
            JSONObject jsonGameTurn = json.getJSONObject("clientID");
            
            gameClientID =  jsonGameTurn.getString("ClientUUID");
            return gameClientID;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } 
    }
}
