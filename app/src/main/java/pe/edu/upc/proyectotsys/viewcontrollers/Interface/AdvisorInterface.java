package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import pe.edu.upc.proyectotsys.models.Advisor;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;

public interface AdvisorInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @POST("/V2/api/advisor")
    void RegisterAdvisor(@Body Advisor advisor, Callback<Advisor> callback);

    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @POST("/V2/api/login/advisor")
    void LoginUser(@Body Advisor advisor, Callback<Advisor> callback);

    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @PUT("/V2/api/advisor")
    void UpdateAdvisor(@Body Advisor advisor, Callback<Advisor> callback);
}