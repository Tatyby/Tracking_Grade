import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private final LocaleManager localeManager = new LocaleManager(new Locale("ru", "RU"));
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        printConsole(localeManager.getText("SELECT_ACTION"));
        printConsole(localeManager.getText("MENU"));
    }

    public void action() {
        printConsole(localeManager.getText("SELECT_FILE"));
        printConsole(localeManager.getText("SELECT_FILE_INPUT"), localeManager.getText("nameExistFile"));
        String input = scannerConsole();
        if (input.equalsIgnoreCase(localeManager.getText("nameExistFile"))) {
            basicMenu(localeManager.getText("nameExistFile"));
        }
        if (input.equalsIgnoreCase(localeManager.getText("NEW"))) {
            String nameFile = new FileNew().newFile();
            basicMenu(nameFile);
        }
    }

    private void basicMenu(String nameFile) {
        menu();
        String input = scannerConsole();
        ActionStudent actionStudent = new ActionStudent();

        while (!input.equals(localeManager.getText("EXIT"))) {
            switch (input) {
                case "a" -> actionStudent.addStudent(nameFile);
                case "b" -> actionStudent.deleteStudent(nameFile);
                case "c" -> actionStudent.updateGradle(nameFile);
                case "d" -> actionStudent.printStudent(nameFile);
                case "e" -> actionStudent.getGradeStudentByName(nameFile);
                default -> printConsole(localeManager.getText("CORRECT_COMMAND"));
            }
            input = scannerConsole();
        }

    }

    public void printConsole(String text) {
        System.out.println(text);
    }

    public void printConsole(String text, String var) {
        System.out.printf(text, var);
    }

    public void printConsole(List<Student> list) {
        System.out.println(list);
    }


    public String scannerConsole() {
        return scanner.nextLine();
    }
}


