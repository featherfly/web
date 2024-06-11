# 1.0.0 2024-06-11
1. 升级spring5到spring6
2. 升级jdk8到jdk17

# 0.4.19 2022-12-14
1. 升级依赖
2. 修复Jxls检测ResolverPath与设置datakey时没有检索request参数的问题
3. Result去掉status属性
4. ObjectMapperConfiguration移除writeNullMapValues,writeEmptyJsonArrays属性
5. ObjectMapperConfiguration加入JsonWriteFeature和JsonReadFeature枚举对应的设置属性

# 0.4.18 2022-11-21
1. 不兼容性依赖升级

# 0.4.17 2022-04-06
1. 修复common-core不兼容升级
2. 升级spring、jxls版本

# 0.4.16 2021-08-30
1. AttachHttpMessageConverter加入ClassLoader参数，用于处理项目使用特殊加载器的情况（如springboot jar）

# 0.4.15 2021-08-30
1. 加入CrosHostInterceptor
2. AttachHttpMessageConverter获取模板(getTemplate)都要从templateBasePath开始查找，并加入从request.attribute中获取模板路径

# 0.4.14 2021-04-01
1. 修复PageHandlerMethodArgumentResolver pageFactory拼写错误问题

# 0.4.13 2021-03-07
1. 返回参数的code带有默认值(OK, ERROR)

# 0.4.12 2020-12-23
1. 更新Response逻辑
2. 删除PaginationHandlerMethodArgumentResolver

# 0.4.11 2020-7-7
1. ResponseBodyWrapFactoryBean支持spring boot

# 0.4.10 2020-5-20
1. 兼容性依赖升级

# 0.4.9 2020-5-20
1. 加入PageHandlerMethodArgumentResolver
2. 升级依赖
3. 删除无用的测试代码

# 0.4.8 2019-12-03
1. 升级依赖

# 0.4.7 2019-8-30
1. ResponseBodyWrapHandler加入黑白名单
2. 加入BindExceptionHandlerExceptionResolver,BindResultHandlerExceptionResolver,ExceptionHandlerExceptionResolver,MethodArgumentNotValidExceptionHandlerExceptionResolver

# 0.4.6 2019-8-27
1. 升级依赖

# 0.4.5 2019-8-20
1. 去除cache和converter包，移入common-spring

# 0.4.4 2019-8-15
1. 升级依赖

# 0.4.3 2019-8-15
1.升级依赖 

# 0.4.2 2019-08-08
1. 加入MulitiUniqueKeyCache,MulitiUniqueKeyCacheManager

# 0.4.1 2019-08-06
1. 加入MulitiCacheManager

# 0.4.0 2019-08-06
1. 升级依赖

# 0.3.6 2017-05-31
1. PaginationHandlerMethodArgumentResolver加入setContentNegotiationManager

# 0.3.5 2017-04-27
1. 加入HttpCacheInterceptor和HttpCache
	
# 0.3.4 2017-04-20
1. AttachHttpMessageConverter加入URI路径找不到使用文件名（即最后的路径）再寻找逻辑
2. PaginationHandlerMethodArgumentResolver加入忽略分页类型

# 0.3.3 2017-04-18
1. 修复ExcelHttpMessageConverter设置默认文件名出错的问题

# 0.3.2 2017-04-14
1. 加入RequestHolderInterceptor
2. 加入JxlsHttpMessageConverter、ExcelHttpMessageConverter

# 0.3.1 2016-11-18
1. 修复StringToDateConverterFactory没有设置默认值的问题
