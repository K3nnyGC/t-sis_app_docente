package pe.edu.upc.proyectotsys.viewcontrollers.Interface;

import java.util.List;

import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Contract;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface ContractInterface {
    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/contract/1")
    void getContracts(Callback<Contract> callback);

    @Headers("Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
    @GET("/V2/api/contracts")
    void getAllContracts(Callback<List<Contract>> callback);
}
