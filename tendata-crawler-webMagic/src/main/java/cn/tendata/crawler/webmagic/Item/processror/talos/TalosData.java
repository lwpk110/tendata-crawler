package cn.tendata.crawler.webmagic.Item.processror.talos;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class TalosData {
    public static String TALOS_REQUEST_URL = "https://talosintelligence.com/reputation_center/lookup?search={IP}";

    public static String TALOS_DISPATCH_PATH = "/api/v2/query_type/";
    public static String TALOS_QUALITY_TARGET_PATH = "/api/v2/details/ip/";

    public static String KEY_EMAIL_REPUTATION = "email_score_name";
    public static String KEY_LAST_DAY_SPAM_LEVEL = "daily_spam_name";

    public static String TALOS_REQUEST_URL_REGEX = "https://talosintelligence.com/reputation_center/lookup\\?search=\\S+";
    public static String TALOS_QUALITY_TARGET_URL_REGEX = "https://talosintelligence.com/sb_api/query_lookup\\?query=/api/v2/details/ip/&query_entry=\\S+";

}
