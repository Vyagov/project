package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.entity.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    List<Author> findAll();

    Optional<Author> findById(String id);

    void deleteById(String id);

    Optional<Author> findByName(String name);
}
