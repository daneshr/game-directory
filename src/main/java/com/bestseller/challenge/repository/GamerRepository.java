package com.bestseller.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.model.GamerInterest;
import com.bestseller.challenge.model.Geography;
import com.bestseller.challenge.model.Level;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Long> {

	Optional<Gamer> findOneByUserId(String userId);
	
	@Query(
			"SELECT g FROM Gamer g inner join GamerInterest gi on g.id = gi.gamer.id "+
					"WHERE gi.name = :gameName "+
						"and (:level is null or gi.level = :level) "+
						"and (:geography is null or g.geography = :geography) "
					)
	List<Gamer> searchForGamers(@Param("gameName")String gameName,@Param("level")Level level,@Param("geography")Geography geography,Pageable pageable );

	List<Gamer> findAll(Specification<GamerInterest> where);



}
