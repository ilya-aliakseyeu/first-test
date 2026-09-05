package second_sprint.webshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${run}.properties",
})
public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("122.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("enableVideo")
    @DefaultValue("true")
    boolean enableVideo();

    @Key("enableVNC")
    @DefaultValue("true")
    boolean enableVNC();
}