import java.util.List;

public class Verification {
    private final String TXT = ".txt";

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
        if (nameFile.length() <= TXT.length()) {
            return false;
        }
        String numbers = nameFile.substring(nameFile.length() - 4);
        return numbers.equals(TXT);
    }


}
