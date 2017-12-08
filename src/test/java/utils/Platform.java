package utils;

public final class Platform {
    private Platform() { }

    public static boolean isOnIOS() {
        return PropertiesManager.getInstance().isOnIOS();
    }
}