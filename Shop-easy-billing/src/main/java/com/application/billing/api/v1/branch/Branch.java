package com.application.billing.api.v1.branch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    private String id;
    private String name;
    private String shortName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String companyId;
    @JsonFormat( pattern="dd-MMM-yyyy hh:mm:ssa")
    private LocalDateTime createdOn;
    @JsonFormat( pattern="dd-MMM-yyyy hh:mm:ssa")
    private LocalDateTime updatedOn;

}
