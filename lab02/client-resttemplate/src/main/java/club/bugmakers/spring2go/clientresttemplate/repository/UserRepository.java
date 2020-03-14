package club.bugmakers.spring2go.clientresttemplate.repository;

import club.bugmakers.spring2go.clientresttemplate.bean.po.ClientUserPO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ClientUserPO, Long> {

    Optional<ClientUserPO> findByUsername(String username);
}
