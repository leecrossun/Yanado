package com.yanado.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yanado.dto.Favorite;
import com.yanado.dto.Product;
import com.yanado.dto.UserDTO;

@Mapper
public interface FavoriteDAO {

	void insertFavorite(Favorite favorite);

	void deleteFavorite(Favorite favorite);

	List<Favorite> findUserIdListByFavorite(Favorite favorite);
	
	List<UserDTO> findSimilarListByUser(UserDTO user);
	
}