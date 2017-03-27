/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface ClienteDAO {
     
    public void save(Cliente c) throws PersistenceException;
    
    public Cliente load(int id) throws PersistenceException;
    
    public List<Cliente> consultarClientes() throws PersistenceException;
    
    public ItemRentado consultarItemRentado(int id) throws PersistenceException;
    
    public void registrarAlquilerCliente(long documento,int idit,Date fechainicio,Date fechafin) throws PersistenceException;
    
    public TipoItem consultarTipoItem(int id) throws PersistenceException;
    
    public void registrarDevolucion(int iditem) throws PersistenceException;
    
    public void vetarCliente(long docu, boolean estado) throws PersistenceException; 
    
}
