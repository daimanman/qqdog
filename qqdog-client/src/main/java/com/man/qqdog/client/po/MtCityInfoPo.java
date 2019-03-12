package com.man.qqdog.client.po;

public class MtCityInfoPo implements java.io.Serializable {
	
    
	public static final long serialVersionUID = 4123216516852207014L;

	public Integer id;

    public String name;

    public String pinyin;

    public String acronym;

    public String rank;

    public String firstchar;

    public String url;
    
    public Integer crawlFlag;
    
    public Integer centerFlag;
    
    

    public Integer getCrawlFlag() {
		return crawlFlag;
	}

	public void setCrawlFlag(Integer crawlFlag) {
		this.crawlFlag = crawlFlag;
	}

	public Integer getCenterFlag() {
		return centerFlag;
	}

	public void setCenterFlag(Integer centerFlag) {
		this.centerFlag = centerFlag;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym == null ? null : acronym.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getFirstchar() {
        return firstchar;
    }

    public void setFirstchar(String firstchar) {
        this.firstchar = firstchar == null ? null : firstchar.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}