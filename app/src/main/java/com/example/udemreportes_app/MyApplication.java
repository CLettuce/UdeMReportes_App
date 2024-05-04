package com.example.udemreportes_app;
import android.app.Application;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MyApplication extends Application {
    private static APIService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        //TrustManager personalizado que no realiza ninguna verificación de certificado
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        try {
            // Se crea un contexto SSL personalizado con el TrustManager personalizado
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // OkHttp para usar el SSLContext personalizado
            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager)trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();

            // Configurar Retrofit con el cliente OkHttpClient personalizado
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://10.0.2.2:7030/") // Utilizar la dirección IP del servidor
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Se crea una instancia de tu interfaz de servicio API
            apiService = retrofit.create(APIService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la instancia del servicio API
    public static APIService getApiService() {
        return apiService;
    }
}
