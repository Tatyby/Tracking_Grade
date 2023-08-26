import java.io.*;
import java.util.*;

public class ActionStudent {
    List<Student> students = new ArrayList<>();
    Verification verification = new Verification();
    LocaleManager localeManager = new LocaleManager(new Locale("ru", "RU"));
    Menu menu = new Menu();

    public void addStudent(String nameFile) {
        List<String> listGrade = new ArrayList<>();
        menu.printConsole(localeManager.getText("INPUT_NAME_STUDENT_AND_GRADE"));
        String input = menu.scannerConsole();
        String[] arr = input.split(" ");
        listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
        checkStudentsIsEmpty(students, nameFile);
        students.add(new Student(arr[0], listGrade));
        menu.printConsole((new Student(arr[0], listGrade)) + localeManager.getText("ADD"));
        writeToFile(nameFile, "\n" + input);
        menu.printConsole(localeManager.getText("INPUT_COMMAND"));

    }

    public void deleteStudent(String nameFile) {
        menu.printConsole(localeManager.getText("INPUT_NAME_STUDENT_FOR_DELETE"));
        String name = menu.scannerConsole();
        checkStudentsIsEmpty(students, nameFile);
        Student remove = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                menu.printConsole(localeManager.getText("STUDENT_DELETE"), students.get(i).getName());
                remove = students.remove(i);
            }
        }
        if (remove == null) {
            menu.printConsole(localeManager.getText("NO_STUDENT"));
        }
        menu.printConsole(students);
        menu.menu();
    }

    public List<Student> getStudent(String nameFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] arr = line.split(" ");
                List<String> listGrade = new ArrayList<>();
                listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
                students.add(new Student(arr[0], listGrade));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void getGradeStudentByName(String nameFile) {
        menu.printConsole(localeManager.getText("INPUT_NAME_STUDENT"));
        String name = menu.scannerConsole();
        checkStudentsIsEmpty(students, nameFile);
        int verificationName = verification.checkName(name, students);
        if (verificationName < 0) {
            menu.printConsole(localeManager.getText("NO_STUDENT"));
            menu.printConsole(students);

        } else {
            menu.printConsole(localeManager.getText("GRATE") + name + "-" + students.get(verificationName).getGrade());
        }
        menu.menu();
    }

    public void printStudent(String nameFile) {
        if (students.isEmpty()) {
            menu.printConsole(getStudent(nameFile));
        } else {
            menu.printConsole(students);
        }
        menu.menu();
    }

    public void updateGradle(String nameFile) {
        menu.printConsole(localeManager.getText("INPUT_NAME_STUDENT_FOR_UPDATE_GRATE"));
        String name = menu.scannerConsole();
        checkStudentsIsEmpty(students, nameFile);
        menu.printConsole(localeManager.getText("INPUT_NEW_GRATE"), name + "\n");
        String input = menu.scannerConsole();
        String[] arr = input.split(" ");
        List<String> listGrade = new ArrayList<>();
        listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
        int verificationName = verification.checkName(name, students);

        if (verificationName < 0) {
            menu.printConsole(localeManager.getText("NO_STUDENT"));
            menu.printConsole(students);
        } else {
            students.get(verificationName).setGrade(listGrade);
        }
        menu.menu();
    }

    public void checkStudentsIsEmpty(List<Student> students, String nameFile) {
        if (students.isEmpty()) {
            getStudent(nameFile);
        }
    }

    private void writeToFile(String nameFile, String text) {

        try (FileWriter writer = new FileWriter(nameFile, true)) {

            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            menu.printConsole(ex.getMessage());
        }
    }
}

