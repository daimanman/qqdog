package com.man.qqdog.client.po;

public class MtBtPo {
    public Long id;

    public String title;

    public String tag;

    public String hash;

    public String dmlDate;

    public String link;

    public String size;

    public String sortName;

    public String torrent;

    public Integer tid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getDmlDate() {
        return dmlDate;
    }

    public void setDmlDate(String dmlDate) {
        this.dmlDate = dmlDate == null ? null : dmlDate.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName == null ? null : sortName.trim();
    }

    public String getTorrent() {
        return torrent;
    }

    public void setTorrent(String torrent) {
        this.torrent = torrent == null ? null : torrent.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}