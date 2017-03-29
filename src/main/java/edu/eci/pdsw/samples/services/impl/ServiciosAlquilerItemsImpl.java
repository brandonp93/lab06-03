package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mybatis.guice.transactional.Transactional;

/**
 * 
 * @author hcadavid
 */
@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

    private static final int MULTA_DIARIA=5000;
    
    @Inject
    private ItemDAO daoItem;
    
    @Inject ClienteDAO daoCliente;
        
    @Override
    public int valorMultaRetrasoxDia() {
        return MULTA_DIARIA;
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.load((int) docu);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el cliente con documento "+ docu, ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.load((int) idcliente).getRentados(); 
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items del cliente" + idcliente, ex);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.consultarClientes();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar todos los clientes", ex);
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }
    
    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.consultarItemsDisponibles();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar todos los items", ex);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        try {
            int oc = daoCliente.consultarItemRentado(iditem).getId();  
            
            return oc;
           /* if (ir == null){
                throw new ExcepcionServiciosAlquiler("El item no esta a la renta");
            }
            else{
                LocalDate fechaMinimaEntrega=ir.getFechafinrenta().toLocalDate();
                LocalDate fechaEntrega=fechaDevolucion.toLocalDate();
                long diasRetraso = ChronoUnit.DAYS.between(fechaMinimaEntrega, fechaEntrega);
                return diasRetraso*MULTA_DIARIA;
                
            }*/
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar la multa del item rentado "+iditem, ex);
        }
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.consultarTipoItem(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item: " + id, ex);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Transactional
    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        LocalDate fechainicio1 = date.toLocalDate();
        LocalDate fechafin1 = fechainicio1.plusDays(numdias);
        Date fechainicio = Date.valueOf(fechainicio1);
        Date fechafin = Date.valueOf(fechafin1);
        try {
            daoCliente.registrarAlquilerCliente(docu, item.getId(), fechainicio, fechafin);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar un alquiler al cliente: " + docu, ex);
        }
    }
    
    @Transactional
    @Override
    public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.save(p);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el cliente: "+p.getDocumento(),ex);
        }
    }
    
    @Transactional
    @Override
    public void registrarDevolucion(int iditem) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
         long res = 0;
        try {
            res = daoItem.consultarCostoAlquiler(iditem,numdias);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el costo del alquiler ", ex);
        }
        return res;
    }
    
    @Transactional
    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Transactional
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            daoItem.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar un nuevo item: " + i.getId(), ex);
        }
    }
    
    @Transactional
    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
