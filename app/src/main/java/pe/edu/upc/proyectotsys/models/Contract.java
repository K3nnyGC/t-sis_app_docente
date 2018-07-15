package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Contract {
    private int code_contract;
    private Knowledge code_knowledge; //Categoria monetizada por el docente
    private Student dni; // Estudiante
    private Grade code_grade; //Grado academico
    private String date_registry;
    private int state_contract;
    private Double score_contract;

    public Contract(int code_contract, Knowledge code_knowledge, Student dni, Grade code_grade, String date_registry, int state_contract, Double score_contract) {
        this.code_contract = code_contract;
        this.code_knowledge = code_knowledge;
        this.dni = dni;
        this.code_grade = code_grade;
        this.date_registry = date_registry;
        this.state_contract = state_contract;
        this.score_contract = score_contract;
    }

    public Contract(Grade code_grade, Knowledge code_knowledge, Student dni, String date_registry) {
        this.code_grade = code_grade;
        this.code_knowledge = code_knowledge;
        this.dni = dni;
        this.date_registry = date_registry;
    }

    public int getCode_contract() {
        return code_contract;
    }

    public void setCode_contract(int code_contract) {
        this.code_contract = code_contract;
    }

    public Knowledge getCode_knowledge() {
        return code_knowledge;
    }

    public void setCode_knowledge(Knowledge code_knowledge) {
        this.code_knowledge = code_knowledge;
    }

    public Student getDni() {
        return dni;
    }

    public void setDni(Student dni) {
        this.dni = dni;
    }

    public Grade getCode_grade() {
        return code_grade;
    }

    public void setCode_grade(Grade code_grade) {
        this.code_grade = code_grade;
    }

    public String getDate_registry() {
        return date_registry;
    }

    public void setDate_registry(String date_registry) {
        this.date_registry = date_registry;
    }

    public int getState_contract() {
        return state_contract;
    }

    public void setState_contract(int state_contract) {
        this.state_contract = state_contract;
    }

    public Double getScore_contract() {
        return score_contract;
    }

    public void setScore_contract(Double score_contract) {
        this.score_contract = score_contract;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "code_contract=" + code_contract +
                ", code_knowledge=" + code_knowledge.toString() +
                ", dni=" + dni.toString() +
                ", code_grade=" + code_grade.toString() +
                ", date_registry='" + date_registry + '\'' +
                ", state_contract=" + state_contract +
                ", score_contract=" + score_contract +
                '}';
    }
}
