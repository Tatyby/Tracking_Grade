import java.util.List;
import java.util.Locale;

public class Verification {
    LocaleManager localeManager = new LocaleManager(new Locale("ru", "RU"));

    public int checkName(String name, List<Student> students) {
        int count = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                count = i;
            }
        }
        return count;
    }
    public boolean checkNameFile(String nameFile) {
        if (nameFile.length() <= localeManager.getText("TXT").length()) {
            return false;
        }
        String numbers = nameFile.substring(nameFile.length() - 4);
        return numbers.equals(localeManager.getText("TXT"));
    }


}
