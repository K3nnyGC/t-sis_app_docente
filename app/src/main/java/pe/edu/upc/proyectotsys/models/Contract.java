package pe.edu.upc.proyectotsys.models;

import java.util.Date;

public class Contract {

    private int code_contract;
    private int code_available_time;
    private int code_knowledge;
    private String dni_student;
    private int code_grade;
    private String date_registry;
    private String date_advisory;
    private int state_contract;
    private int method_payment;
    private int score_contract;

    public Contract(int code_contract, int code_available_time, int code_knowledge, String dni_student, int code_grade, String date_registry, String date_advisory, int state_contract, int method_payment, int score_contract) {
        this.code_contract = code_contract;
        this.code_available_time = code_available_time;
        this.code_knowledge = code_knowledge;
        this.dni_student = dni_student;
        this.code_grade = code_grade;
        this.date_registry = date_registry;
        this.date_advisory = date_advisory;
        this.state_contract = state_contract;
        this.method_payment = method_payment;
        this.score_contract = score_contract;
    }

    public Contract(int code_grade, int code_knowledge, String dni_student, String date_advisory) {
        this.code_contract = 0;
        this.code_available_time = 0;
        this.code_knowledge = code_knowledge;
        this.dni_student = dni_student;
        this.code_grade = code_grade;
        this.date_registry = "";
        this.date_advisory = date_advisory;
        this.state_contract = 0;
        this.method_payment = 0;
        this.score_contract = 0;
    }

    public int getCode_contract() {
        return code_contract;
    }

    public void setCode_contract(int code_contract) {
        this.code_contract = code_contract;
    }

    public int getCode_available_time() {
        return code_available_time;
    }

    public void setCode_available_time(int code_available_time) {
        this.code_available_time = code_available_time;
    }

    public int getCode_knowledge() {
        return code_knowledge;
    }

    public void setCode_knowledge(int code_knowledge) {
        this.code_knowledge = code_knowledge;
    }

    public String getDni_student() {
        return dni_student;
    }

    public void setDni_student(String dni_student) {
        this.dni_student = dni_student;
    }

    public int getCode_grade() {
        return code_grade;
    }

    public void setCode_grade(int code_grade) {
        this.code_grade = code_grade;
    }

    public String getDate_registry() {
        return date_registry;
    }

    public void setDate_registry(String date_registry) {
        this.date_registry = date_registry;
    }

    public String getDate_advisory() {
        return date_advisory;
    }

    public void setDate_advisory(String date_advisory) {
        this.date_advisory = date_advisory;
    }

    public int getState_contract() {
        return state_contract;
    }

    public void setState_contract(int state_contract) {
        this.state_contract = state_contract;
    }

    public int getMethod_payment() {
        return method_payment;
    }

    public void setMethod_payment(int method_payment) {
        this.method_payment = method_payment;
    }

    public int getScore_contract() {
        return score_contract;
    }

    public void setScore_contract(int score_contract) {
        this.score_contract = score_contract;
    }

    public String getCodeGradeS(){
        return "" + code_grade;
    }

    public String getCodeKnowledgeS(){
        return "" + code_knowledge;
    }


    @Override
    public String toString() {
        return "Contract{" +
                "code_contract=" + code_contract +
                ", code_available_time=" + code_available_time +
                ", code_knowledge=" + code_knowledge +
                ", dni_student='" + dni_student + '\'' +
                ", code_grade=" + code_grade +
                ", date_registry='" + date_registry + '\'' +
                ", date_advisory='" + date_advisory + '\'' +
                ", state_contract=" + state_contract +
                ", method_payment=" + method_payment +
                ", score_contract=" + score_contract +
                '}';
    }
}
