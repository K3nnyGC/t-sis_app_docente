package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.models.Knowledge;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface KnowledgeInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @POST("/V2/api/theme")
    void RegisterKnowledge(@Body Knowledge knowledge, Callback<Knowledge> callback);

    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/themes")
    void getAllKnowledge(Callback<List<Knowledge>> callback);
}
