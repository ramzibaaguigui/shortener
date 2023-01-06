package com.ramzanlabs.shortener.awesomeshortener.link;


import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
