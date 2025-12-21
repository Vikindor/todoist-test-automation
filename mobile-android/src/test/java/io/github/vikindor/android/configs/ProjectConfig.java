package io.github.vikindor.android.configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:${platform}.properties"
})
public interface ProjectConfig extends Config {

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("app")
    String app();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("timeout")
    Integer timeout();

    @Key("appium.url")
    String appiumUrl();

    @Key("todoist.email")
    String todoistEmail();

    @Key("todoist.password")
    String todoistPassword();

    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("browserstack.url")
    String browserstackApp();

    @Key("browserstack.app")
    String browserstackUrl();

    @Key("browserstack.project.name")
    String browserstackProjectName();

    @Key("browserstack.build.name")
    String browserstackBuildName();

    @Key("browserstack.session.name")
    String browserstackSessionName();
}
