package com.application.billing.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserDetails {
    private String id;
    private String companyId;
    private String branchId;
}
