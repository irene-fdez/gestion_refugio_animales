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

import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import es.uvigo.esei.mei.refugio.servicios.CiudadService;

@Controller
@RequestMapping("/ciudades")
public class CiudadController {
	@Autowired
	CiudadService ciudadService;

	@GetMapping
	public String prepararListarCiudades(Model modelo) {
		List<Ciudad> ciudades = ciudadService.buscarTodos();
		modelo.addAttribute("ciudades", ciudades);
		modelo.addAttribute("nombre", "");
		return "ciudad/listado_ciudades";
	}

	@PostMapping
	public String actualizarListarCiudad(@RequestParam(required = false) String nombre, Model modelo) {
		
		List<Ciudad> ciudades;
		if ((nombre != null) && !nombre.isEmpty()) {
			ciudades = ciudadService.buscarNombre(nombre);
		} else {
			ciudades = ciudadService.buscarTodos();
		}
		modelo.addAttribute("ciudades", ciudades);	
		return "ciudad/listado_ciudades";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoCiudad() {
		Ciudad ciudad = new Ciudad();
		List<Ciudad> ciudades = ciudadService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("ciudad", ciudad);
		result.addObject("ciudades", ciudades);
		result.addObject("esNuevo", true);
		result.setViewName("ciudad/editar_ciudad");
		return result;
	}

	@PostMapping("nuevo")
	public String crearCiudad(@Valid @ModelAttribute("ciudad") Ciudad ciudad, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			ciudadService.crear(ciudad);
			return "redirect:/ciudades";
		} else {
			modelo.addAttribute("path", "/nuevo");
            modelo.addAttribute("message", "No se ha podido insertar la ciudad");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/ciudades");
            return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarCiudad(@PathVariable("id") Long id, Model modelo) {
		try {
			Ciudad ciudad = ciudadService.buscarPorID(id);
			modelo.addAttribute("ciudad", ciudad);
			modelo.addAttribute("esNuevo", false);		
			return "ciudad/editar_ciudad";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", "/nuevo");
			modelo.addAttribute("error", "Ciudad no encontrada");
			modelo.addAttribute("return", "/ciudades");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarCiudad(@Valid @ModelAttribute Ciudad ciudad, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			ciudadService.modificar(ciudad);
			return "redirect:/ciudades";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "Error al actualizar la ciudad");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/ciudades");
            return "error";
		}
	}
	
	@GetMapping("{id}/detalle")
    public String verCiudad(@PathVariable("id") Long id, Model modelo) {
        Ciudad ciudad = ciudadService.buscarPorID(id);
        if (ciudad != null) {
            modelo.addAttribute(ciudad);
            modelo.addAttribute("return", "/ciudades");
            return "ciudad/detalle_ciudad";
        } else {
        	modelo.addAttribute("path", id +"/detalle");
            modelo.addAttribute("message", "No se ha podido encontrar el ciudad");
            modelo.addAttribute("return", "/ciudades");
            return "error_message";
        }
    }
	

    @GetMapping("{id}/eliminar")
    public String borrarCiudad(@PathVariable("id") Long id, Model modelo) {
        Ciudad ciudad = ciudadService.buscarPorID(id);
        if (ciudad != null) {
			ciudadService.eliminar(ciudad);
			return "redirect:/ciudades";
		} else {
			modelo.addAttribute("path", id + "/eliminar");
			modelo.addAttribute("mensajeError", "Ciudad no encontrada");
			modelo.addAttribute("error", "No se ha podido eliminar la ciudad");
            modelo.addAttribute("return", "/ciudad");
			return "error";
		}
    }
}
