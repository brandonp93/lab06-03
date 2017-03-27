/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class MyBATISClienteDAO  implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;    

    @Override
    public void save(Cliente c) throws PersistenceException {
        clienteMapper.registrarCliente(c.getDocumento(), c.getNombre(), c.getTelefono(), c.getDireccion(), c.getEmail());
    }
    @Override
    public Cliente load(int id) throws PersistenceException {
        return clienteMapper.consultarCliente(id);
    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        return clienteMapper.consultarClientes();
    }

    @Override
    public ItemRentado consultarItemRentado(int id) throws PersistenceException {
            return clienteMapper.consultarItemRentado(id);
    }

    @Override
    public void registrarAlquilerCliente(long documento, int idit, Date fechainicio, Date fechafin) throws PersistenceException {
        
        
        clienteMapper.registrarAlquilerCliente(documento, idit, fechainicio, fechafin);
    }

    @Override
    public void registrarDevolucion(int iditem) throws PersistenceException {
        clienteMapper.registrarDevolucion(iditem);
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws PersistenceException {
        clienteMapper.vetarCliente(docu); 
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws PersistenceException {
        return clienteMapper.consultarTipoItem(id);
    }

    
}
