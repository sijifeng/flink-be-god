package operator.stream;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * aggregation聚合操作,支持sum、min、max、minBy、maxBy
 * KeyedStream → DataStream
 */
public class AggregationOperation {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.fromElements(Tuple2.of("k1", 11), Tuple2.of("k2", 2)
                , Tuple2.of("k3", 3), Tuple2.of("k2", 5), Tuple2.of("k3", 10), Tuple2.of("k1", 9))
                .keyBy(0)//可以用0或t->t.f0
                .max(1)//等同于reduce((t1, t2) -> Tuple2.of(t1.f0, Math.max(t1.f1, t2.f1)))
                .print();
        env.execute();
        /**
         * 结果
         * 2> (k1,11)
         * 6> (k2,2)
         * 1> (k3,3)
         * 6> (k2,5)
         * 1> (k3,10)
         * 2> (k1,11)
         */
    }
}
