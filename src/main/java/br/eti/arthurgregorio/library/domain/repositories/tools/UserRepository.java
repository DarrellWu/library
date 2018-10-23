package br.eti.arthurgregorio.library.domain.repositories.tools;

import br.eti.arthurgregorio.library.domain.model.entities.tools.StoreType;
import br.eti.arthurgregorio.library.domain.model.entities.tools.User;
import br.eti.arthurgregorio.library.domain.model.entities.tools.User_;
import br.eti.arthurgregorio.library.domain.repositories.DefaultRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Optional;

/**
 * The {@link User} accounts repository
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 28/12/2017
 */
@Repository
public interface UserRepository extends DefaultRepository<User> {

    /**
     * Find an {@link User} by the email address
     *
     * @param email the {@link User} email address to find
     * @return an {@link Optional} of the {@link User}
     */
    Optional<User> findOptionalByEmail(String email);

    /**
     * Find an {@link User} by the email address and the {@link StoreType}
     *
     * @param email the {@link User} email address to find
     * @param storeType the enum value of {@link StoreType}
     * @return an {@link Optional} of the {@link User}
     */
    Optional<User> findOptionalByEmailAndStoreType(String email, StoreType storeType);

    /**
     * Find an {@link User} by the username
     *
     * @param username the username to find the {@link User} object
     * @return an {@link Optional} of the {@link User}
     */
    Optional<User> findOptionalByUsername(String username);

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    default SingularAttribute<User, Boolean> getEntityStateProperty() {
        return User_.active;
    }

    /**
     * {@inheritDoc}
     *
     * @param filter
     * @return
     */
    @Override
    default Criteria<User, User> getRestrictions(String filter) {
        return criteria()
                .likeIgnoreCase(User_.name, filter)
                .likeIgnoreCase(User_.username, filter)
                .likeIgnoreCase(User_.email, filter);
    }
}
