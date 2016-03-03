/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.repository;

import localhost.testjpa.model.MasterOne;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author joaozito
 */
public interface MasterOneRep extends CrudRepository<MasterOne, Long> {
	
	@Query("select m from MasterOne m left join fetch m.details where m.id = :id")
	@Override
    public MasterOne findOne(@Param("id") Long id);
	
}
