package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;
import pe.edu.upc.proyectotsys.models.Universities;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface UniversitiesInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/universities")
    void getUniversities(Callback<List<Universities>> callback);
}
