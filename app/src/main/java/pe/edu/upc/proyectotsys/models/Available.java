package pe.edu.upc.proyectotsys.models;

public class Available {
    private int code_available_time;
    private Advisor dni;
    private String date;
    private int hour;
    private int status_available;

    public Available(int code_available_time, Advisor dni, String date, int hour, int status_available) {
        this.code_available_time = code_available_time;
        this.dni = dni;
        this.date = date;
        this.hour = hour;
        this.status_available = status_available;
    }

    public Available(int code, Advisor dni) {
        this.code_available_time = code_available_time;
        this.dni = dni;
        this.date = "";
        this.hour = 0;
        this.status_available = 0;
    }

    public int getCode() {
        return code_available_time;
    }

    public void setCode(int code) {
        this.code_available_time = code;
    }

    public Advisor getDni() {
        return dni;
    }

    public void setDni(Advisor dni) {
        this.dni= dni;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getStatus() {
        return status_available;
    }

    public void setStatus(int status) {
        this.status_available = status;
    }
}
