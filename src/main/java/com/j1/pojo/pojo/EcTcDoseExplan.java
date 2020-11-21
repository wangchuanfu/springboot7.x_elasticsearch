package com.j1.pojo.pojo;

import com.j1.pojo.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/8/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcTcDoseExplan extends BaseBO {
    private String goodsNo;

    private String goodsName;

    private String drugSpecification;

    private String usagePerUseCount;

    private String usageFrequencyCount;

    private String usageDays;

    private String usageMethod;

    private String totalCount;

    private String usagePerUseUnit;

    private String remarks;

    private String usageFrequencyUnit;
}
