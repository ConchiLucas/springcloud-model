package com.ccbc.pojo.pojo;

import com.ccbc.pojo.abspojo.ICcbcVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrdInfoVo extends ICcbcVo implements Serializable{


        private static final long serialVersionUID = 1L;

        private Long prdSeq;
        private String assetSide;
        private String capitalSide;
        private String prdCode;
        private String ccy;
        private String prdDesc;
        private String payMode;
        private String payCustGroup;
        private String payMinTerm;
        private String payMaxTerm;
        private BigDecimal singleDownLimit;
        private BigDecimal singleUpLimit;
        private String prequalificationFlag;
        private String ifAllowDefer;
        private Integer allowDeferNum;
        private String deferIfIquidatedDamages;
        private String ifSettlement;
        private String intCalcBase;
        private String intCalcMode;
        private String aheadRefund;
        private String aheadRefundMinType;
        private BigDecimal aheadRefundMinAmt;
        private BigDecimal aheadRefundMinRatio;
        private String ifIquidatedDamages;
        private String noAheadRefundTerm;
        private String minAheadRefundTerm;
        private String maxAheadTerm;
        private String partAheadRefundIntBase;
        private String ifCollectCompoundInterest;
        private String punitiveRateIntNum;
        private String rateModel;
        private BigDecimal maxRate;
        private BigDecimal minRate;
        private String convTerm;
        private String extract;
        private String rateRatio;
        private String amortize;
        private String amortizeRateRatio;
        private String collectOverdueFine;
        private String holidaysFestivalsDueLatest;
        private String accruedRefundOrder;
        private String noAccruedRefundOrder;
        private String ifForeignCurrency;
        private String riskType;
        private String rateType;
        private String eftDate;
        private String endDate;
        private String minAheadRefundNoliqdam;
        private String maxAheadRefundNoliqdam;
        private String collectPunitiveRate;
        private BigDecimal normalCommitRate;
        private BigDecimal punitiveRate;
        private String loanTerm;
        private String assureMode;
        private String refundInterval;
        private String refundMode;
        private String issuedFrequency;
        private String issuedMode;
        private String bearperiodType;
        private Integer bearperiod;
        private String deductMode;
        private String perSignedFlag;
        private String cancelFlag;
        private String loanPurpose;
        private String otherLoanPurpose;
        private String overdueRateSel;
        private String ifCollectMsgFee;
        private String ifCollectAssetManageCharge;
        private Integer rateProduct;
        private Integer refundDay;
        private BigDecimal discountChargeRate;
        private String ifPreDeposit;
        private Integer zxBearperiod;
        private BigDecimal compoundRate;
        private Integer apportionmentPeriod;



}
