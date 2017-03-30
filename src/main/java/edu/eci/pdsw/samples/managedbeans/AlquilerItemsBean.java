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
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.io.Serializable;
import static java.time.LocalDate.now;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped

public class AlquilerItemsBean implements Serializable {
    
    ServiciosAlquiler sp = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    
    private int dias;
    private int codigo;

    private long costo;
    private long total;
    private long multa;
    
    private String producto;
    private Cliente usuario;
    private Cliente registrarC; 
    private Item disponibles;
    private ItemRentado rentar;

    public ItemRentado getRentar() {
        return rentar;
    }

    public void setRentar(ItemRentado rentar) {
        this.rentar = rentar;
    }

    public Item getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Item disponibles) {
        this.disponibles = disponibles;
    }

    public Cliente getRegistrarC() {
        return registrarC;
    }

    public void setRegistrarC(Cliente registrarC) {
        this.registrarC = registrarC;
    }
    
    public List<Item> getConsultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        return sp.consultarItemsDisponibles();
    }
    
    public List<ItemRentado> getConsultarItemsCliente() throws ExcepcionServiciosAlquiler{
        return sp.consultarItemsCliente(usuario.getDocumento());
    }
        
    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public List<Cliente> getConsultarClientes() throws ExcepcionServiciosAlquiler {
        return sp.consultarClientes();
    }

    public void conMulta() throws ExcepcionServiciosAlquiler{
        long diasRetraso;
        producto = "La multa para " + rentar.getItem().getNombre() + " es de ";
        LocalDate fechaMinimaEntrega=rentar.getFechafinrenta().toLocalDate();
        LocalDate fechaEntrega = now();
        diasRetraso = ChronoUnit.DAYS.between(fechaMinimaEntrega, fechaEntrega);
        if(diasRetraso>0){
            multa = diasRetraso*5000;  
        }
        else{
            multa = 0;
        }  
    }
    
    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {
       registrarC = new Cliente();
    }
    
    public void registrarse() throws ExcepcionServiciosAlquiler {
        sp.registrarCliente(registrarC);
    }
    public void ver(){
        costo = disponibles.getTarifaxDia();
        total = costo*dias;
    }
    
    public void rentar() throws ExcepcionServiciosAlquiler{
        costo = disponibles.getTarifaxDia();
        total = costo*dias;
        sp.registrarAlquilerCliente(java.sql.Date.valueOf(LocalDate.now()), usuario.getDocumento(), sp.consultarItem(disponibles.getId()), dias);
               
    }

}
