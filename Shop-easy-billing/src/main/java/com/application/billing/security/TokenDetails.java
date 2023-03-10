package com.application.billing.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Configuration
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDetails {
    private String id;
}
