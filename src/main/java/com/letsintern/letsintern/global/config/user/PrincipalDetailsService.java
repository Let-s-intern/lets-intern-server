package com.letsintern.letsintern.global.config.user;


import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(user -> createUser(id, user))
                .orElseThrow(() -> new UsernameNotFoundException(id + " -> DB에서 찾을 수 없음"));
    }

    private PrincipalDetails createUser(Long id, User user) {
        return new PrincipalDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
