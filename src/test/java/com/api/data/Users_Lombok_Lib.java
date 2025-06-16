package com.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Users_Lombok_Lib {
	private String name;
	private String email;
	private String gender;
	private String status;
}


// Lombok is a Java library that provides annotations to simplify Java development
//by automating the generation of boilerplate code. Key features include automatic generation of getters, setters, equals, hashCode, and toString methods, 
//as well as a facility for automatic resource management.