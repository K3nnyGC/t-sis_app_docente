package pe.edu.upc.proyectotsys.models;

public class Universities {
    private int code_university;
    private String description_university;

    public Universities(int code_university, String description_university) {
        this.code_university = code_university;
        this.description_university = description_university;
    }

    public Universities(int code_university) {
        this.code_university = code_university;
    }

    public int getCode_university() {
        return code_university;
    }

    public void setCode_university(int code_university) {
        this.code_university = code_university;
    }

    public String getDescription_university() {
        return description_university;
    }

    public void setDescription_university(String description_university) {
        this.description_university = description_university;
    }
}
