package padula.delaiglesia.dam.isi.frsf.lab0002.modelo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    DecimalFormat f = new DecimalFormat("##.00");

    private ElementoMenu[] listaBebidas;
    private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;

    public class ElementoMenu {
        private Integer id;
        private String nombre;
        private Double precio;
        private Tipo tipo;

        public Tipo getTipo() {
            return tipo;
        }

        public ElementoMenu() {
        }

        public ElementoMenu(Integer i, String n, Double p) {
            this.setId(i);
            this.setNombre(n);
            this.setPrecio(p);
        }

        public ElementoMenu(Integer i, String n, Tipo tipo) {
            this(i,n,0.0);
            Random r = new Random();
            this.precio= (r.nextInt(3)+1)*((r.nextDouble()*100));
            this.tipo= tipo;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getPrecio() {
            return precio;
        }

        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return this.nombre+ "( "+f.format(this.precio)+")";
        }
    }

    public void iniciarListas(){
        // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0]=new ElementoMenu(1,"Coca",Tipo.BEBIDA);
        listaBebidas[1]=new ElementoMenu(4,"Jugo",Tipo.BEBIDA);
        listaBebidas[2]=new ElementoMenu(6,"Agua",Tipo.BEBIDA);
        listaBebidas[3]=new ElementoMenu(8,"Soda",Tipo.BEBIDA);
        listaBebidas[4]=new ElementoMenu(9,"Fernet",Tipo.BEBIDA);
        listaBebidas[5]=new ElementoMenu(10,"Vino",Tipo.BEBIDA);
        listaBebidas[6]=new ElementoMenu(11,"Cerveza",Tipo.BEBIDA);
        // inicia lista de platos
        listaPlatos= new ElementoMenu[14];
        listaPlatos[0]=new ElementoMenu(1,"Ravioles ",Tipo.PRINCIPAL);
        listaPlatos[1]=new ElementoMenu(2,"Gnocchi ",Tipo.PRINCIPAL);
        listaPlatos[2]=new ElementoMenu(3,"Tallarines ",Tipo.PRINCIPAL);
        listaPlatos[3]=new ElementoMenu(4,"Lomo ",Tipo.PRINCIPAL);
        listaPlatos[4]=new ElementoMenu(5,"Entrecot ",Tipo.PRINCIPAL);
        listaPlatos[5]=new ElementoMenu(6,"Pollo ",Tipo.PRINCIPAL);
        listaPlatos[6]=new ElementoMenu(7,"Pechuga ",Tipo.PRINCIPAL);
        listaPlatos[7]=new ElementoMenu(8,"Pizza ",Tipo.PRINCIPAL);
        listaPlatos[8]=new ElementoMenu(9,"Empanadas ",Tipo.PRINCIPAL);
        listaPlatos[9]=new ElementoMenu(10,"Milanesas ",Tipo.PRINCIPAL);
        listaPlatos[10]=new ElementoMenu(11,"Picada 1 ",Tipo.PRINCIPAL);
        listaPlatos[11]=new ElementoMenu(12,"Picada 2 ",Tipo.PRINCIPAL);
        listaPlatos[12]=new ElementoMenu(13,"Hamburguesa ",Tipo.PRINCIPAL);
        listaPlatos[13]=new ElementoMenu(14,"Calamares ",Tipo.PRINCIPAL);
        // inicia lista de postres
        listaPostre= new ElementoMenu[15];
        listaPostre[0]=new ElementoMenu(1,"Helado",Tipo.POSTRE);
        listaPostre[1]=new ElementoMenu(2,"Ensalada de Frutas",Tipo.POSTRE);
        listaPostre[2]=new ElementoMenu(3,"Macedonia",Tipo.POSTRE);
        listaPostre[3]=new ElementoMenu(4,"Brownie",Tipo.POSTRE);
        listaPostre[4]=new ElementoMenu(5,"Cheescake",Tipo.POSTRE);
        listaPostre[5]=new ElementoMenu(6,"Tiramisu",Tipo.POSTRE);
        listaPostre[6]=new ElementoMenu(7,"Mousse",Tipo.POSTRE);
        listaPostre[7]=new ElementoMenu(8,"Fondue",Tipo.POSTRE);
        listaPostre[8]=new ElementoMenu(9,"Profiterol",Tipo.POSTRE);
        listaPostre[9]=new ElementoMenu(10,"Selva Negra",Tipo.POSTRE);
        listaPostre[10]=new ElementoMenu(11,"Lemon Pie",Tipo.POSTRE);
        listaPostre[11]=new ElementoMenu(12,"KitKat",Tipo.POSTRE);
        listaPostre[12]=new ElementoMenu(13,"IceCreamSandwich",Tipo.POSTRE);
        listaPostre[13]=new ElementoMenu(14,"Frozen Yougurth",Tipo.POSTRE);
        listaPostre[14]=new ElementoMenu(15,"Queso y Batata",Tipo.POSTRE);

    }

    public ElementoMenu[] getListaPostre(){
        return listaPostre;
    }

    public ElementoMenu[] getListaBebidas(){
        return listaBebidas;
    }

    public ElementoMenu[] getListaPlatos(){
        return listaPlatos;
    }

}