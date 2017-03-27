/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;



/**
 *
 * @author hcadavid
 */
public class MyBATISItemDAO implements ItemDAO{

    @Inject
    private ItemMapper itemMapper;    
        
    @Override
    public void save(Item it) throws PersistenceException{
        try{
            itemMapper.insertarItem(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }          
    }

    @Override
    public Item load(int id) throws PersistenceException {
        try{
            return itemMapper.consultarItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }         
    }

    @Override
    public List<Item> consultarItemsDisponibles(){
            return itemMapper.consultarItemsDisponibles();
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias)  throws PersistenceException {
        try{
            return itemMapper.consultarItem(iditem).getTarifaxDia()*numdias;
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el costo de alquiler del item "+iditem,e);
        }     
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
        try{
            itemMapper.actualizarTarifaItem(id, tarifa);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar la tarifa del item "+id,e);
        }  
    }

   

   
}
