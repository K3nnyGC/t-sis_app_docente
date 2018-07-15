package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Advisor {
    private String dni;
    private String name;
    private String lastname;
    private String email;
    private String address;
    private String password;
    private String phone;
    private Double latitude;
    private Double longitude;
    private int status_advisor;
    private Double prom_score;
    private String token;
    private String picture;

    public Advisor(String email, String pasword) {
        this.dni = "";
        this.name = "";
        this.lastname = "";
        this.email = email;
        this.address = "";
        this.password = pasword;
        this.phone = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.status_advisor = 0;
        this.prom_score = prom_score;
        this.token = token;
        this.picture = picture;
    }

    public Advisor(String dni, String name, String lastname, String email, String address, String password, String phone, Double latitude, Double longitude, int status_advisor, Double prom_score, String token, String picture) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status_advisor = status_advisor;
        this.prom_score = prom_score;
        this.token = token;
        this.picture = picture;
    }

    public Advisor(String dni) {
        this.dni = dni;
        this.name = "";
        this.lastname = "";
        this.email = "";
        this.address = "";
        this.password = "";
        this.phone = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.status_advisor = 0;
        this.prom_score = prom_score;
        this.token = token;
        this.picture = picture;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getStatus_advisor() {
        return status_advisor;
    }

    public void setStatus_advisor(int status_advisor) {
        this.status_advisor = status_advisor;
    }

    public Double getProm_score() {
        return prom_score;
    }

    public void setProm_score(Double prom_score) {
        this.prom_score = prom_score;
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

    @Override
    public String toString() {
        return "Advisor{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", status_advisor=" + status_advisor +
                ", prom_score=" + prom_score +
                ", token='" + token + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
