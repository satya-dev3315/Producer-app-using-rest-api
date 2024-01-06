package com.aitt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Book {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //if getting a record, this nt required,for other CRUD operations its required
	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_price")
	private float bookPrice;
	
	//for soft delete
	private String activeSW;
	
	
}
