package cn.featherfly.web.spring.servlet.view.json;

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
        
        // SerializationFeature
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, writeEnumsUseingIndex);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, writeEnumsUsingToString);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, wrapRootValue);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, indentOutput);
        mapper.configure(SerializationFeature.WRAP_EXCEPTIONS, wrapExceptions);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, failOnEmptyBeans);
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, failOnSelfReferences);
        mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, failOnUnwrappedTypeIdentifiers);
        mapper.configure(SerializationFeature.CLOSE_CLOSEABLE, closeCloseable);
        mapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, flushAfterWriteValue);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, writeDatesAsTimestamps);
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, writeDurationsAsTimestamps);
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, writeDateKeysAsTimestamps);
        mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, writeCharArraysAsJsonArrays);
        mapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, writeSingleElemArraysUnwrapped);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, orderMapEntriesByKeys);
        mapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, useEqualityForObjectId);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, writeNullMapValues);
        mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, writeEmptyJsonArrays);
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, writeDateTimestampsAsNanoseconds);
        mapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, eagerserializerfetch);
        
        // MapperFeature
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, acceptCaseInsensitiveProperties);
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, allowFinalFieldsAsMutators);
        mapper.configure(MapperFeature.AUTO_DETECT_CREATORS, autoDetectCreators);
        mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, autoDetectFields);
        mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, autoDetectGetters);
        mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, autoDetectIsGetters);
        mapper.configure(MapperFeature.AUTO_DETECT_SETTERS, autoDetectSetters);
        mapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, canOverrideAccessModifiers);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, defaultViewInclusion);
        mapper.configure(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS, ignoreDuplicateModuleRegistrations);
        mapper.configure(MapperFeature.INFER_PROPERTY_MUTATORS, inferPropertyMutators);
        mapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, requireSettersForGetters);
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, sortPropertiesAlphabetically);
        mapper.configure(MapperFeature.USE_ANNOTATIONS, useAnnotations);
        mapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, useGettersAsSetters);
        mapper.configure(MapperFeature.USE_STATIC_TYPING, useStaticTyping);
        mapper.configure(MapperFeature.USE_STD_BEAN_NAMING, useStdBeanNaming);
        mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, useWrapperNameAsPropertyName);
        
        // Feature
        mapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, writeNumbersAsStrings);
        mapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, writeBigdecimalAsPlain);
        mapper.configure(Feature.ESCAPE_NON_ASCII, escapeNonAscii);
        mapper.configure(Feature.STRICT_DUPLICATE_DETECTION, strictDuplicateDetection);
        mapper.configure(Feature.IGNORE_UNKNOWN, ignoreUnknown);
        mapper.configure(Feature.AUTO_CLOSE_TARGET, autoCloseTarget);
        mapper.configure(Feature.AUTO_CLOSE_JSON_CONTENT, autoCloseJsonContent);
        mapper.configure(Feature.QUOTE_FIELD_NAMES, quoteFieldNames);
        mapper.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, quoteNonNumericNumbers);
        mapper.configure(Feature.FLUSH_PASSED_TO_STREAM, flushPassedToStream);
        
    }
    
    
    // SerializationFeature
    private boolean writeEnumsUseingIndex;

    private boolean writeEnumsUsingToString;

    private boolean wrapRootValue;

    private boolean indentOutput;

    private boolean wrapExceptions = true;

    private boolean failOnEmptyBeans = true;

    private boolean failOnSelfReferences = true;

    private boolean failOnUnwrappedTypeIdentifiers = true;

    private boolean closeCloseable;

    private boolean flushAfterWriteValue = true;

    private boolean writeDatesAsTimestamps = true;

    private boolean writeDurationsAsTimestamps = true;

    private boolean writeDateKeysAsTimestamps;

    private boolean writeCharArraysAsJsonArrays;

    private boolean writeSingleElemArraysUnwrapped;

    private boolean orderMapEntriesByKeys;

    private boolean useEqualityForObjectId;

    private boolean writeNullMapValues = true;

    private boolean writeEmptyJsonArrays = true;

    private boolean writeDateTimestampsAsNanoseconds = true;

    private boolean eagerserializerfetch = true;

    // SerializationFeature

    // MapperFeature

    private boolean requireSettersForGetters;
    private boolean useStaticTyping;
    private boolean sortPropertiesAlphabetically;
    private boolean acceptCaseInsensitiveProperties;
    private boolean useWrapperNameAsPropertyName;
    private boolean useStdBeanNaming;

    private boolean useAnnotations = true;
    private boolean autoDetectCreators = true;
    private boolean autoDetectFields = true;
    private boolean autoDetectGetters = true;
    private boolean autoDetectIsGetters = true;
    private boolean autoDetectSetters = true;
    private boolean useGettersAsSetters = true;
    private boolean canOverrideAccessModifiers = true;
    private boolean inferPropertyMutators = true;
    private boolean allowFinalFieldsAsMutators = true;
    private boolean defaultViewInclusion = true;
    private boolean ignoreDuplicateModuleRegistrations = true;

    // mapperfeature

    // jsongenerator.feature

    private boolean writeNumbersAsStrings;
    private boolean writeBigdecimalAsPlain;
    private boolean escapeNonAscii;
    private boolean strictDuplicateDetection;
    private boolean ignoreUnknown;

    private boolean autoCloseTarget = true;
    private boolean autoCloseJsonContent = true;
    private boolean quoteFieldNames = true;
    private boolean quoteNonNumericNumbers = true;
    private boolean flushPassedToStream = true;
    
    // JsonGenerator.Feature

    /**
     * 返回writeEnumsUseingIndex
     * @return writeEnumsUseingIndex
     */
    public boolean isWriteEnumsUseingIndex() {
        return writeEnumsUseingIndex;
    }

    /**
     * 设置writeEnumsUseingIndex
     * @param writeEnumsUseingIndex writeEnumsUseingIndex
     */
    public void setWriteEnumsUseingIndex(boolean writeEnumsUseingIndex) {
        this.writeEnumsUseingIndex = writeEnumsUseingIndex;
    }

    /**
     * 返回writeEnumsUsingToString
     * @return writeEnumsUsingToString
     */
    public boolean isWriteEnumsUsingToString() {
        return writeEnumsUsingToString;
    }

    /**
     * 设置writeEnumsUsingToString
     * @param writeEnumsUsingToString writeEnumsUsingToString
     */
    public void setWriteEnumsUsingToString(boolean writeEnumsUsingToString) {
        this.writeEnumsUsingToString = writeEnumsUsingToString;
    }

    /**
     * 返回wrapRootValue
     * @return wrapRootValue
     */
    public boolean isWrapRootValue() {
        return wrapRootValue;
    }

    /**
     * 设置wrapRootValue
     * @param wrapRootValue wrapRootValue
     */
    public void setWrapRootValue(boolean wrapRootValue) {
        this.wrapRootValue = wrapRootValue;
    }

    /**
     * 返回indentOutput
     * @return indentOutput
     */
    public boolean isIndentOutput() {
        return indentOutput;
    }

    /**
     * 设置indentOutput
     * @param indentOutput indentOutput
     */
    public void setIndentOutput(boolean indentOutput) {
        this.indentOutput = indentOutput;
    }

    /**
     * 返回wrapExceptions
     * @return wrapExceptions
     */
    public boolean isWrapExceptions() {
        return wrapExceptions;
    }

    /**
     * 设置wrapExceptions
     * @param wrapExceptions wrapExceptions
     */
    public void setWrapExceptions(boolean wrapExceptions) {
        this.wrapExceptions = wrapExceptions;
    }

    /**
     * 返回failOnEmptyBeans
     * @return failOnEmptyBeans
     */
    public boolean isFailOnEmptyBeans() {
        return failOnEmptyBeans;
    }

    /**
     * 设置failOnEmptyBeans
     * @param failOnEmptyBeans failOnEmptyBeans
     */
    public void setFailOnEmptyBeans(boolean failOnEmptyBeans) {
        this.failOnEmptyBeans = failOnEmptyBeans;
    }

    /**
     * 返回failOnSelfReferences
     * @return failOnSelfReferences
     */
    public boolean isFailOnSelfReferences() {
        return failOnSelfReferences;
    }

    /**
     * 设置failOnSelfReferences
     * @param failOnSelfReferences failOnSelfReferences
     */
    public void setFailOnSelfReferences(boolean failOnSelfReferences) {
        this.failOnSelfReferences = failOnSelfReferences;
    }

    /**
     * 返回failOnUnwrappedTypeIdentifiers
     * @return failOnUnwrappedTypeIdentifiers
     */
    public boolean isFailOnUnwrappedTypeIdentifiers() {
        return failOnUnwrappedTypeIdentifiers;
    }

    /**
     * 设置failOnUnwrappedTypeIdentifiers
     * @param failOnUnwrappedTypeIdentifiers failOnUnwrappedTypeIdentifiers
     */
    public void setFailOnUnwrappedTypeIdentifiers(
            boolean failOnUnwrappedTypeIdentifiers) {
        this.failOnUnwrappedTypeIdentifiers = failOnUnwrappedTypeIdentifiers;
    }

    /**
     * 返回closeCloseable
     * @return closeCloseable
     */
    public boolean isCloseCloseable() {
        return closeCloseable;
    }

    /**
     * 设置closeCloseable
     * @param closeCloseable closeCloseable
     */
    public void setCloseCloseable(boolean closeCloseable) {
        this.closeCloseable = closeCloseable;
    }

    /**
     * 返回flushAfterWriteValue
     * @return flushAfterWriteValue
     */
    public boolean isFlushAfterWriteValue() {
        return flushAfterWriteValue;
    }

    /**
     * 设置flushAfterWriteValue
     * @param flushAfterWriteValue flushAfterWriteValue
     */
    public void setFlushAfterWriteValue(boolean flushAfterWriteValue) {
        this.flushAfterWriteValue = flushAfterWriteValue;
    }

    /**
     * 返回writeDatesAsTimestamps
     * @return writeDatesAsTimestamps
     */
    public boolean isWriteDatesAsTimestamps() {
        return writeDatesAsTimestamps;
    }

    /**
     * 设置writeDatesAsTimestamps
     * @param writeDatesAsTimestamps writeDatesAsTimestamps
     */
    public void setWriteDatesAsTimestamps(boolean writeDatesAsTimestamps) {
        this.writeDatesAsTimestamps = writeDatesAsTimestamps;
    }

    /**
     * 返回writeDurationsAsTimestamps
     * @return writeDurationsAsTimestamps
     */
    public boolean isWriteDurationsAsTimestamps() {
        return writeDurationsAsTimestamps;
    }

    /**
     * 设置writeDurationsAsTimestamps
     * @param writeDurationsAsTimestamps writeDurationsAsTimestamps
     */
    public void setWriteDurationsAsTimestamps(boolean writeDurationsAsTimestamps) {
        this.writeDurationsAsTimestamps = writeDurationsAsTimestamps;
    }

    /**
     * 返回writeDateKeysAsTimestamps
     * @return writeDateKeysAsTimestamps
     */
    public boolean isWriteDateKeysAsTimestamps() {
        return writeDateKeysAsTimestamps;
    }

    /**
     * 设置writeDateKeysAsTimestamps
     * @param writeDateKeysAsTimestamps writeDateKeysAsTimestamps
     */
    public void setWriteDateKeysAsTimestamps(boolean writeDateKeysAsTimestamps) {
        this.writeDateKeysAsTimestamps = writeDateKeysAsTimestamps;
    }

    /**
     * 返回writeCharArraysAsJsonArrays
     * @return writeCharArraysAsJsonArrays
     */
    public boolean isWriteCharArraysAsJsonArrays() {
        return writeCharArraysAsJsonArrays;
    }

    /**
     * 设置writeCharArraysAsJsonArrays
     * @param writeCharArraysAsJsonArrays writeCharArraysAsJsonArrays
     */
    public void setWriteCharArraysAsJsonArrays(boolean writeCharArraysAsJsonArrays) {
        this.writeCharArraysAsJsonArrays = writeCharArraysAsJsonArrays;
    }

    /**
     * 返回writeSingleElemArraysUnwrapped
     * @return writeSingleElemArraysUnwrapped
     */
    public boolean isWriteSingleElemArraysUnwrapped() {
        return writeSingleElemArraysUnwrapped;
    }

    /**
     * 设置writeSingleElemArraysUnwrapped
     * @param writeSingleElemArraysUnwrapped writeSingleElemArraysUnwrapped
     */
    public void setWriteSingleElemArraysUnwrapped(
            boolean writeSingleElemArraysUnwrapped) {
        this.writeSingleElemArraysUnwrapped = writeSingleElemArraysUnwrapped;
    }

    /**
     * 返回orderMapEntriesByKeys
     * @return orderMapEntriesByKeys
     */
    public boolean isOrderMapEntriesByKeys() {
        return orderMapEntriesByKeys;
    }

    /**
     * 设置orderMapEntriesByKeys
     * @param orderMapEntriesByKeys orderMapEntriesByKeys
     */
    public void setOrderMapEntriesByKeys(boolean orderMapEntriesByKeys) {
        this.orderMapEntriesByKeys = orderMapEntriesByKeys;
    }

    /**
     * 返回useEqualityForObjectId
     * @return useEqualityForObjectId
     */
    public boolean isUseEqualityForObjectId() {
        return useEqualityForObjectId;
    }

    /**
     * 设置useEqualityForObjectId
     * @param useEqualityForObjectId useEqualityForObjectId
     */
    public void setUseEqualityForObjectId(boolean useEqualityForObjectId) {
        this.useEqualityForObjectId = useEqualityForObjectId;
    }

    /**
     * 返回writeNullMapValues
     * @return writeNullMapValues
     */
    public boolean isWriteNullMapValues() {
        return writeNullMapValues;
    }

    /**
     * 设置writeNullMapValues
     * @param writeNullMapValues writeNullMapValues
     */
    public void setWriteNullMapValues(boolean writeNullMapValues) {
        this.writeNullMapValues = writeNullMapValues;
    }

    /**
     * 返回writeEmptyJsonArrays
     * @return writeEmptyJsonArrays
     */
    public boolean isWriteEmptyJsonArrays() {
        return writeEmptyJsonArrays;
    }

    /**
     * 设置writeEmptyJsonArrays
     * @param writeEmptyJsonArrays writeEmptyJsonArrays
     */
    public void setWriteEmptyJsonArrays(boolean writeEmptyJsonArrays) {
        this.writeEmptyJsonArrays = writeEmptyJsonArrays;
    }

    /**
     * 返回writeDateTimestampsAsNanoseconds
     * @return writeDateTimestampsAsNanoseconds
     */
    public boolean isWriteDateTimestampsAsNanoseconds() {
        return writeDateTimestampsAsNanoseconds;
    }

    /**
     * 设置writeDateTimestampsAsNanoseconds
     * @param writeDateTimestampsAsNanoseconds writeDateTimestampsAsNanoseconds
     */
    public void setWriteDateTimestampsAsNanoseconds(
            boolean writeDateTimestampsAsNanoseconds) {
        this.writeDateTimestampsAsNanoseconds = writeDateTimestampsAsNanoseconds;
    }

    /**
     * 返回eagerserializerfetch
     * @return eagerserializerfetch
     */
    public boolean isEagerserializerfetch() {
        return eagerserializerfetch;
    }

    /**
     * 设置eagerserializerfetch
     * @param eagerserializerfetch eagerserializerfetch
     */
    public void setEagerserializerfetch(boolean eagerserializerfetch) {
        this.eagerserializerfetch = eagerserializerfetch;
    }

    /**
     * 返回requireSettersForGetters
     * @return requireSettersForGetters
     */
    public boolean isRequireSettersForGetters() {
        return requireSettersForGetters;
    }

    /**
     * 设置requireSettersForGetters
     * @param requireSettersForGetters requireSettersForGetters
     */
    public void setRequireSettersForGetters(boolean requireSettersForGetters) {
        this.requireSettersForGetters = requireSettersForGetters;
    }

    /**
     * 返回useStaticTyping
     * @return useStaticTyping
     */
    public boolean isUseStaticTyping() {
        return useStaticTyping;
    }

    /**
     * 设置useStaticTyping
     * @param useStaticTyping useStaticTyping
     */
    public void setUseStaticTyping(boolean useStaticTyping) {
        this.useStaticTyping = useStaticTyping;
    }

    /**
     * 返回sortPropertiesAlphabetically
     * @return sortPropertiesAlphabetically
     */
    public boolean isSortPropertiesAlphabetically() {
        return sortPropertiesAlphabetically;
    }

    /**
     * 设置sortPropertiesAlphabetically
     * @param sortPropertiesAlphabetically sortPropertiesAlphabetically
     */
    public void setSortPropertiesAlphabetically(boolean sortPropertiesAlphabetically) {
        this.sortPropertiesAlphabetically = sortPropertiesAlphabetically;
    }

    /**
     * 返回acceptCaseInsensitiveProperties
     * @return acceptCaseInsensitiveProperties
     */
    public boolean isAcceptCaseInsensitiveProperties() {
        return acceptCaseInsensitiveProperties;
    }

    /**
     * 设置acceptCaseInsensitiveProperties
     * @param acceptCaseInsensitiveProperties acceptCaseInsensitiveProperties
     */
    public void setAcceptCaseInsensitiveProperties(
            boolean acceptCaseInsensitiveProperties) {
        this.acceptCaseInsensitiveProperties = acceptCaseInsensitiveProperties;
    }

    /**
     * 返回useWrapperNameAsPropertyName
     * @return useWrapperNameAsPropertyName
     */
    public boolean isUseWrapperNameAsPropertyName() {
        return useWrapperNameAsPropertyName;
    }

    /**
     * 设置useWrapperNameAsPropertyName
     * @param useWrapperNameAsPropertyName useWrapperNameAsPropertyName
     */
    public void setUseWrapperNameAsPropertyName(boolean useWrapperNameAsPropertyName) {
        this.useWrapperNameAsPropertyName = useWrapperNameAsPropertyName;
    }

    /**
     * 返回useStdBeanNaming
     * @return useStdBeanNaming
     */
    public boolean isUseStdBeanNaming() {
        return useStdBeanNaming;
    }

    /**
     * 设置useStdBeanNaming
     * @param useStdBeanNaming useStdBeanNaming
     */
    public void setUseStdBeanNaming(boolean useStdBeanNaming) {
        this.useStdBeanNaming = useStdBeanNaming;
    }

    /**
     * 返回useAnnotations
     * @return useAnnotations
     */
    public boolean isUseAnnotations() {
        return useAnnotations;
    }

    /**
     * 设置useAnnotations
     * @param useAnnotations useAnnotations
     */
    public void setUseAnnotations(boolean useAnnotations) {
        this.useAnnotations = useAnnotations;
    }

    /**
     * 返回autoDetectCreators
     * @return autoDetectCreators
     */
    public boolean isAutoDetectCreators() {
        return autoDetectCreators;
    }

    /**
     * 设置autoDetectCreators
     * @param autoDetectCreators autoDetectCreators
     */
    public void setAutoDetectCreators(boolean autoDetectCreators) {
        this.autoDetectCreators = autoDetectCreators;
    }

    /**
     * 返回autoDetectFields
     * @return autoDetectFields
     */
    public boolean isAutoDetectFields() {
        return autoDetectFields;
    }

    /**
     * 设置autoDetectFields
     * @param autoDetectFields autoDetectFields
     */
    public void setAutoDetectFields(boolean autoDetectFields) {
        this.autoDetectFields = autoDetectFields;
    }

    /**
     * 返回autoDetectGetters
     * @return autoDetectGetters
     */
    public boolean isAutoDetectGetters() {
        return autoDetectGetters;
    }

    /**
     * 设置autoDetectGetters
     * @param autoDetectGetters autoDetectGetters
     */
    public void setAutoDetectGetters(boolean autoDetectGetters) {
        this.autoDetectGetters = autoDetectGetters;
    }

    /**
     * 返回autoDetectIsGetters
     * @return autoDetectIsGetters
     */
    public boolean isAutoDetectIsGetters() {
        return autoDetectIsGetters;
    }

    /**
     * 设置autoDetectIsGetters
     * @param autoDetectIsGetters autoDetectIsGetters
     */
    public void setAutoDetectIsGetters(boolean autoDetectIsGetters) {
        this.autoDetectIsGetters = autoDetectIsGetters;
    }

    /**
     * 返回autoDetectSetters
     * @return autoDetectSetters
     */
    public boolean isAutoDetectSetters() {
        return autoDetectSetters;
    }

    /**
     * 设置autoDetectSetters
     * @param autoDetectSetters autoDetectSetters
     */
    public void setAutoDetectSetters(boolean autoDetectSetters) {
        this.autoDetectSetters = autoDetectSetters;
    }

    /**
     * 返回useGettersAsSetters
     * @return useGettersAsSetters
     */
    public boolean isUseGettersAsSetters() {
        return useGettersAsSetters;
    }

    /**
     * 设置useGettersAsSetters
     * @param useGettersAsSetters useGettersAsSetters
     */
    public void setUseGettersAsSetters(boolean useGettersAsSetters) {
        this.useGettersAsSetters = useGettersAsSetters;
    }

    /**
     * 返回canOverrideAccessModifiers
     * @return canOverrideAccessModifiers
     */
    public boolean isCanOverrideAccessModifiers() {
        return canOverrideAccessModifiers;
    }

    /**
     * 设置canOverrideAccessModifiers
     * @param canOverrideAccessModifiers canOverrideAccessModifiers
     */
    public void setCanOverrideAccessModifiers(boolean canOverrideAccessModifiers) {
        this.canOverrideAccessModifiers = canOverrideAccessModifiers;
    }

    /**
     * 返回inferPropertyMutators
     * @return inferPropertyMutators
     */
    public boolean isInferPropertyMutators() {
        return inferPropertyMutators;
    }

    /**
     * 设置inferPropertyMutators
     * @param inferPropertyMutators inferPropertyMutators
     */
    public void setInferPropertyMutators(boolean inferPropertyMutators) {
        this.inferPropertyMutators = inferPropertyMutators;
    }

    /**
     * 返回allowFinalFieldsAsMutators
     * @return allowFinalFieldsAsMutators
     */
    public boolean isAllowFinalFieldsAsMutators() {
        return allowFinalFieldsAsMutators;
    }

    /**
     * 设置allowFinalFieldsAsMutators
     * @param allowFinalFieldsAsMutators allowFinalFieldsAsMutators
     */
    public void setAllowFinalFieldsAsMutators(boolean allowFinalFieldsAsMutators) {
        this.allowFinalFieldsAsMutators = allowFinalFieldsAsMutators;
    }

    /**
     * 返回defaultViewInclusion
     * @return defaultViewInclusion
     */
    public boolean isDefaultViewInclusion() {
        return defaultViewInclusion;
    }

    /**
     * 设置defaultViewInclusion
     * @param defaultViewInclusion defaultViewInclusion
     */
    public void setDefaultViewInclusion(boolean defaultViewInclusion) {
        this.defaultViewInclusion = defaultViewInclusion;
    }

    /**
     * 返回ignoreDuplicateModuleRegistrations
     * @return ignoreDuplicateModuleRegistrations
     */
    public boolean isIgnoreDuplicateModuleRegistrations() {
        return ignoreDuplicateModuleRegistrations;
    }

    /**
     * 设置ignoreDuplicateModuleRegistrations
     * @param ignoreDuplicateModuleRegistrations ignoreDuplicateModuleRegistrations
     */
    public void setIgnoreDuplicateModuleRegistrations(
            boolean ignoreDuplicateModuleRegistrations) {
        this.ignoreDuplicateModuleRegistrations = ignoreDuplicateModuleRegistrations;
    }

    /**
     * 返回writeNumbersAsStrings
     * @return writeNumbersAsStrings
     */
    public boolean isWriteNumbersAsStrings() {
        return writeNumbersAsStrings;
    }

    /**
     * 设置writeNumbersAsStrings
     * @param writeNumbersAsStrings writeNumbersAsStrings
     */
    public void setWriteNumbersAsStrings(boolean writeNumbersAsStrings) {
        this.writeNumbersAsStrings = writeNumbersAsStrings;
    }

    /**
     * 返回writeBigdecimalAsPlain
     * @return writeBigdecimalAsPlain
     */
    public boolean isWriteBigdecimalAsPlain() {
        return writeBigdecimalAsPlain;
    }

    /**
     * 设置writeBigdecimalAsPlain
     * @param writeBigdecimalAsPlain writeBigdecimalAsPlain
     */
    public void setWriteBigdecimalAsPlain(boolean writeBigdecimalAsPlain) {
        this.writeBigdecimalAsPlain = writeBigdecimalAsPlain;
    }

    /**
     * 返回escapeNonAscii
     * @return escapeNonAscii
     */
    public boolean isEscapeNonAscii() {
        return escapeNonAscii;
    }

    /**
     * 设置escapeNonAscii
     * @param escapeNonAscii escapeNonAscii
     */
    public void setEscapeNonAscii(boolean escapeNonAscii) {
        this.escapeNonAscii = escapeNonAscii;
    }

    /**
     * 返回strictDuplicateDetection
     * @return strictDuplicateDetection
     */
    public boolean isStrictDuplicateDetection() {
        return strictDuplicateDetection;
    }

    /**
     * 设置strictDuplicateDetection
     * @param strictDuplicateDetection strictDuplicateDetection
     */
    public void setStrictDuplicateDetection(boolean strictDuplicateDetection) {
        this.strictDuplicateDetection = strictDuplicateDetection;
    }

    /**
     * 返回ignoreUnknown
     * @return ignoreUnknown
     */
    public boolean isIgnoreUnknown() {
        return ignoreUnknown;
    }

    /**
     * 设置ignoreUnknown
     * @param ignoreUnknown ignoreUnknown
     */
    public void setIgnoreUnknown(boolean ignoreUnknown) {
        this.ignoreUnknown = ignoreUnknown;
    }

    /**
     * 返回autoCloseTarget
     * @return autoCloseTarget
     */
    public boolean isAutoCloseTarget() {
        return autoCloseTarget;
    }

    /**
     * 设置autoCloseTarget
     * @param autoCloseTarget autoCloseTarget
     */
    public void setAutoCloseTarget(boolean autoCloseTarget) {
        this.autoCloseTarget = autoCloseTarget;
    }

    /**
     * 返回autoCloseJsonContent
     * @return autoCloseJsonContent
     */
    public boolean isAutoCloseJsonContent() {
        return autoCloseJsonContent;
    }

    /**
     * 设置autoCloseJsonContent
     * @param autoCloseJsonContent autoCloseJsonContent
     */
    public void setAutoCloseJsonContent(boolean autoCloseJsonContent) {
        this.autoCloseJsonContent = autoCloseJsonContent;
    }

    /**
     * 返回quoteFieldNames
     * @return quoteFieldNames
     */
    public boolean isQuoteFieldNames() {
        return quoteFieldNames;
    }

    /**
     * 设置quoteFieldNames
     * @param quoteFieldNames quoteFieldNames
     */
    public void setQuoteFieldNames(boolean quoteFieldNames) {
        this.quoteFieldNames = quoteFieldNames;
    }

    /**
     * 返回quoteNonNumericNumbers
     * @return quoteNonNumericNumbers
     */
    public boolean isQuoteNonNumericNumbers() {
        return quoteNonNumericNumbers;
    }

    /**
     * 设置quoteNonNumericNumbers
     * @param quoteNonNumericNumbers quoteNonNumericNumbers
     */
    public void setQuoteNonNumericNumbers(boolean quoteNonNumericNumbers) {
        this.quoteNonNumericNumbers = quoteNonNumericNumbers;
    }

    /**
     * 返回flushPassedToStream
     * @return flushPassedToStream
     */
    public boolean isFlushPassedToStream() {
        return flushPassedToStream;
    }

    /**
     * 设置flushPassedToStream
     * @param flushPassedToStream flushPassedToStream
     */
    public void setFlushPassedToStream(boolean flushPassedToStream) {
        this.flushPassedToStream = flushPassedToStream;
    }
}
