package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Knowledge {
    private int code_knowledge;
    private Advisor dni; //Docente
    private Category id_theme; //Categoria elegida
    private Double price;

    public Knowledge(int code_knowledge, Advisor dni, Category id_theme, Double price) {
        this.code_knowledge = code_knowledge;
        this.dni = dni;
        this.id_theme = id_theme;
        this.price = price;
    }

    public int getCode_knowledge() {
        return code_knowledge;
    }

    public void setCode_knowledge(int code_knowledge) {
        this.code_knowledge = code_knowledge;
    }

    public Advisor getDni() {
        return dni;
    }

    public void setDni(Advisor dni) {
        this.dni = dni;
    }

    public Category getId_theme() {
        return id_theme;
    }

    public void setId_theme(Category id_theme) {
        this.id_theme = id_theme;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "code_knowledge=" + code_knowledge +
                ", dni=" + dni.toString() +
                ", id_theme=" + id_theme.toString() +
                ", price=" + price +
                '}';
    }
}
