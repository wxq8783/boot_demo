package com.wu.memery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class FutureContract {

    private Integer num;

    private BigDecimal big1;
    private BigDecimal big2;
    private BigDecimal big3;
    private BigDecimal big4;
    private BigDecimal big5;
    private BigDecimal big6;
    private BigDecimal big7;
    private BigDecimal big8;
    private BigDecimal big9;
    private BigDecimal big10;
    private BigDecimal big11;
    private BigDecimal big12;
    private BigDecimal big13;

    private Long long1;
    private Long long3;
    private Long long4;

    private String str1;
    private String str2;
    private String str3;

    private Integer int1;
    private Integer int2;
    private Integer int3;
    private Integer int4;

    private Date date1;
    private Date date2;

    public FutureContract(Integer num){
        this.num = num;
        Random random = new Random();
        this.big1 = BigDecimal.valueOf(random.nextInt());
        this.big2 = BigDecimal.valueOf(random.nextInt());
        this.big3 = BigDecimal.valueOf(random.nextInt());
        this.big4 = BigDecimal.valueOf(random.nextInt());
        this.big5 = BigDecimal.valueOf(random.nextInt());
        this.big6 = BigDecimal.valueOf(random.nextInt());
        this.big7 =BigDecimal.valueOf(random.nextInt());
        this.big8 = BigDecimal.valueOf(random.nextInt());
        this.big9 = BigDecimal.valueOf(random.nextInt());
        this.big10 = BigDecimal.valueOf(random.nextInt());
        this.big11 = BigDecimal.valueOf(random.nextInt());
        this.big12 = BigDecimal.valueOf(random.nextInt());
        this.big13 = BigDecimal.valueOf(random.nextInt());

        this.long1 = random.nextLong();
        this.long3 = random.nextLong();
        this.long4 = random.nextLong();

        this.str1 = "sdjflsdjlkfjdsfjlkdsjlkfdsjlkfjs"+random.nextInt();
        this.str2 = "sjdddddjlksncxnsieojsljdlksldslasmda"+random.nextInt();
        this.str3 = "sjdsmcjaskdjlaksjdlkjsaldjalksjdlkjsaljdlsajdlkaslk"+random.nextInt();
        this.int1 = random.nextInt(10000);
        this.int2 = random.nextInt(100000);
        this.int3 = random.nextInt(100000);
        this.int4 = random.nextInt(1000000);
        this.date1 = new Date();
        this.date2 = new Date();
    }

    public BigDecimal getBig1() {
        return big1;
    }

    public void setBig1(BigDecimal big1) {
        this.big1 = big1;
    }

    public BigDecimal getBig2() {
        return big2;
    }

    public void setBig2(BigDecimal big2) {
        this.big2 = big2;
    }

    public BigDecimal getBig3() {
        return big3;
    }

    public void setBig3(BigDecimal big3) {
        this.big3 = big3;
    }

    public BigDecimal getBig4() {
        return big4;
    }

    public void setBig4(BigDecimal big4) {
        this.big4 = big4;
    }

    public BigDecimal getBig5() {
        return big5;
    }

    public void setBig5(BigDecimal big5) {
        this.big5 = big5;
    }

    public BigDecimal getBig6() {
        return big6;
    }

    public void setBig6(BigDecimal big6) {
        this.big6 = big6;
    }

    public BigDecimal getBig7() {
        return big7;
    }

    public void setBig7(BigDecimal big7) {
        this.big7 = big7;
    }

    public BigDecimal getBig8() {
        return big8;
    }

    public void setBig8(BigDecimal big8) {
        this.big8 = big8;
    }

    public BigDecimal getBig9() {
        return big9;
    }

    public void setBig9(BigDecimal big9) {
        this.big9 = big9;
    }

    public BigDecimal getBig10() {
        return big10;
    }

    public void setBig10(BigDecimal big10) {
        this.big10 = big10;
    }

    public BigDecimal getBig11() {
        return big11;
    }

    public void setBig11(BigDecimal big11) {
        this.big11 = big11;
    }

    public BigDecimal getBig12() {
        return big12;
    }

    public void setBig12(BigDecimal big12) {
        this.big12 = big12;
    }

    public BigDecimal getBig13() {
        return big13;
    }

    public void setBig13(BigDecimal big13) {
        this.big13 = big13;
    }

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public Long getLong3() {
        return long3;
    }

    public void setLong3(Long long3) {
        this.long3 = long3;
    }

    public Long getLong4() {
        return long4;
    }

    public void setLong4(Long long4) {
        this.long4 = long4;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    public Integer getInt3() {
        return int3;
    }

    public void setInt3(Integer int3) {
        this.int3 = int3;
    }

    public Integer getInt4() {
        return int4;
    }

    public void setInt4(Integer int4) {
        this.int4 = int4;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "FutureContract【num=" + num + "】";
    }
}
