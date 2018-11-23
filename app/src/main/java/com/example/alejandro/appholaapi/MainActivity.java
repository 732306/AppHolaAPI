package com.example.alejandro.appholaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    private MobileServiceClient conexionServidorApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            conexionServidorApi = new MobileServiceClient("https://dssdemo1.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        llamarApi();

    }

    private void llamarApi(){
        final ListenableFuture<Usuario> resultado =
                conexionServidorApi.invokeApi("ObtenerUsuario", "", Usuario.class);

        Futures.addCallback(resultado,new FutureCallback<Usuario>() {
            @Override
            public void onFailure(Throwable exc) {
                // Acciones a realizar si la llamada devuelve un error
            }
            @Override
            public void onSuccess(Usuario usuario) {
                // Acciones a realizar si la llamada devuelve un ok
                //Toast.makeText(getApplicationContext(), usuario.getUsuario() + " : " + usuario.getId(),
                       // Toast.LENGTH_LONG).show();
                TextView tv_Resultado = (TextView) findViewById(R.id.tv_resultado);
                tv_Resultado.setText(usuario.getUsuario() + " : " + usuario.getId());
            }
        });
    }
}
