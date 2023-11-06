package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.review.domian.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
