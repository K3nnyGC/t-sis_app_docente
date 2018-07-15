package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Category;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface CategoryInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/categories")
    void getCategories(Callback<List<Category>> callback);
}
