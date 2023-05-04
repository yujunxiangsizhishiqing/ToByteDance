package demoPackage.demoUtil;

import com.google.common.collect.Lists;
import com.sun.istack.NotNull;
import demoClass.*;
import demoEnum.DBExecutorType;
import demoEnum.MybatisBatchOperationType;
import demoPackage.demoMapper.SalMgtMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DBBatchManager<T> {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private SalMgtMapper salMgtMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    //默认的list拆分尺寸
    private final int DEFAULT_SPILI_SIZE = 1000;

    /**
     * @apiNote mybatis的批量插入的统一管理，后续项目中所有需要批量插入的需求均可以在这里实现。
     * @param executionType 本次执行批量插入执行的方案：simple(遍历插入);foreach（标签）;batch（批处理）
     * @param batchInsertType 批量插入的类型，用来确定是哪一个批量插入服务；
     * @param data 本次批量插入的源数据
     * @param spiltSize 需要拆分data是可手动设置拆分的尺寸
     * */
    public String batchInsert(DBExecutorType executionType, MybatisBatchOperationType batchInsertType, int spiltSize, @NotNull List<T> data){

        long startTime = System.currentTimeMillis();
        String result = null;
        try {
            switch (executionType){
                case SIMPLE:
                    //遍历插入
                    logger.info("执行器方案：遍历插入");
                    result = insertBySimple(batchInsertType,data);
                    break;
                case FOREACH:
                    //使用foreach标签
                    logger.info("执行器方案：foreach标签");
                    result = insertByForeach(batchInsertType,spiltSize,data);
                    break;
                case BATCH:
                    //使用batch
                    logger.info("执行器方案：batch");
                    result = insertByBatch(batchInsertType,spiltSize,data);
                    break;
                case BATCH_FOREACH:
                    //batch和foreach复合策略，使用foreach插入，batch提交事务，进一步提高效率
                    break;
                default:
                    logger.info("未知的批量插入类型，请确认入参是否正确");
                    result = "未知的批量插入类型，请确认入参是否正确";
                    break;
            }
        }catch (Exception e){
            result = e.toString();
        }
        logger.info("本次插入耗时:"+(System.currentTimeMillis()-startTime)+"ms");
        return result;
    }

    /**
     * @apiNote 批量更新
     * */
    public void batchUpdate(){
        return;
    }

    /**
     * @apiNote 遍历插入多条数据
     * */
    private String insertBySimple(MybatisBatchOperationType batchInsertType,@NotNull List<T> data){
        String result = null;
        try{
            switch (batchInsertType){
                case TRASXFINTERFACEDETAILST:
                    //数据转换
                    List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();
                    data.forEach(item->{
                        TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                        BeanUtils.copyProperties(item,detailsT,TraSxfInterfaceDetailsT.class);
                        detailsTList.add(detailsT);
                    });
                    //插入
                    detailsTList.forEach(s->{
                        salMgtMapper.insertTraSxfDetails(s);
                    });
                    break;
                case LACOM:
                    //数据转换
                    List<Lacom> lacomList = new ArrayList<>();
                    data.forEach(item->{
                        Lacom lacom = new Lacom();
                        BeanUtils.copyProperties(item,lacom,Lacom.class);
                        lacomList.add(lacom);
                    });
                    //插入
                    lacomList.forEach(s->{
                        salMgtMapper.insertLacom(s);
                    });
                    break;
                case LDCOMF:
                    //数据转换
                    List<LDComF> lDComFList = new ArrayList<>();
                    data.forEach(item->{
                        LDComF lDComF = new LDComF();
                        BeanUtils.copyProperties(item,lDComF,Lacom.class);
                        lDComFList.add(lDComF);
                    });
                    //插入
                    lDComFList.forEach(s->{
                        salMgtMapper.insertLDComf(s);
                    });
                    break;
                case LAGXJDBANKCOMPACT:
                    //数据转换
                    List<LagxjdbankCompact> lagxjdbankCompactList = new ArrayList<>();
                    data.forEach(item->{
                        LagxjdbankCompact lagxjdbankCompact = new LagxjdbankCompact();
                        BeanUtils.copyProperties(item,lagxjdbankCompact,LagxjdbankCompact.class);
                        lagxjdbankCompactList.add(lagxjdbankCompact);
                    });
                    //插入
                    lagxjdbankCompactList.forEach(s->{
                        salMgtMapper.insertLagxjdbankCompact(s);
                    });
                    break;
                case LAGXJDRATECHARGE:
                    //数据转换
                    List<LagxjdrateCharge> lagxjdrateChargeList = new ArrayList<>();
                    data.forEach(item->{
                        LagxjdrateCharge lagxjdrateCharge = new LagxjdrateCharge();
                        BeanUtils.copyProperties(item,lagxjdrateCharge,LagxjdrateCharge.class);
                        lagxjdrateChargeList.add(lagxjdrateCharge);
                    });
                    //插入
                    lagxjdrateChargeList.forEach(s->{
                        salMgtMapper.insertLagxjdrateCharge(s);
                    });
                    break;
                case LMRISKAPP:
                    //数据转换
                    List<LMRiskApp> lmRiskAppList = new ArrayList<>();
                    data.forEach(item->{
                        LMRiskApp lmRiskApp = new LMRiskApp();
                        BeanUtils.copyProperties(item,lmRiskApp,LMRiskApp.class);
                        lmRiskAppList.add(lmRiskApp);
                    });
                    //插入
                    lmRiskAppList.forEach(s->{
                        salMgtMapper.insertLMRiskApp(s);
                    });
                    break;
                case SMCFGTABLEERR:
                    //数据转换
                    List<SMCfgTableErr> smCfgTableErrList = new ArrayList<>();
                    data.forEach(item->{
                        SMCfgTableErr smCfgTableErr = new SMCfgTableErr();
                        BeanUtils.copyProperties(item,smCfgTableErr,SMCfgTableErr.class);
                        smCfgTableErrList.add(smCfgTableErr);
                    });
                    //插入
                    smCfgTableErrList.forEach(s->{
                        salMgtMapper.inseertSMCfgTableErr(s);
                    });
                    break;
                default:
                    logger.info("未知的批量插入服务");
                    result = "未知的批量插入服务";
                    break;
            }
        }catch (Exception e){
            result = "遍历插入异常，异常信息："+e.toString();
        }

        return result;
    }

    /**
     * @apiNote 使用foreach标签插入多条数据，mybatis需要实现对应的list insert方法，并且可能存在sql过长的问题，请合理控制spiltSize大小。
     * */
    private String insertByForeach(MybatisBatchOperationType batchInsertType, int spiltSize, @NotNull List<T> data){
        String result = null;
        try{
            List<List<T>> subDetailsTLists = new ArrayList<>();
            switch (batchInsertType){
                case TRASXFINTERFACEDETAILSTSPLIT:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();
                        item.forEach(subItem->{
                            TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                            BeanUtils.copyProperties(subItem,detailsT,TraSxfInterfaceDetailsT.class);
                            detailsTList.add(detailsT);
                        });
                        //数据插入
                        salMgtMapper.insertTraSxfDetailsListSplit(detailsTList);
                    });
                    break;
                case TRASXFINTERFACEDETAILST:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<TraSxfInterfaceDetailsT> detailsTList = new ArrayList<>();
                        item.forEach(subItem->{
                            TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                            BeanUtils.copyProperties(subItem,detailsT,TraSxfInterfaceDetailsT.class);
                            detailsTList.add(detailsT);
                        });
                        //数据插入
                        salMgtMapper.insertTraSxfDetailsList(detailsTList);
                    });
                    break;
                case LACOM:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<Lacom> lacomList = new ArrayList<>();
                        item.forEach(subItem->{
                            Lacom lacom = new Lacom();
                            BeanUtils.copyProperties(subItem,lacom,Lacom.class);
                            lacomList.add(lacom);
                        });
                        //数据插入
                        salMgtMapper.insertLacomList(lacomList);
                    });
                    break;
                case LDCOMF:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<LDComF> lacomList = new ArrayList<>();
                        item.forEach(subItem->{
                            LDComF lacom = new LDComF();
                            BeanUtils.copyProperties(subItem,lacom,LDComF.class);
                            lacomList.add(lacom);
                        });
                        //数据插入
                        salMgtMapper.insertLDComfList(lacomList);
                    });
                    break;
                case LAGXJDBANKCOMPACT:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<LagxjdbankCompact> lagxjdbankCompactList = new ArrayList<>();
                        item.forEach(subItem->{
                            LagxjdbankCompact lagxjdbankCompact = new LagxjdbankCompact();
                            BeanUtils.copyProperties(subItem,lagxjdbankCompact,LagxjdbankCompact.class);
                            lagxjdbankCompactList.add(lagxjdbankCompact);
                        });
                        //数据插入
                        salMgtMapper.insertLagxjdbankCompactList(lagxjdbankCompactList);
                    });
                    break;
                case LAGXJDRATECHARGE:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<LagxjdrateCharge> lagxjdrateChargeList = new ArrayList<>();
                        item.forEach(subItem->{
                            LagxjdrateCharge lagxjdrateCharge = new LagxjdrateCharge();
                            BeanUtils.copyProperties(subItem,lagxjdrateCharge,LagxjdrateCharge.class);
                            lagxjdrateChargeList.add(lagxjdrateCharge);
                        });
                        //数据插入
                        salMgtMapper.insertLagxjdrateChargeList(lagxjdrateChargeList);
                    });
                    break;
                case LMRISKAPP:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<LMRiskApp> lmRiskAppList = new ArrayList<>();
                        item.forEach(subItem->{
                            LMRiskApp lmRiskApp = new LMRiskApp();
                            BeanUtils.copyProperties(subItem,lmRiskApp,LMRiskApp.class);
                            lmRiskAppList.add(lmRiskApp);
                        });
                        //数据插入
                        salMgtMapper.insertLMRiskAppList(lmRiskAppList);
                    });
                    break;
                case SMCFGTABLEERR:
                    //数据拆分
                    subDetailsTLists = Lists.partition(data, spiltSize > 0 ? spiltSize : DEFAULT_SPILI_SIZE);
                    subDetailsTLists.forEach(item->{
                        //数据转换
                        List<SMCfgTableErr> smCfgTableErrList = new ArrayList<>();
                        item.forEach(subItem->{
                            SMCfgTableErr smCfgTableErr = new SMCfgTableErr();
                            BeanUtils.copyProperties(subItem,smCfgTableErr,SMCfgTableErr.class);
                            smCfgTableErrList.add(smCfgTableErr);
                        });
                        //数据插入
                        salMgtMapper.inseertSMCfgTableErrList(smCfgTableErrList);
                    });
                    break;
                default:
                    logger.info("未知的批量插入服务");
                    result = "未知的批量插入服务";
                    break;
            }
        }catch (Exception e){
            result = "foreach插入异常，异常信息：" + e.toString();
        }

        return result;
    }

    /**
     * @apiNote 使用batch模式插入多条数据
     * */
    private String insertByBatch(MybatisBatchOperationType batchInsertType, int spiltSize, @NotNull List<T> data){
        String result = null;
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try{
            switch (batchInsertType){
                case TRASXFINTERFACEDETAILST:
                    SalMgtMapper mapper = sqlSession.getMapper(SalMgtMapper.class);
                    data.forEach(item->{
                        TraSxfInterfaceDetailsT detailsT = new TraSxfInterfaceDetailsT();
                        BeanUtils.copyProperties(item,detailsT,TraSxfInterfaceDetailsT.class);
                        mapper.insertTraSxfDetails(detailsT);
                    });
                    break;
                case LACOM:
                    break;
                case LDCOMF:
                    break;
                case LAGXJDBANKCOMPACT:
                    break;
                case LAGXJDRATECHARGE:
                    break;
                case LMRISKAPP:
                    break;
                case SMCFGTABLEERR:
                    break;
                default:
                    logger.info("未知的批量插入服务");
                    result = "未知的批量插入服务";
                    break;
            }
            /**提交事务，清除缓存**/
            sqlSession.commit();
            sqlSession.clearCache();
        }catch (Exception e){
            result = "batch插入异常，异常信息：" + e.toString();
        }finally {
            //关闭session...
            if (null != sqlSession){
                sqlSession.close();
            }
        }

        return result;
    }
}
