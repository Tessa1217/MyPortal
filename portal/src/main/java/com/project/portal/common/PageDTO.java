package com.project.portal.common;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageDTO {
	
	private int pageCount;
	private int startPage;
	private int endPage;
	private int realEnd;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	public PageDTO() {};
	
	public PageDTO(int total, int pageCount, Criteria cri) {
		this.total = total;
		this.cri = cri;
		this.pageCount = pageCount;
		
		this.endPage = (int) (Math.ceil(cri.getPageNum() * 1.0 / pageCount)) * pageCount;
		this.startPage = endPage - (pageCount - 1);
		
		realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));
		
		if (endPage > realEnd) {
			endPage = realEnd == 0 ? 1 : realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}

}
