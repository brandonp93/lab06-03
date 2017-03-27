package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> getItems();        
    
    public Item consultarItem(@Param ("id") int id);
    
    public void insertarItem(@Param ("item") Item it);
    
    public List<Item> consultarItemsDisponibles();
    
    public void actualizarTarifaItem(@Param ("id") int id,@Param ("tarifa") long tarifa);

      
    
    
}
