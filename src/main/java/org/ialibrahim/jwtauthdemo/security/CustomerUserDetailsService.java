package org.ialibrahim.jwtauthdemo.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ialibrahim.jwtauthdemo.dao.CustomerEntity;
import org.ialibrahim.jwtauthdemo.dao.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customerUserDetailsService")
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            CustomerEntity dbUser = getUserFromDB(username);
            User user = modelMapper.map(dbUser, User.class);
            user.getAuthorities().add(AccessAuthority.CUSTOMER);

            return user;
        } catch (EntityNotFoundException ex) {
            throw new UsernameNotFoundException("User with username '" + username + "' not found");
        }
    }

    private CustomerEntity getUserFromDB(String username) {

        return customerRepository.getByUsername(username);
    }

}
