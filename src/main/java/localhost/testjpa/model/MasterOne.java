/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

/**
 *
 * @author joaozito
 */
@Entity
@Data
public class MasterOne {

	@Id
	@GeneratedValue
	Long id;
	
	@Column(nullable = false, length = 80)
	String title;
	
	@OneToMany(mappedBy = "parent")
	private List<DetailOne> details;

	public MasterOne() {
	}

	public static MasterOne toMasterOne(String title) {
		MasterOne out = new MasterOne();
		out.title = title;
		out.details = new ArrayList<>();
		return out;
	}

}
