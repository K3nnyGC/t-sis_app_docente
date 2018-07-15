package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Career {
    private int code_career;
    private String description_career;

    public Career(int code_career, String description_career) {
        this.code_career = code_career;
        this.description_career = description_career;
    }

    public Career(int code_career) {
        this.code_career = code_career;
    }

    public int getCode_career() {
        return code_career;
    }

    public void setCode_career(int code_career) {
        this.code_career = code_career;
    }

    public String getDescription_career() {
        return description_career;
    }

    public void setDescription_career(String description_career) {
        this.description_career = description_career;
    }

    @Override
    public String toString() {
        return "Career{" +
                "code_career=" + code_career +
                ", description_career='" + description_career + '\'' +
                '}';
    }
}
