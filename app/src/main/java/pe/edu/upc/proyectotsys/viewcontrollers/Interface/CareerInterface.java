package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Career;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface CareerInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/careers")
    void getCareer(Callback<List<Career>> callback);
}
