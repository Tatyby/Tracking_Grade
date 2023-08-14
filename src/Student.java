import java.util.List;

public class Student {
    private String name;
    private List<String> grade;

    public Student(String name, List<String> grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Студент-" +
                name +
                ", Список оценок: " + grade +"\n";
    }
}
