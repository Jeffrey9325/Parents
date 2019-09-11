package com.everis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "parents")
public class Parents implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @NotNull
   String id;

  String fullName;
  String gender;
    //@JsonFormat(pattern = "yyy-MM-dd")
  String dateofBirth;
  String typeofIdentificationDocument;
  String identificationDocumentNumber;  
    
}
