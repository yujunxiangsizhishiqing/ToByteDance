package demoPackage.demoInterface;

import demoEnum.NoType;

import java.math.BigDecimal;

/**
 * 流水号生成工具
 */
public interface SeriNo {
    /**
     * 获得一个流水号
     *
     * @param noType
     *            号码类型
     * @return 流水号
     */
    public String getASeriNo(NoType noType);

    public BigDecimal getNumberId(NoType noType);
}
