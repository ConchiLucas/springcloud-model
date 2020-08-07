package com.ccbc.pojo.pojo;

import com.ccbc.pojo.abspojo.ICcbcVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Setter
@Getter
public class CapitalAffiancePlanVo extends ICcbcVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long affiancePlanSeq;
    private String affiancePlanNo;
    private String affiancePlanName;
    private String capitalSide;
    private long affianceSeq;
    private String startDate;
    private String dueDate;
    private BigDecimal totalAmt;
    private BigDecimal usedAmt;
    private Long custNum;
    private String affiancePlanSts;
    private String createTime;
    private String lastModifyTime;
    private String lastModifyUser;
    private String createUser;
    private String assetSide;
    private String termNum;
    private String affianceSpecialAccBank;
    private String affianceSpecialAcc;
    private String affianceSpecialAccName;
    private String isDuration;
    private String affianceType;
    private String amtType;
    private String affiancePrdType;
    private BigDecimal durationAmt;
    private String proxyBank;
    private String proxyComName;
    private String proxyAcct;

}
