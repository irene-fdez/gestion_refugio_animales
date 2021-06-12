package es.uvigo.esei.mei.refugio.controladores;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uvigo.esei.mei.refugio.entidades.Acogida;
import es.uvigo.esei.mei.refugio.servicios.AcogidaService;
import es.uvigo.esei.mei.refugio.entidades.Centro;
import es.uvigo.esei.mei.refugio.servicios.CentroService;
import es.uvigo.esei.mei.refugio.entidades.Animal;
import es.uvigo.esei.mei.refugio.servicios.AnimalService;

@Controller
@RequestMapping("/acogidas")
public class AcogidaController {
	@Autowired
	AcogidaService acogidaService;
	
	@Autowired
	CentroService centroService;
	
	@Autowired
	AnimalService animalService;

	@GetMapping
	public String prepararListarAcogidas(Model modelo) {
		List<Acogida> acogidas = acogidaService.buscarTodos();
		
		System.out.print(acogidas.toString());
		modelo.addAttribute("acogidas", acogidas);
		modelo.addAttribute("nombre", "");
		return "acogida/listado_acogidas";
	}

	@PostMapping
	public String actualizarListarAcogida(@RequestParam(required = false) String tipoEntrada, Model modelo) {
		
		List<Acogida> acogidas;
		if ((tipoEntrada != null) && !tipoEntrada.isEmpty() ) {
			acogidas = acogidaService.buscarTipoEntrada(tipoEntrada);
		} else {
			acogidas = acogidaService.buscarTodos();
		}
		modelo.addAttribute("acogidas", acogidas);	
		return "acogida/listado_acogidas";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoAcogida() {
		Acogida acogida = new Acogida();
		List<Acogida> acogidas = acogidaService.buscarTodos();
		List<Centro> centros = centroService.buscarTodos();
		List<Animal> animales = animalService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("acogida", acogida);
		result.addObject("acogidas", acogidas);
		result.addObject("centros", centros);
		result.addObject("animales", animales);
		result.addObject("esNuevo", true);
		result.setViewName("acogida/editar_acogida");
		return result;
	}

	@PostMapping("nuevo")
	public String crearAcogida(@Valid @ModelAttribute("acogida") Acogida acogida, BindingResult resultado, Model modelo) {

		if (!resultado.hasErrors()) {
			acogidaService.crear(acogida);
			return "redirect:/acogidas";
		} else {
			modelo.addAttribute("path", "/nuevo");
            modelo.addAttribute("error", "No se ha podido insertar la acogida");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/acogidas");
            return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarAcogida(@PathVariable("id") Long id, Model modelo) {
		try {
			Acogida acogida = acogidaService.buscarPorID(id);
			List<Centro> centros = centroService.buscarTodos();
			List<Animal> animales = animalService.buscarTodos();
			
			modelo.addAttribute("acogida", acogida);
			modelo.addAttribute("centros", centros);
			modelo.addAttribute("animales", animales);
			modelo.addAttribute("esNuevo", false);		
			return "acogida/editar_acogida";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", id + "/editar");
            modelo.addAttribute("error", "Acogida no encontrada");
            modelo.addAttribute("return", "/acogidas");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarAcogida(@Valid @ModelAttribute Acogida acogida, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			acogidaService.modificar(acogida);
			return "redirect:/acogidas";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "Error al actualizar la acogida");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/acogidas");
            return "error";
		}
	}
	
	@GetMapping("{id}/detalle")
    public String verAcogida(@PathVariable("id") Long id, Model modelo) {
        Acogida acogida = acogidaService.buscarPorID(id);
        if (acogida != null) {
            modelo.addAttribute(acogida);
            modelo.addAttribute("return", "/acogidas");
            return "acogida/detalle_acogida";
        } else {
        	modelo.addAttribute("path",  id + "/detalle");
            modelo.addAttribute("message", "No se ha podido encontrar el acogida");
            modelo.addAttribute("return", "/acogidas");
            return "error";
        }
    }
	
    @GetMapping("{id}/eliminar")
    public String borrarAcogida(@PathVariable("id") Long id, Model modelo) {
        Acogida acogida = acogidaService.buscarPorID(id);
        if (acogida != null) {
			acogidaService.eliminar(acogida);
			return "redirect:/acogidas";
		} else {
			modelo.addAttribute("path", id + "/eliminar");
            modelo.addAttribute("error", "No se ha podido eliminar la acogida");
            modelo.addAttribute("return", "/acogidas");
			return "error";
		}
    }
}
