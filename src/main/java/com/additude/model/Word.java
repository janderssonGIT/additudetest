package com.additude.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WORD")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @Column(name = "synid")
    Long synid;


	@Column(name = "name")
    String name;
	
	public Word(Long synid, String name) {
		this.synid = synid;
		this.name = name;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getSynid() {
		return synid;
	}

	public void setSynid(Long synid) {
		this.synid = synid;
	}
}