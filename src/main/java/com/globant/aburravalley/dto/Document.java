package com.globant.aburravalley.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto documento para crear el Dao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
  private String number;
  private DocumentType documentType;
  private Date issueDate;
  private Date expirationDate;
  private String issuePlace;
  private String extraInfo;
}
