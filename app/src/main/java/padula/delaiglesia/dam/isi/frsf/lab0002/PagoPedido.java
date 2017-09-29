package padula.delaiglesia.dam.isi.frsf.lab0002;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import padula.delaiglesia.dam.isi.frsf.lab0002.modelo.Tarjeta;

/**
 * Created by st on 07/09/2017.
 */
public class PagoPedido extends AppCompatActivity {
    private Button buttonConfirmarPedido;
    private Button buttonCancelar;

    private EditText nombre;
    private  EditText email;
    private EditText nombreTarjeta;
    private  EditText numeroTarjeta;
    private EditText diaVencimiento;
    private EditText mesVencimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago_pedido);
        buttonConfirmarPedido = (Button) findViewById(R.id.buttonConfirmarPedido);
        buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
        nombre = (EditText) findViewById(R.id.nombre);
        email = (EditText) findViewById(R.id.email);
        nombreTarjeta = (EditText) findViewById(R.id.nombreTarjeta);
        numeroTarjeta = (EditText) findViewById(R.id.numeroTarjeta);
        diaVencimiento = (EditText) findViewById(R.id.dia);
        mesVencimiento = (EditText) findViewById(R.id.mes);

        buttonConfirmarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombreCliente = nombre.getText().toString();
                    String emailCliente = email.getText().toString();
                    String nombreTarjetaCredito = nombreTarjeta.getText().toString();
                    Integer numero=-1;

                    if(!numeroTarjeta.getText().toString().trim().equalsIgnoreCase("")){
                        numero = Integer.parseInt(numeroTarjeta.getText().toString());
                    }


                    String fechaVencimiento = diaVencimiento.getText().toString() + "/" + mesVencimiento.getText().toString();

                    Tarjeta t = new Tarjeta(numero,nombreTarjetaCredito,123,fechaVencimiento);


                    Intent resultado = getIntent();
                    resultado.putExtra("RESULTADO",10 );
                    resultado.putExtra("TARJETA",t);
                    resultado.putExtra("CLIENTE",nombreCliente);
                    setResult(RESULT_OK, resultado);
                    finish();
                }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = getIntent();
                resultado.putExtra("RESULTADO","Cancelado" );
                setResult(RESULT_CANCELED, resultado);
                finish();


            }
        });

    }
}
