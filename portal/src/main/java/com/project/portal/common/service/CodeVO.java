package com.project.portal.common.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVO {
	private String detailCode;
	private String detailName;
	private String groupCode;
	private int detailOrder;
}
