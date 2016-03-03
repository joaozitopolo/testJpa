/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.repository;

import localhost.testjpa.model.DetailOne;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author joaozito
 */
public interface DetailOneRep extends CrudRepository<DetailOne, Long> {
	
}
