package demoPackage.demoMapper;

import demoClass.*;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface SalMgtMapper {

    /**
     * 查询配置表全数据
     * */
    List<Lacom> queryAllLacom();
    List<LDComF> queryAllLDComf();
    List<LMRiskApp> queryAllLMRiskApp();
    List<LagxjdbankCompact> queryAllLagxjdbankCompact();
    List<LagxjdrateCharge> queryAllLagxjdrateCharge();

    /**
     * 清空业财配置表和配置备份表
     * */
    void truncateLacom();
    void truncateLDComf();
    void truncateLMRiskApp();
    void truncateLagxjdbankCompact();
    void truncateLagxjdrateCharge();
    void truncateLacomBackup();
    void truncateLDComfBackup();
    void truncateLMRiskAppBackup();
    void truncateLagxjdbankCompactBackup();
    void truncateLagxjdrateChargeBackup();
    /**
     * 清空异常配置数据存储表
     * */
    void truncateSMCfgTableErr();

    /**
     * 向sm_cfgtable_err插入异常数据信息
     * */
    long inseertSMCfgTableErr(@Param("smCfgTableErr") SMCfgTableErr smCfgTableErr);
    long inseertSMCfgTableErrList(@Param("smCfgTableErrList") List<SMCfgTableErr> smCfgTableErrList);

    /**
     * 向tra_sxf_interface_details_t表中插入数据，插入所有列
     * */
    long insertTraSxfDetails(@Param("detailsT") TraSxfInterfaceDetailsT detailsT);
    long insertTraSxfDetailsList(@Param("detailsTList") List<TraSxfInterfaceDetailsT> detailsTList);

    long insertTraSxfDetailsListSplit(@Param("detailsTList") List<TraSxfInterfaceDetailsT> detailsTList);

    void testwx(@Param("names") List<String> names);
    /**
     * 统计tra_sxf_interface_details_t有多少个字段
     * */
    long countColumn();

    /**
     * 向TRA_SXF_INTERFACE_BATCHES表插入数据,插入所有列.
     * */
    long insertTraSxfBatches(@Param("traSxfBatches") TraSxfInterfaceBatches traSxfBatches);

    /**
     * 向LACOM-代理机构表插入数据，插入所有列
     * */
    long insertLacom(@Param("lacom") Lacom lacom);
    long insertLacomList(@Param("lacomList") List<Lacom> lacomList);

    /**
     * 向LDComF-直销管理机构信息表插入数据，插入所有列
     * */
    long insertLDComf(@Param("ldComF") LDComF ldComF);
    long insertLDComfList(@Param("ldComFList") List<LDComF> ldComFList);

    /**
     * 向LMRiskApp-险种信息表插入数据，插入所有列
     * */
    long insertLMRiskApp(@Param("lmRiskApp") LMRiskApp lmRiskApp);
    long insertLMRiskAppList(@Param("lmRiskAppList") List<LMRiskApp> lmRiskAppList);

    /**
     * 向LAGXJDBANKCOMPACT-合同信息表插入数据，插入所有列
     * */
    long insertLagxjdbankCompact(@Param("lagxjdbankCompact") LagxjdbankCompact lagxjdbankCompact);
    long insertLagxjdbankCompactList(@Param("lagxjdbankCompactList") List<LagxjdbankCompact> lagxjdbankCompactList);

    /**
     * 向LAGXJDRATECHARGE-手续费率表插入数据，插入所有列
     * */
    long insertLagxjdrateCharge(@Param("lagxjdrateCharge") LagxjdrateCharge lagxjdrateCharge);
    long insertLagxjdrateChargeList(@Param("lagxjdrateChargeList") List<LagxjdrateCharge> lagxjdrateChargeList);

    /**
     * 备份LACOM-代理机构表数据到LACOMBACKUP表，插入所有列
     * */
    long backupLacom(@Param("lacom") Lacom lacom);

    /**
     * 备份LDComF-直销管理机构信息表数据到LDComFBACKUP表，插入所有列
     * */
    long backupLDComf(@Param("ldComF") LDComF ldComF);

    /**
     * 备份LMRiskApp-险种信息表数据到LMRiskAppBACKUP表，插入所有列
     * */
    long backupLMRiskApp(@Param("lmRiskApp") LMRiskApp lmRiskApp);

    /**
     * 备份LAGXJDBANKCOMPACT-合同信息表数据到LAGXJDBANKCOMPACTBACKUP表，插入所有列
     * */
    long backupLagxjdbankCompact(@Param("lagxjdbankCompact") LagxjdbankCompact lagxjdbankCompact);

    /**
     * 备份LAGXJDRATECHARGE-手续费率表数据到LAGXJDRATECHARGEBACKUP表，插入所有列
     * */
    long backupLagxjdrateCharge(@Param("lagxjdrateCharge") LagxjdrateCharge lagxjdrateCharge);

    /**
     * 根据批次号查询批次表的数据
     * */
    TraSxfInterfaceBatches queryTraSxfInterfaceBatches(@Param("batchId") String batchId);


    List<TManageCom> queryAllLacomLevel(@Param("upManageCom") String upManageCom);

    /**
     * 查询财务机构映射
     * @param agentCode
     * @return
     */
    String getAgentcomMapping(@Param("agentCode") String agentCode);

    /**
     * 查询财务险种映射
     * @param riskcode
     * @return
     */

    String getRiskcodeMapping(@Param("riskcode") String riskcode);

    void updateStatus(@Param("batchId") String batchId, @Param("status") String status);

    void updateStatusToTra_sxf_interface_batches(@Param("batchId") String batchId, @Param("status") String status);
    void updateStatusToTra_sxf_interface_details_t(@Param("batchId") String batchId, @Param("status") String status);

    void updateStatusToTra_sxf_interface_details_t_split(@Param("batchId") String batchId, @Param("status") String status);

    /**
     * 获取机构编码映射值(业财编码转财务编码)
     * */
    String getManageComMapping(@Param("managecom") String managecom);

    /**
     * 获取机构编码映射值(业财编码转财务编码)
     * */
    String getDepartmentMapping(@Param("department") String agentCode);

}
