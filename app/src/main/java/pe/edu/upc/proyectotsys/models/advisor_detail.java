package pe.edu.upc.proyectotsys.models;

public class advisor_detail {
    private int code_detail;
    private Advisor dni;
    private Universities code_university;
    private Grade code_grade;
    private int year_egress;
    private Career code_career;

    public advisor_detail(int code_detail, Advisor dni, Universities code_university, Grade code_grade, int year_egress, Career code_career) {
        this.code_detail = code_detail;
        this.dni = dni;
        this.code_university = code_university;
        this.code_grade = code_grade;
        this.year_egress = year_egress;
        this.code_career = code_career;
    }

    public int getCode_detail() {
        return code_detail;
    }

    public void setCode_detail(int code_detail) {
        this.code_detail = code_detail;
    }

    public Advisor getDni() {
        return dni;
    }

    public void setDni(Advisor dni) {
        this.dni = dni;
    }

    public Universities getCode_university() {
        return code_university;
    }

    public void setCode_university(Universities code_university) {
        this.code_university = code_university;
    }

    public Grade getCode_grade() {
        return code_grade;
    }

    public void setCode_grade(Grade code_grade) {
        this.code_grade = code_grade;
    }

    public int getYear_egress() {
        return year_egress;
    }

    public void setYear_egress(int year_egress) {
        this.year_egress = year_egress;
    }

    public Career getCode_career() {
        return code_career;
    }

    public void setCode_career(Career code_career) {
        this.code_career = code_career;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "code_detail=" + code_detail +
                ", dni=" + dni.toString() +
                ", code_university=" + code_university.toString() +
                ", code_grade =" + code_grade.toString() +
                ", year_egress='" + year_egress + '\'' +
                ", code_career=" + code_career.toString() +
                '}';
    }
}