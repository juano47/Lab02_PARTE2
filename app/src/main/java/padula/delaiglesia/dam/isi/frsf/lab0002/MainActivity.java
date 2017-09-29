package padula.delaiglesia.dam.isi.frsf.lab0002;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import padula.delaiglesia.dam.isi.frsf.lab0002.modelo.Pedido;
import padula.delaiglesia.dam.isi.frsf.lab0002.modelo.Tarjeta;
import padula.delaiglesia.dam.isi.frsf.lab0002.modelo.Utils;

public class MainActivity extends AppCompatActivity {

    private final Integer CODIGO_LLAMADA_PAGO_PEDIDO=800;
    private final Integer CODIGO_LLAMADA_ACT3=900;

    ArrayAdapter<Utils.ElementoMenu> miAdaptador;

    private Utils utils;
    private ListView miLista;
    private Utils.ElementoMenu[] elementos;
    private Pedido pedido;
    private Utils.ElementoMenu elementoSeleccionado;
    private int idRadioButtonSeleccionado;
    private Button buttonAgregar;
    private boolean pedidoConfirmado = false;
    private TextView txtPedido;
    private Button buttonConfirmarPedido;
    private Button buttonReiniciar;
    private String[] horarios = new String[] {"20.00", "20.30", "21.00", "21.30", "22.00"};
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pedido = new Pedido();
        txtPedido = (TextView) findViewById(R.id.txtPedido);
        buttonConfirmarPedido = (Button) findViewById(R.id.buttonConfirmarPedido);
        buttonReiniciar = (Button) findViewById(R.id.buttonReiniciar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,horarios);
        spinner.setAdapter(spinnerAdapter);

        miLista = (ListView) findViewById(R.id.lista);
        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);
        utils= new Utils();
        utils.iniciarListas();

        elementos= utils.getListaPlatos();
        ArrayList<Utils.ElementoMenu> lst = new ArrayList<Utils.ElementoMenu>(Arrays.asList(elementos));

        miAdaptador= new ArrayAdapter<Utils.ElementoMenu>(
                this,
                android.R.layout.simple_list_item_single_choice,
                lst);


        miLista.setAdapter( miAdaptador );

        RadioGroup radioGrupo = (RadioGroup)findViewById(R.id.visualRadioGrupo);
        idRadioButtonSeleccionado = radioGrupo.getCheckedRadioButtonId();
        radioGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int idRadioButtonSeleccionado) {
                switch (idRadioButtonSeleccionado){
                    case R.id.radioButtonPlato:
                            elementos = utils.getListaPlatos();
                            resetAdapterDataSet(elementos);

                        break;
                    case R.id.radioButtonBebida:

                        elementos= utils.getListaBebidas();
                        resetAdapterDataSet(elementos);
                        break;
                    case R.id.radioButtonPostre:

                        elementos= utils.getListaPostre();
                        resetAdapterDataSet(elementos);
                        break;
                }


            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pedido.setHoraEntrega(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setEsDelivery(toggleButton.getText() == "DELIVERY");
            }
        });

       buttonConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoConfirmado = true;
                Intent intent = new Intent(MainActivity.this, PagoPedido.class);
                startActivityForResult(intent,CODIGO_LLAMADA_PAGO_PEDIDO);
            }
        });

        buttonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarPedido();
            }
        });

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(elementoSeleccionado != null && !pedidoConfirmado ){

                    switch (elementoSeleccionado.getTipo()){
                        case BEBIDA:
                            pedido.setBebida(elementoSeleccionado);
                            break;
                        case POSTRE:
                            pedido.setPostre(elementoSeleccionado);
                            break;
                        case PRINCIPAL:
                            pedido.setPlato(elementoSeleccionado);
                            break;
                    }

                    String textoPlato;
                    String textoPostre;
                    String textoBebida;

                    if(pedido.getPlato() == null){
                        textoPlato = "";
                    }
                    else{
                        textoPlato = pedido.getPlato().toString();
                    }

                    if(pedido.getPostre() == null){
                        textoPostre = "";
                    }
                    else{
                        textoPostre = pedido.getPostre().toString();
                    }

                    if(pedido.getBebida() == null){
                        textoBebida = "";
                    }
                    else{
                        textoBebida = pedido.getBebida().toString();
                    }

                    txtPedido.setText(textoPlato + "\n"
                            +  textoPostre + "\n"
                            + textoBebida
                    );

                }
                else if (!pedidoConfirmado){
                    Toast.makeText(getApplicationContext(),"Debe seleccionar algo del menú", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"El pedido ya ha sido confirmado", Toast.LENGTH_LONG).show();
                }
            }
        });

        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    elementoSeleccionado = (Utils.ElementoMenu)miLista.getItemAtPosition(i);
                    String itemText = elementoSeleccionado.toString();

            }
        });








    }

    private  void reiniciarPedido(){
        pedidoConfirmado = false;
        clearListViewSelection();
        pedido.clear();
        txtPedido.setText("");
    }

    private void clearListViewSelection(){
        elementoSeleccionado = null;
        miLista.clearChoices();
        miAdaptador.notifyDataSetChanged();
    }
    private void resetAdapterDataSet(Utils.ElementoMenu[] newDataSet) {

        miAdaptador.clear();
        clearListViewSelection();
        ArrayList<Utils.ElementoMenu> lst = new ArrayList<Utils.ElementoMenu>(Arrays.asList(newDataSet));
        miAdaptador.addAll(newDataSet);
        miAdaptador.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("PEDIDO",pedido);
        outState.putBoolean("STATUS_PEDIDO",pedidoConfirmado);
        outState.putInt("RADIOBTN_SELECTED",idRadioButtonSeleccionado);
        outState.putString("PEDIDO_TEXT",txtPedido.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle previousState){
            super.onRestoreInstanceState(previousState);

            idRadioButtonSeleccionado = previousState.getInt("RADIOBTN_SELECTED");
            pedidoConfirmado = previousState.getBoolean("STATUS_PEDIDO");
            pedido = (Pedido) previousState.getSerializable("PEDIDO");
            txtPedido.setText(previousState.getString("PEDIDO_TEXT"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CODIGO_LLAMADA_PAGO_PEDIDO){
            if (resultCode == RESULT_OK) {
                DecimalFormat f = new DecimalFormat("##.00");
                Toast.makeText(getApplicationContext(),"Pago confirmado. Monto total: " + f.format(this.pedido.calcularCostoTotal()), Toast.LENGTH_LONG).show();

                Tarjeta t = (Tarjeta)data.getExtras().get("TARJETA");
                String cliente = (String)data.getExtras().get("CLIENTE");

                String currentText = txtPedido.getText().toString();

                txtPedido.setText(currentText + "\n" + cliente + "\n" + t.toString());


            }
            else {
                Toast.makeText(getApplicationContext(),"Resultado: Cancelado ", Toast.LENGTH_LONG).show();
                pedidoConfirmado=false;
            }
            }
        }
    }

