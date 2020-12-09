package com.bestseller.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bestseller.challenge.dto.GameMaxCreditContainer;
import com.bestseller.challenge.model.GamerInterest;

@Repository
public interface GamerInterestsRepository extends JpaRepository<GamerInterest, Long> {

	@Query("DELETE GamerInterest gi WHERE gi.gamer.id = :id")
	void deleteAllInterestUser(Long id);

	Optional<GamerInterest> findOneByNameAndGamer(String gameName, Long userId);

	// @formatter:off
	@Query(
			value = 
					"SELECT g.USER_ID gamerId, gi.name gameName, gi.level gamerLevel, g.credit credit "+
					"FROM GAMER_INTEREST gi, GAMER g, "+
						"(SELECT gi1.name gname ,gi1.level lvl, MAX(g1.credit) mxCredit " +
						"FROM GAMER_INTEREST gi1, GAMER g1 " +
						"WHERE gi1.GAMER_ID = g1.ID " +
						"GROUP BY gi1.name,gi1.level "+
						") maxScore " +
					"WHERE gi.GAMER_ID = g.ID and "+
						"gi.name = maxScore.gname and "+
						"gi.level = maxScore.lvl and "+
						"g.credit = maxScore.mxCredit "+
					"ORDER BY gi.level, gi.name"	
			,nativeQuery = true)
	// @formatter:on
	List<GameMaxCreditContainer> maximumCreditPerGamePerLevel();
	
}
