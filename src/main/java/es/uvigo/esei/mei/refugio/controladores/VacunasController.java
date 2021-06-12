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

import es.uvigo.esei.mei.refugio.entidades.Vacuna;
import es.uvigo.esei.mei.refugio.servicios.VacunaService;
import es.uvigo.esei.mei.refugio.entidades.Animal;
import es.uvigo.esei.mei.refugio.servicios.AnimalService;

@Controller
@RequestMapping("/vacunas")
public class VacunasController {
	@Autowired
	VacunaService vacunaService;
	
	@Autowired
	AnimalService animalService;

	@GetMapping
	public String prepararListarVacunas(Model modelo) {
		List<Vacuna> vacunas = vacunaService.buscarTodos();
		modelo.addAttribute("vacunas", vacunas);
		modelo.addAttribute("nombre", "");
		return "vacuna/listado_vacunas";
	}

	@PostMapping
	public String actualizarListarVacuna(@RequestParam(required = false) String tipo, Model modelo) {
		
		List<Vacuna> vacunas;
		if ((tipo != null) && !tipo.isEmpty() ) {
			vacunas = vacunaService.buscarTipo(tipo);
		} else {
			vacunas = vacunaService.buscarTodos();
		}
		modelo.addAttribute("vacunas", vacunas);	
		return "vacuna/listado_vacunas";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoVacuna() {
		Vacuna vacuna = new Vacuna();
		List<Vacuna> vacunas = vacunaService.buscarTodos();
		List<Animal> animales = animalService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("vacuna", vacuna);
		result.addObject("vacunas", vacunas);
		result.addObject("animales", animales);
		result.addObject("esNuevo", true);
		result.setViewName("vacuna/editar_vacuna");
		return result;
	}

	@PostMapping("nuevo")
	public String crearVacuna(@Valid @ModelAttribute("vacuna") Vacuna vacuna, BindingResult resultado, Model modelo) {

		if (!resultado.hasErrors()) {
			vacunaService.crear(vacuna);
			return "redirect:/vacunas";
		} else {
			modelo.addAttribute("path", "/nuevo");
            modelo.addAttribute("error", "No se ha podido insertar el vacuna");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/vacunas");
            return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarVacuna(@PathVariable("id") Long id, Model modelo) {
		try {
			Vacuna vacuna = vacunaService.buscarPorID(id);
			List<Animal> animales = animalService.buscarTodos();
			modelo.addAttribute("vacuna", vacuna);
			modelo.addAttribute("animales", animales);
			modelo.addAttribute("esNuevo", false);		
			return "vacuna/editar_vacuna";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", id+"/editar");
			modelo.addAttribute("error", "Vacuna no encontrada");
			modelo.addAttribute("return", "/vacunas");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarVacuna(@Valid @ModelAttribute Vacuna vacuna, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			vacunaService.modificar(vacuna);
			return "redirect:/vacunas";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "No se ha podido actualizar el vacuna");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/vacunas");
            return "error";
		}
	}
	
	@GetMapping("{id}/detalle")
    public String verVacuna(@PathVariable("id") Long id, Model modelo) {
        Vacuna vacuna = vacunaService.buscarPorID(id);
        if (vacuna != null) {
            modelo.addAttribute(vacuna);
            modelo.addAttribute("return", "/vacunas");
            return "vacuna/detalle_vacuna";
        } else {
        	modelo.addAttribute("path", id+ "/detalle");
            modelo.addAttribute("error", "No se ha podido encontrar el vacuna");
            modelo.addAttribute("return", "/vacunas");
            return "error";
        }
    }
	

    @GetMapping("{id}/eliminar")
    public String borrarVacuna(@PathVariable("id") Long id, Model modelo) {
        Vacuna vacuna = vacunaService.buscarPorID(id);
        if (vacuna != null) {
			vacunaService.eliminar(vacuna);
			return "redirect:/vacunas";
		} else {
			modelo.addAttribute("path", id+ "/eliminar");
			modelo.addAttribute("error", "Vacuna no encontrada");
			modelo.addAttribute("return", "/vacunas");
			return "error";
		}
    }
}
