package com.hacom.li.manager

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by Edgar Rios on 23/10/2017.
 */
@Component
@ConfigurationProperties("limanager")
class ConfigManager {

    static String keyEncrypt
}
