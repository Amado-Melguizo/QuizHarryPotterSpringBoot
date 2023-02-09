package practica.amado.melguizo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import practica.amado.melguizo.model.User;

@Repository
public class UserJdbc implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

	@Override
	public int save(User user) {
		return jdbcTemplate.update("insert into users (nombre, casa, puntos) values(?,?,?)", user.getNombre(),
				user.getCasa(),
				user.getPuntos());
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update("update users set puntos = ? where id = ?", user.getPuntos(), user.getId());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete from users where id = ?", id);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users", (rs, rowNum) -> new User(rs.getLong("id"),
				rs.getString("nombre"), rs.getString("casa"), rs.getInt("puntos")));
	}

	// jdbcTemplate.queryForObject, populates a single object
	@Override
	public Optional<User> findById(Long id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id },
				(rs, rowNum) -> Optional.of(new User(rs.getLong("id"), rs.getString("nombre"), rs.getString("casa"),
						rs.getInt("puntos"))));
	}

	@Override
	public List<User> findByNombreAndPuntos(String nombre, Integer puntos) {
		return jdbcTemplate.query("select * from users where nombre like ? and puntos <= ?",
				new Object[] { "%" + nombre + "%", puntos },
				(rs, rowNum) -> new User(rs.getLong("id"), rs.getString("nombre"), rs.getString("casa"),rs.getInt("puntos")));
	}

	@Override
	public String getNameById(Long id) {
		return jdbcTemplate.queryForObject("select nombre from users where id = ?", new Object[] { id }, String.class);
	}

}
