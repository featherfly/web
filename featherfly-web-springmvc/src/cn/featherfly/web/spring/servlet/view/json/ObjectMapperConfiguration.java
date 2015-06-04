package cn.featherfly.web.spring.servlet.view.json;

import java.text.SimpleDateFormat;

import cn.featherfly.common.lang.LangUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <p>
 * ObjectMapperConfiguration
 * </p>
 * 
 * @author 钟冀
 */
public class ObjectMapperConfiguration {
    /**
     * <p>
     * 使用配置创建ObjectMapper
     * </p>
     * @return ObjectMapper
     */
    public ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        configure(mapper);
        return mapper;
    }
    /**
     * <p>
     * 使用配置信息设置传入mapper对象
     * </p>
     * @param mapper mapper
     */
    public void configure(ObjectMapper mapper) {
        if (mapper == null) return;
        
        if (LangUtils.isNotEmpty(dateFormat)) {
            mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        }
        if (include != null) {            
            mapper.setSerializationInclusion(include);
        }
        // SerializationFeature
        configure(mapper, SerializationFeature.WRITE_ENUMS_USING_INDEX, writeEnumsUseingIndex);
        configure(mapper, SerializationFeature.WRITE_ENUMS_USING_TO_STRING, writeEnumsUsingToString);
        configure(mapper, SerializationFeature.WRAP_ROOT_VALUE, wrapRootValue);
        configure(mapper, SerializationFeature.INDENT_OUTPUT, indentOutput);
        configure(mapper, SerializationFeature.WRAP_EXCEPTIONS, wrapExceptions);
        configure(mapper, SerializationFeature.FAIL_ON_EMPTY_BEANS, failOnEmptyBeans);
        configure(mapper, SerializationFeature.FAIL_ON_SELF_REFERENCES, failOnSelfReferences);
        configure(mapper, SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, failOnUnwrappedTypeIdentifiers);
        configure(mapper, SerializationFeature.CLOSE_CLOSEABLE, closeCloseable);
        configure(mapper, SerializationFeature.FLUSH_AFTER_WRITE_VALUE, flushAfterWriteValue);
        configure(mapper, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, writeDatesAsTimestamps);
        configure(mapper, SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, writeDurationsAsTimestamps);
        configure(mapper, SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, writeDateKeysAsTimestamps);
        configure(mapper, SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, writeCharArraysAsJsonArrays);
        configure(mapper, SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, writeSingleElemArraysUnwrapped);
        configure(mapper, SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, orderMapEntriesByKeys);
        configure(mapper, SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, useEqualityForObjectId);
        configure(mapper, SerializationFeature.WRITE_NULL_MAP_VALUES, writeNullMapValues);
        configure(mapper, SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, writeEmptyJsonArrays);
        configure(mapper, SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, writeDateTimestampsAsNanoseconds);
        configure(mapper, SerializationFeature.EAGER_SERIALIZER_FETCH, eagerserializerfetch);
        
        // MapperFeature
        configure(mapper, MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, acceptCaseInsensitiveProperties);
        configure(mapper, MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, allowFinalFieldsAsMutators);
        configure(mapper, MapperFeature.AUTO_DETECT_CREATORS, autoDetectCreators);
        configure(mapper, MapperFeature.AUTO_DETECT_FIELDS, autoDetectFields);
        configure(mapper, MapperFeature.AUTO_DETECT_GETTERS, autoDetectGetters);
        configure(mapper, MapperFeature.AUTO_DETECT_IS_GETTERS, autoDetectIsGetters);
        configure(mapper, MapperFeature.AUTO_DETECT_SETTERS, autoDetectSetters);
        configure(mapper, MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, canOverrideAccessModifiers);
        configure(mapper, MapperFeature.DEFAULT_VIEW_INCLUSION, defaultViewInclusion);
        configure(mapper, MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS, ignoreDuplicateModuleRegistrations);
        configure(mapper, MapperFeature.INFER_PROPERTY_MUTATORS, inferPropertyMutators);
        configure(mapper, MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, requireSettersForGetters);
        configure(mapper, MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, sortPropertiesAlphabetically);
        configure(mapper, MapperFeature.USE_ANNOTATIONS, useAnnotations);
        configure(mapper, MapperFeature.USE_GETTERS_AS_SETTERS, useGettersAsSetters);
        configure(mapper, MapperFeature.USE_STATIC_TYPING, useStaticTyping);
        configure(mapper, MapperFeature.USE_STD_BEAN_NAMING, useStdBeanNaming);
        configure(mapper, MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, useWrapperNameAsPropertyName);
        
        // Feature
        configure(mapper, Feature.WRITE_NUMBERS_AS_STRINGS, writeNumbersAsStrings);
        configure(mapper, Feature.WRITE_BIGDECIMAL_AS_PLAIN, writeBigdecimalAsPlain);
        configure(mapper, Feature.ESCAPE_NON_ASCII, escapeNonAscii);
        configure(mapper, Feature.STRICT_DUPLICATE_DETECTION, strictDuplicateDetection);
        configure(mapper, Feature.IGNORE_UNKNOWN, ignoreUnknown);
        configure(mapper, Feature.AUTO_CLOSE_TARGET, autoCloseTarget);
        configure(mapper, Feature.AUTO_CLOSE_JSON_CONTENT, autoCloseJsonContent);
        configure(mapper, Feature.QUOTE_FIELD_NAMES, quoteFieldNames);
        configure(mapper, Feature.QUOTE_NON_NUMERIC_NUMBERS, quoteNonNumericNumbers);
        configure(mapper, Feature.FLUSH_PASSED_TO_STREAM, flushPassedToStream);
    }
    
    private void configure(ObjectMapper mapper, SerializationFeature feature, Boolean state) {
        if (state != null) mapper.configure(feature, state);
    }
    private void configure(ObjectMapper mapper, MapperFeature feature, Boolean state) {
        if (state != null) mapper.configure(feature, state);
    }
    private void configure(ObjectMapper mapper, Feature feature, Boolean state) {
        if (state != null) mapper.configure(feature, state);
    }
    
    private String dateFormat;
    
    private Include include;
    
    // SerializationFeature
    private Boolean writeEnumsUseingIndex;

    private Boolean writeEnumsUsingToString;

    private Boolean wrapRootValue;

    private Boolean indentOutput;

    private Boolean wrapExceptions/* = true*/;

    private Boolean failOnEmptyBeans/* = true*/;

    private Boolean failOnSelfReferences/* = true*/;

    private Boolean failOnUnwrappedTypeIdentifiers/* = true*/;

    private Boolean closeCloseable;

    private Boolean flushAfterWriteValue/* = true*/;

    private Boolean writeDatesAsTimestamps/* = true*/;

    private Boolean writeDurationsAsTimestamps/* = true*/;

    private Boolean writeDateKeysAsTimestamps;

    private Boolean writeCharArraysAsJsonArrays;

    private Boolean writeSingleElemArraysUnwrapped;

    private Boolean orderMapEntriesByKeys;

    private Boolean useEqualityForObjectId;

    private Boolean writeNullMapValues/* = true*/;

    private Boolean writeEmptyJsonArrays/* = true*/;

    private Boolean writeDateTimestampsAsNanoseconds/* = true*/;

    private Boolean eagerserializerfetch/* = true*/;

    // SerializationFeature

    // MapperFeature

    private Boolean requireSettersForGetters;
    private Boolean useStaticTyping;
    private Boolean sortPropertiesAlphabetically;
    private Boolean acceptCaseInsensitiveProperties;
    private Boolean useWrapperNameAsPropertyName;
    private Boolean useStdBeanNaming;

    private Boolean useAnnotations/* = true*/;
    private Boolean autoDetectCreators/* = true*/;
    private Boolean autoDetectFields/* = true*/;
    private Boolean autoDetectGetters/* = true*/;
    private Boolean autoDetectIsGetters/* = true*/;
    private Boolean autoDetectSetters/* = true*/;
    private Boolean useGettersAsSetters/* = true*/;
    private Boolean canOverrideAccessModifiers/* = true*/;
    private Boolean inferPropertyMutators/* = true*/;
    private Boolean allowFinalFieldsAsMutators/* = true*/;
    private Boolean defaultViewInclusion/* = true*/;
    private Boolean ignoreDuplicateModuleRegistrations/* = true*/;

    // mapperfeature

    // jsongenerator.feature

    private Boolean writeNumbersAsStrings;
    private Boolean writeBigdecimalAsPlain;
    private Boolean escapeNonAscii;
    private Boolean strictDuplicateDetection;
    private Boolean ignoreUnknown;

    private Boolean autoCloseTarget/* = true*/;
    private Boolean autoCloseJsonContent/* = true*/;
    private Boolean quoteFieldNames/* = true*/;
    private Boolean quoteNonNumericNumbers/* = true*/;
    private Boolean flushPassedToStream/* = true*/;
    
    // JsonGenerator.Feature
    
    /**
     * 返回dateFormat
     * @return dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }
    /**
     * 设置dateFormat
     * @param dateFormat dateFormat
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    /**
     * 返回writeEnumsUseingIndex
     * @return writeEnumsUseingIndex
     */
    public Boolean getWriteEnumsUseingIndex() {
        return writeEnumsUseingIndex;
    }
    /**
     * 设置writeEnumsUseingIndex
     * @param writeEnumsUseingIndex writeEnumsUseingIndex
     */
    public void setWriteEnumsUseingIndex(Boolean writeEnumsUseingIndex) {
        this.writeEnumsUseingIndex = writeEnumsUseingIndex;
    }
    /**
     * 返回writeEnumsUsingToString
     * @return writeEnumsUsingToString
     */
    public Boolean getWriteEnumsUsingToString() {
        return writeEnumsUsingToString;
    }
    /**
     * 设置writeEnumsUsingToString
     * @param writeEnumsUsingToString writeEnumsUsingToString
     */
    public void setWriteEnumsUsingToString(Boolean writeEnumsUsingToString) {
        this.writeEnumsUsingToString = writeEnumsUsingToString;
    }
    /**
     * 返回wrapRootValue
     * @return wrapRootValue
     */
    public Boolean getWrapRootValue() {
        return wrapRootValue;
    }
    /**
     * 设置wrapRootValue
     * @param wrapRootValue wrapRootValue
     */
    public void setWrapRootValue(Boolean wrapRootValue) {
        this.wrapRootValue = wrapRootValue;
    }
    /**
     * 返回indentOutput
     * @return indentOutput
     */
    public Boolean getIndentOutput() {
        return indentOutput;
    }
    /**
     * 设置indentOutput
     * @param indentOutput indentOutput
     */
    public void setIndentOutput(Boolean indentOutput) {
        this.indentOutput = indentOutput;
    }
    /**
     * 返回wrapExceptions
     * @return wrapExceptions
     */
    public Boolean getWrapExceptions() {
        return wrapExceptions;
    }
    /**
     * 设置wrapExceptions
     * @param wrapExceptions wrapExceptions
     */
    public void setWrapExceptions(Boolean wrapExceptions) {
        this.wrapExceptions = wrapExceptions;
    }
    /**
     * 返回failOnEmptyBeans
     * @return failOnEmptyBeans
     */
    public Boolean getFailOnEmptyBeans() {
        return failOnEmptyBeans;
    }
    /**
     * 设置failOnEmptyBeans
     * @param failOnEmptyBeans failOnEmptyBeans
     */
    public void setFailOnEmptyBeans(Boolean failOnEmptyBeans) {
        this.failOnEmptyBeans = failOnEmptyBeans;
    }
    /**
     * 返回failOnSelfReferences
     * @return failOnSelfReferences
     */
    public Boolean getFailOnSelfReferences() {
        return failOnSelfReferences;
    }
    /**
     * 设置failOnSelfReferences
     * @param failOnSelfReferences failOnSelfReferences
     */
    public void setFailOnSelfReferences(Boolean failOnSelfReferences) {
        this.failOnSelfReferences = failOnSelfReferences;
    }
    /**
     * 返回failOnUnwrappedTypeIdentifiers
     * @return failOnUnwrappedTypeIdentifiers
     */
    public Boolean getFailOnUnwrappedTypeIdentifiers() {
        return failOnUnwrappedTypeIdentifiers;
    }
    /**
     * 设置failOnUnwrappedTypeIdentifiers
     * @param failOnUnwrappedTypeIdentifiers failOnUnwrappedTypeIdentifiers
     */
    public void setFailOnUnwrappedTypeIdentifiers(
            Boolean failOnUnwrappedTypeIdentifiers) {
        this.failOnUnwrappedTypeIdentifiers = failOnUnwrappedTypeIdentifiers;
    }
    /**
     * 返回closeCloseable
     * @return closeCloseable
     */
    public Boolean getCloseCloseable() {
        return closeCloseable;
    }
    /**
     * 设置closeCloseable
     * @param closeCloseable closeCloseable
     */
    public void setCloseCloseable(Boolean closeCloseable) {
        this.closeCloseable = closeCloseable;
    }
    /**
     * 返回flushAfterWriteValue
     * @return flushAfterWriteValue
     */
    public Boolean getFlushAfterWriteValue() {
        return flushAfterWriteValue;
    }
    /**
     * 设置flushAfterWriteValue
     * @param flushAfterWriteValue flushAfterWriteValue
     */
    public void setFlushAfterWriteValue(Boolean flushAfterWriteValue) {
        this.flushAfterWriteValue = flushAfterWriteValue;
    }
    /**
     * 返回writeDatesAsTimestamps
     * @return writeDatesAsTimestamps
     */
    public Boolean getWriteDatesAsTimestamps() {
        return writeDatesAsTimestamps;
    }
    /**
     * 设置writeDatesAsTimestamps
     * @param writeDatesAsTimestamps writeDatesAsTimestamps
     */
    public void setWriteDatesAsTimestamps(Boolean writeDatesAsTimestamps) {
        this.writeDatesAsTimestamps = writeDatesAsTimestamps;
    }
    /**
     * 返回writeDurationsAsTimestamps
     * @return writeDurationsAsTimestamps
     */
    public Boolean getWriteDurationsAsTimestamps() {
        return writeDurationsAsTimestamps;
    }
    /**
     * 设置writeDurationsAsTimestamps
     * @param writeDurationsAsTimestamps writeDurationsAsTimestamps
     */
    public void setWriteDurationsAsTimestamps(Boolean writeDurationsAsTimestamps) {
        this.writeDurationsAsTimestamps = writeDurationsAsTimestamps;
    }
    /**
     * 返回writeDateKeysAsTimestamps
     * @return writeDateKeysAsTimestamps
     */
    public Boolean getWriteDateKeysAsTimestamps() {
        return writeDateKeysAsTimestamps;
    }
    /**
     * 设置writeDateKeysAsTimestamps
     * @param writeDateKeysAsTimestamps writeDateKeysAsTimestamps
     */
    public void setWriteDateKeysAsTimestamps(Boolean writeDateKeysAsTimestamps) {
        this.writeDateKeysAsTimestamps = writeDateKeysAsTimestamps;
    }
    /**
     * 返回writeCharArraysAsJsonArrays
     * @return writeCharArraysAsJsonArrays
     */
    public Boolean getWriteCharArraysAsJsonArrays() {
        return writeCharArraysAsJsonArrays;
    }
    /**
     * 设置writeCharArraysAsJsonArrays
     * @param writeCharArraysAsJsonArrays writeCharArraysAsJsonArrays
     */
    public void setWriteCharArraysAsJsonArrays(Boolean writeCharArraysAsJsonArrays) {
        this.writeCharArraysAsJsonArrays = writeCharArraysAsJsonArrays;
    }
    /**
     * 返回writeSingleElemArraysUnwrapped
     * @return writeSingleElemArraysUnwrapped
     */
    public Boolean getWriteSingleElemArraysUnwrapped() {
        return writeSingleElemArraysUnwrapped;
    }
    /**
     * 设置writeSingleElemArraysUnwrapped
     * @param writeSingleElemArraysUnwrapped writeSingleElemArraysUnwrapped
     */
    public void setWriteSingleElemArraysUnwrapped(
            Boolean writeSingleElemArraysUnwrapped) {
        this.writeSingleElemArraysUnwrapped = writeSingleElemArraysUnwrapped;
    }
    /**
     * 返回orderMapEntriesByKeys
     * @return orderMapEntriesByKeys
     */
    public Boolean getOrderMapEntriesByKeys() {
        return orderMapEntriesByKeys;
    }
    /**
     * 设置orderMapEntriesByKeys
     * @param orderMapEntriesByKeys orderMapEntriesByKeys
     */
    public void setOrderMapEntriesByKeys(Boolean orderMapEntriesByKeys) {
        this.orderMapEntriesByKeys = orderMapEntriesByKeys;
    }
    /**
     * 返回useEqualityForObjectId
     * @return useEqualityForObjectId
     */
    public Boolean getUseEqualityForObjectId() {
        return useEqualityForObjectId;
    }
    /**
     * 设置useEqualityForObjectId
     * @param useEqualityForObjectId useEqualityForObjectId
     */
    public void setUseEqualityForObjectId(Boolean useEqualityForObjectId) {
        this.useEqualityForObjectId = useEqualityForObjectId;
    }
    /**
     * 返回writeNullMapValues
     * @return writeNullMapValues
     */
    public Boolean getWriteNullMapValues() {
        return writeNullMapValues;
    }
    /**
     * 设置writeNullMapValues
     * @param writeNullMapValues writeNullMapValues
     */
    public void setWriteNullMapValues(Boolean writeNullMapValues) {
        this.writeNullMapValues = writeNullMapValues;
    }
    /**
     * 返回writeEmptyJsonArrays
     * @return writeEmptyJsonArrays
     */
    public Boolean getWriteEmptyJsonArrays() {
        return writeEmptyJsonArrays;
    }
    /**
     * 设置writeEmptyJsonArrays
     * @param writeEmptyJsonArrays writeEmptyJsonArrays
     */
    public void setWriteEmptyJsonArrays(Boolean writeEmptyJsonArrays) {
        this.writeEmptyJsonArrays = writeEmptyJsonArrays;
    }
    /**
     * 返回writeDateTimestampsAsNanoseconds
     * @return writeDateTimestampsAsNanoseconds
     */
    public Boolean getWriteDateTimestampsAsNanoseconds() {
        return writeDateTimestampsAsNanoseconds;
    }
    /**
     * 设置writeDateTimestampsAsNanoseconds
     * @param writeDateTimestampsAsNanoseconds writeDateTimestampsAsNanoseconds
     */
    public void setWriteDateTimestampsAsNanoseconds(
            Boolean writeDateTimestampsAsNanoseconds) {
        this.writeDateTimestampsAsNanoseconds = writeDateTimestampsAsNanoseconds;
    }
    /**
     * 返回eagerserializerfetch
     * @return eagerserializerfetch
     */
    public Boolean getEagerserializerfetch() {
        return eagerserializerfetch;
    }
    /**
     * 设置eagerserializerfetch
     * @param eagerserializerfetch eagerserializerfetch
     */
    public void setEagerserializerfetch(Boolean eagerserializerfetch) {
        this.eagerserializerfetch = eagerserializerfetch;
    }
    /**
     * 返回requireSettersForGetters
     * @return requireSettersForGetters
     */
    public Boolean getRequireSettersForGetters() {
        return requireSettersForGetters;
    }
    /**
     * 设置requireSettersForGetters
     * @param requireSettersForGetters requireSettersForGetters
     */
    public void setRequireSettersForGetters(Boolean requireSettersForGetters) {
        this.requireSettersForGetters = requireSettersForGetters;
    }
    /**
     * 返回useStaticTyping
     * @return useStaticTyping
     */
    public Boolean getUseStaticTyping() {
        return useStaticTyping;
    }
    /**
     * 设置useStaticTyping
     * @param useStaticTyping useStaticTyping
     */
    public void setUseStaticTyping(Boolean useStaticTyping) {
        this.useStaticTyping = useStaticTyping;
    }
    /**
     * 返回sortPropertiesAlphabetically
     * @return sortPropertiesAlphabetically
     */
    public Boolean getSortPropertiesAlphabetically() {
        return sortPropertiesAlphabetically;
    }
    /**
     * 设置sortPropertiesAlphabetically
     * @param sortPropertiesAlphabetically sortPropertiesAlphabetically
     */
    public void setSortPropertiesAlphabetically(Boolean sortPropertiesAlphabetically) {
        this.sortPropertiesAlphabetically = sortPropertiesAlphabetically;
    }
    /**
     * 返回acceptCaseInsensitiveProperties
     * @return acceptCaseInsensitiveProperties
     */
    public Boolean getAcceptCaseInsensitiveProperties() {
        return acceptCaseInsensitiveProperties;
    }
    /**
     * 设置acceptCaseInsensitiveProperties
     * @param acceptCaseInsensitiveProperties acceptCaseInsensitiveProperties
     */
    public void setAcceptCaseInsensitiveProperties(
            Boolean acceptCaseInsensitiveProperties) {
        this.acceptCaseInsensitiveProperties = acceptCaseInsensitiveProperties;
    }
    /**
     * 返回useWrapperNameAsPropertyName
     * @return useWrapperNameAsPropertyName
     */
    public Boolean getUseWrapperNameAsPropertyName() {
        return useWrapperNameAsPropertyName;
    }
    /**
     * 设置useWrapperNameAsPropertyName
     * @param useWrapperNameAsPropertyName useWrapperNameAsPropertyName
     */
    public void setUseWrapperNameAsPropertyName(Boolean useWrapperNameAsPropertyName) {
        this.useWrapperNameAsPropertyName = useWrapperNameAsPropertyName;
    }
    /**
     * 返回useStdBeanNaming
     * @return useStdBeanNaming
     */
    public Boolean getUseStdBeanNaming() {
        return useStdBeanNaming;
    }
    /**
     * 设置useStdBeanNaming
     * @param useStdBeanNaming useStdBeanNaming
     */
    public void setUseStdBeanNaming(Boolean useStdBeanNaming) {
        this.useStdBeanNaming = useStdBeanNaming;
    }
    /**
     * 返回useAnnotations
     * @return useAnnotations
     */
    public Boolean getUseAnnotations() {
        return useAnnotations;
    }
    /**
     * 设置useAnnotations
     * @param useAnnotations useAnnotations
     */
    public void setUseAnnotations(Boolean useAnnotations) {
        this.useAnnotations = useAnnotations;
    }
    /**
     * 返回autoDetectCreators
     * @return autoDetectCreators
     */
    public Boolean getAutoDetectCreators() {
        return autoDetectCreators;
    }
    /**
     * 设置autoDetectCreators
     * @param autoDetectCreators autoDetectCreators
     */
    public void setAutoDetectCreators(Boolean autoDetectCreators) {
        this.autoDetectCreators = autoDetectCreators;
    }
    /**
     * 返回autoDetectFields
     * @return autoDetectFields
     */
    public Boolean getAutoDetectFields() {
        return autoDetectFields;
    }
    /**
     * 设置autoDetectFields
     * @param autoDetectFields autoDetectFields
     */
    public void setAutoDetectFields(Boolean autoDetectFields) {
        this.autoDetectFields = autoDetectFields;
    }
    /**
     * 返回autoDetectGetters
     * @return autoDetectGetters
     */
    public Boolean getAutoDetectGetters() {
        return autoDetectGetters;
    }
    /**
     * 设置autoDetectGetters
     * @param autoDetectGetters autoDetectGetters
     */
    public void setAutoDetectGetters(Boolean autoDetectGetters) {
        this.autoDetectGetters = autoDetectGetters;
    }
    /**
     * 返回autoDetectIsGetters
     * @return autoDetectIsGetters
     */
    public Boolean getAutoDetectIsGetters() {
        return autoDetectIsGetters;
    }
    /**
     * 设置autoDetectIsGetters
     * @param autoDetectIsGetters autoDetectIsGetters
     */
    public void setAutoDetectIsGetters(Boolean autoDetectIsGetters) {
        this.autoDetectIsGetters = autoDetectIsGetters;
    }
    /**
     * 返回autoDetectSetters
     * @return autoDetectSetters
     */
    public Boolean getAutoDetectSetters() {
        return autoDetectSetters;
    }
    /**
     * 设置autoDetectSetters
     * @param autoDetectSetters autoDetectSetters
     */
    public void setAutoDetectSetters(Boolean autoDetectSetters) {
        this.autoDetectSetters = autoDetectSetters;
    }
    /**
     * 返回useGettersAsSetters
     * @return useGettersAsSetters
     */
    public Boolean getUseGettersAsSetters() {
        return useGettersAsSetters;
    }
    /**
     * 设置useGettersAsSetters
     * @param useGettersAsSetters useGettersAsSetters
     */
    public void setUseGettersAsSetters(Boolean useGettersAsSetters) {
        this.useGettersAsSetters = useGettersAsSetters;
    }
    /**
     * 返回canOverrideAccessModifiers
     * @return canOverrideAccessModifiers
     */
    public Boolean getCanOverrideAccessModifiers() {
        return canOverrideAccessModifiers;
    }
    /**
     * 设置canOverrideAccessModifiers
     * @param canOverrideAccessModifiers canOverrideAccessModifiers
     */
    public void setCanOverrideAccessModifiers(Boolean canOverrideAccessModifiers) {
        this.canOverrideAccessModifiers = canOverrideAccessModifiers;
    }
    /**
     * 返回inferPropertyMutators
     * @return inferPropertyMutators
     */
    public Boolean getInferPropertyMutators() {
        return inferPropertyMutators;
    }
    /**
     * 设置inferPropertyMutators
     * @param inferPropertyMutators inferPropertyMutators
     */
    public void setInferPropertyMutators(Boolean inferPropertyMutators) {
        this.inferPropertyMutators = inferPropertyMutators;
    }
    /**
     * 返回allowFinalFieldsAsMutators
     * @return allowFinalFieldsAsMutators
     */
    public Boolean getAllowFinalFieldsAsMutators() {
        return allowFinalFieldsAsMutators;
    }
    /**
     * 设置allowFinalFieldsAsMutators
     * @param allowFinalFieldsAsMutators allowFinalFieldsAsMutators
     */
    public void setAllowFinalFieldsAsMutators(Boolean allowFinalFieldsAsMutators) {
        this.allowFinalFieldsAsMutators = allowFinalFieldsAsMutators;
    }
    /**
     * 返回defaultViewInclusion
     * @return defaultViewInclusion
     */
    public Boolean getDefaultViewInclusion() {
        return defaultViewInclusion;
    }
    /**
     * 设置defaultViewInclusion
     * @param defaultViewInclusion defaultViewInclusion
     */
    public void setDefaultViewInclusion(Boolean defaultViewInclusion) {
        this.defaultViewInclusion = defaultViewInclusion;
    }
    /**
     * 返回ignoreDuplicateModuleRegistrations
     * @return ignoreDuplicateModuleRegistrations
     */
    public Boolean getIgnoreDuplicateModuleRegistrations() {
        return ignoreDuplicateModuleRegistrations;
    }
    /**
     * 设置ignoreDuplicateModuleRegistrations
     * @param ignoreDuplicateModuleRegistrations ignoreDuplicateModuleRegistrations
     */
    public void setIgnoreDuplicateModuleRegistrations(
            Boolean ignoreDuplicateModuleRegistrations) {
        this.ignoreDuplicateModuleRegistrations = ignoreDuplicateModuleRegistrations;
    }
    /**
     * 返回writeNumbersAsStrings
     * @return writeNumbersAsStrings
     */
    public Boolean getWriteNumbersAsStrings() {
        return writeNumbersAsStrings;
    }
    /**
     * 设置writeNumbersAsStrings
     * @param writeNumbersAsStrings writeNumbersAsStrings
     */
    public void setWriteNumbersAsStrings(Boolean writeNumbersAsStrings) {
        this.writeNumbersAsStrings = writeNumbersAsStrings;
    }
    /**
     * 返回writeBigdecimalAsPlain
     * @return writeBigdecimalAsPlain
     */
    public Boolean getWriteBigdecimalAsPlain() {
        return writeBigdecimalAsPlain;
    }
    /**
     * 设置writeBigdecimalAsPlain
     * @param writeBigdecimalAsPlain writeBigdecimalAsPlain
     */
    public void setWriteBigdecimalAsPlain(Boolean writeBigdecimalAsPlain) {
        this.writeBigdecimalAsPlain = writeBigdecimalAsPlain;
    }
    /**
     * 返回escapeNonAscii
     * @return escapeNonAscii
     */
    public Boolean getEscapeNonAscii() {
        return escapeNonAscii;
    }
    /**
     * 设置escapeNonAscii
     * @param escapeNonAscii escapeNonAscii
     */
    public void setEscapeNonAscii(Boolean escapeNonAscii) {
        this.escapeNonAscii = escapeNonAscii;
    }
    /**
     * 返回strictDuplicateDetection
     * @return strictDuplicateDetection
     */
    public Boolean getStrictDuplicateDetection() {
        return strictDuplicateDetection;
    }
    /**
     * 设置strictDuplicateDetection
     * @param strictDuplicateDetection strictDuplicateDetection
     */
    public void setStrictDuplicateDetection(Boolean strictDuplicateDetection) {
        this.strictDuplicateDetection = strictDuplicateDetection;
    }
    /**
     * 返回ignoreUnknown
     * @return ignoreUnknown
     */
    public Boolean getIgnoreUnknown() {
        return ignoreUnknown;
    }
    /**
     * 设置ignoreUnknown
     * @param ignoreUnknown ignoreUnknown
     */
    public void setIgnoreUnknown(Boolean ignoreUnknown) {
        this.ignoreUnknown = ignoreUnknown;
    }
    /**
     * 返回autoCloseTarget
     * @return autoCloseTarget
     */
    public Boolean getAutoCloseTarget() {
        return autoCloseTarget;
    }
    /**
     * 设置autoCloseTarget
     * @param autoCloseTarget autoCloseTarget
     */
    public void setAutoCloseTarget(Boolean autoCloseTarget) {
        this.autoCloseTarget = autoCloseTarget;
    }
    /**
     * 返回autoCloseJsonContent
     * @return autoCloseJsonContent
     */
    public Boolean getAutoCloseJsonContent() {
        return autoCloseJsonContent;
    }
    /**
     * 设置autoCloseJsonContent
     * @param autoCloseJsonContent autoCloseJsonContent
     */
    public void setAutoCloseJsonContent(Boolean autoCloseJsonContent) {
        this.autoCloseJsonContent = autoCloseJsonContent;
    }
    /**
     * 返回quoteFieldNames
     * @return quoteFieldNames
     */
    public Boolean getQuoteFieldNames() {
        return quoteFieldNames;
    }
    /**
     * 设置quoteFieldNames
     * @param quoteFieldNames quoteFieldNames
     */
    public void setQuoteFieldNames(Boolean quoteFieldNames) {
        this.quoteFieldNames = quoteFieldNames;
    }
    /**
     * 返回quoteNonNumericNumbers
     * @return quoteNonNumericNumbers
     */
    public Boolean getQuoteNonNumericNumbers() {
        return quoteNonNumericNumbers;
    }
    /**
     * 设置quoteNonNumericNumbers
     * @param quoteNonNumericNumbers quoteNonNumericNumbers
     */
    public void setQuoteNonNumericNumbers(Boolean quoteNonNumericNumbers) {
        this.quoteNonNumericNumbers = quoteNonNumericNumbers;
    }
    /**
     * 返回flushPassedToStream
     * @return flushPassedToStream
     */
    public Boolean getFlushPassedToStream() {
        return flushPassedToStream;
    }
    /**
     * 设置flushPassedToStream
     * @param flushPassedToStream flushPassedToStream
     */
    public void setFlushPassedToStream(Boolean flushPassedToStream) {
        this.flushPassedToStream = flushPassedToStream;
    }

    /**
     * 返回include
     * @return include
     */
    public Include getInclude() {
        return include;
    }

    /**
     * 设置include
     * @param include include
     */
    public void setInclude(Include include) {
        this.include = include;
    }
}
