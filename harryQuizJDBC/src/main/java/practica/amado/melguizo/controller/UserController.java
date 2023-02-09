package practica.amado.melguizo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import practica.amado.melguizo.dao.UserDao;
import practica.amado.melguizo.model.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userdao;

	// Codigo Quiz

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String portada() {
		return "portada";
	}

	@RequestMapping(value = "/pregunta1", method = RequestMethod.POST)
	public String p1(String nombre, HttpSession sesion) {
		sesion.setAttribute("nombre", nombre);
		System.out.println(nombre);
		return "pregunta1";
	}

	@RequestMapping(value = "/pregunta2", method = RequestMethod.POST)
	public String p2(String q1, HttpSession sesion) {
		sesion.setAttribute("q1", q1);
		System.out.println(q1);
		return "pregunta2";
	}

	@RequestMapping(value = "/pregunta3", method = RequestMethod.POST)
	public String p3(String q2, HttpSession sesion) {
		sesion.setAttribute("q2", q2);
		System.out.println(q2);
		return "pregunta3";
	}

	@RequestMapping(value = "/pregunta4", method = RequestMethod.POST)
	public String p4(String q3, HttpSession sesion) {
		sesion.setAttribute("q3", q3);
		System.out.println(q3);
		return "pregunta4";
	}

	@RequestMapping(value = "/pregunta5", method = RequestMethod.POST)
	public String p5(String q4, HttpSession sesion) {
		sesion.setAttribute("q4", q4);
		System.out.println(q4);
		return "pregunta5";
	}

	@RequestMapping(value = "/pregunta6", method = RequestMethod.POST)
	public String p6(String q5, HttpSession sesion) {
		sesion.setAttribute("q5", q5);
		System.out.println(q5);
		return "pregunta6";
	}

	@RequestMapping(value = "/pregunta7", method = RequestMethod.POST)
	public String p7(String q6, HttpSession sesion) {
		sesion.setAttribute("q6", q6);
		System.out.println(q6);
		return "pregunta7";

	}

	@RequestMapping(value = "/resultado", method = RequestMethod.POST)
	public String resultado(String q7, HttpSession sesion) {
		return calcularResultado(q7, sesion);
	}

	public String calcularResultado(String q7, HttpSession sesion) {
		int puntos = 0;
		String casa;
		String name = (String) sesion.getAttribute("nombre");
		sesion.setAttribute("q7", q7);
		System.out.println(q7);

		for (int i = 1; i < 8; i++) {
			if (sesion.getAttribute("q" + i) != null)
				puntos += Integer.parseInt(sesion.getAttribute("q" + i).toString());
		}
		System.out.println(puntos);

		if (puntos > 19) {
			casa = "Grifindor";
			System.out.println(casa);
			crearUsuario(null, name, casa, puntos);
			return "Grifindor";
		} else if (puntos > 17) {
			casa = "Hufflepuf";
			System.out.println(casa);
			crearUsuario(null, name, casa, puntos);
			return "Hufflepuf";
		} else if (puntos > 13) {
			casa = "Ravenclaw";
			System.out.println(casa);
			crearUsuario(null, name, casa, puntos);
			return "Ravenclaw";
		} else {
			casa = "Slitherin";
			System.out.println(casa);
			crearUsuario(null, name, casa, puntos);
			return "Slitherin";
		}

	}

	/// Codigo Clasificacion
	public String crearUsuario(Model modelo, @RequestParam String nombre, @RequestParam String casa,
			@RequestParam Integer puntos) {

		User user = new User(nombre, casa, puntos);
		userdao.save(user);

		return "redirect:/resultado";
	}

	@RequestMapping(value = "/clasificacion", method = RequestMethod.GET)
	public String index(Model modelo) {

		List<User> users = userdao.findAll();

		modelo.addAttribute("users", users);

		return "clasificacion";
	}

	@RequestMapping(value = "/nuevouser", method = RequestMethod.POST)
	public String crearUser(Model modelo, @RequestParam String nombre, @RequestParam String casa,
			@RequestParam Integer puntos) {

		User user = new User(nombre, casa, puntos);
		userdao.save(user);

		return "redirect:/clasificacion";
	}

	@RequestMapping(value = "/borraruser/{id}", method = RequestMethod.GET)
	public String borraruser(@PathVariable("id") long idUser) {

		userdao.deleteById(idUser);

		return "redirect:/clasificacion";
	}

	@RequestMapping(value = "/editaruser/{id}", method = RequestMethod.GET)
	public String editaruser(@PathVariable("id") long idUser, Model modelo) {

		Optional<User> user = userdao.findById(idUser);

		if (user == null) {
			return "clasificacion";

		}
		User u1 = user.get();

		modelo.addAttribute("user", u1);
		return "editaruser";
	}

	@RequestMapping(value = "/editaruser/{id}", method = RequestMethod.POST)
	public String modificaruser(@PathVariable("id") long idUser, @RequestParam String nombre, @RequestParam String casa,
			@RequestParam Integer puntos) {

		User user = new User(idUser, nombre, casa, puntos);
		userdao.update(user);

		return "redirect:/clasificacion";
	}

	// Destroy Session

	@RequestMapping(value = "/destroy", method = RequestMethod.POST)
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
