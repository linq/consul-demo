# consul-demo
spring with consul dynamic configuration

Spring dynanic config backend by consul.

The Config is base on Spring java-style. I recommend copy the config foder to you project.

## How to get config

Becuase we schedule  pull config from consul, you can't get config with normal spring @value style.

You should autowire the org.springframework.core.env.Environment，then getProperty('some property');

Demo can refer to TestController.
