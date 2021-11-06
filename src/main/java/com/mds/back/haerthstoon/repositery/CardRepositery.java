package com.mds.back.haerthstoon.repositery;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.mds.back.haerthstoon.domain.Card;
import com.mds.back.haerthstoon.domain.Card.Kingdom;

public interface CardRepositery extends CrudRepository<Card, Long> {
	List<Card> findByKingdom(Kingdom kingdom);
	Card findById(long id);
}
