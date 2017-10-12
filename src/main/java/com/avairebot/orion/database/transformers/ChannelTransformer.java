package com.avairebot.orion.database.transformers;

import com.avairebot.orion.contracts.database.transformers.Transformer;
import com.avairebot.orion.database.collection.DataRow;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

public class ChannelTransformer extends Transformer {

    private final MessageModule welcome = new MessageModule();
    private final MessageModule goodbye = new MessageModule();

    public ChannelTransformer(DataRow data) {
        super(data);

        if (hasData()) {
            if (data.get("welcome", null) != null) {
                DataRow welcomeData = new DataRow((LinkedTreeMap<String, Object>) data.get("welcome"));

                welcome.setEnabled(welcomeData.getBoolean("enabled", false));
                welcome.setMessage(welcomeData.getString("message", null));
            }

            if (data.get("goodbye", null) != null) {
                DataRow goodbyeData = new DataRow((LinkedTreeMap<String, Object>) data.get("goodbye"));

                goodbye.setEnabled(goodbyeData.getBoolean("enabled", false));
                goodbye.setMessage(goodbyeData.getString("message", null));
            }
        }
    }

    public ChannelTransformer(HashMap<String, Object> data) {
        this(new DataRow(data));
    }

    public String getId() {
        return data.getString("id", "invalid-id");
    }

    public MessageModule getWelcome() {
        return welcome;
    }

    public MessageModule getGoodbye() {
        return goodbye;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> objects = new HashMap<>();

        objects.put("welcome", welcome.toMap());
        objects.put("goodbye", goodbye.toMap());

        return objects;
    }

    public class MessageModule {
        private boolean enabled = false;
        private String message = null;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        HashMap<String, Object> toMap() {
            HashMap<String, Object> objects = new HashMap<>();

            objects.put("enabled", enabled);
            objects.put("message", message);

            return objects;
        }
    }
}
