package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.models.advisor_detail;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface AdvisorDetailInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @POST("/V2/api/detail")
    void RegisterAdvisorDetails(@Body advisor_detail advisor, Callback<advisor_detail> callback);

    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/details")
    void getAllDetails(Callback<List<advisor_detail>> callback);
}
