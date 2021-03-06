package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for CtaFollow that maps the structure from the database entities.
 * This model will carry the data in other sections
 * CtaFollow refers to the CTA follow-up records
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/23
 */
@Entity
public class CtaFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String operation;

    private String type;

    private Date contactDate;

    private Date nextDate;

    private Integer contactId;

    private Integer staffId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getType() {
        return type;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public Integer getContactId() {
        return contactId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
