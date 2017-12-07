package isep.web.sakila.webapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isep.web.sakila.dao.repositories.StaffRepository;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.webapi.security.Login;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StaffRepository repo;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Staff staff = repo.findByEmail(email);
		if (staff == null) {
			throw new UsernameNotFoundException(email);
		}
		return new Login(staff.getStaffId(), staff.getEmail(), bCryptPasswordEncoder.encode(staff.getPassword()), staff.getProfiles());
	}
}
