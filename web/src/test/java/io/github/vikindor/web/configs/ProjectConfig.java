package io.github.vikindor.web.configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:auth.properties"})
public interface ProjectConfig extends Config {

    @Key("todoist.email")
    String todoistEmail();

    @Key("todoist.password")
    String todoistPassword();

    @Key("todoist.device_id")
    String todoistDeviceId();
}
