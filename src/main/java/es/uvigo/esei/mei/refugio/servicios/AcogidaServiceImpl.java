package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.AcogidaDAO;
import es.uvigo.esei.mei.refugio.entidades.Acogida;
import es.uvigo.esei.mei.refugio.servicios.AcogidaService;
import java.util.Date;

@Service
public class AcogidaServiceImpl implements AcogidaService {

    @Autowired
    AcogidaDAO dao;

    @Override
    @Transactional
    public Acogida crear(Acogida acogida) {
        return dao.save(acogida);
    }

    @Override
    @Transactional
    public Acogida modificar(Acogida acogida) {
        return dao.save(acogida);
    }

    @Override
    @Transactional
    public void eliminar(Acogida acogida) {
        dao.delete(acogida);
    }

    @Override
    @Transactional(readOnly = true)
    public Acogida buscarPorID(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Acogida> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Acogida> buscarTipoEntradaTipoSalida(String tipoEntrada, String tipoSalida) {
        return dao.findByTipoEntradaTipoSalida(tipoEntrada, tipoSalida);
    }
    
    @Override
    public List<Acogida> buscarTipoEntrada(String tipoEntrada) {
        return dao.findByTipoEntrada(tipoEntrada);
    }

}
