package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import dev.dima.betservice.models.OutcomeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReferenceMapper {

    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@NonNull final UUID id, @TargetType Class<T> type) {
        return entityManager.getReference(type, id);
    }

    @ObjectFactory
    public OutcomeType getOutcomeTypeByType(@NonNull final String type) {
        try {
            String query = "SELECT ot FROM OutcomeType ot WHERE ot.type = :type";
            TypedQuery<OutcomeType> typedQuery = entityManager.createQuery(query, OutcomeType.class);
            typedQuery.setParameter("type", type);
            return typedQuery.getSingleResult();
        } catch (Exception ignored) {}
        return null;
    }

    @ObjectFactory
    public Competitor getCompetitorByName(String name) {
        try {
            String query = "SELECT c FROM Competitor c WHERE c.name = :name";
            TypedQuery<Competitor> typedQuery = entityManager.createQuery(query, Competitor.class);
            typedQuery.setParameter("name", name);
            return typedQuery.getSingleResult();
        } catch (Exception ignored) {}
        return null;
    }

    @ObjectFactory
    public Discipline getDisciplineByName(@NonNull final String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Discipline> criteriaQuery = criteriaBuilder.createQuery(Discipline.class);
        Root<Discipline> root = criteriaQuery.from(Discipline.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Discipline> typedQuery = entityManager.createQuery(criteriaQuery);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }
}
