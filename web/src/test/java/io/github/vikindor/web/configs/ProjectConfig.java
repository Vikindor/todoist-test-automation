package io.github.vikindor.web.configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:auth.properties"})
public interface ProjectConfig extends Config {

    @Key("todoist.accounts.count")
    int todoistAccountsCount();

    @Key("todoist.accounts[%d].email")
    String todoistEmail(int accountIndex);

    @Key("todoist.accounts[%d].password")
    String todoistPassword(int accountIndex);

    @Key("todoist.accounts[%d].device_id")
    String todoistDeviceId(int accountIndex);
}
