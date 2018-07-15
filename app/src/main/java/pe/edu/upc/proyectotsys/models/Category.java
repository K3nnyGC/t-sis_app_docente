package pe.edu.upc.proyectotsys.models;

/**
 * Created by Kenny on 10/07/2018.
 */
public class Category {
    private int id_theme;
    private Career code_career;
    private String name_theme;

    public Category(int id_theme, Career code_career, String name_theme) {
        this.id_theme = id_theme;
        this.code_career = code_career;
        this.name_theme = name_theme;
    }

    public Category(int id_theme, String name_theme) {
        this.id_theme = id_theme;
        this.name_theme = name_theme;
    }

    public Category(int id_theme) {
        this.id_theme = id_theme;
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    public Career getCode_career() {
        return code_career;
    }

    public void setCode_career(Career code_career) {
        this.code_career = code_career;
    }

    public String getName_theme() {
        return name_theme;
    }

    public void setName_theme(String name_theme) {
        this.name_theme = name_theme;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id_theme=" + id_theme +
                ", code_career=" + code_career.toString() +
                ", name_theme='" + name_theme + '\'' +
                '}';
    }
}
