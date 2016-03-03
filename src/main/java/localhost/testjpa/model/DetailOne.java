/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author joaozito
 */
@Entity
@Data
public class DetailOne {
	
	@Id
	@GeneratedValue
	Long id;
	
	@ManyToOne
	MasterOne parent;
	
	
	@Column(nullable = false, length = 80)
	String title;

	public static DetailOne toDetailOne(MasterOne parent, String title) {
		DetailOne out = new DetailOne();
		out.parent = parent;
		out.title = title;
		return out;
	}
	
	public static DetailOne toDetailOne(MasterOne parent, Long id, String title) {
		DetailOne out = new DetailOne();
		out.parent = parent;
		out.id = id;
		out.title = title;
		return out;
	}
	
}
