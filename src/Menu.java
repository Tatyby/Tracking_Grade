import java.util.Scanner;
import java.util.Set;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private final ActionStudent actionStudent = new ActionStudent();
    private final String EXIT = "exit";
    private final String nameExistFile = "File.txt";
    private final String NEW = "new";
    private final String CORRECT_COMMAND = "Введите корректную комнаду";
    private final String SELECT_FILE = "Вы хотите загрузить данные из существующего файла или загрузить новый?";
    private final String SELECT_FILE_INPUT = "Напишите %s или new \n";
    private final String MENU = "a) Добавить нового ученика" + "\n" +
            "b) Удалить ученика" + "\n" +
            "c) Обновить оценку ученика" + "\n" +
            "d) Просмотр оценок всех учащихся" + "\n" +
            "e) просмотр оценок конкретного участника";
    private final String SELECT_ACTION = "Выберите действие (чтобы выйти из программы, введите exit):";

    public void menu() {
        System.out.println(SELECT_ACTION);
        System.out.println(MENU);
    }

    public void action() {
        System.out.println(SELECT_FILE);
        System.out.printf(SELECT_FILE_INPUT, nameExistFile);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase(nameExistFile)) {
            basicMenu(nameExistFile);
        }
        if (input.equalsIgnoreCase(NEW)) {
            String nameFile = new FileNew().newFile();
            basicMenu(nameFile);
        }
    }

    private void basicMenu(String nameFile) {
        menu();
        String input = scanner.nextLine();

        while (!input.equals(EXIT)) {
            switch (input) {
                case "a" -> actionStudent.addStudent(nameFile);
                case "b" -> actionStudent.deleteStudent(nameFile);
                case "c" -> actionStudent.updateGradle(nameFile);
                case "d" -> actionStudent.printStudent(nameFile);
                case "e" -> actionStudent.getGradeStudentByName(nameFile);
                default -> System.out.println(CORRECT_COMMAND);
            }
            input = scanner.nextLine();
        }

    }
}


