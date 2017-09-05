package cn.xinbee.crawler.data.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import cn.xinbee.crawler.data.jackson.DataView;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Interface for auditable entities. Allows storing and retrieving creation and modification information. The changing
 * instance (typically some user) is to be defined by a generics definition.
 *
 * @param <ID> the type of the audited type's identifier
 * @author Xu Cheng
 */
@MappedSuperclass
public abstract class AbstractEntityAuditable<ID extends Serializable> extends AbstractEntity<ID> {

	private static final long serialVersionUID = 7479587631743834284L;
	
	@JsonView(DataView.Basic.class)
	private DateTime createdDate;
	@JsonView(DataView.Basic.class)
    private DateTime lastModifiedDate;
    
    /**
	 * Returns the creation date of the entity.
	 * 
	 * @return the createdDate
	 */
    @CreatedDate
	public DateTime getCreatedDate() {
		return createdDate;
	}
	
    /**
	 * Sets the creation date of the entity.
	 * 
	 * @param creationDate the creation date to set
	 */
	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Returns the date of the last modification.
	 * 
	 * @return the lastModifiedDate
	 */
	@LastModifiedDate
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	/**
	 * Sets the date of the last modification.
	 * 
	 * @param lastModifiedDate the date of the last modification to set
	 */
	public void setLastModifiedDate(final DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
