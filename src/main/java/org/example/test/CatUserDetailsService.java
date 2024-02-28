package org.example.test;

import lombok.AllArgsConstructor;
import org.example.test.entity.Cat;
import org.example.test.repository.CatRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatUserDetailsService implements UserDetailsService {

    private CatRepository catRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cat cat = catRepository.findByUsername(username);
        if (cat == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CatUserDetails(cat);
    }
}
