package isep.web.sakila.dao.repositories;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.jpa.entities.Staff;
 
public interface StaffRepository extends CrudRepository<Staff, Integer>{
	@Transactional(readOnly=true)
	Staff findByEmail(String email);
}
 
