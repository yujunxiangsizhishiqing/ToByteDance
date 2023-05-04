package demoEnum;

public enum DBExecutorType {
    /**
     * mybatis批量操作的执行类型：遍历;foreach;batch
     * */
    SIMPLE,FOREACH,BATCH,BATCH_FOREACH;
/*
    mybatis模式
    ExecutorType.SIMPLE: 这个执行器类型不做特殊的事情。它为每个语句的执行创建一个新的预处理语句。（默认）
    ExecutorType.REUSE: 这个执行器类型会复用预处理语句。
    ExecutorType.BATCH: 这个执行器会批量执行所有更新语句,如果 SELECT 在它们中间执行还会标定它们是 必须的,来保证一个简单并易于理解的行为。
    SIMPLE, REUSE, BATCH
*/
}
