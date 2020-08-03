package com.ccbc.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ApiModel(value="贷款请求对象",description="接收贷款请求参数")
public class LoanApplyReq {

    @ApiModelProperty(value="贷款ID",name="loanOrderId",example="DD0002302320200727105200e71b4c")
    private String loanOrderId;

    private long loanAmount;

    private int totalInstallment;

    private int repayType;

    private int loanRating;

    private int penaltyInterestRate;

    private String preAbsId;

    private String productNo;

    private String mobile;

    private String name;

    private String idNo;

    private String bankCardNo;

    private String bankName;

    private String bankAbbr;

    private String loanExtends;

    private int loanUsage;

    private String withdrawContactInfo;

    private Map didiRcFeature;

    private String notifyUrl;

    private int mortgageType;

    private int isForward;

    private int receAccType;

    private int receCertType;

    private List repayPlanList;

/*    private int loanNumber;

    private String repayPlanBeginDate;

    private String repayPlanEndDate;

    private long principal;

    private long interest;

    private long penalty;

    private long residualPrincipal;*/



}
