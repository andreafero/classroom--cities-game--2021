package com.drpicox.game.testSteps.game;

import com.drpicox.game.testSteps.navigator.NavigableScreen;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class EntityTestView implements NavigableScreen {

    private final GameTestView gameTestView;
    private final NavigatorTestView navigatorTestView;
    private Map<String, Object> form;

    public EntityTestView(GameTestView gameTestView, NavigatorTestView navigatorTestView) {
        this.gameTestView = gameTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    public String getScreenName() {
        return "entity";
    }

    @Override
    public void show() {
        clear();
    }

    private void clear() {
        form = new TreeMap<>();
    }

    public EntityResponse getEntity() {
        var game = gameTestView.getGame();
        String entityId = getEntityId();

        return game.getEntityResponse(entityId);
    }

    public String getEntityId() {
        return navigatorTestView.peekId();
    }

    public int getEntityPropertyInt(String key) {
        return getEntity().getInt(key);
    }

    public String getEntityPropertyString(String key) {
        return getEntityPropertyString(getEntityId(), key);
    }

    public String getEntityPropertyString(String entityId, String key) {
        return gameTestView.getGame().getEntityResponse(entityId).get(key, String.class);
    }

    public boolean getEntityPropertyBoolean(String key) {
        return getEntity().get(key, Boolean.class);
    }

    public void putFormKey(String key, Object value) {
        form.put(key, value);
    }

    public GameResponse postxxx(String url) {
        return this.post(url, new TreeMap<>());
    }

    public GameResponse post(String collection, String action) {
        return this.post("/api/v1/" + collection + "/" + getEntityId() + "/" + action, new TreeMap<>());
    }

    public GameResponse post(String url, Map<String, Object> extraData) {
        var data = new TreeMap<>();
        data.putAll(form);
        data.putAll(extraData);

        return gameTestView.post(url, data);
    }

}
