package com.everis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parents")
public class Parents implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	String id;
	
	String Nombrecompleto;
    String Genero;
    //@JsonFormat(pattern = "yyy-MM-dd")
    String Fechanacimiento;
    String Tipodocumentoidentificacion;
    String Numerodocumentoidentificacion;
    
}
