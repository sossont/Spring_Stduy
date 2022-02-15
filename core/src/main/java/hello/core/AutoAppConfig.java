package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.ComponentScan.Filter;

@ComponentScan(
        excludeFilters = @Filter(classes = Configuration.class)
)
public class AutoAppConfig {
}
