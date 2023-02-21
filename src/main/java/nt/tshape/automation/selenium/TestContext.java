package nt.tshape.automation.selenium;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestContext {
    private static final Map<String, String> contextAttribute = new HashMap<>();
    private static final Map<String, ArrayList<?>> contextObjects = new HashMap<>();

    public static void setAttribute(String key, String value) {
        contextAttribute.put(key, value);
    }

    public static String getAttributeByName(String keyName) {
        return contextAttribute.get(keyName);
    }

    public static List<?> getContextObjectsWithName(String objectName) {
        return contextObjects.get(objectName);
    }

    public static void setContextObjectsWithName(String objectName, ArrayList<?> listObjects) {
        contextObjects.put(objectName, listObjects);
    }

}
