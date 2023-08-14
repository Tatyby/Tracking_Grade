import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileNew {
    private final String FILE_NO_CREATE = "Файл не был создан";
    private final String FILE_CREATE = "файл %s успешно создан  \n";
    private final String INPUT_NAME_FILE = "укажите имя файла в формате XXX.txt";
    private final String INPUT_CORRECT_NAME_FILE = "Введите корректное наименование файла";
    Scanner scanner = new Scanner(System.in);
    Verification verification = new Verification();

    public String newFile() {
        System.out.println(INPUT_NAME_FILE);
        String nameFile = scanner.nextLine();
        while (!verification.checkNameFile(nameFile)) {
            System.out.println(INPUT_CORRECT_NAME_FILE);
            nameFile = scanner.nextLine();
        }
        File file = new File(nameFile);
        try {
            if (file.createNewFile()) {
                System.out.printf(FILE_CREATE, nameFile);

            } else {
                System.out.println(FILE_NO_CREATE);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return nameFile;
    }


}
