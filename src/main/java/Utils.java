import java.io.IOException;
    import java.io.Serializable;
    import java.util.Properties;

/**
 * Created by sushrut on 29/9/16.
 */
public class Utils implements Serializable {
  private static final long serialVersionUID = 1L;


  private static final String SUMMARIES_SPARK_CONF_PROPS_FILE = "/sparkConf.properties";


  private static final Properties summariesSparkConfProps = new Properties();

  static {
    try {

      summariesSparkConfProps.load(Utils.class.getResourceAsStream(SUMMARIES_SPARK_CONF_PROPS_FILE));

    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public static Properties getSummariesSparkConfProps() {
    return summariesSparkConfProps;
  }


}