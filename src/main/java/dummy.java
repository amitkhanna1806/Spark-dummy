import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.Properties;

public class dummy {
  private static final SparkSession spark;
  private static final JavaSparkContext sc;
  private static final SQLContext sqlContext;
  private static final Configuration conf;

  private static final double MAX_THRESHOLD = 100.0;
  private static final long MAX_COUNT = 100000;

  static {
    SparkSession.Builder sparkSessionbuilder = SparkSession.builder();

    final Properties sparkConfig = Utils.getSummariesSparkConfProps();
    sparkConfig.keySet().stream()
        .forEach(k -> sparkSessionbuilder.config(k.toString(), sparkConfig.get(k).toString()));

    System.out.println("Creating spark context");
    spark = sparkSessionbuilder.getOrCreate();
    sc = new JavaSparkContext(spark.sparkContext());
    sqlContext = spark.sqlContext();
    conf = spark.sparkContext().hadoopConfiguration();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Args:" + Arrays.asList(args).toString());

    System.out.println("Load props");

    spark.read().parquet(args[0])
        .write().parquet(args[1]);
  }
}