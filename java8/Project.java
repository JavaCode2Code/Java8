package java8;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

class Project{

	private String projectCode;
	private String name ;
	private String client;
	private String buLeadName;
}