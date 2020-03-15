package club.bugmakers.spring2go.clientresttemplate.user;

import club.bugmakers.spring2go.clientresttemplate.user.ClientUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ClientUser, Long> {

    Optional<ClientUser> findByUsername(String username);
}
