/*
 * Copyright © 2019 Robert Bosch Engineering and Business Solutions Private Limited. All Rights Reserved.

  NOTICE:  All information contained herein is, and remains the property of Robert Bosch Engineering and Business Solutions Private Limited.
  Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from 
  Robert Bosch Engineering and Business Solutions Private Limited.
*/

package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {

    private String userId;

	private String userName;

    private Set<String> roles;
    
    private String emailId;
    
    private String patnerId;
}
