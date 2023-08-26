import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private static final String BASE_NAME = "static";
    private ResourceBundle resourceBundle;
    public LocaleManager(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
    }
    public String getText(String key) {
        return resourceBundle.getString(key);
    }
}
