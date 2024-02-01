package com.takeo.entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catid")
    private Long catid;

    @Column(name = "cname")
    private String cname;

    
    @ManyToMany
	@JoinTable(
	    name = "categoriesPosts",
	    joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "catId"),
	    inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "pid")
	)
	List<Post> categoriesPosts = new ArrayList<>();


	public Long getCatid() {
		return catid;
	}


	public void setCatid(Long catid) {
		this.catid = catid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public List<Post> getCategoriesPosts() {
		return categoriesPosts;
	}


	public void setCategoriesPosts(List<Post> categoriesPosts) {
		this.categoriesPosts = categoriesPosts;
	}
    
    
    
    
    
}
