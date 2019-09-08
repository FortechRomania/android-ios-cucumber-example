package utils;

public final class Platform {
    private Platform() { }

    public static boolean isOnIOS() {
        return ConfigurationManager.getInstance().isOnIOS();
    }
}