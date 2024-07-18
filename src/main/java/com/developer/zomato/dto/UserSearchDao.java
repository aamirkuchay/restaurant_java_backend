package com.developer.zomato.dto;

import com.developer.zomato.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchDao {
    private final EntityManager em;

    public List<User> findAllBySimpleQuery(String fullName,String email,String status){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        select * from user
        Root<User> root = criteriaQuery.from(User.class);

//        prepare WHERE clause
        Predicate fullnamePredicate = criteriaBuilder.like(root.get("fullName"),"%"+fullName+"%");
        Predicate emailPredicate = criteriaBuilder.like(root.get("email"),"%"+email+"%");
        Predicate statusPredicate = criteriaBuilder.like(root.get("status"),"%"+status+"%");
        Predicate orPredicate = criteriaBuilder.or(fullnamePredicate,emailPredicate);
//        select * from user where fullname like or email or status
       var andEmailPredicate =criteriaBuilder.and(orPredicate,statusPredicate);
        criteriaQuery.where(andEmailPredicate);

        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<User> findAllByCreteria(SearchRequest searchRequest){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if(searchRequest.getFullName() != null){
            Predicate fullNamePredicate = criteriaBuilder.like(root.get("fullName"),"%"+searchRequest.getFullName()+"%");
            predicates.add(fullNamePredicate);
        }
        if(searchRequest.getEmail() != null){
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"),"%"+searchRequest.getEmail()+"%");
            predicates.add(emailPredicate);
        }
        if(searchRequest.getStatus() != null){
            Predicate statusPredicate = criteriaBuilder.like(root.get("status"),"%"+searchRequest.getStatus()+"%");
            predicates.add(statusPredicate);
        }
        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
