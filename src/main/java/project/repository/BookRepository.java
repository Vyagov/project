package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
