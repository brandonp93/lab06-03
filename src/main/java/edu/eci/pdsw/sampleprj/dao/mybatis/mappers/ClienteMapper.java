package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param ("id") int id); 
    
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'id
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void registrarAlquilerCliente(@Param ("id") long id, @Param ("idit") int idit,@Param ("fechainicio") Date fechainicio,@Param ("fechafin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
       
    
    public void registrarCliente(@Param("documento") long documento,
            @Param("nombre") String nombre,
            @Param("telefono") String telefono,
            @Param("direccion") String direccion,
            @Param("email") String email);
    
    public TipoItem consultarTipoItem(@Param("id") int id);
    
    public List<ItemRentado> consultarItemsCliente(@Param ("idcliente") int idcliente);
    
    public ItemRentado consultarItemRentado(@Param ("id") int id);
  
    
    public void registrarDevolucion(@Param ("iditem") int iditem);
    
    public void vetarCliente(@Param ("docu") long docu);
}
