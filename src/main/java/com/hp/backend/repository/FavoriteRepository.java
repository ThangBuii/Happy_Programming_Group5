package com.hp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.backend.entity.Favorite_Mentor;

import java.util.List;


@Repository
public interface FavoriteRepository extends JpaRepository<Favorite_Mentor,Integer>{
    @Query("Select f from Favorite_Mentor f where f.mentee_id = :mentee_id")
    List<Favorite_Mentor> findByMentee_id(@Param("mentee_id") int mentee_id);

    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
                   "FROM Favorite_Mentor f " +
                   "WHERE f.mentor_id = :mentor_id AND f.mentee_id = :mentee_id")
    boolean existsByMentorIdAndMenteeId(@Param("mentor_id") int mentor_id, @Param("mentee_id") int mentee_id);

    @Query("Select f from Favorite_Mentor f where f.mentee_id = :mentee_id and f.mentor_id = :mentor_id")
    Favorite_Mentor findByMentorMenteeId(int mentor_id, int mentee_id);
}
