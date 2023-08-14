import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ActionStudent {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = new ArrayList<>();
    Verification verification = new Verification();
    private final String NO_STUDENT = "Такого студента нет, но есть студенты: ";
    private final String INPUT_NAME_STUDENT = "Введите имя студента";
    private final String INPUT_NAME_STUDENT_AND_GRADE = "Введите имя студента и его оценки через пробел";
    private final String ADD = " добавлен";
    private final String INPUT_COMMAND = "Введите команду";
    private final String INPUT_NAME_STUDENT_FOR_DELETE = "Введите имя студента, которого хотите удалить";
    private final String STUDENT_DELETE = "Cтудент %s удален из списка";
    private final String GRATE = "Оценки студента: ";
    private final String INPUT_NAME_STUDENT_FOR_UPDATE_GRATE = "Введите имя студента, оценку которого хотите обновить";
    private final String INPUT_NEW_GRATE = "Введите новые оценки студента %s через пробел";

    public void addStudent(String nameFile) {
        List<String> listGrade = new ArrayList<>();
        System.out.println(INPUT_NAME_STUDENT_AND_GRADE);
        String input = scanner.nextLine();

        String[] arr = input.split(" ");

        listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
        checkStudentsIsEmpty(students, nameFile);
        students.add(new Student(arr[0], listGrade));
        System.out.println((new Student(arr[0], listGrade)) + ADD);
        writeToFile(nameFile, "\n" + input);
        System.out.println(INPUT_COMMAND);
    }

    public void deleteStudent(String nameFile) {
        System.out.println(INPUT_NAME_STUDENT_FOR_DELETE);
        String name = scanner.nextLine();
        checkStudentsIsEmpty(students, nameFile);
        Student remove = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                System.out.printf(STUDENT_DELETE, students.get(i).getName());
                remove = students.remove(i);
            }
        }
        if (remove == null) {
            System.out.println(NO_STUDENT);
        }
        System.out.println(students);
        new Menu().menu();
    }

    public List<Student> getStudent(String nameFile) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nameFile));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] arr = line.split(" ");
                List<String> listGrade = new ArrayList<>();
                listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
                students.add(new Student(arr[0], listGrade));
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void getGradeStudentByName(String nameFile) {
        System.out.println(INPUT_NAME_STUDENT);
        String name = scanner.nextLine();
        checkStudentsIsEmpty(students, nameFile);
        int verificationName = verification.checkName(name, students);
        if (verificationName < 0) {
            System.out.println(NO_STUDENT);
            System.out.println(students);
        } else {
            System.out.println(GRATE + name + "-" + students.get(verificationName).getGrade());
        }
        new Menu().menu();
    }

    public void printStudent(String nameFile) {
        if (students.isEmpty()) {
            System.out.println(getStudent(nameFile));
        } else {
            System.out.println(students);
        }
        new Menu().menu();
    }

    public void updateGradle(String nameFile) {
        System.out.println(INPUT_NAME_STUDENT_FOR_UPDATE_GRATE);
        String name = scanner.nextLine();
        checkStudentsIsEmpty(students, nameFile);

        System.out.printf(INPUT_NEW_GRATE, name + "\n");
        String input = scanner.nextLine();
        String[] arr = input.split(" ");
        List<String> listGrade = new ArrayList<>();
        listGrade.addAll(Arrays.asList(arr).subList(1, arr.length));
        int verificationName = verification.checkName(name, students);

        if (verificationName < 0) {
            System.out.println(NO_STUDENT);
            System.out.println(students);
        } else {
            students.get(verificationName).setGrade(listGrade);
        }
        new Menu().menu();
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
            System.out.println(ex.getMessage());
        }
    }
}

