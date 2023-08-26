import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FileNew {
    LocaleManager localeManager = new LocaleManager(new Locale("ru", "RU"));
    Verification verification = new Verification();
    Menu menu = new Menu();

    public String newFile() {
        menu.printConsole(localeManager.getText("INPUT_NAME_FILE"));
        String nameFile = menu.scannerConsole();
        while (!verification.checkNameFile(nameFile)) {
            menu.printConsole(localeManager.getText("INPUT_CORRECT_NAME_FILE"));
            nameFile = menu.scannerConsole();
        }
        File file = new File(nameFile);
        try {
            if (file.createNewFile()) {
                menu.printConsole(localeManager.getText("FILE_CREATE"), nameFile);

            } else {
                menu.printConsole(localeManager.getText("FILE_NO_CREATE"));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return nameFile;
    }


}
