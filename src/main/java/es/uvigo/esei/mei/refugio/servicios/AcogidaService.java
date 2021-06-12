package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Acogida;
import org.springframework.stereotype.Service;

@Service
public interface AcogidaService {

    public Acogida crear(Acogida acogida);

    public Acogida modificar(Acogida acogida);

    public void eliminar(Acogida acogida);

    public Acogida buscarPorID(Long id);

    public List<Acogida> buscarTodos();
    
    public List<Acogida> buscarTipoEntradaTipoSalida(String tipoEntrada, String tipoSalida);
    
    public List<Acogida> buscarTipoEntrada(String tipoEntrada);


}