package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Grade {
    private int code_grade;
    private String description_grade;

    public Grade(int code_grade, String description_grade) {
        this.code_grade = code_grade;
        this.description_grade = description_grade;
    }

    public Grade(int code_grade) {
        this.code_grade = code_grade;
    }

    public int getCode_grade() {
        return code_grade;
    }

    public void setCode_grade(int code_grade) {
        this.code_grade = code_grade;
    }

    public String getDescription_grade() {
        return description_grade;
    }

    public void setDescription_grade(String description_grade) {
        this.description_grade = description_grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "code_grade=" + code_grade +
                ", description_grade='" + description_grade + '\'' +
                '}';
    }
}
