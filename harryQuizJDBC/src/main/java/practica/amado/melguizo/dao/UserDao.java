package practica.amado.melguizo.dao;

import java.util.List;
import java.util.Optional;

import practica.amado.melguizo.model.User;

public interface UserDao {

	int count();

	int save(User user);

	int update(User user);

	int deleteById(Long id);

	List<User> findAll();

	List<User> findByNombreAndPuntos(String nombre, Integer puntos);

	Optional<User> findById(Long id);

	String getNameById(Long id);

}
