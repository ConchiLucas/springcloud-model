package com.ccbc.pojo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ApplyInfoVo {

        private static final long serialVersionUID = 1L;

        private Long applySeq;
        private String assetSide;
        private String capitalSide;
        private Long custManagerSeq;
        private String applyNo;
        private String custName;
        private String idType;
        private String idNo;
        private String mobile;
        private String applyDate;
        private String prdCode;
        private String cardNo;
        private String curStep;
        private String status;
        private BigDecimal initialLimit;
        private String address;
        private String avalDate;
        private String issueAuthority;
        private Long prdAcctSeq;
        private String creditNumber;
        private String approvalMessage;
        private String loanCity;
        private String loanProvince;
        private Long affiancePlanSeq;
        private String underLineContractNo;
        private String loanContractCode;
        private String affiancePlanNo;
        private String affiancePlanTermNum;
        private Integer apportionmentPeriod;
}
