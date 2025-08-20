package demoPackage;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.istack.NotNull;
import demoClass.*;
import demoEnum.DBExecutorType;
import demoEnum.MybatisBatchOperationType;
import demoEnum.NoType;
import demoPackage.demoInterface.SalMgtService;
import demoPackage.demoInterface.SeriNo;
import demoPackage.demoMapper.SalMgtMapper;
import demoPackage.demoUtil.DBBatchManager;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class DT_SalMgtServiceImpl implements SalMgtService {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private SalMgtMapper salMgtMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private SeriNo seriNo;
    @Autowired
    private DBBatchManager dbBatchManager;
    //@Autowired
    //private List<TraSxfInterfaceDetailsT> detailsTList;//多例可以解决并发的安全问题，但会存在线程池资源不够的隐患，先不用
    //@Autowired
    //private SalMgtModel salMgtModel;//多例可以解决并发的安全问题，但会存在线程池资源不够的隐患，先不用

    public boolean backupCfgTableData() {
        long start = System.currentTimeMillis();
        //清空数据
        truncateBackupCfgTable();
        //获取数据
        List<Lacom> lacomList = salMgtMapper.queryAllLacom();
        List<LDComF> lDComFList = salMgtMapper.queryAllLDComf();
        List<LMRiskApp> lMRiskAppList = salMgtMapper.queryAllLMRiskApp();
        List<LagxjdbankCompact> lagxjdbankCompactList = salMgtMapper.queryAllLagxjdbankCompact();
        List<LagxjdrateCharge> lagxjdrateChargeList = salMgtMapper.queryAllLagxjdrateCharge();
        //备份数据
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            SalMgtMapper mapper = session.getMapper(SalMgtMapper.class);
            lacomList.forEach(item -> {
                mapper.backupLacom(item);
            });
            lDComFList.forEach(item -> {
                mapper.backupLDComf(item);
            });
            lMRiskAppList.forEach(item -> {
                mapper.backupLMRiskApp(item);
            });
            lagxjdbankCompactList.forEach(item -> {
                mapper.backupLagxjdbankCompact(item);
            });
            lagxjdrateChargeList.forEach(item -> {
                mapper.backupLagxjdrateCharge(item);
            });
            /**
             * 事务提交，清除缓存。
             * */
            session.commit();
            session.clearCache();

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("备份配置表数据用时：" + (System.currentTimeMillis() - start) + "ms");
        return true;
    }

    public boolean truncateCfgTable() {
        salMgtMapper.truncateLacom();
        salMgtMapper.truncateLDComf();
        salMgtMapper.truncateLMRiskApp();
        salMgtMapper.truncateLagxjdbankCompact();
        salMgtMapper.truncateLagxjdrateCharge();
        return true;
    }

    public boolean truncateBackupCfgTable() {
        salMgtMapper.truncateLacomBackup();
        salMgtMapper.truncateLDComfBackup();
        salMgtMapper.truncateLMRiskAppBackup();
        salMgtMapper.truncateLagxjdbankCompactBackup();
        salMgtMapper.truncateLagxjdrateChargeBackup();
        return true;
    }

    @Override
    public boolean importBusinessData(@NotNull String salMgtCfgInfo) throws Exception {
        //解析以获得导入的参数信息（txt文件存放路径等）
        SalMgtModel salMgtModel = getNecessaryBusinessParams(salMgtCfgInfo);
        if (salMgtModel == null) {
            logger.info("获取业务数据异常");
            return false;
        }
        //向TRA_SXF_INTERFACE_BATCHES表插入数据
        long rows1 = insertTraSxfInterfaceBatches(salMgtModel);
        if (rows1 <= 0) {
            logger.info("插入数据异常,表：tra_sxf_interface_batches");
            return false;
        }
        //获取业务数据，并且将业务数据插入到`tra_sxf_interface_details_t`表中
        //开始插入《不拆分》的业务数据
        List<TraSxfInterfaceDetailsT> detailsTList = getBusinessData(salMgtModel);
        String result = insertBusinessData(detailsTList);
        if (null != result) {
            logger.info("插入数据异常，批次号(BATCH_ID)："+salMgtModel.getBatchId()+"表：tra_sxf_interface_details_t,异常信息:" + result);
//            salMgtMapper.updateStatus(salMgtModel.getBatchId(), "E");
            salMgtMapper.updateStatusToTra_sxf_interface_batches(salMgtModel.getBatchId(), "E");
            salMgtMapper.updateStatusToTra_sxf_interface_details_t(salMgtModel.getBatchId(), "E");
            return false;
        }else {
            salMgtMapper.updateStatusToTra_sxf_interface_batches(salMgtModel.getBatchId(), "S");
            salMgtMapper.updateStatusToTra_sxf_interface_details_t(salMgtModel.getBatchId(), "S");
            logger.info("《不拆分》业务数据插入成功！批次号(BATCH_ID)："+salMgtModel.getBatchId()+",表：tra_sxf_interface_details_t");
        }
        //开始插入《拆分》的业务数据
        List<TraSxfInterfaceDetailsT> detailsTListSplit = splitBusinessData(detailsTList);
        String result2 = insertSplitBusinessData(detailsTListSplit);
        if (null != result2) {
            logger.info("插入数据异常，批次号(BATCH_ID)："+salMgtModel.getBatchId()+"表：tra_sxf_interface_details_t_split,异常信息:" + result2);
//            salMgtMapper.updateStatus(salMgtModel.getBatchId(), "E");
            salMgtMapper.updateStatusToTra_sxf_interface_batches(salMgtModel.getBatchId(), "E");
            salMgtMapper.updateStatusToTra_sxf_interface_details_t_split(salMgtModel.getBatchId(), "E");
            return false;
        }else {
            salMgtMapper.updateStatusToTra_sxf_interface_batches(salMgtModel.getBatchId(), "S");
            salMgtMapper.updateStatusToTra_sxf_interface_details_t_split(salMgtModel.getBatchId(), "S");
            logger.info("《拆分》业务数据插入成功！批次号(BATCH_ID)："+salMgtModel.getBatchId()+",表：tra_sxf_interface_details_t_split");
        }
//        salMgtMapper.updateStatus(salMgtModel.getBatchId(),"S");
        logger.info("《拆分》及《不拆分》业务数据均插入成功！批次号(BATCH_ID)："+salMgtModel.getBatchId());
        return true;
    }

    /**
     * @apiNote 需求不需要该接口，未实现。日后按需拓展。
     * @author wx
     * @date 2023/01/09
     */
    @Override
    public boolean importCfgTableData(String salMgtCfgInfo) throws Exception {
        //解析以获得导入的配置信息（txt文件存放路径等）
        SalMgtModel salMgtModel = getNecessaryCfgParams(salMgtCfgInfo);
        if (salMgtModel == null) {
            logger.info("获取配置数据异常");
            return false;
        }
        //通过解析的配置信息获取并导入数据
        long rows = insertCfgTableData(salMgtModel);
        if (rows <= 0) {
            logger.info("插配置入数据异常");
            return false;
        }
        logger.info("插入配置数据成功");
        return true;
    }

    @Override
    public boolean importCfgTableData() throws Exception {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(DRUID_PROPERTIES_PARH);
        properties.load(fileInputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = null;
        try {
            /**
             * 数据备份
             * */
            //backupCfgTableData();//暂不备份

            /**
             * 清空业财配置表数据
             * */
            truncateCfgTable();

            connection = dataSource.getConnection();
            /**
             * LACOM-代理机构表
             * */
            //数据统计
            PreparedStatement lacomCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LACOMD);
            ResultSet lacomCountRes = lacomCountPstmt.executeQuery();
            long lacomCount = 0;
            for (; lacomCountRes.next(); ) {
                //logger.info("LACOM结果集总数为： " + lacomCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
                lacomCount = Long.valueOf(lacomCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
            }
            //数据组装
            PreparedStatement lacomQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LACOMD);
            ResultSet lacomQueryRes = lacomQueryPstmt.executeQuery();
            long lacomStartTime = System.currentTimeMillis();
            List<Lacom> lacomList = getLacomData(lacomQueryRes);
            if (CollectionUtils.isEmpty(lacomList)) {
                return false;
            }
            logger.info("LACOM配置表数据组装用时：" + (System.currentTimeMillis() - lacomStartTime)
                    + ",源数据有" + lacomCount + "条数据。"
                    + "实际插入" + lacomList.size() + "条数据");
            //数据同步[insert]
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.LACOM, 600, lacomList);

            /**
             * LDComF-直销管理机构信息表
             * */
            //数据统计
            PreparedStatement lDComFCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LDCOMF);
            ResultSet lDComFCountRes = lDComFCountPstmt.executeQuery();
            long ldComFCount = 0;
            for (; lDComFCountRes.next(); ) {
                //logger.info("LDComF结果集总数为： " + ldComFCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
                ldComFCount = Long.valueOf(lDComFCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
            }
            //数据组装
            PreparedStatement ldComFQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LDCOMF);
            ResultSet ldComFQueryRes = ldComFQueryPstmt.executeQuery();
            long ldComFStartTime = System.currentTimeMillis();
            List<LDComF> lDComFList = getLDComFData(ldComFQueryRes);
            if (CollectionUtils.isEmpty(lDComFList)) {
                return false;
            }
            logger.info("LDComF配置表数据组装用时：" + (System.currentTimeMillis() - ldComFStartTime)
                    + ",源数据有" + ldComFCount + "条数据。"
                    + "实际插入" + lDComFList.size() + "条数据");
            //数据同步[insert]
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.LDCOMF, 600, lDComFList);

            /**
             * LMRiskApp-险种信息表
             * */
            //数据统计
            PreparedStatement lmRiskAppCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LMRISKAPP);
            ResultSet lmRiskAppCountRes = lmRiskAppCountPstmt.executeQuery();
            long lmRiskAppCount = 0;
            for (; lmRiskAppCountRes.next(); ) {
                //logger.info("LMRiskApp结果集总数为： " + lmRiskAppCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
                lmRiskAppCount = Long.valueOf(lmRiskAppCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
            }
            //数据组装
            PreparedStatement lmRiskAppQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LMRISKAPP);
            ResultSet lmRiskAppQueryRes = lmRiskAppQueryPstmt.executeQuery();
            long lMRiskAppStartTime = System.currentTimeMillis();
            List<LMRiskApp> lMRiskAppList = getLMRiskAppData(lmRiskAppQueryRes);
            if (CollectionUtils.isEmpty(lMRiskAppList)) {
                return false;
            }
            logger.info("LMRiskApp配置表数据组装用时：" + (System.currentTimeMillis() - lMRiskAppStartTime)
                    + ",源数据有" + lmRiskAppCount + "条数据。"
                    + "实际插入" + lMRiskAppList.size() + "条数据");
            //数据同步[insert]
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.LMRISKAPP, 600, lMRiskAppList);

            /**
             * LAGXJDBANKCOMPACT-合同信息表
             * */
            //数据统计
            PreparedStatement lagxJdbankCompactCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LAGXJDBANKCOMPACT);
            ResultSet lagxJdbankCompactCountRes = lagxJdbankCompactCountPstmt.executeQuery();
            long lagxJdbankCompactCount = 0;
            for (; lagxJdbankCompactCountRes.next(); ) {
                //logger.info("LAGXJDBANKCOMPACT结果集总数为： " + lagxJdbankCompactCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
                lagxJdbankCompactCount = Long.valueOf(lagxJdbankCompactCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
            }
            //数据组装
            PreparedStatement lagxJdbankCompactQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LAGXJDBANKCOMPACT);
            ResultSet lagxJdbankCompactQueryRes = lagxJdbankCompactQueryPstmt.executeQuery();
            long lagxJdbankCompactStartTime = System.currentTimeMillis();
            List<LagxjdbankCompact> lagxjdbankCompactList = getLagxjdbankCompactData(lagxJdbankCompactQueryRes);
            if (CollectionUtils.isEmpty(lagxjdbankCompactList)) {
                return false;
            }
            logger.info("LAGXJDRATECHARGE配置表数据组装用时：" + (System.currentTimeMillis() - lagxJdbankCompactStartTime)
                    + ",源数据有" + lagxJdbankCompactCount + "条数据。"
                    + "实际插入" + lagxjdbankCompactList.size() + "条数据");
            //数据同步[insert]
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.LAGXJDBANKCOMPACT, 600, lagxjdbankCompactList);

            /**
             * LAGXJDRATECHARGE-手续费率表
             * */
            //数据统计
            PreparedStatement lagxJdrateChargeCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LAGXJDRATECHARGE);
            ResultSet lagxJdrateChargeCountRes = lagxJdrateChargeCountPstmt.executeQuery();
            long lagxJdrateChargeCount = 0;
            for (; lagxJdrateChargeCountRes.next(); ) {
                //logger.info("LAGXJDRATECHARGE结果集总数为： " + lagxJdrateChargeCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
                lagxJdrateChargeCount = Long.valueOf(lagxJdrateChargeCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
            }
            //数据组装
            PreparedStatement lagxJdrateChargeQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LAGXJDRATECHARGE);
            ResultSet lagxJdrateChargeQueryRes = lagxJdrateChargeQueryPstmt.executeQuery();
            long lagxJdrateChargeStartTime = System.currentTimeMillis();
            List<LagxjdrateCharge> lagxjdrateChargeList = getLagxjdrateChargeData(lagxJdrateChargeQueryRes);
            if (CollectionUtils.isEmpty(lagxjdrateChargeList)) {
                return false;
            }
            logger.info("LAGXJDRATECHARGE配置表数据组装用时：" + (System.currentTimeMillis() - lagxJdrateChargeStartTime)
                    + ",源数据有" + lagxJdrateChargeCount + "条数据。"
                    + "实际插入" + lagxjdrateChargeList.size() + "条数据");
            //数据同步[insert]
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.LAGXJDRATECHARGE, 600, lagxjdrateChargeList);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("配置数据插入异常：" + e.toString());
            return false;
        }

        return true;

//
//        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream(DRUID_PROPERTIES_PARH);
//        properties.load(fileInputStream);
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//
//        Connection connection = null;
//        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
//        try {
//            /**
//             * 数据备份
//             * */
//            //backupCfgTableData();//暂不备份
//
//            /**
//             * 清空业财配置表数据
//             * */
//            truncateCfgTable();
//
//            connection = dataSource.getConnection();
//            SalMgtMapper mapper = session.getMapper(SalMgtMapper.class);
//            /**
//             * LACOM-代理机构表
//             * */
//            //数据统计
//            PreparedStatement lacomCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LACOMD);
//            ResultSet lacomCountRes = lacomCountPstmt.executeQuery();
//            long lacomCount = 0;
//            for (;lacomCountRes.next();){
//                //logger.info("LACOM结果集总数为： " + lacomCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//                lacomCount = Long.valueOf(lacomCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//            }
//            //数据组装
//            PreparedStatement lacomQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LACOMD);
//            ResultSet lacomQueryRes = lacomQueryPstmt.executeQuery();
//            long lacomStartTime = System.currentTimeMillis();
//            List<Lacom> lacomList = getLacomData(lacomQueryRes);
//            if (CollectionUtils.isEmpty(lacomList)){
//                return false;
//            }
//            logger.info("LACOM配置表数据组装用时：" + (System.currentTimeMillis()-lacomStartTime)
//                    + ",源数据有"+lacomCount+"条数据。"
//                    + "实际插入" + lacomList.size() + "条数据");
//            //数据同步[insert]
//            lacomList.forEach(item -> {
//                mapper.insertLacom(item);
//            });
//
//            /**
//             * LDComF-直销管理机构信息表
//             * */
//            //数据统计
//            PreparedStatement lDComFCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LDCOMF);
//            ResultSet lDComFCountRes = lDComFCountPstmt.executeQuery();
//            long ldComFCount = 0;
//            for (;lDComFCountRes.next();){
//                //logger.info("LDComF结果集总数为： " + ldComFCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//                ldComFCount = Long.valueOf(lDComFCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//            }
//            //数据组装
//            PreparedStatement ldComFQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LDCOMF);
//            ResultSet ldComFQueryRes = ldComFQueryPstmt.executeQuery();
//            long ldComFStartTime = System.currentTimeMillis();
//            List<LDComF> lDComFList = getLDComFData(ldComFQueryRes);
//            if (CollectionUtils.isEmpty(lDComFList)){
//                return false;
//            }
//            logger.info("LDComF配置表数据组装用时：" + (System.currentTimeMillis()-ldComFStartTime)
//                    + ",源数据有"+ldComFCount+"条数据。"
//                    + "实际插入" + lDComFList.size() + "条数据");
//            //数据同步[insert]
//            lDComFList.forEach(item -> {
//                mapper.insertLDComf(item);
//            });
//
//            /**
//             * LMRiskApp-险种信息表
//             * */
//            //数据统计
//            PreparedStatement lmRiskAppCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LMRISKAPP);
//            ResultSet lmRiskAppCountRes = lmRiskAppCountPstmt.executeQuery();
//            long lmRiskAppCount = 0;
//            for (;lmRiskAppCountRes.next();){
//                //logger.info("LMRiskApp结果集总数为： " + lmRiskAppCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//                lmRiskAppCount = Long.valueOf(lmRiskAppCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//            }
//            //数据组装
//            PreparedStatement lmRiskAppQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LMRISKAPP);
//            ResultSet lmRiskAppQueryRes = lmRiskAppQueryPstmt.executeQuery();
//            long lMRiskAppStartTime = System.currentTimeMillis();
//            List<LMRiskApp> lMRiskAppList = getLMRiskAppData(lmRiskAppQueryRes);
//            if (CollectionUtils.isEmpty(lMRiskAppList)){
//                return false;
//            }
//            logger.info("LMRiskApp配置表数据组装用时：" + (System.currentTimeMillis()-lMRiskAppStartTime)
//                    + ",源数据有"+lmRiskAppCount+"条数据。"
//                    + "实际插入" + lMRiskAppList.size() + "条数据");
//            //数据同步[insert]
//            lMRiskAppList.forEach(item->{
//                mapper.insertLMRiskApp(item);
//            });
//
//            /**
//             * LAGXJDBANKCOMPACT-合同信息表
//             * */
//            //数据统计
//            PreparedStatement lagxJdbankCompactCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LAGXJDBANKCOMPACT);
//            ResultSet lagxJdbankCompactCountRes = lagxJdbankCompactCountPstmt.executeQuery();
//            long lagxJdbankCompactCount = 0;
//            for (;lagxJdbankCompactCountRes.next();){
//                //logger.info("LAGXJDBANKCOMPACT结果集总数为： " + lagxJdbankCompactCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//                lagxJdbankCompactCount = Long.valueOf(lagxJdbankCompactCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//            }
//            //数据组装
//            PreparedStatement lagxJdbankCompactQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LAGXJDBANKCOMPACT);
//            ResultSet lagxJdbankCompactQueryRes = lagxJdbankCompactQueryPstmt.executeQuery();
//            long lagxJdbankCompactStartTime = System.currentTimeMillis();
//            List<LagxjdbankCompact> lagxjdbankCompactList= getLagxjdbankCompactData(lagxJdbankCompactQueryRes);
//            if (CollectionUtils.isEmpty(lagxjdbankCompactList)){
//                return false;
//            }
//            logger.info("LAGXJDRATECHARGE配置表数据组装用时：" + (System.currentTimeMillis()-lagxJdbankCompactStartTime)
//                    + ",源数据有"+lagxJdbankCompactCount+"条数据。"
//                    + "实际插入" + lagxjdbankCompactList.size() + "条数据");
//            //数据同步[insert]
//            lagxjdbankCompactList.forEach(item->{
//                mapper.insertLagxjdbankCompact(item);
//            });
//
//            /**
//             * LAGXJDRATECHARGE-手续费率表
//             * */
//            //数据统计
//            PreparedStatement lagxJdrateChargeCountPstmt = connection.prepareStatement(SQLSERVER_COUNT_LAGXJDRATECHARGE);
//            ResultSet lagxJdrateChargeCountRes = lagxJdrateChargeCountPstmt.executeQuery();
//            long lagxJdrateChargeCount = 0;
//            for (;lagxJdrateChargeCountRes.next();){
//                //logger.info("LAGXJDRATECHARGE结果集总数为： " + lagxJdrateChargeCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//                lagxJdrateChargeCount = Long.valueOf(lagxJdrateChargeCountRes.getString(SQLSEERVER_COUNT_RESULT_COLUMN_NAME));
//            }
//            //数据组装
//            PreparedStatement lagxJdrateChargeQueryPstmt = connection.prepareStatement(SQLSERVER_QUERY_DATA_FROM_LAGXJDRATECHARGE);
//            ResultSet lagxJdrateChargeQueryRes = lagxJdrateChargeQueryPstmt.executeQuery();
//            long lagxJdrateChargeStartTime = System.currentTimeMillis();
//            List<LagxjdrateCharge> lagxjdrateChargeList = getLagxjdrateChargeData(lagxJdrateChargeQueryRes);
//            if (CollectionUtils.isEmpty(lagxjdrateChargeList)){
//                return false;
//            }
//            logger.info("LAGXJDRATECHARGE配置表数据组装用时：" + (System.currentTimeMillis()-lagxJdrateChargeStartTime)
//                    + ",源数据有"+lagxJdrateChargeCount+"条数据。"
//                    + "实际插入" + lagxjdrateChargeList.size() + "条数据");
//            //数据同步[insert]
//            lagxjdrateChargeList.forEach(item->{
//                mapper.insertLagxjdrateCharge(item);
//            });
//
//
//            /**
//             * 事务提交，清除缓存。
//             * */
//            session.commit();
//            session.clearCache();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.rollback();
//            return false;
//        }finally{
//            if (connection!=null){
//                connection.close();
//            }
//            if (session!=null){
//                session.close();
//            }
//        }
//
//        return true;
    }

    /**
     * @param salMgtCfgInfo
     * @return SalMgtModel：解析得到的必要参数都放在该对象实例中
     * @apiNote 销管业务数据导入流程必要参数获取:解析销管系统传入的相关配置信息，后续需要的txt文件路径等信息均包括在其中
     */
    public SalMgtModel getNecessaryBusinessParams(@NotNull String salMgtCfgInfo) throws Exception {
        logger.info("销管以JSON形式向业财传入必要配置表数据的参数信息");
        //解析得到的必要参数都放在该对象实例中
        SalMgtModel salMgtModel = new SalMgtModel();
        String replaceStr = salMgtCfgInfo.toString().replace(" ", "");
        //String转换为JSONObject
        JSONObject jsonObj = JSONObject.parseObject(replaceStr);
        /**
         * 根据约定好的JSON格式获取所需参数
         * */
        //批次号[BATCH_ID]
        String batchId = (String) jsonObj.get("batchId");
        if (StringUtils.isEmpty(batchId)) {
            logger.info("批次号不可为空");
            return null;
        }
        //批次数据来源[BATCH_SOURCE]
        String batchSource = (String) jsonObj.get("batchSource");
        if (StringUtils.isEmpty(batchSource)) {
            logger.info("批次数据来源不可为空");
            return null;
        }
        //批次数据业务日期起期[BATCH_START_DATE]
        String batchStartDateStr = (String) jsonObj.get("batchStartDate");
        if (StringUtils.isEmpty(batchStartDateStr)) {
            logger.info("批次数据业务日期起期不可为空");
            return null;
        }
        Date batchStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(batchStartDateStr);
        //批次数据业务日期止期[BATCH_END_DATE]
        String batchEndDateStr = (String) jsonObj.get("batchEndDate");
        if (StringUtils.isEmpty(batchEndDateStr)) {
            logger.info("批次数据业务日期止期不可为空");
            return null;
        }
        Date batchEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(batchEndDateStr);
        //批次数据条数[BATCH_COUNT]
//        String batchCountStr = (String) jsonObj.get("BATCH_COUNT");
//        if (StringUtils.isEmpty(batchCountStr)){
//            logger.info("批次数据条数不可为空");
//            return null;
//        }
//        Long batchCount = Long.valueOf(batchCountStr);
        //批次状态[BATCH_FLAG]
        String batchFlag = (String) jsonObj.get("batchFlag");
        if (StringUtils.isEmpty(batchFlag)) {
            logger.info("批次状态不可为空");
            return null;
        }
        //文件归属目录,不包括文件名[file]
        String filePath = (String) jsonObj.get("filePath");
        if (StringUtils.isEmpty(filePath)) {
            logger.info("文件归属目录不可为空");
            return null;
        }
        //文件名[name]
        String fileName = (String) jsonObj.get("fileName");
        if (StringUtils.isEmpty(fileName)) {
            logger.info("文件名不可为空");
            return null;
        }

        /**
         * 参数配置
         * */
        salMgtModel.setBatchId(batchId);
        salMgtModel.setBatchSource(batchSource);
        salMgtModel.setBatchStartDate(batchStartDate);
        salMgtModel.setBatchEndDate(batchEndDate);
        //salMgtModel.setBatchCount(batchCount);
        salMgtModel.setBatchFlag(batchFlag);
        salMgtModel.setFileName(fileName);
        salMgtModel.setFilePath(filePath);
        return salMgtModel;
    }

    /**
     * @param salMgtCfgInfo
     * @return SalMgtModel：解析得到的必要参数都放在该对象实例中
     * @apiNote 销管业务数据导入流程必要参数获取:解析销管系统传入的相关配置信息，后续需要的txt文件路径等信息均包括在其中
     */
    public SalMgtModel getNecessaryCfgParams(@NotNull String salMgtCfgInfo) {
        logger.info("(1)配置表数据导入由定时任务完成，无需调用解析服务.(2)销管给予了业财查询销管数据库配置表的权限，业财定期查询，查询到结果之后同步至业财数据库中");
        return null;
    }

    /**
     * @apiNote 向批次表[tra_sxf_interface_batches]插入数据
     */
    public long insertTraSxfInterfaceBatches(@NotNull SalMgtModel salMgtModel) {
        TraSxfInterfaceBatches traSxfBatches = new TraSxfInterfaceBatches();
        BeanUtils.copyProperties(salMgtModel, traSxfBatches);
        traSxfBatches.setBatchMakeDate(new Date());
        traSxfBatches.setBatchMakeTime(new Date().toString().substring(11, 19));
        traSxfBatches.setBatchCreatUser("ADMIN");//ADMIN;AUTO;MANUAL
        traSxfBatches.setBatchModifyDate(new Date());
        traSxfBatches.setBatchModifyTime(new Date().toString().substring(11, 19));
        traSxfBatches.setBatchModifyUser("ADMIN");//ADMIN;AUTO;MANUAL
        //插入批次表数据至'tra_sxf_interface_batches'
        long rows = salMgtMapper.insertTraSxfBatches(traSxfBatches);
        if (rows <= 0) {
            logger.info("插入数据异常.表:tra_sxf_interface_batches");
            return 0;
        }
        return rows;
    }

    /**
     * 获取业务数据
     */
    private List<TraSxfInterfaceDetailsT> getBusinessData(@NotNull SalMgtModel salMgtModel) throws Exception {
        List<TraSxfInterfaceDetailsT> detailsTList = null;
        switch (PARSE_BY_DEFAULT) {
            case PARSE_BY_JSON:
                logger.info("以标准JSON格式去解析TXT文件数据源");
                detailsTList = getSalMgtDataByJson(salMgtModel);
                break;
            case PARSE_BY_SEPARATION_CHAR:
                logger.info("以用户规定的分隔符'" + SEPARATION_CHAR_DEFAULT + "'去解析TXT文件数据源");
                detailsTList = getSalMgtDataBySeparationChar(salMgtModel.getFilePath());
                break;
            default:
                logger.info("未知的TXT文本解析类型");
                break;
        }
        return detailsTList;
    }

    /**
     * @param priKeyIsNormal true:主键正常；false：主键异常
     * @param tableName      异常数据所在的表名称
     * @param columnName     主键正常时：正常主键名称；主键异常时：异常主键的名称
     * @param columnValue    主键正常时：对应主键的值；主键异常时：null
     * @param tips           主键正常时：异常字段的名称；主键异常时：`PRIKEY_ABNORMAL`(final String)
     *                       主键正常时：传入[true]  [表名称] [正常主键的名称] [对应主键下的列值] [异常字段的名称]
     *                       主键异常时：传入[false] [表名称] [异常主键名称]  [null]          [PRIKEY_ABNORMAL]
     * @apiNote 配置数据导入异常时，将本次异常数据中的关键信息存储下来，方便追溯。主键正常时，根据主键下的值查到对应异常的数据。主键异常时，只留下异常信息，运维自己排查。
     * @table sm_cfgtable_err
     */
    private SMCfgTableErr getCfgTableErrDataInfo(boolean priKeyIsNormal, @NotNull String tableName, @NotNull String columnName, String columnValue, @NotNull String tips) {
        SMCfgTableErr smCfgTableErr = new SMCfgTableErr();

        smCfgTableErr.setTableName(tableName);
        smCfgTableErr.setPrimaryKeyName(columnName);
        smCfgTableErr.setCreateDate(new Date());
        if (priKeyIsNormal) {
            //主键正常，其他非空字段异常
            //smCfgTableErr.setTableName(tableName);
            //smCfgTableErr.setPrimaryKeyName(columnName);
            smCfgTableErr.setPrimaryKeyValue(columnValue);
            smCfgTableErr.setAbnormalTips(tableName + "的" + tips + "异常");
            //smCfgTableErr.setCreateDate(new Date());
        } else {
            //主键异常
            //smCfgTableErr.setTableName(tableName);
            //smCfgTableErr.setPrimaryKeyName(columnName);
            smCfgTableErr.setAbnormalTips(tableName + "的" + PRIKEY_ABNORMAL + ",异常主键为：" + columnName);
            //smCfgTableErr.setCreateDate(new Date());
        }
        return smCfgTableErr;
    }

    /**
     * 获取配置表数据:LACOM-代理机构表
     */
    private List<Lacom> getLacomData(ResultSet lacomQueryRes) throws Exception {
        List<Lacom> lacomList = new ArrayList<>();
        List<SMCfgTableErr> errList = new ArrayList<>();
        for (; lacomQueryRes.next(); ) {
            Lacom lacom = new Lacom();
            //数据组装
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("AGENTCOM"))) {
                String agentcom = lacomQueryRes.getString("AGENTCOM");
                //过滤掉不是分公司的（5位）
              /*  int length = agentcom.trim().length();
                if (length != 5) {
                    continue;
                }*/
                lacom.setAgentcom(agentcom);
            } else {
                logger.info("代理机构不可为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LACOM, "AGENTCOM", null, PRIKEY_ABNORMAL));
                continue;
            }
            String managecom = lacomQueryRes.getString("MANAGECOM");
            lacom.setManagecom(managecom);
            lacom.setUpManageCom(managecom.substring(0, managecom.length() - 2));
            lacom.setAreatype(lacomQueryRes.getString("AREATYPE"));
            lacom.setChanneltype(lacomQueryRes.getString("CHANNELTYPE"));
            lacom.setUpagentcom(lacomQueryRes.getString("UPAGENTCOM"));
            lacom.setName(lacomQueryRes.getString("NAME"));
            lacom.setAddress(lacomQueryRes.getString("ADDRESS"));
            lacom.setZipcode(lacomQueryRes.getString("ZIPCODE"));
            lacom.setPhone(lacomQueryRes.getString("PHONE"));
            lacom.setFax(lacomQueryRes.getString("FAX"));
            lacom.setEmail(lacomQueryRes.getString("EMAIL"));
            lacom.setWebaddress(lacomQueryRes.getString("WEBADDRESS"));
            lacom.setLinkman(lacomQueryRes.getString("LINKMAN"));
            lacom.setPassword(lacomQueryRes.getString("PASSWORD"));
            lacom.setCorporation(lacomQueryRes.getString("CORPORATION"));
            lacom.setBankcode(lacomQueryRes.getString("BANKCODE"));
            lacom.setBankaccno(lacomQueryRes.getString("BANKACCNO"));
            lacom.setBusinesstype(lacomQueryRes.getString("BUSINESSTYPE"));
            lacom.setGrpnature(lacomQueryRes.getString("GRPNATURE"));
            lacom.setActype(lacomQueryRes.getString("ACTYPE"));
            lacom.setSellflag(lacomQueryRes.getString("SELLFLAG"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("OPERATOR"))) {
                lacom.setOperator(lacomQueryRes.getString("OPERATOR"));
            } else {
                logger.info("操作员代码不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LACOM, "AGENTCOM", lacomQueryRes.getString("AGENTCOM"), "OPERATOR"));
                continue;
            }

            if (StringUtils.isNotEmpty(lacomQueryRes.getString("MAKEDATE"))) {
                lacom.setMakedate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("MAKEDATE")));
            }
            lacom.setMaketime(lacomQueryRes.getString("MAKETIME"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("MODIFYDATE"))) {
                lacom.setModifydate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("MODIFYDATE")));
            }
            lacom.setModifytime(lacomQueryRes.getString("MODIFYTIME"));
            lacom.setBanktype(lacomQueryRes.getString("BANKTYPE"));
            lacom.setCalflag(lacomQueryRes.getString("CALFLAG"));
            lacom.setBusinesscode(lacomQueryRes.getString("BUSILICENSECODE"));
            lacom.setInsureid(lacomQueryRes.getString("INSUREID"));
            lacom.setInsureprincipal(lacomQueryRes.getString("INSUREPRINCIPAL"));
            lacom.setChiefbusiness(lacomQueryRes.getString("CHIEFBUSINESS"));
            lacom.setBusiaddress(lacomQueryRes.getString("BUSIADDRESS"));
            lacom.setSubscribeman(lacomQueryRes.getString("SUBSCRIBEMAN"));
            lacom.setSubscribemanduty(lacomQueryRes.getString("SUBSCRIBEMANDUTY"));
            lacom.setLicenseno(lacomQueryRes.getString("LICENSENO"));
            lacom.setRegionalismcode(lacomQueryRes.getString("REGIONALISMCODE"));
            lacom.setAppagentcom(lacomQueryRes.getString("APPAGENTCOM"));
            lacom.setState(lacomQueryRes.getString("STATE"));
            lacom.setNoti(lacomQueryRes.getString("NOTI"));
            lacom.setBusinesscode(lacomQueryRes.getString("BUSINESSCODE"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("LICENSESTARTDATE"))) {
                lacom.setLicensestartdate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("LICENSESTARTDATE")));
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("LICENSEENDDATE"))) {
                lacom.setLicenseenddate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("LICENSEENDDATE")));
            }
            lacom.setBranchtype(lacomQueryRes.getString("BRANCHTYPE"));
            lacom.setBranchtype2(lacomQueryRes.getString("BRANCHTYPE2"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("ASSETS"))) {
                lacom.setAssets(Double.valueOf(lacomQueryRes.getString("ASSETS")));
                ;
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("INCOME"))) {
                lacom.setIncome(Double.valueOf(lacomQueryRes.getString("INCOME")));
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("PROFITS"))) {
                lacom.setProfits(Double.valueOf(lacomQueryRes.getString("PROFITS")));
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("PERSONNALSUM"))) {
                lacom.setPersonnalsum(Long.valueOf(lacomQueryRes.getString("PERSONNALSUM")));
            }
            lacom.setProtocalno(lacomQueryRes.getString("PROTOCALNO"));
            lacom.setHeadoffice(lacomQueryRes.getString("HEADOFFICE"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("FOUNDDATE"))) {
                lacom.setFounddate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("FOUNDDATE")));
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("ENDDATE"))) {
                lacom.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("ENDDATE")));
            }
            lacom.setTouliansellflag(lacomQueryRes.getString("TOULIANSELLFLAG"));
            lacom.setLicenseowner(lacomQueryRes.getString("LICENSEOWNER"));
            lacom.setBatchflag(lacomQueryRes.getString("BATCHFLAG"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("BATCHMAKEDATE"))) {
                lacom.setBatchmakedate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("BATCHMAKEDATE")));
            }
            lacom.setBatchmaketime(lacomQueryRes.getString("BATCHMAKETIME"));
            lacom.setSocialcode(lacomQueryRes.getString("SOCIALCODE"));
            lacom.setSocialtype(lacomQueryRes.getString("SOCIALTYPE"));
            lacom.setBusunesslicensenumber(lacomQueryRes.getString("BUSINESSLICENSENUMBER"));
            lacom.setBusinesslicensenamecode(lacomQueryRes.getString("BUSINESSLICENSENAMECODE"));
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("EXPIRYDATE"))) {
                if (false) {
                    //当作Date处理
                    //lacom.setExpirydate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("EXPIRYDATE")));
                } else {
                    //该字段业务含义为日期，但在数据库中仍然是varchar类型。
                    lacom.setExpirydate(lacomQueryRes.getString("EXPIRYDATE"));
                }
            }
            if (StringUtils.isNotEmpty(lacomQueryRes.getString("CONTRACTDATE"))) {
                if (false) {
                    //当作Date处理
                    //lacom.setContractdate(new SimpleDateFormat("yyyy-MM-dd").parse(lacomQueryRes.getString("CONTRACTDATE")));
                } else {
                    //该字段业务含义为日期，但在数据库中仍然是varchar类型。
                    lacom.setExpirydate(lacomQueryRes.getString("CONTRACTDATE"));
                }
            }
            lacom.setBusinesscode(lacomQueryRes.getString("BusinessScope"));
            lacom.setBusinessarea(lacomQueryRes.getString("BusinessArea"));
            lacom.setManagername(lacomQueryRes.getString("ManagerName"));
            lacom.setIllegalrecord(lacomQueryRes.getString("IllegalRecord"));
            //添加元素至list
            lacomList.add(lacom);
        }

//        if (errList.size()>0){
//            //目前异常数据不多，使用循环方式插入，后续异常数据量变多的场景，这里需要提高效率。
//            errList.forEach(s->{
//                salMgtMapper.inseertSMCfgTableErr(s);
//            });
//
//        }

        if (!CollectionUtils.isEmpty(errList)) {
            dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.SMCFGTABLEERR, 600, errList);
        }
        return lacomList;
    }

    /**
     * 获取配置表数据:LDComF-直销管理机构信息表
     */
    private List<LDComF> getLDComFData(ResultSet ldComFQueryRes) throws Exception {
        List<LDComF> lDComFList = new ArrayList<>();
        List<SMCfgTableErr> errList = new ArrayList<>();

        for (; ldComFQueryRes.next(); ) {
            LDComF ldComF = new LDComF();
            ldComF.setComcode(ldComFQueryRes.getString("ComCode"));
            ldComF.setOutcomcode(ldComFQueryRes.getString("OutComCode"));
            if (StringUtils.isNotEmpty(ldComFQueryRes.getString("ZOutComCode"))) {
                ldComF.setZoutcomcode(ldComFQueryRes.getString("ZOutComCode"));
            } else {
                logger.info("直销类营业部还原机构代码不可为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LDCOMF, "ZOutComCode", null, PRIKEY_ABNORMAL));
                continue;
            }
            ldComF.setBranchtype(ldComFQueryRes.getString("BranchType"));
            ldComF.setT0code(ldComFQueryRes.getString("T0Code"));
            ldComF.setT2code(ldComFQueryRes.getString("T2Code"));
            ldComF.setT1code(ldComFQueryRes.getString("T1Code"));
            ldComF.setComgrade(ldComFQueryRes.getString("ComGrade"));
            ldComF.setOrganno(ldComFQueryRes.getString("OrganNo"));
            ldComF.setV1(ldComFQueryRes.getString("V1"));
            ldComF.setV2(ldComFQueryRes.getString("V2"));
            ldComF.setV3(ldComFQueryRes.getString("V3"));
            ldComF.setV4(ldComFQueryRes.getString("V4"));
            if (StringUtils.isNotEmpty(ldComFQueryRes.getString("D5"))) {
                ldComF.setD5(new SimpleDateFormat("yyyy-MM-dd").parse(ldComFQueryRes.getString("D5")));
            }
            ldComF.setV6(ldComFQueryRes.getString("V6"));
            ldComF.setOperator(ldComFQueryRes.getString("Operator"));
            if (StringUtils.isNotEmpty(ldComFQueryRes.getString("MakeDate"))) {
                ldComF.setMakedate(new SimpleDateFormat("yyyy-MM-dd").parse(ldComFQueryRes.getString("MakeDate")));
            }
            ldComF.setMaketime(ldComFQueryRes.getString("MakeTime"));
            ldComF.setZipcode(ldComFQueryRes.getString("zipcode"));
            ldComF.setFounddate(ldComFQueryRes.getString("founddate"));
            ldComF.setPhone(ldComFQueryRes.getString("phone"));
            ldComF.setState(ldComFQueryRes.getString("state"));
            ldComF.setEnddate(ldComFQueryRes.getString("enddate"));
            ldComF.setAddress(ldComFQueryRes.getString("address"));
            ldComF.setFax(ldComFQueryRes.getString("fax"));
            ldComF.setWebaddresss(ldComFQueryRes.getString("webaddresss"));
            ldComF.setNoti(ldComFQueryRes.getString("noti"));
            ldComF.setCalflag(ldComFQueryRes.getString("calflag"));
            ldComF.setInsureid(ldComFQueryRes.getString("insureid"));
            if (StringUtils.isNotEmpty(ldComFQueryRes.getString("modifydate"))) {
                ldComF.setModifydate(new SimpleDateFormat("yyyy-MM-dd").parse(ldComFQueryRes.getString("modifydate")));
            }
            ldComF.setModifytime(ldComFQueryRes.getString("modifytime"));
            ldComF.setGrpNature(ldComFQueryRes.getString("GrpNature"));
            ldComF.setBusinesscode(ldComFQueryRes.getString("businesscode"));
            ldComF.setSocialcode(ldComFQueryRes.getString("SOCIALCODE"));
            ldComF.setSocialtype(ldComFQueryRes.getString("SocialType"));
            ldComF.setBusinesslicensenumber(ldComFQueryRes.getString("BusinessLicenseNumber"));
            ldComF.setBusinesslicensenamecode(ldComFQueryRes.getString("BusinessLicenseNameCode"));
            ldComF.setExpirydate(ldComFQueryRes.getString("ExpiryDate"));
            ldComF.setContractdate(ldComFQueryRes.getString("ContractDate"));
            ldComF.setBusinesscode(ldComFQueryRes.getString("BusinessScope"));
            ldComF.setBusinessarea(ldComFQueryRes.getString("BusinessArea"));
            ldComF.setManagername(ldComFQueryRes.getString("ManagerName"));
            ldComF.setIllegalrecord(ldComFQueryRes.getString("IllegalRecord"));
            //添加元素至list
            lDComFList.add(ldComF);
        }

        if (errList.size() > 0) {
            //目前异常数据不多，使用循环方式插入，后续异常数据量变多的场景，这里需要提高效率。
            errList.forEach(s -> {
                salMgtMapper.inseertSMCfgTableErr(s);
            });
        }
        return lDComFList;
    }

    /**
     * 获取配置表数据:LMRiskApp-险种信息表
     */
    private List<LMRiskApp> getLMRiskAppData(ResultSet lmRiskAppQueryRes) throws Exception {
        List<LMRiskApp> lMRiskAppList = new ArrayList<>();
        List<SMCfgTableErr> errList = new ArrayList<>();

        for (; lmRiskAppQueryRes.next(); ) {
            LMRiskApp lmRiskApp = new LMRiskApp();

            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKCODE"))) {
                lmRiskApp.setRiskcode(lmRiskAppQueryRes.getString("RISKCODE"));
            } else {
                logger.info("险种编码不可为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LMRISKAPP, "RISKCODE", null, PRIKEY_ABNORMAL));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKVER"))) {
                lmRiskApp.setRiskver(lmRiskAppQueryRes.getString("RISKVER"));
            } else {
                logger.info("险种编码不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "RISKVER"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKNAME"))) {
                lmRiskApp.setRiskname(lmRiskAppQueryRes.getString("RISKNAME"));
            } else {
                logger.info("险种名称不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "RISKNAME"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("KINDCODE"))) {
                lmRiskApp.setKindcode(lmRiskAppQueryRes.getString("KINDCODE"));
            } else {
                logger.info("险类编码不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "KINDCODE"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKTYPE"))) {
                lmRiskApp.setRisktype(lmRiskAppQueryRes.getString("RISKTYPE"));
            } else {
                logger.info("险种分类不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "RISKTYPE"));
                continue;
            }
            lmRiskApp.setRisktype1(lmRiskAppQueryRes.getString("RISKTYPE1"));
            lmRiskApp.setRisktype2(lmRiskAppQueryRes.getString("RISKTYPE2"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKPROP"))) {
                lmRiskApp.setRiskprop(lmRiskAppQueryRes.getString("RISKPROP"));
            } else {
                logger.info("险种性质不可为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LMRISKAPP, "RISKPROP", null, PRIKEY_ABNORMAL));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKPERIOD"))) {
                lmRiskApp.setRiskperiod(lmRiskAppQueryRes.getString("RISKPERIOD"));
            } else {
                logger.info("险种类别不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "RISKPERIOD"));
                continue;
            }
            lmRiskApp.setRisktypedetail(lmRiskAppQueryRes.getString("RISKTYPEDETAIL"));
            lmRiskApp.setRiskflag(lmRiskAppQueryRes.getString("RISKFLAG"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("POLTYPE"))) {
                lmRiskApp.setPoltype(lmRiskAppQueryRes.getString("POLTYPE"));
            } else {
                logger.info("保单类型不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "POLTYPE"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("INVESTFLAG"))) {
                lmRiskApp.setInvestflag(lmRiskAppQueryRes.getString("INVESTFLAG"));
            } else {
                logger.info("投资标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "INVESTFLAG"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("BONUSFLAG"))) {
                lmRiskApp.setBonusflag(lmRiskAppQueryRes.getString("BONUSFLAG"));
            } else {
                logger.info("分红标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "BONUSFLAG"));
                continue;
            }
            lmRiskApp.setBonusmode(lmRiskAppQueryRes.getString("BONUSMODE"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("LISTFLAG"))) {
                lmRiskApp.setListflag(lmRiskAppQueryRes.getString("LISTFLAG"));
            } else {
                logger.info("有无名单标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "LISTFLAG"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("SUBRISKFLAG"))) {
                lmRiskApp.setSubriskflag(lmRiskAppQueryRes.getString("SUBRISKFLAG"));
            } else {
                logger.info("主附险标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "SUBRISKFLAG"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("CALDIGITAL"))) {
                lmRiskApp.setCaldigital(Integer.valueOf(lmRiskAppQueryRes.getString("CALDIGITAL")));
            }
            lmRiskApp.setCalchomode(lmRiskAppQueryRes.getString("CALCHOMODE"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RISKAMNTMULT"))) {
                lmRiskApp.setRiskamntmult(Integer.valueOf(lmRiskAppQueryRes.getString("RISKAMNTMULT")));
            }
            lmRiskApp.setInsuperiodflag(lmRiskAppQueryRes.getString("INSUPERIODFLAG"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("MAXENDPERIOD"))) {
                lmRiskApp.setMaxendperiod(Integer.valueOf(lmRiskAppQueryRes.getString("MAXENDPERIOD")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("AGELMT"))) {
                lmRiskApp.setAgelmt(Integer.valueOf(lmRiskAppQueryRes.getString("AGELMT")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("SIGNDATECALMODE"))) {
                lmRiskApp.setSigndatecalmode(Integer.valueOf(lmRiskAppQueryRes.getString("SIGNDATECALMODE")));
            }
            lmRiskApp.setProtocolflag(lmRiskAppQueryRes.getString("PROTOCOLFLAG"));
            lmRiskApp.setGetchgflag(lmRiskAppQueryRes.getString("GETCHGFLAG"));
            lmRiskApp.setProtocolpayflag(lmRiskAppQueryRes.getString("PROTOCOLPAYFLAG"));
            lmRiskApp.setEnsuplanflag(lmRiskAppQueryRes.getString("ENSUPLANFLAG"));
            lmRiskApp.setEnsuplanadjflag(lmRiskAppQueryRes.getString("ENSUPLANADJFLAG"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("STARTDATE"))) {
                lmRiskApp.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(lmRiskAppQueryRes.getString("STARTDATE")));
            } else {
                logger.info("开办日期不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "STARTDATE"));
                continue;
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("ENDDATE"))) {
                lmRiskApp.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(lmRiskAppQueryRes.getString("ENDDATE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("MINAPPNTAGE"))) {
                lmRiskApp.setMinappntage(Integer.valueOf(lmRiskAppQueryRes.getString("MINAPPNTAGE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("MAXAPPNTAGE"))) {
                lmRiskApp.setMaxappntage(Integer.valueOf(lmRiskAppQueryRes.getString("MAXAPPNTAGE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("MAXINSUREDAGE"))) {
                lmRiskApp.setMaxinsuredage(Integer.valueOf(lmRiskAppQueryRes.getString("MAXINSUREDAGE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("MININSUREDAGE"))) {
                lmRiskApp.setMinunsuredage(Integer.valueOf(lmRiskAppQueryRes.getString("MININSUREDAGE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("APPINTEREST"))) {
                lmRiskApp.setAppinterest(Double.valueOf(lmRiskAppQueryRes.getString("APPINTEREST")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("APPPREMRATE"))) {
                lmRiskApp.setApppremrate(Double.valueOf(lmRiskAppQueryRes.getString("APPPREMRATE")));
            }
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("INSUREDFLAG"))) {
                lmRiskApp.setInsuredflag(lmRiskAppQueryRes.getString("INSUREDFLAG"));
            } else {
                logger.info("多被保人标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "INSUREDFLAG"));
                continue;
            }
            lmRiskApp.setShareflag(lmRiskAppQueryRes.getString("SHAREFLAG"));
            lmRiskApp.setBnfflag(lmRiskAppQueryRes.getString("BNFFLAG"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("TEMPPAYFLAG"))) {
                lmRiskApp.setTemppatflag(lmRiskAppQueryRes.getString("TEMPPAYFLAG"));
            } else {
                logger.info("暂缴费标记不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LMRISKAPP, "RISKCODE", lmRiskAppQueryRes.getString("RISKCODE"), "TEMPPAYFLAG"));
                continue;
            }
            lmRiskApp.setInppayplan(lmRiskAppQueryRes.getString("INPPAYPLAN"));
            lmRiskApp.setImpartflag(lmRiskAppQueryRes.getString("IMPARTFLAG"));
            lmRiskApp.setInsuexpeflag(lmRiskAppQueryRes.getString("INSUEXPEFLAG"));
            lmRiskApp.setLoanfalg(lmRiskAppQueryRes.getString("LOANFALG"));
            lmRiskApp.setMortagageflag(lmRiskAppQueryRes.getString("MORTAGAGEFLAG"));
            lmRiskApp.setIdifreturnflag(lmRiskAppQueryRes.getString("IDIFRETURNFLAG"));
            lmRiskApp.setCutamntstoppay(lmRiskAppQueryRes.getString("CUTAMNTSTOPPAY"));
            if (StringUtils.isNotEmpty(lmRiskAppQueryRes.getString("RINSRATE"))) {
                lmRiskApp.setRinsrate(Double.valueOf(lmRiskAppQueryRes.getString("RINSRATE")));
            }
            lmRiskApp.setSaleflag(lmRiskAppQueryRes.getString("SALEFLAG"));
            lmRiskApp.setFileappflag(lmRiskAppQueryRes.getString("FILEAPPFLAG"));
            lmRiskApp.setMngcom(lmRiskAppQueryRes.getString("MNGCOM"));
            lmRiskApp.setAutopayflag(lmRiskAppQueryRes.getString("AUTOPAYFLAG"));
            lmRiskApp.setNeedprinthospital(lmRiskAppQueryRes.getString("NEEDPRINTHOSPITAL"));
            lmRiskApp.setNeedprintget(lmRiskAppQueryRes.getString("NEEDPRINTGET"));
            lmRiskApp.setRisktype3(lmRiskAppQueryRes.getString("RISKTYPE3"));
            lmRiskApp.setRisktype4(lmRiskAppQueryRes.getString("RISKTYPE4"));
            lmRiskApp.setRisktype5(lmRiskAppQueryRes.getString("RISKTYPE5"));
            lmRiskApp.setNotprintpol(lmRiskAppQueryRes.getString("NOTPRINTPOL"));
            lmRiskApp.setNeedgetpoldate(lmRiskAppQueryRes.getString("NEEDGETPOLDATE"));
            lmRiskApp.setNeedrereadbank(lmRiskAppQueryRes.getString("NEEDREREADBANK"));
            lmRiskApp.setSpecflag(lmRiskAppQueryRes.getString("SPECFLAG"));
            lmRiskApp.setInterestdifflag(lmRiskAppQueryRes.getString("INTERESTDIFFLAG"));
            lmRiskApp.setBranchtype(lmRiskAppQueryRes.getString("branchtype"));
            //添加元素至list
            lMRiskAppList.add(lmRiskApp);
        }

        if (errList.size() > 0) {
            //目前异常数据不多，使用循环方式插入，后续异常数据量变多的场景，这里需要提高效率。
            errList.forEach(s -> {
                salMgtMapper.inseertSMCfgTableErr(s);
            });
        }
        return lMRiskAppList;
    }

    /**
     * 获取配置表数据:LAGXJDBANKCOMPACT-合同信息表
     */
    private List<LagxjdbankCompact> getLagxjdbankCompactData(ResultSet lagxJdbankCompactQueryRes) throws Exception {
        List<LagxjdbankCompact> lagxjdbankCompactList = new ArrayList<>();
        List<SMCfgTableErr> errList = new ArrayList<>();

        for (; lagxJdbankCompactQueryRes.next(); ) {
            LagxjdbankCompact lagxjdbankCompact = new LagxjdbankCompact();

            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("COMPACTCODE"))) {
                lagxjdbankCompact.setCompactcode(lagxJdbankCompactQueryRes.getString("COMPACTCODE"));
            } else {
                logger.info("合同编码不可为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LAGXJDBANKCOMPACT, "COMPACTCODE", null, PRIKEY_ABNORMAL));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("COMPACTNAME"))) {
                lagxjdbankCompact.setCompactname(lagxJdbankCompactQueryRes.getString("COMPACTNAME"));
            } else {
                logger.info("合同名称不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDBANKCOMPACT, "COMPACTCODE", lagxJdbankCompactQueryRes.getString("COMPACTCODE"), "COMPACTNAME"));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("BANKLEVEL"))) {
                lagxjdbankCompact.setBanklevel(lagxJdbankCompactQueryRes.getString("BANKLEVEL"));
            } else {
                logger.info("中介机构级别不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDBANKCOMPACT, "COMPACTCODE", lagxJdbankCompactQueryRes.getString("COMPACTCODE"), "BANKLEVEL"));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("AGENTCOM"))) {
                lagxjdbankCompact.setAgentcom(lagxJdbankCompactQueryRes.getString("AGENTCOM"));
            } else {
                logger.info("中介机构编码不可为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDBANKCOMPACT, "COMPACTCODE", lagxJdbankCompactQueryRes.getString("COMPACTCODE"), "AGENTCOM"));
                continue;
            }
            lagxjdbankCompact.setBranchtype(lagxJdbankCompactQueryRes.getString("BRANCHTYPE"));
            lagxjdbankCompact.setBranchtype2(lagxJdbankCompactQueryRes.getString("BRANCHTYPE2"));
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("STARTDATE"))) {
                lagxjdbankCompact.setStartdate(new SimpleDateFormat("yyyy-MM-ddd").parse(lagxJdbankCompactQueryRes.getString("STARTDATE")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("ENDDATE"))) {
                lagxjdbankCompact.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("ENDDATE")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("SUSPENDDATE"))) {
                lagxjdbankCompact.setSuspenddate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("SUSPENDDATE")));
            }
            lagxjdbankCompact.setManagecom(lagxJdbankCompactQueryRes.getString("MANAGECOM"));
            lagxjdbankCompact.setCompactnature(lagxJdbankCompactQueryRes.getString("COMPACTNATURE"));
            lagxjdbankCompact.setReviewstatus(lagxJdbankCompactQueryRes.getString("REVIEWSTATUS"));
            lagxjdbankCompact.setV1(lagxJdbankCompactQueryRes.getString("V1"));
            lagxjdbankCompact.setV2(lagxJdbankCompactQueryRes.getString("V2"));
            lagxjdbankCompact.setV3(lagxJdbankCompactQueryRes.getString("V3"));
            lagxjdbankCompact.setV4(lagxJdbankCompactQueryRes.getString("V4"));
            lagxjdbankCompact.setV5(lagxJdbankCompactQueryRes.getString("V5"));
            lagxjdbankCompact.setV6(lagxJdbankCompactQueryRes.getString("V6"));
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D1"))) {
                lagxjdbankCompact.setD1(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D1")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D2"))) {
                lagxjdbankCompact.setD2(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D2")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D3"))) {
                lagxjdbankCompact.setD3(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D3")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D4"))) {
                lagxjdbankCompact.setD4(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D4")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D5"))) {
                lagxjdbankCompact.setD5(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D5")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("D6"))) {
                lagxjdbankCompact.setD6(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("D6")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I1"))) {
                lagxjdbankCompact.setI1(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I1")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I2"))) {
                lagxjdbankCompact.setI2(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I2")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I3"))) {
                lagxjdbankCompact.setI3(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I3")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I4"))) {
                lagxjdbankCompact.setI4(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I4")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I5"))) {
                lagxjdbankCompact.setI5(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I5")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("I6"))) {
                lagxjdbankCompact.setI6(Integer.valueOf(lagxJdbankCompactQueryRes.getString("I6")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC1"))) {
                lagxjdbankCompact.setDc1(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC1")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC2"))) {
                lagxjdbankCompact.setDc2(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC2")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC2"))) {
                lagxjdbankCompact.setDc2(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC2")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC3"))) {
                lagxjdbankCompact.setDc3(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC3")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC4"))) {
                lagxjdbankCompact.setDc4(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC4")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC5"))) {
                lagxjdbankCompact.setDc5(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC5")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("DC6"))) {
                lagxjdbankCompact.setDc6(Double.valueOf(lagxJdbankCompactQueryRes.getString("DC6")));
            }
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("MAKEDATE"))) {
                lagxjdbankCompact.setMakedate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("MAKEDATE")));
            }
            lagxjdbankCompact.setMaketime(lagxJdbankCompactQueryRes.getString("MAKETIME"));
            if (StringUtils.isNotEmpty(lagxJdbankCompactQueryRes.getString("MODIFYDATE"))) {
                lagxjdbankCompact.setModifydate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdbankCompactQueryRes.getString("MODIFYDATE")));
            }
            lagxjdbankCompact.setModifytime(lagxJdbankCompactQueryRes.getString("MODIFYTIME"));
            lagxjdbankCompact.setOperator(lagxJdbankCompactQueryRes.getString("OPERATOR"));
            lagxjdbankCompact.setNoti(lagxJdbankCompactQueryRes.getString("NOTI"));

            //添加元素至list
            lagxjdbankCompactList.add(lagxjdbankCompact);
        }

        if (errList.size() > 0) {
            //目前异常数据不多，使用循环方式插入，后续异常数据量变多的场景，这里需要提高效率。
            errList.forEach(s -> {
                salMgtMapper.inseertSMCfgTableErr(s);
            });
        }
        return lagxjdbankCompactList;
    }

    /**
     * 获取配置表数据:LAGXJDRATECHARGE-手续费率表
     */
    private List<LagxjdrateCharge> getLagxjdrateChargeData(ResultSet lagxJdrateChargeQueryRes) throws Exception {
        List<LagxjdrateCharge> lagxjdrateChargeList = new ArrayList<>();
        List<SMCfgTableErr> errList = new ArrayList<>();

        for (; lagxJdrateChargeQueryRes.next(); ) {
            LagxjdrateCharge lagxjdrateCharge = new LagxjdrateCharge();

            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("RATESN"))) {
                lagxjdrateCharge.setRatesn(Integer.valueOf(lagxJdrateChargeQueryRes.getString("RATESN")));
            } else {
                logger.info("手续费率流水号不能为空");
                errList.add(getCfgTableErrDataInfo(false, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", null, PRIKEY_ABNORMAL));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("COMPACTCODE"))) {
                lagxjdrateCharge.setCompactcode(lagxJdrateChargeQueryRes.getString("COMPACTCODE"));
            } else {
                logger.info("合同号不能为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", lagxJdrateChargeQueryRes.getString("RATESN"), "COMPACTCODE"));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("RATETYPE"))) {
                lagxjdrateCharge.setRatetype(lagxJdrateChargeQueryRes.getString("RATETYPE"));
            } else {
                logger.info("费率类型不能为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", lagxJdrateChargeQueryRes.getString("RATESN"), "RATETYPE"));
                continue;
            }
            lagxjdrateCharge.setRiskcode(lagxJdrateChargeQueryRes.getString("RISKCODE"));
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("PAYYEARS"))) {
                lagxjdrateCharge.setPayyears(Integer.valueOf(lagxJdrateChargeQueryRes.getString("PAYYEARS")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("PAYYEARSSTART"))) {
                lagxjdrateCharge.setPayyearsstart(Integer.valueOf(lagxJdrateChargeQueryRes.getString("PAYYEARSSTART")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("PAYYEARSEND"))) {
                lagxjdrateCharge.setPayyearsend(Integer.valueOf(lagxJdrateChargeQueryRes.getString("PAYYEARSEND")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("PAYYEARSTART"))) {
                lagxjdrateCharge.setPayyearstart(Integer.valueOf(lagxJdrateChargeQueryRes.getString("PAYYEARSTART")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("PAYYEAREND"))) {
                lagxjdrateCharge.setPayyearend(Integer.valueOf(lagxJdrateChargeQueryRes.getString("PAYYEAREND")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("NEWFYPSUMUP"))) {
                lagxjdrateCharge.setNewfypsumup(Double.valueOf(lagxJdrateChargeQueryRes.getString("NEWFYPSUMUP")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("NEWFYPSUMDOWN"))) {
                lagxjdrateCharge.setNewfypsumdown(Double.valueOf(lagxJdrateChargeQueryRes.getString("NEWFYPSUMDOWN")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("CONTINUERATEUP"))) {
                lagxjdrateCharge.setContinuerateup(Double.valueOf(lagxJdrateChargeQueryRes.getString("CONTINUERATEUP")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("CONTINUERATEDOWN"))) {
                lagxjdrateCharge.setContinueratedown(Double.valueOf(lagxJdrateChargeQueryRes.getString("CONTINUERATEDOWN")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("RATE"))) {
                lagxjdrateCharge.setRate(Double.valueOf(lagxJdrateChargeQueryRes.getString("RATE")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("CHARGE"))) {
                lagxjdrateCharge.setCharge(Double.valueOf(lagxJdrateChargeQueryRes.getString("CHARGE")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("CHARGEUP"))) {
                lagxjdrateCharge.setChargeup(Double.valueOf(lagxJdrateChargeQueryRes.getString("CHARGEUP")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("CHARGEDOWN"))) {
                lagxjdrateCharge.setChargedown(Double.valueOf(lagxJdrateChargeQueryRes.getString("CHARGEDOWN")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("STARTDATE"))) {
                lagxjdrateCharge.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("STARTDATE")));
            } else {
                logger.info("生效日期起期/承保日期起期不能为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", lagxJdrateChargeQueryRes.getString("RATESN"), "STARTDATE"));
                continue;
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("ENDDATE"))) {
                lagxjdrateCharge.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("ENDDATE")));
            } else {
                logger.info("生效日期止期/承保日期止期不能为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", lagxJdrateChargeQueryRes.getString("RATESN"), "ENDDATE"));
                continue;
            }
            lagxjdrateCharge.setReviewstatus(lagxJdrateChargeQueryRes.getString("REVIEWSTATUS"));
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("ENDDATE"))) {
                lagxjdrateCharge.setBranchtype(lagxJdrateChargeQueryRes.getString("BranchType"));
            } else {
                logger.info("BranchType不能为空");
                errList.add(getCfgTableErrDataInfo(true, SM_CFGTABLE_LAGXJDRATECHARGE, "RATESN", lagxJdrateChargeQueryRes.getString("RATESN"), "BranchType"));
                continue;
            }
            lagxjdrateCharge.setBranchtype2(lagxJdrateChargeQueryRes.getString("BranchType2"));
            lagxjdrateCharge.setV1(lagxJdrateChargeQueryRes.getString("V1"));
            lagxjdrateCharge.setV2(lagxJdrateChargeQueryRes.getString("V2"));
            lagxjdrateCharge.setV3(lagxJdrateChargeQueryRes.getString("V3"));
            lagxjdrateCharge.setV4(lagxJdrateChargeQueryRes.getString("V4"));
            lagxjdrateCharge.setV5(lagxJdrateChargeQueryRes.getString("V5"));
            lagxjdrateCharge.setV6(lagxJdrateChargeQueryRes.getString("V6"));
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D1"))) {
                lagxjdrateCharge.setD1(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D1")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D2"))) {
                lagxjdrateCharge.setD2(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D2")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D3"))) {
                lagxjdrateCharge.setD3(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D3")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D4"))) {
                lagxjdrateCharge.setD4(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D4")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D5"))) {
                lagxjdrateCharge.setD5(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D5")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("D6"))) {
                lagxjdrateCharge.setD6(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("D6")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I1"))) {
                lagxjdrateCharge.setI1(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I1")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I2"))) {
                lagxjdrateCharge.setI2(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I2")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I3"))) {
                lagxjdrateCharge.setI3(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I3")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I4"))) {
                lagxjdrateCharge.setI4(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I4")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I5"))) {
                lagxjdrateCharge.setI5(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I5")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("I6"))) {
                lagxjdrateCharge.setI6(Integer.valueOf(lagxJdrateChargeQueryRes.getString("I6")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC1"))) {
                lagxjdrateCharge.setDc1(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC1")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC2"))) {
                lagxjdrateCharge.setDc2(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC2")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC3"))) {
                lagxjdrateCharge.setDc3(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC3")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC4"))) {
                lagxjdrateCharge.setDc4(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC4")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC5"))) {
                lagxjdrateCharge.setDc5(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC5")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("DC6"))) {
                lagxjdrateCharge.setDc6(Double.valueOf(lagxJdrateChargeQueryRes.getString("DC6")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("MAKEDATE"))) {
                lagxjdrateCharge.setMakedate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("MAKEDATE")));
            }
            lagxjdrateCharge.setMaketime(lagxJdrateChargeQueryRes.getString("MAKETIME"));
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("MODIFYDATE"))) {
                lagxjdrateCharge.setModifydate(new SimpleDateFormat("yyyy-MM-dd").parse(lagxJdrateChargeQueryRes.getString("MODIFYDATE")));
            }
            lagxjdrateCharge.setModifytime(lagxJdrateChargeQueryRes.getString("MODIFYTIME"));
            lagxjdrateCharge.setOperator(lagxJdrateChargeQueryRes.getString("OPERATOR"));
            lagxjdrateCharge.setContinuerate(lagxJdrateChargeQueryRes.getString("CONTINUERATE"));
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("NEWPCPRMSUMUP"))) {
                lagxjdrateCharge.setNewfypsumup(Double.valueOf(lagxJdrateChargeQueryRes.getString("NEWPCPRMSUMUP")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("NEWPCPRMSUMDOWN"))) {
                lagxjdrateCharge.setNewpcprmsumdown(Double.valueOf(lagxJdrateChargeQueryRes.getString("NEWPCPRMSUMDOWN")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("RPAIDPCPRMUP"))) {
                lagxjdrateCharge.setRpaidpcprmup(Double.valueOf(lagxJdrateChargeQueryRes.getString("RPAIDPCPRMUP")));
            }
            if (StringUtils.isNotEmpty(lagxJdrateChargeQueryRes.getString("RPAIDPCPRMDOWN"))) {
                lagxjdrateCharge.setRpaidpcprmdown(Double.valueOf(lagxJdrateChargeQueryRes.getString("RPAIDPCPRMDOWN")));
            }

            //添加元素至list
            lagxjdrateChargeList.add(lagxjdrateCharge);
        }

        if (errList.size() > 0) {
            //目前异常数据不多，使用循环方式插入，后续异常数据量变多的场景，这里需要提高效率。
            errList.forEach(s -> {
                salMgtMapper.inseertSMCfgTableErr(s);
            });
        }
        return lagxjdrateChargeList;
    }

    /**
     * 批量插入业务数据
     */
    private String insertBusinessData(@NotNull List<TraSxfInterfaceDetailsT> detailsTList) throws Exception {
        return dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.TRASXFINTERFACEDETAILST, 600, detailsTList);
    }

    /**
     * 批量插入业务数据
     */
    private String insertSplitBusinessData(@NotNull List<TraSxfInterfaceDetailsT> detailsTListSplit) throws Exception {
        return dbBatchManager.batchInsert(DBExecutorType.FOREACH, MybatisBatchOperationType.TRASXFINTERFACEDETAILSTSPLIT, 600, detailsTListSplit);
    }

    /**
     * 单个插入业务数据
     */
    private String insertBusinessData(@NotNull TraSxfInterfaceDetailsT detailsT) throws Exception {
        String result = null;
        try {
            salMgtMapper.insertTraSxfDetails(detailsT);
        } catch (Exception e) {
            result = "业务数据插入异常：" + e.toString();
        }
        return result;
    }

    private long insertCfgTableData(@NotNull SalMgtModel salMgtModel) throws Exception {
        return 0;
    }


    public List<TraSxfInterfaceDetailsT> getSalMgtDataBySeparationChar(@NotNull String filePath) throws Exception {
        List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();

        /**
         * -------------------------------------------------------------------
         * 此处后续会实现从用户指定的位置获取txt数据的逻辑
         * */
        logger.info("模拟获取数据源，获取开始");
        //读取txt文件流
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufReader = new BufferedReader(inputStreamReader);
        logger.info("模拟获取数据源，获取成功，获取结束");
        /**
         * -------------------------------------------------------------------
         * */

        //第一行为列的名称
        String line = bufReader.readLine();
        StringBuffer columnStrBuf = new StringBuffer();
        if (line == null) {
            logger.info("数据文件存在异常，请检查文件是否为空");
        }
        columnStrBuf.append(line);
        String columnStr = columnStrBuf.toString();
        String[] columnArr = columnStr.split(SEPARATION_CHAR_DEFAULT);
        //validate
        if ((!columnArr[0].equals("INTERFACE_ID")) || (columnArr.length != salMgtMapper.countColumn())) {
            logger.info("数据文件存在异常，请检查文件格式是否正确");
        }

        //从第二行开始均为数据
        String dataStr = "";
        StringBuffer dataStrBuf = new StringBuffer();
        while ((dataStr = bufReader.readLine()) != null) {
            TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
            String[] dataArr = dataStr.split(SEPARATION_CHAR_DEFAULT);
            /**
             * 主键
             * */
            //INTERFACE_ID
            if (dataArr[0] != null) {
                detailsT.setInterfaceId(dataArr[0]);
            } else {
                logger.info("内部主键不能为空");
                return null;
            }
            //BATCH_ID
            if (dataArr[1] != null) {
                detailsT.setBatchId(dataArr[1]);
            } else {
                logger.info("批次号不能为空");
                return null;
            }
            /**
             * 手续费信息
             * */
            //TRANSACTION_SOURCE
            if (dataArr[2] != null) {
                detailsT.setTransactionSource(dataArr[2]);
            } else {
                logger.info("来源系统编码不能为空");
                return null;
            }
            //TRANSACTION_TYPE
            if (dataArr[3] != null) {
                detailsT.setTransactionType(dataArr[3]);
            } else {
                logger.info("交易类型不能为空");
                return null;
            }
            //CHANNEL_CODE
            if (dataArr[4] != null) {
                detailsT.setChannelCode(dataArr[4]);
            } else {
                logger.info("渠道代码不能为空");
                return null;
            }
            //CHANNEL_DESC
            if (dataArr[5] != null) {
                detailsT.setChannelDesc(dataArr[5]);
            } else {
                logger.info("渠道名称不能为空");
                return null;
            }
            //SETTLEMENT_TYPE_CODE
            if (dataArr[6] != null) {
                detailsT.setSettlementTypeCode(dataArr[6]);
            }
            else{
                logger.info("结算单类型编码不能为空");
                return null;
            }
            //SETTLEMENT_TYPE_NAME
            if (dataArr[7] != null) {
                detailsT.setSettlementTypeName(dataArr[7]);
            }
            else{
                logger.info("结算单类型名称不能为空");
                return null;
            }
            //SERVICE_FEE_CATEGORY_CODE
            if (dataArr[8] != null) {
                detailsT.setServiceFeeCategoryCode(dataArr[8]);
            } else {
                logger.info("手续费类型编码不能为空");
                return null;
            }
            //SERVICE_FEE_CATEGORY_NAME
            if (dataArr[9] != null) {
                detailsT.setServiceFeeCategoryName(dataArr[9]);
            } else {
                logger.info("手续费类型名称不能为空");
                return null;
            }
            //TRANSACTION_DATE
            if (dataArr[10] != null) {
                detailsT.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[10]));
            } else {
                logger.info("业务日期不能为空");
                return null;
            }
            //AGENCY_CODE
            if (dataArr[11] != null) {
                detailsT.setAgencyCode(dataArr[11]);
            }
            //AGENCY_NAME
            if (dataArr[12] != null) {
                detailsT.setAgencyName(dataArr[12]);
            }
            //AGENCY_COMPANY_CODE
            if (dataArr[13] != null) {
                detailsT.setAgencyCompanyCode(dataArr[13]);
            }
            //AGENCY_COMPANY_NAME
            if (dataArr[14] != null) {
                detailsT.setAgencyCompanyName(dataArr[14]);
            }
            //AP_MONEY
            if (dataArr[15] != null) {
                detailsT.setApMoney(Double.valueOf(dataArr[15]));
            }
            //COMMISSION_MONEY
            if (dataArr[16] != null) {
                detailsT.setCommissionMoney(Double.valueOf(dataArr[16]));
            }
            //COMMISSION_NO
            if (dataArr[17] != null) {
                detailsT.setCommissionNo(dataArr[17]);
            }
            //COMMISSION_DATE
            if (dataArr[18] != null) {
                detailsT.setCommissionDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[18]));
            }
            //AGREEMENT_CODE
            if (dataArr[19] != null) {
                detailsT.setAgreementCode(dataArr[19]);
            }
            //AGREEMENT_NAME
            if (dataArr[20] != null) {
                detailsT.setAgreementName(dataArr[20]);
            }
            //AGREEMENT_STARTDATE
            if (dataArr[21] != null) {
                detailsT.setAgreementStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[21]));
            }
            //AGREEMENT_ENDDATE
            if (dataArr[22] != null) {
                detailsT.setAgreementStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[22]));
            }
            //SERVICE_FEE_STATUS
            if (dataArr[23] != null) {
                detailsT.setServiceFeeStatus(dataArr[23]);
            }
            //INITIAL_YEAR_DESC
            if (dataArr[24] != null) {
                detailsT.setInitialYearDesc(dataArr[24]);
            }
            /**
             * 保单数据信息
             * */
            //POLICY_NO
            if (dataArr[25] != null) {
                detailsT.setPolicyNo(dataArr[25]);
            } else {
                logger.info("保单号不能为空");
                return null;
            }
            //APPLICATION_NO
            if (dataArr[26] != null) {
                detailsT.setApplicationNo(dataArr[26]);
            } else {
                logger.info("投保单号不能为空");
                return null;
            }
            //POLICY_HOLDER_NAME
            if (dataArr[27] != null) {
                detailsT.setPolicyHolderName(dataArr[27]);
            } else {
                logger.info("投保人姓名不能为空");
                return null;
            }
            //POLICY_ORG_CODE
            if (dataArr[28] != null) {
                detailsT.setPolicyOrgCode(dataArr[28]);
            } else {
                logger.info("保单管理机构不能为空");
                return null;
            }
            //PRODUCT_CODE
            if (dataArr[29] != null) {
                detailsT.setProductCode(dataArr[29]);
            } else {
                logger.info("产品代码不能为空");
                return null;
            }
            //PRODUCT_NAME
            if (dataArr[30] != null) {
                detailsT.setProductName(dataArr[30]);
            } else {
                logger.info("产品名称不能为空");
                return null;
            }
            //PAYMENT_FREQUENCY_DESC
            if (dataArr[31] != null) {
                detailsT.setPaymentFrequencyDesc(dataArr[31]);
            } else {
                logger.info("缴别名称不能为空");
                return null;
            }
            //SIGNDATE
            if (dataArr[32] != null) {
                detailsT.setSignDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[32]));
            } else {
                logger.info("保单承保日期不能为空");
                return null;
            }
            //ENTERACCDATE
            if (dataArr[33] != null) {
                detailsT.setEnterAccDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[33]));
            } else {
                logger.info("保单实收日期不能为空");
                return null;
            }
            //CVALIDATE
            if (dataArr[34] != null) {
                detailsT.setCvaliDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[34]));
            } else {
                logger.info("保单生效日期不能为空");
                return null;
            }
            //PREM
            if (dataArr[35] != null) {
                detailsT.setPrem(Double.valueOf(dataArr[35]));
            } else {
                logger.info("险种保费不能为空");
                logger.info("险种保费不能为空");
                return null;
            }
            //CONTSTATE
            if (dataArr[36] != null) {
                detailsT.setContState(dataArr[36]);
            } else {
                logger.info("保单状态不能为空");
                return null;
            }
            /**
             * 支付信息数据
             * */
            //BILLING_PAY_ORG_CODE
            if (dataArr[37] != null) {
                detailsT.setBillingPayOrgCode(dataArr[37]);
            }
            //BANK_ACCOUNT_NO
            if (dataArr[38] != null) {
                detailsT.setBankAccountNo(dataArr[38]);
            }
            //BANK_ACCOUNT_NAME
            if (dataArr[39] != null) {
                detailsT.setBankAccountName(dataArr[39]);
            }
            //TARGET_BANK_ACCOUNT_NO
            if (dataArr[40] != null) {
                detailsT.setTargetBankAccountNo(dataArr[40]);
            }
            //TARGET_BANK_ACCOUNT_NAME
            if (dataArr[41] != null) {
                detailsT.setTargetBankAccountName(dataArr[41]);
            }
            //CURRENCY_CODE
            if (dataArr[42] != null) {
                detailsT.setCurrencyCode(dataArr[42]);
            } else {
                logger.info("币种不能为空");
                return null;
            }
            //SERVICE_FEE_AMOUNT
            if (dataArr[43] != null) {
                detailsT.setServiceFeeAmount(Double.valueOf(dataArr[43]));
            }
            //TOTAL_AMOUNT
            if (dataArr[44] != null) {
                detailsT.setTotalAmount(Double.valueOf(dataArr[44]));
            }
            //TAX_AMOUNT
            if (dataArr[45] != null) {
                detailsT.setTaxAmount(Double.valueOf(dataArr[45]));
            }
            //GETACCDATE
            if (dataArr[46] != null) {//格式要求：YYYY/MM/DD
                detailsT.setGetAccDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[46]));
            }
            //RETURNACCDATE
            if (dataArr[47] != null) {//格式要求：YYYY/MM/DD
                detailsT.setReturnAccDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[47]));
            }
            //RETURNFLAG
            if (dataArr[48] != null) {
                detailsT.setReturnFlag(dataArr[48]);
            }
            /**
             * 系统表通用规范
             * */
            //ATTRIBUTE1
            if (dataArr[49] != null) {
                detailsT.setAttribute1(dataArr[49]);
            }
            //ATTRIBUTE2
            if (dataArr[50] != null) {
                detailsT.setAttribute2(dataArr[50]);
            }
            //ATTRIBUTE3
            if (dataArr[51] != null) {
                detailsT.setAttribute3(dataArr[51]);
            }
            //ATTRIBUTE4
            if (dataArr[52] != null) {
                detailsT.setAttribute4(dataArr[52]);
            }
            //ATTRIBUTE5
            if (dataArr[53] != null) {
                detailsT.setAttribute5(dataArr[53]);
            }
            //ATTRIBUTE6
            if (dataArr[54] != null) {
                detailsT.setAttribute6(dataArr[54]);
            }
            //ATTRIBUTE7
            if (dataArr[55] != null) {
                detailsT.setAttribute7(dataArr[55]);
            }
            //ATTRIBUTE8
            if (dataArr[56] != null) {
                detailsT.setAttribute8(dataArr[56]);
            }
            //ATTRIBUTE9
            if (dataArr[57] != null) {
                detailsT.setAttribute9(dataArr[57]);
            }
            //ATTRIBUTE10
            if (dataArr[58] != null) {
                detailsT.setAttribute1(dataArr[58]);
            }
            //BATCH_FLAG
            if (dataArr[59] != null) {
                detailsT.setBatchFlag(dataArr[59]);
            } else {
                logger.info("批次状态不能为空");
                return null;
            }
            //BATCH_MAKE_DATE
            if (dataArr[60] != null) {
                detailsT.setBatchMakeDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[60]));
            } else {
                logger.info("创建日期不能为空");
                return null;
            }
            //BATCH_MAKE_TIME
            if (dataArr[61] != null) {
                detailsT.setBatchMakeTime(dataArr[61]);
            } else {
                logger.info("创建时间不能为空");
                return null;
            }
            //BATCH_CREAT_USER
            if (dataArr[62] != null) {
                detailsT.setBatchCreatUser(dataArr[62]);
            } else {
                logger.info("创建人不能为空");
                return null;
            }
            //BATCH_MODIFY_DATE
            if (dataArr[63] != null) {
                detailsT.setBatchModifyDate(new SimpleDateFormat("yyyy-MM-dd").parse(dataArr[63]));
            } else {
                logger.info("最后更新日期不能为空");
                return null;
            }
            //BATCH_MODIFY_TIME
            if (dataArr[64] != null) {
                detailsT.setBatchModifyTime(dataArr[64]);
            } else {
                logger.info("最后更新时间不能为空");
                return null;
            }
            //BATCH_MODIFY_USER
            if (dataArr[65] != null) {
                detailsT.setBatchModifyUser(dataArr[65]);
            } else {
                logger.info("最后更新人不能为空");
                return null;
            }
            detailsTList.add(detailsT);
        }

        return detailsTList;
    }

    public List<TraSxfInterfaceDetailsT> getSalMgtDataByJson(@NotNull SalMgtModel salMgtModel) throws Exception {
        String filePath = salMgtModel.getFilePath();
        List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();
        TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();


        /**
         * -------------------------------------------------------------------
         * 此处后续会实现从用户指定的位置获取txt数据的逻辑
         * */
        logger.info("模拟获取数据源，获取开始");
        //读取txt文件流
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufReader = new BufferedReader(inputStreamReader);
        logger.info("模拟获取数据源，获取成功，获取结束");
        /**
         * -------------------------------------------------------------------
         * */

        try {
            String line = "";
            //读取每行内容
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                sb.append(line);
            }
            //去除空格
            String sbreplace = sb.toString().replace(" ", "");
            //转换成为JSONObject对象
            JSONObject jsonObj = JSONObject.parseObject(sbreplace);
            //获取txt文档中所有的JSONOBJECT对象。`JSON_OBJECT_NAME`是和用户约定好的名称，不可随意变换。
            JSONArray jsonObjectArr = (JSONArray) jsonObj.get(JSON_OBJECT_NAME);
            if (jsonObjectArr.isEmpty()) {
                logger.info("未获取到任何JsonObject对象的数据");
                return null;
            }
            for (int i = 0; i < jsonObjectArr.size(); i++) {
                String jsonStr = jsonObjectArr.get(i).toString();
                if (StringUtils.isNotEmpty(jsonStr)) {
                    try {
                        String aSeriNo = seriNo.getASeriNo(NoType.TRA_SXF_INTERFACE_DETAILS_T);
                        String batchId = salMgtModel.getBatchId();
                        String batchFlag = salMgtModel.getBatchFlag();
                        Date date = new Date();
                        String substring = new Date().toString().substring(11, 19);
                        String admin = "admin";
                        //销管推送的数据可能会有手续费类型编码组合在一起情况，业财需要拆分为一条条数据后再插入
                        List<TraSxfInterfaceDetailsT> detailsTSubList = ParseBusinessDataDetailJsonStrList(jsonStr);
                        if (detailsTSubList.size() > 0) {
                            detailsTSubList.stream().forEach(x -> x.setInterfaceId(aSeriNo));
                            detailsTSubList.stream().forEach(x -> x.setBatchId(batchId));
                            detailsTSubList.stream().forEach(x -> x.setBatchFlag(batchFlag));
                            detailsTSubList.stream().forEach(x -> x.setBatchMakeDate(date));
                            detailsTSubList.stream().forEach(x -> x.setBatchMakeTime(substring));
                            detailsTSubList.stream().forEach(x -> x.setBatchCreatUser(admin));
                            detailsTSubList.stream().forEach(x -> x.setBatchModifyDate(date));
                            detailsTSubList.stream().forEach(x -> x.setBatchModifyTime(substring));
                            detailsTSubList.stream().forEach(x -> x.setBatchModifyUser(admin));
                            //将解析的数据组装到detailsTList实例集合对象中
                            detailsTList.addAll(detailsTSubList);
                        } else {
                            logger.info("解析JSONSTR出现异常,异常的JsonStr：  " + jsonStr);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            bufReader.close();
        }

        if (detailsTList.size() <= 0) {
            return null;
        }
        return detailsTList;
    }

    /**
     * @description: 业务数据导入 不拆分合并的手续费
     * @date 2023/02/20/0020 15:25:12
     */
    private List<TraSxfInterfaceDetailsT> ParseBusinessDataDetailJsonStrList(@NotNull String jsonStr) throws ParseException {
        String replaceStr = jsonStr.toString().replace(" ", "");
        boolean parseFlag = true;
        JSONObject jsonObj = JSONObject.parseObject(replaceStr);
        TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
        List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();

        /**
         * @description: 1.如果serviceFeeCategoryCode 手续费类型为空，则返回null
        2.1如果 serviceFeeCategoryCode 手续费类型 不为空，并且serviceFeeCategoryCode 手续费类型 通过" "拆分后的数量大于一，
        则进入for循环，拆分赋值 并添加到list里面返回给上一层
        2.2 如果 serviceFeeCategoryCode 手续费类型 不为空，并且serviceFeeCategoryCode 手续费类型 通过" "拆分后的数量等于一，
        则直接赋值serviceFeeCategoryCode

         * @date 2023/02/20/0020 15:29:51
         */

        if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeCategoryCode")) {
            //不拆分时 从这里开始注释
//            String code = (String) jsonObj.get("serviceFeeCategoryCode");
//            String name = (String) jsonObj.get("serviceFeeCategoryName");
//            String[] codeList = code.split(SEPARATION_CHAR_MIDLINE);
//            String[] nameList = name.split(SEPARATION_CHAR_MIDLINE);
//            if (codeList.length > 1 && nameList.length > 1) {
//                for (int index = 0; index < codeList.length; index++) {
//                    TraSxfInterfaceDetailsT tmp = new TraSxfInterfaceDetailsT();
//                    tmp.setServiceFeeCategoryCode(codeList[index]);
//                    tmp.setServiceFeeCategoryName(nameList[index]);
//                    /**
//                     * 手续费信息
//                     * */
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionSource")) {
//                        tmp.setTransactionSource((String) jsonObj.get("transactionSource"));
//                    } else {
//                        logger.info("来源系统编码不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionType")) {
//                        tmp.setTransactionType((String) jsonObj.get("transactionType"));
//                    } else {
//                        logger.info("交易类型不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionDate")) {
//                        tmp.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("transactionDate")));
//                    } else {
//                        logger.info("业务日期不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionNo")) {
//                        tmp.setTransactionNo((String) jsonObj.get("transactionNo"));
//                    } else {
//                        logger.info("业务号码不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "channelCode")) {
//                        tmp.setChannelCode((String) jsonObj.get("channelCode"));
//                    } else {
//                        logger.info("渠道代码不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "channelDesc")) {
//                        tmp.setChannelDesc((String) jsonObj.get("channelDesc"));
//                    } else {
//                        logger.info("渠道名称不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "settlementTypeCode")) {
//                        tmp.setSettlementTypeCode((String) jsonObj.get("settlementTypeCode"));
//                    }
//                    //如果计提
//                    //交易类型：01-计提、02-结算、03-实付
//                    else if (((String) jsonObj.get("transactionType")).equals("01")) {
//                        //手续费类型名
//                        String serviceFeeCategoryName = nameList[index];
//                        //渠道代码："01-经代渠道 02-网销渠道"
//                        String channelCode=(String) jsonObj.get("channelCode");
//                        //首续年标志
//                        String initialYearDesc=(String) jsonObj.get("initialYearDesc");
//                        //如果经代
//                        if (channelCode.equals("01")){
//                            switch (serviceFeeCategoryName) {
//                                case "月度奖金":
//                                case "年度奖金":
////                                case "首期基本手续费":
//                                case "首年基本手续费":
//                                    tmp.setSettlementTypeCode("0101");
//                                    break;
//                                case "补充手续费":
//                                    tmp.setSettlementTypeCode("0102");
//                                    break;
//                                case "续期服务津贴":
//                                case "续期基本手续费":
//                                    tmp.setSettlementTypeCode("0103");
//                                    break;
//                                case "继续率奖金":
//                                    tmp.setSettlementTypeCode("0104");
//                                    break;
//                            }
//                        }
//                        //如果网销
//                        else if (channelCode.equals("02")) {
//                            switch (serviceFeeCategoryName) {
//                                case "月度奖金":
//                                case "新单业务推广费":
//                                    tmp.setSettlementTypeCode("0201");
//                                    break;
//                                case "阶段业务推动费":
//                                    tmp.setSettlementTypeCode("0202");
//                                    break;
//                                case "首年及续年经纪费":
//                                    if (initialYearDesc.equals("续年手续费")){
//                                        tmp.setSettlementTypeCode("0203");
//                                    }else if (initialYearDesc.equals("首年手续费")){
//                                        tmp.setSettlementTypeCode("0201");
//                                    }
//                                    break;
//                                case "继续率奖金":
//                                    tmp.setSettlementTypeCode("0204");
//                                    break;
//                                case "专项奖励费":
//                                    tmp.setSettlementTypeCode("0205");
//                                    break;
//                            }
//                        }
//                    } else {
//                        logger.info("业务数据拆分时遇到了问题：结算、支付类数据，手续费类型编码（serviceFeeCategoryCode）不能为空");
//                        return null;
//                    }
//
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "settlementTypeName")) {
//                        tmp.setSettlementTypeName((String) jsonObj.get("settlementTypeName"));
//                    }
//                    //如果计提
//                    //交易类型：01-计提、02-结算、03-实付
//                    else if (((String) jsonObj.get("transactionType")).equals("01")) {
//                        //手续费类型名
//                        String serviceFeeCategoryName = nameList[index];
//                        //渠道代码："01-经代渠道 02-网销渠道"
//                        String channelCode=(String) jsonObj.get("channelCode");
//                        //首续年标志
//                        String initialYearDesc=(String) jsonObj.get("initialYearDesc");
//                        //如果经代
//                        //渠道代码："01-经代渠道 02-网销渠道"
//                        if (channelCode.equals("01")) {
//                            switch (serviceFeeCategoryName) {
//                                case "月度奖金":
//                                case "年度奖金":
////                                case "首期基本手续费":
//                                case "首年基本手续费":
//                                    tmp.setSettlementTypeName("经代新单手续费");
//                                    break;
//                                case "补充手续费":
//                                    tmp.setSettlementTypeName("经代补充手续费");
//                                    break;
//                                case "续期服务津贴":
//                                case "续期基本手续费":
//                                    tmp.setSettlementTypeName("经代续期手续费");
//                                    break;
//                                case "继续率奖金":
//                                    tmp.setSettlementTypeName("经代继续率奖金");
//                                    break;
//                            }
//                        }
//                        //如果网销
//                        else if (channelCode.equals("02")) {
//                            switch (serviceFeeCategoryName) {
//                                case "月度奖金":
//                                case "新单业务推广费":
//                                    tmp.setSettlementTypeName("网销新单手续费");
//                                    break;
//                                case "阶段业务推动费":
//                                    tmp.setSettlementTypeName("网销业务推动费");
//                                    break;
//                                case "首年及续年经纪费":
//                                    if (initialYearDesc.equals("续年手续费")){
//                                        tmp.setSettlementTypeName("网销续期手续费");
//                                    }else if (initialYearDesc.equals("首年手续费")){
//                                        tmp.setSettlementTypeName("网销新单手续费");
//                                    }
//                                    break;
//                                case "继续率奖金":
//                                    tmp.setSettlementTypeName("网销继续率奖金");
//                                    break;
//                                case "专项奖励费":
//                                    tmp.setSettlementTypeName("网销专项奖励费");
//                                    break;
//                            }
//                        }
//                    } else {
//                        logger.info("业务数据拆分时遇到了问题：结算、支付类数据，手续费类型名称（settlementTypeName）不能为空");
//                        return null;
//                    }
////                    if (jsonObjectKeyValueIsNotEmpty(jsonObj,"serviceFeeCategoryCode")){
////                        tmp.setServiceFeeCategoryCode((String) jsonObj.get("serviceFeeCategoryCode"));
////                    }else{
////                        logger.info("手续费类型编码不能为空");
////                        return null;
////                    }
////                    if (jsonObjectKeyValueIsNotEmpty(jsonObj,"serviceFeeCategoryName")){
////                        tmp.setServiceFeeCategoryName((String) jsonObj.get("serviceFeeCategoryName"));
////                    }else{
////                        logger.info("手续费类型名称不能为空");
////                        return null;
////                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCode")) {
//                        tmp.setAgencyCode((String) jsonObj.get("agencyCode"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyName")) {
//                        tmp.setAgencyName((String) jsonObj.get("agencyName"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCompanyCode")) {
//                        tmp.setAgencyCompanyCode((String) jsonObj.get("agencyCompanyCode"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCompanyName")) {
//                        tmp.setAgencyCompanyName((String) jsonObj.get("agencyCompanyName"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "fbasicChargeRate")) {
//                        tmp.setfBasicChargeRate(Double.valueOf((String) jsonObj.get("fbasicChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "fbasicCharge")) {
//                        tmp.setfBasicCharge(Double.valueOf((String) jsonObj.get("fbasicCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "monthChargeRate")) {
//                        tmp.setMonthChargeRate(Double.valueOf((String) jsonObj.get("monthChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "monthCharge")) {
//                        tmp.setMonthCharge(Double.valueOf((String) jsonObj.get("monthCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "yearChargeRate")) {
//                        tmp.setYearChargeRate(Double.valueOf((String) jsonObj.get("yearChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "yearCharge")) {
//                        tmp.setYearCharge(Double.valueOf((String) jsonObj.get("yearCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newChargeSum")) {
//                        tmp.setNewChargeSum(Double.valueOf((String) jsonObj.get("newChargeSum")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newOrderChargeRate")) {
//                        detailsT.setNewOrderChargeRate(Double.valueOf((String) jsonObj.get("newOrderChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newOrderCharge")) {
//                        detailsT.setNewOrderCharge(Double.valueOf((String) jsonObj.get("newOrderCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "busMotivateChargeRate")) {
//                        detailsT.setBusMotivateChargeRate(Double.valueOf((String) jsonObj.get("busMotivateChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "busMotivateCharge")) {
//                        detailsT.setBusMotivateCharge(Double.valueOf((String) jsonObj.get("busMotivateCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "specialChargeRate")) {
//                        detailsT.setSpecialChargeRate(Double.valueOf((String) jsonObj.get("specialChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "specialCharge")) {
//                        detailsT.setSpecialCharge(Double.valueOf((String) jsonObj.get("specialCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "supPlementChargeRate")) {
//                        tmp.setSupPlementChargeRate(Double.valueOf((String) jsonObj.get("supPlementChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "supPlementCharge")) {
//                        tmp.setSupPlementCharge(Double.valueOf((String) jsonObj.get("supPlementCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rbasicChargeRate")) {
//                        tmp.setrBasicChargeRate(Double.valueOf((String) jsonObj.get("rbasicChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rbasicCharge")) {
//                        tmp.setrBasicCharge(Double.valueOf((String) jsonObj.get("rbasicCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rserviceChargeRate")) {
//                        tmp.setrServiceChargeRate(Double.valueOf((String) jsonObj.get("rserviceChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rserviceCharge")) {
//                        tmp.setrServiceCharge(Double.valueOf((String) jsonObj.get("rserviceCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rchargeSum")) {
//                        tmp.setrChargeSum(Double.valueOf((String) jsonObj.get("rchargeSum")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionChargeRate")) {
//                        tmp.setConversionChargeRate(Double.valueOf((String) jsonObj.get("conversionChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionCahrge")) {
//                        tmp.setConversionCharge(Double.valueOf((String) jsonObj.get("conversionCahrge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionRate")) {
//                        tmp.setContinuationRate(Double.valueOf((String) jsonObj.get("conversionRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "continuationChargeRate")) {
//                        tmp.setContinuationChargeRate(Double.valueOf((String) jsonObj.get("continuationChargeRate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "continuationCharge")) {
//                        tmp.setContinuationCharge(Double.valueOf((String) jsonObj.get("continuationCharge")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "apMoney")) {
//                        tmp.setApMoney(Double.valueOf((String) jsonObj.get("apMoney")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionMoney")) {
//                        tmp.setCommissionMoney(Double.valueOf((String) jsonObj.get("commissionMoney")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionNo")) {
//                        tmp.setCommissionNo((String) jsonObj.get("commissionNo"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionDate")) {
//                        tmp.setCommissionDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("commissionDate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "startMonth")) {
//                        tmp.setStartMonth((String) jsonObj.get("startMonth"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "endMonth")) {
//                        tmp.setEndMonth((String) jsonObj.get("endMonth"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementCode")) {
//                        tmp.setAgreementCode((String) jsonObj.get("agreementCode"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementName")) {
//                        tmp.setAgreementName((String) jsonObj.get("agreementName"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementStartDate")) {
//                        tmp.setAgreementStartDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("agreementStartDate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementEndDate")) {
//                        tmp.setAgreementStartDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("agreementEndDate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeStatus")) {
//                        tmp.setServiceFeeStatus((String) jsonObj.get("serviceFeeStatus"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "initialYearDesc")) {
//                        tmp.setInitialYearDesc((String) jsonObj.get("initialYearDesc"));
//                    }
//                    /**
//                     * 保单数据信息
//                     * */
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyNo")) {
//                        tmp.setPolicyNo((String) jsonObj.get("policyNo"));
//                    } else {
//                        logger.info("保单号不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "applicationNo")) {
//                        tmp.setApplicationNo((String) jsonObj.get("applicationNo"));
//                    } else {
//                        logger.info("投保单号不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyHolderName")) {
//                        tmp.setPolicyHolderName((String) jsonObj.get("policyHolderName"));
//                    } else {
//                        logger.info("投保人姓名不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyOrgCode")) {
//                        tmp.setPolicyOrgCode((String) jsonObj.get("policyOrgCode"));
//                    } else {
//                        logger.info("保单管理机构不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "productCode")) {
//                        tmp.setProductCode((String) jsonObj.get("productCode"));
//                    } else {
//                        logger.info("产品代码不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "productName")) {
//                        tmp.setProductName((String) jsonObj.get("productName"));
//                    } else {
//                        logger.info("产品名称不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "paymentFrequencyDesc")) {
//                        tmp.setPaymentFrequencyDesc((String) jsonObj.get("paymentFrequencyDesc"));
//                    } else {
//                        logger.info("缴别名称不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "signDate")) {
//                        tmp.setSignDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("signDate")));
//                    } else {
//                        logger.info("保单承保日期不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "enterAccDate")) {
//                        tmp.setEnterAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("enterAccDate")));
//                    } else {
//                        logger.info("保单实收日期不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "cvaliDate")) {
//                        tmp.setCvaliDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("cvaliDate")));
//                    } else {
//                        logger.info("保单生效日期不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "prem")) {
//                        tmp.setPrem(Double.valueOf((String) jsonObj.get("prem")));
//                    } else {
//                        logger.info("险种保费不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transStandMoney")) {
//                        tmp.setTransStandMoney(Double.valueOf((String) jsonObj.get("transStandMoney")));
//                    } else {
//                        logger.info("标准保费不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "contState")) {
//                        tmp.setContState((String) jsonObj.get("contState"));
//                    } else {
//                        logger.info("保单状态不能为空");
//                        return null;
//                    }
//                    /**
//                     * 支付信息数据
//                     * */
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "billingPayOrgCode")) {
//                        tmp.setBillingPayOrgCode((String) jsonObj.get("billingPayOrgCode"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "bankAccountNo")) {
//                        tmp.setBankAccountNo((String) jsonObj.get("bankAccountNo"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "bankAccountName")) {
//                        tmp.setBankAccountName((String) jsonObj.get("bankAccountName"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "targetBankAccountNo")) {
//                        tmp.setTargetBankAccountNo((String) jsonObj.get("targetBankAccountNo"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "targetBankAccountName")) {
//                        tmp.setTargetBankAccountName((String) jsonObj.get("targetBankAccountName"));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "currencyCode")) {
//                        tmp.setCurrencyCode((String) jsonObj.get("currencyCode"));
//                    } else {
//                        logger.info("币种不能为空");
//                        return null;
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeAmount")) {
//                        tmp.setServiceFeeAmount(Double.valueOf((String) jsonObj.get("serviceFeeAmount")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "totalAmount")) {
//                        tmp.setTotalAmount(Double.valueOf((String) jsonObj.get("totalAmount")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "taxAmount")) {
//                        tmp.setTaxAmount(Double.valueOf((String) jsonObj.get("taxAmount")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "getAccDate")) {//格式要求：YYYY/MM/DD
//                        tmp.setGetAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("getAccDate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "returnAccDate")) {//格式要求：YYYY/MM/DD
//                        tmp.setReturnAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("returnAccDate")));
//                    }
//                    if (jsonObjectKeyValueIsNotEmpty(jsonObj, "returnFlag")) {
//                        tmp.setReturnFlag((String) jsonObj.get("returnFlag"));
//                    }
//
//
//                    detailsTList.add(tmp);
//                }
//            } else {
            //这里结束
            /**
             * 手续费信息
             * */
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionSource")) {
                detailsT.setTransactionSource((String) jsonObj.get("transactionSource"));
            } else {
                logger.info("来源系统编码不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionType")) {
                detailsT.setTransactionType((String) jsonObj.get("transactionType"));
            } else {
                logger.info("交易类型不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionDate")) {
                detailsT.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("transactionDate")));
            } else {
                logger.info("业务日期不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transactionNo")) {
                detailsT.setTransactionNo((String) jsonObj.get("transactionNo"));
            } else {
                logger.info("业务号码不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "channelCode")) {
                detailsT.setChannelCode((String) jsonObj.get("channelCode"));
            } else {
                logger.info("渠道代码不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "channelDesc")) {
                detailsT.setChannelDesc((String) jsonObj.get("channelDesc"));
            } else {
                logger.info("渠道名称不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "settlementTypeCode")) {
                detailsT.setSettlementTypeCode((String) jsonObj.get("settlementTypeCode"));
            }
            //结算单类型编码为空的时候，通过手续费类型反推结算单类型编码
            /**
             0101-经代新单手续费；
             0102-经代补充手续费；
             0103-经代续期手续费；
             0104-经代继续率奖金；
             0201-网销新单手续费；
             0202-网销业务推动费；
             0203-网销续期手续费；
             0204-网销继续率奖金；
             0205-网销专项奖励费
             */


            //如果计提
            //交易类型：01-计提、02-结算、03-实付
            else if (((String) jsonObj.get("transactionType")).equals("01")) {
                //手续费类型名
                String serviceFeeCategoryName = (String) jsonObj.get("serviceFeeCategoryName");
                //渠道代码："01-经代渠道 02-网销渠道"
                String channelCode=(String) jsonObj.get("channelCode");
                //如果经代
                //渠道代码："01-经代渠道 02-网销渠道"
                if (channelCode.equals("01")) {
                    switch (serviceFeeCategoryName) {
                        case "月度奖金":
                        case "年度奖金":
//                            case "首期基本手续费":
                        case "首年基本手续费":
                            detailsT.setSettlementTypeCode("0101");
                            break;
                        case "补充手续费":
                            detailsT.setSettlementTypeCode("0102");
                            break;
                        case "续期服务津贴":
                        case "续期基本手续费":
                            detailsT.setSettlementTypeCode("0103");
                            break;
                        case "继续率奖金":
                            detailsT.setSettlementTypeCode("0104");
                            break;
                    }
                }
                //如果网销
                else if (channelCode.equals("02")) {
                    switch (serviceFeeCategoryName) {
                        case "月度奖金":
                        case "新单业务推广费":
                            detailsT.setSettlementTypeCode("0201");
                            break;
                        case "阶段业务推动费":
                            detailsT.setSettlementTypeCode("0202");
                            break;
                        case "首年及续年经纪费":
                            if (((String) jsonObj.get("initialYearDesc")).equals("续年手续费")){
                                detailsT.setSettlementTypeCode("0203");
                            }else {
                                detailsT.setSettlementTypeCode("0201");
                            }
                            break;
                        case "继续率奖金":
                            detailsT.setSettlementTypeCode("0204");
                            break;
                        case "专项奖励费":
                            detailsT.setSettlementTypeCode("0205");
                            break;
                    }
                }
            } else {
                logger.info("结算、支付类数据，手续费类型编码（serviceFeeCategoryCode）不能为空");
                return null;
            }
//                else {
//                    logger.info("结算单类型编码不能为空");
//                    return null;
//                }
            //逻辑为：结算单类型名称有值时就正常赋值，无值时就判断是否时计提（01），如果是就进行反推，不是就报错：结算、支付类数据手续费类型名称（settlementTypeName）不能为空
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "settlementTypeName")) {
                detailsT.setSettlementTypeName((String) jsonObj.get("settlementTypeName"));
            }
            //结算单类型名称为空的时候，通过手续费类型反推结算单类型名称
            /**
             如果计提
             如果经代
             如果('月度奖金','年度奖金','首期基本手续费')则'经代新单手续费'
             如果('补充手续费')则'经代补充手续费'
             如果('续期服务津贴','续期基本手续费')则'经代续期手续费'
             如果('继续率奖金') 则'经代继续率奖金'
             如果网销
             如果('月度奖金','新单业务推广费')则'网销新单手续费'
             如果('首年及续年经纪费') and SUBSTR( a.INITIAL_YEAR_DESC, 1, 2 )='首年'则'网销新单手续费'
             如果('阶段业务推动费')则'网销业务推动费'
             如果('首年及续年经纪费')and SUBSTR( a.INITIAL_YEAR_DESC, 1, 2 )='续年'则'网销续期手续费'
             如果('继续率奖金')则'网销继续率奖金'
             如果('专项奖励费')则'网销专项奖励费'
             */
            //如果计提
            //交易类型：01-计提、02-结算、03-实付
            else if (((String) jsonObj.get("transactionType")).equals("01")) {
                //手续费类型名
                String serviceFeeCategoryName = (String) jsonObj.get("serviceFeeCategoryName");
                //渠道代码："01-经代渠道 02-网销渠道"
                String channelCode=(String) jsonObj.get("channelCode");
                //如果经代
                //渠道代码："01-经代渠道 02-网销渠道"
                if (channelCode.equals("01")) {
                    switch (serviceFeeCategoryName) {
                        case "月度奖金":
                        case "年度奖金":
//                            case "首期基本手续费":
                        case "首年基本手续费":
                            detailsT.setSettlementTypeName("经代新单手续费");
                            break;
                        case "补充手续费":
                            detailsT.setSettlementTypeName("经代补充手续费");
                            break;
                        case "续期服务津贴":
                        case "续期基本手续费":
                            detailsT.setSettlementTypeName("经代续期手续费");
                            break;
                        case "继续率奖金":
                            detailsT.setSettlementTypeName("经代继续率奖金");
                            break;
                    }
                }
                //如果网销
                else if (channelCode.equals("02")) {
                    switch (serviceFeeCategoryName) {
                        case "月度奖金":
                        case "新单业务推广费":
                            detailsT.setSettlementTypeName("网销新单手续费");
                            break;
                        case "阶段业务推动费":
                            detailsT.setSettlementTypeName("网销业务推动费");
                            break;
                        case "首年及续年经纪费":
                            if (((String) jsonObj.get("initialYearDesc")).equals("续年手续费")){
                                detailsT.setSettlementTypeName("网销续期手续费");
                            }else {
                                detailsT.setSettlementTypeName("网销新单手续费");
                            }
                            break;
                        case "继续率奖金":
                            detailsT.setSettlementTypeName("网销继续率奖金");
                            break;
                        case "专项奖励费":
                            detailsT.setSettlementTypeName("网销专项奖励费");
                            break;
                    }
                }
            } else {
                logger.info("结算、支付类数据，手续费类型名称（settlementTypeName）不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeCategoryCode")) {
                detailsT.setServiceFeeCategoryCode((String) jsonObj.get("serviceFeeCategoryCode"));
            } else {
                logger.info("手续费类型编码不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeCategoryName")) {
                detailsT.setServiceFeeCategoryName((String) jsonObj.get("serviceFeeCategoryName"));
            } else {
                logger.info("手续费类型名称不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCode")) {
                detailsT.setAgencyCode((String) jsonObj.get("agencyCode"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyName")) {
                detailsT.setAgencyName((String) jsonObj.get("agencyName"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCompanyCode")) {
                detailsT.setAgencyCompanyCode((String) jsonObj.get("agencyCompanyCode"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agencyCompanyName")) {
                detailsT.setAgencyCompanyName((String) jsonObj.get("agencyCompanyName"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "fbasicChargeRate")) {
                detailsT.setfBasicChargeRate(Double.valueOf((String) jsonObj.get("fbasicChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "fbasicCharge")) {
                detailsT.setfBasicCharge(Double.valueOf((String) jsonObj.get("fbasicCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "monthChargeRate")) {
                detailsT.setMonthChargeRate(Double.valueOf((String) jsonObj.get("monthChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "monthCharge")) {
                detailsT.setMonthCharge(Double.valueOf((String) jsonObj.get("monthCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "yearChargeRate")) {
                detailsT.setYearChargeRate(Double.valueOf((String) jsonObj.get("yearChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "yearCharge")) {
                detailsT.setYearCharge(Double.valueOf((String) jsonObj.get("yearCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newChargeSum")) {
                detailsT.setNewChargeSum(Double.valueOf((String) jsonObj.get("newChargeSum")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newOrderChargeRate")) {
                detailsT.setNewOrderChargeRate(Double.valueOf((String) jsonObj.get("newOrderChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "newOrderCharge")) {
                detailsT.setNewOrderCharge(Double.valueOf((String) jsonObj.get("newOrderCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "busMotivateChargeRate")) {
                detailsT.setBusMotivateChargeRate(Double.valueOf((String) jsonObj.get("busMotivateChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "busMotivateCharge")) {
                detailsT.setBusMotivateCharge(Double.valueOf((String) jsonObj.get("busMotivateCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "specialChargeRate")) {
                detailsT.setSpecialChargeRate(Double.valueOf((String) jsonObj.get("specialChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "specialCharge")) {
                detailsT.setSpecialCharge(Double.valueOf((String) jsonObj.get("specialCharge")));
            }

            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "supPlementChargeRate")) {
                detailsT.setSupPlementChargeRate(Double.valueOf((String) jsonObj.get("supPlementChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "supPlementCharge")) {
                detailsT.setSupPlementCharge(Double.valueOf((String) jsonObj.get("supPlementCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rbasicChargeRate")) {
                detailsT.setrBasicChargeRate(Double.valueOf((String) jsonObj.get("rbasicChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rbasicCharge")) {
                detailsT.setrBasicCharge(Double.valueOf((String) jsonObj.get("rbasicCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rserviceChargeRate")) {
                detailsT.setrServiceChargeRate(Double.valueOf((String) jsonObj.get("rserviceChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rserviceCharge")) {
                detailsT.setrServiceCharge(Double.valueOf((String) jsonObj.get("rserviceCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "rchargeSum")) {
                detailsT.setrChargeSum(Double.valueOf((String) jsonObj.get("rchargeSum")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionChargeRate")) {
                detailsT.setConversionChargeRate(Double.valueOf((String) jsonObj.get("conversionChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionCahrge")) {
                detailsT.setConversionCharge(Double.valueOf((String) jsonObj.get("conversionCahrge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "conversionRate")) {
                detailsT.setContinuationRate(Double.valueOf((String) jsonObj.get("conversionRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "continuationChargeRate")) {
                detailsT.setContinuationChargeRate(Double.valueOf((String) jsonObj.get("continuationChargeRate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "continuationCharge")) {
                detailsT.setContinuationCharge(Double.valueOf((String) jsonObj.get("continuationCharge")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "apMoney")) {
                detailsT.setApMoney(Double.valueOf((String) jsonObj.get("apMoney")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionMoney")) {
                detailsT.setCommissionMoney(Double.valueOf((String) jsonObj.get("commissionMoney")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionNo")) {
                detailsT.setCommissionNo((String) jsonObj.get("commissionNo"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "commissionDate")) {
                detailsT.setCommissionDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("commissionDate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "startMonth")) {
                detailsT.setStartMonth((String) jsonObj.get("startMonth"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "endMonth")) {
                detailsT.setEndMonth((String) jsonObj.get("endMonth"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementCode")) {
                detailsT.setAgreementCode((String) jsonObj.get("agreementCode"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementName")) {
                detailsT.setAgreementName((String) jsonObj.get("agreementName"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementStartDate")) {
                detailsT.setAgreementStartDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("agreementStartDate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "agreementEndDate")) {
                detailsT.setAgreementEndDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("agreementEndDate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeStatus")) {
                detailsT.setServiceFeeStatus((String) jsonObj.get("serviceFeeStatus"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "initialYearDesc")) {
                detailsT.setInitialYearDesc((String) jsonObj.get("initialYearDesc"));
            }
            /**
             * 保单数据信息
             * */
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyNo")) {
                detailsT.setPolicyNo((String) jsonObj.get("policyNo"));
            } else {
                logger.info("保单号不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "applicationNo")) {
                detailsT.setApplicationNo((String) jsonObj.get("applicationNo"));
            } else {
                logger.info("投保单号不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyHolderName")) {
                detailsT.setPolicyHolderName((String) jsonObj.get("policyHolderName"));
            } else {
                logger.info("投保人姓名不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "policyOrgCode")) {
                detailsT.setPolicyOrgCode((String) jsonObj.get("policyOrgCode"));
            } else {
                logger.info("保单管理机构不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "productCode")) {
                detailsT.setProductCode((String) jsonObj.get("productCode"));
            } else {
                logger.info("产品代码不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "productName")) {
                detailsT.setProductName((String) jsonObj.get("productName"));
            } else {
                logger.info("产品名称不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "paymentFrequencyDesc")) {
                detailsT.setPaymentFrequencyDesc((String) jsonObj.get("paymentFrequencyDesc"));
            } else {
                logger.info("缴别名称不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "signDate")) {
                detailsT.setSignDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("signDate")));
            } else {
                logger.info("保单承保日期不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "enterAccDate")) {
                detailsT.setEnterAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("enterAccDate")));
            } else {
                logger.info("保单实收日期不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "cvaliDate")) {
                detailsT.setCvaliDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("cvaliDate")));
            } else {
                logger.info("保单生效日期不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "prem")) {
                detailsT.setPrem(Double.valueOf((String) jsonObj.get("prem")));
            } else {
                logger.info("险种保费不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "transStandMoney")) {
                detailsT.setTransStandMoney(Double.valueOf((String) jsonObj.get("transStandMoney")));
            } else {
                logger.info("标准保费不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "contState")) {
                detailsT.setContState((String) jsonObj.get("contState"));
            } else {
                logger.info("保单状态不能为空");
                return null;
            }
            /**
             * 支付信息数据
             * */
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "billingPayOrgCode")) {
                detailsT.setBillingPayOrgCode((String) jsonObj.get("billingPayOrgCode"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "bankAccountNo")) {
                detailsT.setBankAccountNo((String) jsonObj.get("bankAccountNo"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "bankAccountName")) {
                detailsT.setBankAccountName((String) jsonObj.get("bankAccountName"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "targetBankAccountNo")) {
                detailsT.setTargetBankAccountNo((String) jsonObj.get("targetBankAccountNo"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "targetBankAccountName")) {
                detailsT.setTargetBankAccountName((String) jsonObj.get("targetBankAccountName"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "currencyCode")) {
                detailsT.setCurrencyCode((String) jsonObj.get("currencyCode"));
            } else {
                logger.info("币种不能为空");
                return null;
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "serviceFeeAmount")) {
                detailsT.setServiceFeeAmount(Double.valueOf((String) jsonObj.get("serviceFeeAmount")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "totalAmount")) {
                detailsT.setTotalAmount(Double.valueOf((String) jsonObj.get("totalAmount")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "taxAmount")) {
                detailsT.setTaxAmount(Double.valueOf((String) jsonObj.get("taxAmount")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "getAccDate")) {//格式要求：YYYY/MM/DD
                detailsT.setGetAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("getAccDate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "returnAccDate")) {//格式要求：YYYY/MM/DD
                detailsT.setReturnAccDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("returnAccDate")));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "returnFlag")) {
                detailsT.setReturnFlag((String) jsonObj.get("returnFlag"));
            }
            if (jsonObjectKeyValueIsNotEmpty(jsonObj, "performInfoSriNo")) {
                detailsT.setPerformInfoSriNo((String) jsonObj.get("performInfoSriNo"));
            }
            detailsTList.add(detailsT);
            //不拆分时 从这里开始注释
//            }
        } else {
            logger.info("手续费类型编码不能为空");
            return null;
        }
        return detailsTList;
    }

    /**
     * @description: 通过ParseBusinessDataDetailJsonStr方法改造 拆分合并的手续费
     * @date 2023/02/20/0020 15:25:12
     */
    private List<TraSxfInterfaceDetailsT> splitBusinessData(@NotNull List<TraSxfInterfaceDetailsT> detailsTList) throws ParseException {
        List<TraSxfInterfaceDetailsT> detailsTListSplit = new ArrayList<>();
        String splitChar = "-";
        detailsTList.forEach(item->{
//            logger.info(item.toString());
            if (item.getServiceFeeCategoryCode().contains(splitChar)){
                String[] codeList = item.getServiceFeeCategoryCode().split(splitChar);
                String[] nameList = item.getServiceFeeCategoryName().split(splitChar);
                //拆分手续费类型名称
                for (int i=0;i<codeList.length;i++){
                    TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                    BeanUtils.copyProperties(item,detailsT);
                    detailsT.setInterfaceId(seriNo.getASeriNo(NoType.TRA_SXF_INTERFACE_DETAILS_T));
                    detailsT.setServiceFeeCategoryCode(codeList[i]);
                    detailsT.setServiceFeeCategoryName(nameList[i]);
                    detailsTListSplit.add(detailsT);
                }
            }else {
                //不拆分直接赋值
                TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                BeanUtils.copyProperties(item,detailsT);
                detailsTListSplit.add(detailsT);
            }
        });
        return detailsTListSplit;
    }

    /**
     * @return true：为空 false：不为空
     * @apiNote jsonObject的key-value非空校验
     */
    private static boolean jsonObjectKeyValueIsNotEmpty(JSONObject jsonObj, String key) {
        return (jsonObj.get(key) != null) && !("".equals(jsonObj.get(key)));
    }
}
