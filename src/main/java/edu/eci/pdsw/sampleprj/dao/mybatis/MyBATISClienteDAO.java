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
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;

/**
 *
 * @author estudiante
 */
public class MyBATISClienteDAO  implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;    

    @Override
    public void save(Cliente c) throws PersistenceException {
        try{
            clienteMapper.agregarItemRentadoACliente((int) c.getDocumento(), c.getRentados().get(c.getRentados().size()).getId(), 
                    c.getRentados().get(c.getRentados().size()).getFechainiciorenta(), c.getRentados().get(c.getRentados().size()).getFechafinrenta());
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+c.toString(),e);
        }      }

    @Override
    public Cliente load(int id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
    }
     
}
