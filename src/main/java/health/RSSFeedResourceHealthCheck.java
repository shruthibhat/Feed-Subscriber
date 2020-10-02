package health;

import com.codahale.metrics.health.HealthCheck;

public class RSSFeedResourceHealthCheck extends HealthCheck {

    protected Result check() {
            return Result.healthy();
    }
}
