package club.bugmakers.spring2go.clientresttemplate.security;

import club.bugmakers.spring2go.clientresttemplate.user.ClientUser;
import club.bugmakers.spring2go.clientresttemplate.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientUser> optionalClientUser = userRepository.findByUsername(username);
        if (!optionalClientUser.isPresent()) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        return new ClientUserDetails(optionalClientUser.get());
    }
}
