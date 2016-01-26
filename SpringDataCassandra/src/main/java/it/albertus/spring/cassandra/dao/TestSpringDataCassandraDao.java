package it.albertus.spring.cassandra.dao;

import it.albertus.spring.cassandra.model.TestSpringDataCassandra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSpringDataCassandraDao extends CrudRepository<TestSpringDataCassandra, Integer> {

}
