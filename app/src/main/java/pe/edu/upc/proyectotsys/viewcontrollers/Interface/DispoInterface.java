package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import pe.edu.upc.proyectotsys.models.Available;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface DispoInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @POST("/V2/api/available")
    void RegisterDispo(@Body Available avai, Callback<Available> callback);
}
