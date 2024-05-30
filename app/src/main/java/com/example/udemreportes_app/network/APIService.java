package com.example.udemreportes_app.network;
import com.example.udemreportes_app.response.LoginRequest;
import com.example.udemreportes_app.response.PersonaResponse;
import com.example.udemreportes_app.response.SolicitudRequest;
import com.example.udemreportes_app.response.SolicitudResponse;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("CallAndroid/VerificarUsuario")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);
    @POST("CallAndroid/InsertarSolicitud")
    Call<ResponseBody> insertarSolicitud(@Body SolicitudRequest solicitudRequest);
    @GET("CallAndroid/ObtenerSolicitudesPorUsuario/{username}")
    Call<List<SolicitudResponse>> obtenerSolicitudesPorUsuario(@Path("username") String username);
    @GET("CallAndroid/ObtenerInfoUsuario/{username}")
    Call<PersonaResponse> obtenerInfoUsuario(@Path("username") String username);

}
