package org.esosa.api.backend.repository;

import org.esosa.api.backend.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
