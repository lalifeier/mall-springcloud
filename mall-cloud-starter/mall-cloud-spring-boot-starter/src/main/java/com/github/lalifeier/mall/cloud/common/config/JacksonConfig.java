package com.github.lalifeier.mall.cloud.common.config;

//@Configuration
//public class JacksonConfig {
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        return objectMapper;
//    }
//}


//@Configuration
//public class JacksonConfig implements SmartInitializingSingleton {
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Override
//  public void afterSingletonsInstantiated() {
//    SimpleModule simpleModule = new SimpleModule();
//    simpleModule.addDeserializer(Enum.class, new EnumDeserializer());
//    objectMapper.registerModule(simpleModule);
//  }
//}
