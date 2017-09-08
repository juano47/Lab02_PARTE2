package padula.delaiglesia.dam.isi.frsf.lab0002;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by st on 07/09/2017.
 */
public class PagoPedido extends AppCompatActivity {
    private Button buttonConfirmarPedido;
    private Button buttonCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago_pedido);

        buttonConfirmarPedido.setOnClickListener(new View.OnClickListener() {
                @Override


                public void onClick(View view) {
                    Intent resultado = getIntent();
                   // resultado.putExtra("RESULTADO", )


                }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }
}
