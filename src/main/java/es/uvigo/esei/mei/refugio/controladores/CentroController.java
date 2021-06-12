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

import es.uvigo.esei.mei.refugio.entidades.Centro;
import es.uvigo.esei.mei.refugio.servicios.CentroService;
import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import es.uvigo.esei.mei.refugio.servicios.CiudadService;

@Controller
@RequestMapping("/centros")
public class CentroController {
	@Autowired
	CentroService centroService;
	
	@Autowired
	CiudadService ciudadService;

	@GetMapping
	public String prepararListarCentros(Model modelo) {
		List<Centro> centros = centroService.buscarTodos();
		modelo.addAttribute("centros", centros);
		modelo.addAttribute("nombre", "");
		return "centro/listado_centros";
	}

	@PostMapping
	public String actualizarListarCentro(@RequestParam(required = false) String nombre, @RequestParam(required = false) String ciudad, Model modelo) {
		
		List<Centro> centros;
		if ((nombre != null) && !nombre.isEmpty() || (ciudad != null) && !ciudad.isEmpty()) {
			centros = centroService.buscarNombreCiudad(nombre, ciudad);
		} else {
			centros = centroService.buscarTodos();
		}
		modelo.addAttribute("centros", centros);	
		return "centro/listado_centros";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoCentro() {
		Centro centro = new Centro();
		List<Centro> centros = centroService.buscarTodos();
		List<Ciudad> ciudades = ciudadService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("centro", centro);
		result.addObject("centros", centros);
		result.addObject("ciudades", ciudades);
		result.addObject("esNuevo", true);
		result.setViewName("centro/editar_centro");
		return result;
	}

	@PostMapping("nuevo")
	public String crearCentro(@Valid @ModelAttribute("centro") Centro centro, BindingResult resultado, Model modelo) {

		if (!resultado.hasErrors()) {
			centroService.crear(centro);
			return "redirect:/centros";
		} else {
			modelo.addAttribute("path", "/nuevo");
            modelo.addAttribute("error", "No se ha podido insertar el centro");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/centros");
            return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarCentro(@PathVariable("id") Long id, Model modelo) {
		try {
			Centro centro = centroService.buscarPorID(id);
			modelo.addAttribute("centro", centro);
			modelo.addAttribute("esNuevo", false);		
			return "centro/editar_centro";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", id+"/editar");
			modelo.addAttribute("error", "Centro no encontrado");
			modelo.addAttribute("return", "/centros");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarCentro(@Valid @ModelAttribute Centro centro, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			centroService.modificar(centro);
			return "redirect:/centros";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "Error al actualizar el centro");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/acogidas");
            return "error";
		}
	}
	
	@GetMapping("{id}/detalle")
    public String verCentro(@PathVariable("id") Long id, Model modelo) {
        Centro centro = centroService.buscarPorID(id);
        if (centro != null) {
            modelo.addAttribute(centro);
            modelo.addAttribute("return", "/centros");
            return "centro/detalle_centro";
        } else {
        	modelo.addAttribute("path", id +"/detalle");
            modelo.addAttribute("message", "No se ha podido encontrar el centro");
            modelo.addAttribute("return", "/centros");
            return "error";
        }
    }
	

    @GetMapping("{id}/eliminar")
    public String borrarCentro(@PathVariable("id") Long id, Model modelo) {
        Centro centro = centroService.buscarPorID(id);
        if (centro != null) {
			centroService.eliminar(centro);
			return "redirect:/centros";
		} else {
			modelo.addAttribute("path", id +"/eliminar");
			modelo.addAttribute("mensajeError", "Centro no encontrada");
			modelo.addAttribute("return", "/centros");
			return "error";
		}
    }
}
