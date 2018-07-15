package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Grade;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface GradeInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/grades")
    void getGrade(Callback<List<Grade>> callback);
}
