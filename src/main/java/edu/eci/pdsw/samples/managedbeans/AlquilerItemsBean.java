/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped

public class AlquilerItemsBean implements Serializable {
    
    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    
    
    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private Cliente seleccionado;
    private ItemRentado seleccionado2;
    private List<Cliente> clientes;
    private List<Cliente> clientes1;
    private List<Item> items1;
    private List<ItemRentado> items;
    private int dias;
    private int codigo;
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Item seleccionado1;
    private long costo;
    private long total;
    private long multa;
    private String producto;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public ItemRentado getSeleccionado2() {
        return seleccionado2;
    }

    public void setSeleccionado2(ItemRentado seleccionado2) {
        this.seleccionado2 = seleccionado2;
    }
  
    public long getMulta() {
        return multa;
    }

    public void setMulta(long multa) {
        this.multa = multa;
    }
    
    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
            
    public Item getSeleccionado1() {
        return seleccionado1;
    }

    public void setSeleccionado1(Item seleccionado1) {
        this.seleccionado1 = seleccionado1;
    }
    
    public List<Item> getItems1() {
        return items1;
    }

    public void setItems1(List<Item> items1) {
        this.items1 = items1;
    }

    public List<ItemRentado> getItems() {
        return items;
    }

    public void setItems(List<ItemRentado> items) {
        this.items = items;
    }
        
     public List<Cliente> getClientes1() {
        return clientes1;
    }

    public void setClientes1(List<Cliente> clientes1) {
        this.clientes1 = clientes1;
    }
    
    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cliente getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cliente seleccionado) {
        this.seleccionado = seleccionado;
    }
   
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void conMulta() throws ExcepcionServiciosAlquiler{
        if(seleccionado2!=null){
            producto = "La multa para " + seleccionado2.getItem().getNombre() + " es de ";
            multa = sp.consultarMultaAlquiler(seleccionado2.getItem().getId(), seleccionado2.getFechafinrenta());
        }
    }
 
    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {
       sp.poblar();
       clientes= sp.consultarClientes();
       items1 = sp.consultarItemsDisponibles();   
    }
    
    public void registrarse() throws ExcepcionServiciosAlquiler {
        sp.registrarCliente(new Cliente(nombre,documento,telefono,direccion,email));
        clientes= sp.consultarClientes();       
    }
    public void ver(){
        if(seleccionado1!=null){
            costo = seleccionado1.getTarifaxDia();
            total = costo*dias;
        }
        
    }
    public void rentar() throws ExcepcionServiciosAlquiler{
        if(seleccionado1!=null){
            codigo = seleccionado1.getId();
            costo = seleccionado1.getTarifaxDia();
            total = costo*dias;
        }
        System.out.println("Jhgfdsasdfghjkjhgfdsadfghjkljhgfdsasdfghjklkjhgfdsdfghjk");
        sp.registrarAlquilerCliente(java.sql.Date.valueOf(sdf.format(date)), documento, sp.consultarItem(codigo), dias);
        items = sp.consultarItemsCliente(documento);
        
    }
    
    public void onRowSelect(SelectEvent event) throws IOException, ExcepcionServiciosAlquiler{
        if(seleccionado!=null){
            nombre = seleccionado.getNombre();    
            documento = seleccionado.getDocumento();
            telefono = seleccionado.getTelefono();
            direccion = seleccionado.getDireccion();
            email = seleccionado.getEmail();
            items = sp.consultarItemsCliente(documento);
            
            }
    
        sp.consultarItemsCliente(documento);
        FacesContext.getCurrentInstance().getExternalContext().redirect("RegistroClienteItem.xhtml");
        

    }

}
