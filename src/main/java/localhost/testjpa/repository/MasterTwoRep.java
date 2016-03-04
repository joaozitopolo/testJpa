/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.repository;

import localhost.testjpa.model.MasterTwo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author joaozito
 */
public interface MasterTwoRep extends CrudRepository<MasterTwo, Long> {
	
	@Query("select m from MasterTwo m left join fetch m.details where m.id = :id")
	@Override
    public MasterTwo findOne(@Param("id") Long id);
	
}
