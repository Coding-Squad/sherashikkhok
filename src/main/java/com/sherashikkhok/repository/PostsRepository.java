package com.sherashikkhok.repository;

import com.sherashikkhok.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{

	/*@Query("SELECT p FROM Posts p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
	List<Posts> findLatest5Posts(Pageable pageable);
	
	Posts findByUsersEmail(String email);*/
	
	//List<Posts> findByAuthorId(Long id);
	
	
	
}
