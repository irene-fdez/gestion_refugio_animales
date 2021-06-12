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

import es.uvigo.esei.mei.refugio.entidades.Persona;
import es.uvigo.esei.mei.refugio.servicios.PersonaService;
import es.uvigo.esei.mei.refugio.entidades.Centro;
import es.uvigo.esei.mei.refugio.servicios.CentroService;

@Controller
@RequestMapping("/personas")
public class PersonasController {
	@Autowired
	PersonaService personaService;
	
	@Autowired
	CentroService centroService;

	@GetMapping
	public String prepararListarPersonas(Model modelo) {
		List<Persona> personas = personaService.buscarTodos();
		modelo.addAttribute("personas", personas);
		modelo.addAttribute("nombre", "");
		return "persona/listado_personas";
	}

	@PostMapping
	public String actualizarListarPersona(@RequestParam(required = false) String nombre, @RequestParam(required = false) String especialidad, Model modelo) {

		List<Persona> personas;
		if ((nombre != null) && !nombre.isEmpty() || (especialidad != null) && !especialidad.isEmpty() ) {
			personas = personaService.buscarNombreEspecialidad(nombre, especialidad);
		} else {
			personas = personaService.buscarTodos();
		}
		modelo.addAttribute("personas", personas);
		return "persona/listado_personas";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoPersona() {
		Persona persona = new Persona();
		List<Persona> personas = personaService.buscarTodos();
		List<Centro> centros = centroService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("persona", persona);
		result.addObject("personas", personas);
		result.addObject("centros", centros);
		result.addObject("esNuevo", true);
		result.setViewName("persona/editar_persona");
		return result;
	}

	@PostMapping("nuevo")
	public String crearPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult resultado, Model modelo) {
		System.out.print(persona);
		if (!resultado.hasErrors()) {
			personaService.crear(persona);
			return "redirect:/personas";
		} else {
			modelo.addAttribute("path", "/nuevo");
			modelo.addAttribute("error", "No se ha podido insertar la persona");
			modelo.addAttribute("binding_result", resultado.toString());
			modelo.addAttribute("return", "/personas");
			return "error";
		}
	}

	@GetMapping("{dni}")
	public String prepararEditarPersona(@PathVariable("dni") String dni, Model modelo) {
		try {
			Persona persona = personaService.buscarPorDNI(dni);
			List<Centro> centros = centroService.buscarTodos();
			
			modelo.addAttribute("persona", persona);
			modelo.addAttribute("centros", centros);
			modelo.addAttribute("esNuevo", false);
			return "persona/editar_persona";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", dni +"/editar");
			modelo.addAttribute("error", "Persona no encontrada");
			modelo.addAttribute("return", "/personas");
			return "error";
		}
	}

	@PostMapping("{dni}")
	public String actualizarPersona(@Valid @ModelAttribute Persona persona, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			personaService.modificar(persona);
			return "redirect:/personas";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "Error al actualizar el usuario");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/personas");
            return "error";
		}
	}

	@GetMapping("{dni}/detalle")
	public String verPersona(@PathVariable("dni") String dni, Model modelo) {
		Persona persona = personaService.buscarPorDNI(dni);
		if (persona != null) {
			modelo.addAttribute(persona);
			modelo.addAttribute("return", "/personas");
			return "persona/detalle_persona";
		} else {
			modelo.addAttribute("path",  dni + "/detalle");
			modelo.addAttribute("error", "No se ha podido encontrar el persona");
			modelo.addAttribute("return", "/personas");
			return "error";
		}
	}

	@GetMapping("{dni}/eliminar")
	public String borrarPersona(@PathVariable("dni") String dni, Model modelo) {
		Persona persona = personaService.buscarPorDNI(dni);
		if (persona != null) {
			personaService.eliminar(persona);
			return "redirect:/personas";
		} else {
			modelo.addAttribute("path", dni + "/eliminar");
			modelo.addAttribute("error", "Persona no encontrada");
			modelo.addAttribute("return", "/personas");
			return "error";
		}
	}
}
