package com.kuan.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Voice")
public class Voice {
    @Id
    @Column(name="rowkey")
    private String rowkey;

    @Column(name="region")
    private String region;

    @Column(name="translation")
    private String translation;

    @Column(name="content")
    private byte[] content;

    public String getRowKey() {
        return rowkey;
    }

    public void setRowKey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
