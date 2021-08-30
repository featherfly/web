package cn.featherfly.web.spring.messageconverter;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {
    private String name;
    private Date birthDate;
    private Integer year;
    private BigDecimal payment;
    private BigDecimal bonus;

    /**
     * get name value
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name value
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get birthDate value
     *
     * @return birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * set birthDate value
     *
     * @param birthDate birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * get payment value
     *
     * @return payment
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * set payment value
     *
     * @param payment payment
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * get bonus value
     *
     * @return bonus
     */
    public BigDecimal getBonus() {
        return bonus;
    }

    /**
     * set bonus value
     *
     * @param bonus bonus
     */
    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    /**
     * get year value
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * set year value
     * @param year year
     */
    public void setYear(Integer year) {
        this.year = year;
    }
    
    

}