package com.man.qqdog.client.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuserInfoPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuserInfoPoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNull() {
            addCriterion("sex_type is null");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNotNull() {
            addCriterion("sex_type is not null");
            return (Criteria) this;
        }

        public Criteria andSexTypeEqualTo(String value) {
            addCriterion("sex_type =", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotEqualTo(String value) {
            addCriterion("sex_type <>", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThan(String value) {
            addCriterion("sex_type >", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sex_type >=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThan(String value) {
            addCriterion("sex_type <", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThanOrEqualTo(String value) {
            addCriterion("sex_type <=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLike(String value) {
            addCriterion("sex_type like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotLike(String value) {
            addCriterion("sex_type not like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeIn(List<String> values) {
            addCriterion("sex_type in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotIn(List<String> values) {
            addCriterion("sex_type not in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeBetween(String value1, String value2) {
            addCriterion("sex_type between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotBetween(String value1, String value2) {
            addCriterion("sex_type not between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(String value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(String value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(String value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(String value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(String value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(String value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLike(String value) {
            addCriterion("age like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotLike(String value) {
            addCriterion("age not like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<String> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<String> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(String value1, String value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(String value1, String value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andBirthyearIsNull() {
            addCriterion("birthyear is null");
            return (Criteria) this;
        }

        public Criteria andBirthyearIsNotNull() {
            addCriterion("birthyear is not null");
            return (Criteria) this;
        }

        public Criteria andBirthyearEqualTo(String value) {
            addCriterion("birthyear =", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotEqualTo(String value) {
            addCriterion("birthyear <>", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearGreaterThan(String value) {
            addCriterion("birthyear >", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearGreaterThanOrEqualTo(String value) {
            addCriterion("birthyear >=", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearLessThan(String value) {
            addCriterion("birthyear <", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearLessThanOrEqualTo(String value) {
            addCriterion("birthyear <=", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearLike(String value) {
            addCriterion("birthyear like", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotLike(String value) {
            addCriterion("birthyear not like", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearIn(List<String> values) {
            addCriterion("birthyear in", values, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotIn(List<String> values) {
            addCriterion("birthyear not in", values, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearBetween(String value1, String value2) {
            addCriterion("birthyear between", value1, value2, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotBetween(String value1, String value2) {
            addCriterion("birthyear not between", value1, value2, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNull() {
            addCriterion("bloodtype is null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNotNull() {
            addCriterion("bloodtype is not null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeEqualTo(String value) {
            addCriterion("bloodtype =", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotEqualTo(String value) {
            addCriterion("bloodtype <>", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThan(String value) {
            addCriterion("bloodtype >", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThanOrEqualTo(String value) {
            addCriterion("bloodtype >=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThan(String value) {
            addCriterion("bloodtype <", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThanOrEqualTo(String value) {
            addCriterion("bloodtype <=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLike(String value) {
            addCriterion("bloodtype like", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotLike(String value) {
            addCriterion("bloodtype not like", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIn(List<String> values) {
            addCriterion("bloodtype in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotIn(List<String> values) {
            addCriterion("bloodtype not in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeBetween(String value1, String value2) {
            addCriterion("bloodtype between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotBetween(String value1, String value2) {
            addCriterion("bloodtype not between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCareerIsNull() {
            addCriterion("career is null");
            return (Criteria) this;
        }

        public Criteria andCareerIsNotNull() {
            addCriterion("career is not null");
            return (Criteria) this;
        }

        public Criteria andCareerEqualTo(String value) {
            addCriterion("career =", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerNotEqualTo(String value) {
            addCriterion("career <>", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerGreaterThan(String value) {
            addCriterion("career >", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerGreaterThanOrEqualTo(String value) {
            addCriterion("career >=", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerLessThan(String value) {
            addCriterion("career <", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerLessThanOrEqualTo(String value) {
            addCriterion("career <=", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerLike(String value) {
            addCriterion("career like", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerNotLike(String value) {
            addCriterion("career not like", value, "career");
            return (Criteria) this;
        }

        public Criteria andCareerIn(List<String> values) {
            addCriterion("career in", values, "career");
            return (Criteria) this;
        }

        public Criteria andCareerNotIn(List<String> values) {
            addCriterion("career not in", values, "career");
            return (Criteria) this;
        }

        public Criteria andCareerBetween(String value1, String value2) {
            addCriterion("career between", value1, value2, "career");
            return (Criteria) this;
        }

        public Criteria andCareerNotBetween(String value1, String value2) {
            addCriterion("career not between", value1, value2, "career");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andHcIsNull() {
            addCriterion("hc is null");
            return (Criteria) this;
        }

        public Criteria andHcIsNotNull() {
            addCriterion("hc is not null");
            return (Criteria) this;
        }

        public Criteria andHcEqualTo(String value) {
            addCriterion("hc =", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcNotEqualTo(String value) {
            addCriterion("hc <>", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcGreaterThan(String value) {
            addCriterion("hc >", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcGreaterThanOrEqualTo(String value) {
            addCriterion("hc >=", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcLessThan(String value) {
            addCriterion("hc <", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcLessThanOrEqualTo(String value) {
            addCriterion("hc <=", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcLike(String value) {
            addCriterion("hc like", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcNotLike(String value) {
            addCriterion("hc not like", value, "hc");
            return (Criteria) this;
        }

        public Criteria andHcIn(List<String> values) {
            addCriterion("hc in", values, "hc");
            return (Criteria) this;
        }

        public Criteria andHcNotIn(List<String> values) {
            addCriterion("hc not in", values, "hc");
            return (Criteria) this;
        }

        public Criteria andHcBetween(String value1, String value2) {
            addCriterion("hc between", value1, value2, "hc");
            return (Criteria) this;
        }

        public Criteria andHcNotBetween(String value1, String value2) {
            addCriterion("hc not between", value1, value2, "hc");
            return (Criteria) this;
        }

        public Criteria andHpIsNull() {
            addCriterion("hp is null");
            return (Criteria) this;
        }

        public Criteria andHpIsNotNull() {
            addCriterion("hp is not null");
            return (Criteria) this;
        }

        public Criteria andHpEqualTo(String value) {
            addCriterion("hp =", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotEqualTo(String value) {
            addCriterion("hp <>", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThan(String value) {
            addCriterion("hp >", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpGreaterThanOrEqualTo(String value) {
            addCriterion("hp >=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThan(String value) {
            addCriterion("hp <", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLessThanOrEqualTo(String value) {
            addCriterion("hp <=", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpLike(String value) {
            addCriterion("hp like", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotLike(String value) {
            addCriterion("hp not like", value, "hp");
            return (Criteria) this;
        }

        public Criteria andHpIn(List<String> values) {
            addCriterion("hp in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotIn(List<String> values) {
            addCriterion("hp not in", values, "hp");
            return (Criteria) this;
        }

        public Criteria andHpBetween(String value1, String value2) {
            addCriterion("hp between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andHpNotBetween(String value1, String value2) {
            addCriterion("hp not between", value1, value2, "hp");
            return (Criteria) this;
        }

        public Criteria andHcoIsNull() {
            addCriterion("hco is null");
            return (Criteria) this;
        }

        public Criteria andHcoIsNotNull() {
            addCriterion("hco is not null");
            return (Criteria) this;
        }

        public Criteria andHcoEqualTo(String value) {
            addCriterion("hco =", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoNotEqualTo(String value) {
            addCriterion("hco <>", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoGreaterThan(String value) {
            addCriterion("hco >", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoGreaterThanOrEqualTo(String value) {
            addCriterion("hco >=", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoLessThan(String value) {
            addCriterion("hco <", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoLessThanOrEqualTo(String value) {
            addCriterion("hco <=", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoLike(String value) {
            addCriterion("hco like", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoNotLike(String value) {
            addCriterion("hco not like", value, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoIn(List<String> values) {
            addCriterion("hco in", values, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoNotIn(List<String> values) {
            addCriterion("hco not in", values, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoBetween(String value1, String value2) {
            addCriterion("hco between", value1, value2, "hco");
            return (Criteria) this;
        }

        public Criteria andHcoNotBetween(String value1, String value2) {
            addCriterion("hco not between", value1, value2, "hco");
            return (Criteria) this;
        }

        public Criteria andMarriageIsNull() {
            addCriterion("marriage is null");
            return (Criteria) this;
        }

        public Criteria andMarriageIsNotNull() {
            addCriterion("marriage is not null");
            return (Criteria) this;
        }

        public Criteria andMarriageEqualTo(String value) {
            addCriterion("marriage =", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotEqualTo(String value) {
            addCriterion("marriage <>", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageGreaterThan(String value) {
            addCriterion("marriage >", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageGreaterThanOrEqualTo(String value) {
            addCriterion("marriage >=", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageLessThan(String value) {
            addCriterion("marriage <", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageLessThanOrEqualTo(String value) {
            addCriterion("marriage <=", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageLike(String value) {
            addCriterion("marriage like", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotLike(String value) {
            addCriterion("marriage not like", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageIn(List<String> values) {
            addCriterion("marriage in", values, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotIn(List<String> values) {
            addCriterion("marriage not in", values, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageBetween(String value1, String value2) {
            addCriterion("marriage between", value1, value2, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotBetween(String value1, String value2) {
            addCriterion("marriage not between", value1, value2, "marriage");
            return (Criteria) this;
        }

        public Criteria andMsgNumIsNull() {
            addCriterion("msg_num is null");
            return (Criteria) this;
        }

        public Criteria andMsgNumIsNotNull() {
            addCriterion("msg_num is not null");
            return (Criteria) this;
        }

        public Criteria andMsgNumEqualTo(Integer value) {
            addCriterion("msg_num =", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumNotEqualTo(Integer value) {
            addCriterion("msg_num <>", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumGreaterThan(Integer value) {
            addCriterion("msg_num >", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_num >=", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumLessThan(Integer value) {
            addCriterion("msg_num <", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumLessThanOrEqualTo(Integer value) {
            addCriterion("msg_num <=", value, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumIn(List<Integer> values) {
            addCriterion("msg_num in", values, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumNotIn(List<Integer> values) {
            addCriterion("msg_num not in", values, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumBetween(Integer value1, Integer value2) {
            addCriterion("msg_num between", value1, value2, "msgNum");
            return (Criteria) this;
        }

        public Criteria andMsgNumNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_num not between", value1, value2, "msgNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumIsNull() {
            addCriterion("emot_num is null");
            return (Criteria) this;
        }

        public Criteria andEmotNumIsNotNull() {
            addCriterion("emot_num is not null");
            return (Criteria) this;
        }

        public Criteria andEmotNumEqualTo(Integer value) {
            addCriterion("emot_num =", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumNotEqualTo(Integer value) {
            addCriterion("emot_num <>", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumGreaterThan(Integer value) {
            addCriterion("emot_num >", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("emot_num >=", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumLessThan(Integer value) {
            addCriterion("emot_num <", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumLessThanOrEqualTo(Integer value) {
            addCriterion("emot_num <=", value, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumIn(List<Integer> values) {
            addCriterion("emot_num in", values, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumNotIn(List<Integer> values) {
            addCriterion("emot_num not in", values, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumBetween(Integer value1, Integer value2) {
            addCriterion("emot_num between", value1, value2, "emotNum");
            return (Criteria) this;
        }

        public Criteria andEmotNumNotBetween(Integer value1, Integer value2) {
            addCriterion("emot_num not between", value1, value2, "emotNum");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andPhotoNumIsNull() {
            addCriterion("photo_num is null");
            return (Criteria) this;
        }

        public Criteria andPhotoNumIsNotNull() {
            addCriterion("photo_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoNumEqualTo(Integer value) {
            addCriterion("photo_num =", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumNotEqualTo(Integer value) {
            addCriterion("photo_num <>", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumGreaterThan(Integer value) {
            addCriterion("photo_num >", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("photo_num >=", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumLessThan(Integer value) {
            addCriterion("photo_num <", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumLessThanOrEqualTo(Integer value) {
            addCriterion("photo_num <=", value, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumIn(List<Integer> values) {
            addCriterion("photo_num in", values, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumNotIn(List<Integer> values) {
            addCriterion("photo_num not in", values, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumBetween(Integer value1, Integer value2) {
            addCriterion("photo_num between", value1, value2, "photoNum");
            return (Criteria) this;
        }

        public Criteria andPhotoNumNotBetween(Integer value1, Integer value2) {
            addCriterion("photo_num not between", value1, value2, "photoNum");
            return (Criteria) this;
        }

        public Criteria andImgNumIsNull() {
            addCriterion("img_num is null");
            return (Criteria) this;
        }

        public Criteria andImgNumIsNotNull() {
            addCriterion("img_num is not null");
            return (Criteria) this;
        }

        public Criteria andImgNumEqualTo(Integer value) {
            addCriterion("img_num =", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumNotEqualTo(Integer value) {
            addCriterion("img_num <>", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumGreaterThan(Integer value) {
            addCriterion("img_num >", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("img_num >=", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumLessThan(Integer value) {
            addCriterion("img_num <", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumLessThanOrEqualTo(Integer value) {
            addCriterion("img_num <=", value, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumIn(List<Integer> values) {
            addCriterion("img_num in", values, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumNotIn(List<Integer> values) {
            addCriterion("img_num not in", values, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumBetween(Integer value1, Integer value2) {
            addCriterion("img_num between", value1, value2, "imgNum");
            return (Criteria) this;
        }

        public Criteria andImgNumNotBetween(Integer value1, Integer value2) {
            addCriterion("img_num not between", value1, value2, "imgNum");
            return (Criteria) this;
        }

        public Criteria andCreateGmtIsNull() {
            addCriterion("create_gmt is null");
            return (Criteria) this;
        }

        public Criteria andCreateGmtIsNotNull() {
            addCriterion("create_gmt is not null");
            return (Criteria) this;
        }

        public Criteria andCreateGmtEqualTo(Date value) {
            addCriterion("create_gmt =", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtNotEqualTo(Date value) {
            addCriterion("create_gmt <>", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtGreaterThan(Date value) {
            addCriterion("create_gmt >", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_gmt >=", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtLessThan(Date value) {
            addCriterion("create_gmt <", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtLessThanOrEqualTo(Date value) {
            addCriterion("create_gmt <=", value, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtIn(List<Date> values) {
            addCriterion("create_gmt in", values, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtNotIn(List<Date> values) {
            addCriterion("create_gmt not in", values, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtBetween(Date value1, Date value2) {
            addCriterion("create_gmt between", value1, value2, "createGmt");
            return (Criteria) this;
        }

        public Criteria andCreateGmtNotBetween(Date value1, Date value2) {
            addCriterion("create_gmt not between", value1, value2, "createGmt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}