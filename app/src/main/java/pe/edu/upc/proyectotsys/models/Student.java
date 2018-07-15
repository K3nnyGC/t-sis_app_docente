package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Student {
    private String dni;
    private String name;
    private String lastname;
    private String email;
    private String address;
    private String password;
    private String phone;
    private String token;
    private String picture;
    private int status_student;

    public Student(String email, String password) {
        this.dni = "";
        this.name = "";
        this.lastname = "";
        this.email = email;
        this.address = "";
        this.password = password;
        this.phone = "";
        this.token = "";
        this.picture = "";
        this.status_student = 0;
    }

    public Student(String dni, String name, String lastname, String email, String address, String password, String phone, String token, String picture, int status_student) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.token = token;
        this.picture = picture;
        this.status_student = status_student;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStatus_student() {
        return status_student;
    }

    public void setStatus_student(int status_student) {
        this.status_student = status_student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "dni=" + dni +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", picture='" + picture + '\'' +
                ", status_student=" + status_student +
                '}';
    }
}
