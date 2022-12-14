package cn.featherfly.web.spring.servlet.view.json;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import cn.featherfly.common.lang.Lang;

/**
 * ObjectMapperConfiguration.
 *
 * @author 钟冀
 */
public class ObjectMapperConfiguration {
    /**
     * 使用配置创建ObjectMapper.
     *
     * @return ObjectMapper
     */
    public ObjectMapper create() {
        return configure();
    }

    private ObjectMapper configure() {
        JsonMapper.Builder mapperBuilder = JsonMapper.builder();

        // JsonWriteFeature
        configure(mapperBuilder, JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS, writeNumbersAsStrings);
        configure(mapperBuilder, JsonWriteFeature.ESCAPE_NON_ASCII, escapeNonAscii);
        configure(mapperBuilder, JsonWriteFeature.QUOTE_FIELD_NAMES, quoteFieldNames);
        configure(mapperBuilder, JsonWriteFeature.WRITE_NAN_AS_STRINGS, writeNanAsStrings);

        // JsonReadFeature
        configure(mapperBuilder, JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,
                allowBackslashEscapingAnyCharacter);
        configure(mapperBuilder, JsonReadFeature.ALLOW_JAVA_COMMENTS, allowJavaComments);
        configure(mapperBuilder, JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS,
                allowLeadingDecimalPointForNumbers);
        configure(mapperBuilder, JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS, allowLeadingZerosForNumbers);
        configure(mapperBuilder, JsonReadFeature.ALLOW_MISSING_VALUES, allowMissingValues);
        configure(mapperBuilder, JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS, allowNonNumericNumbers);
        configure(mapperBuilder, JsonReadFeature.ALLOW_SINGLE_QUOTES, allowSingleQuotes);
        configure(mapperBuilder, JsonReadFeature.ALLOW_TRAILING_COMMA, allowTrailingComma);
        configure(mapperBuilder, JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, allowUnescapedControlChars);
        configure(mapperBuilder, JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES, allowUnquotedFieldNames);
        configure(mapperBuilder, JsonReadFeature.ALLOW_YAML_COMMENTS, allowYamlComments);

        // MapperFeature
        configure(mapperBuilder, MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, acceptCaseInsensitiveProperties);
        configure(mapperBuilder, MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, allowFinalFieldsAsMutators);
        configure(mapperBuilder, MapperFeature.AUTO_DETECT_CREATORS, autoDetectCreators);
        configure(mapperBuilder, MapperFeature.AUTO_DETECT_FIELDS, autoDetectFields);
        configure(mapperBuilder, MapperFeature.AUTO_DETECT_GETTERS, autoDetectGetters);
        configure(mapperBuilder, MapperFeature.AUTO_DETECT_IS_GETTERS, autoDetectIsGetters);
        configure(mapperBuilder, MapperFeature.AUTO_DETECT_SETTERS, autoDetectSetters);
        configure(mapperBuilder, MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, canOverrideAccessModifiers);
        configure(mapperBuilder, MapperFeature.DEFAULT_VIEW_INCLUSION, defaultViewInclusion);
        configure(mapperBuilder, MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS,
                ignoreDuplicateModuleRegistrations);
        configure(mapperBuilder, MapperFeature.INFER_PROPERTY_MUTATORS, inferPropertyMutators);
        configure(mapperBuilder, MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, requireSettersForGetters);
        configure(mapperBuilder, MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, sortPropertiesAlphabetically);
        configure(mapperBuilder, MapperFeature.USE_ANNOTATIONS, useAnnotations);
        configure(mapperBuilder, MapperFeature.USE_GETTERS_AS_SETTERS, useGettersAsSetters);
        configure(mapperBuilder, MapperFeature.USE_STATIC_TYPING, useStaticTyping);
        configure(mapperBuilder, MapperFeature.USE_STD_BEAN_NAMING, useStdBeanNaming);
        configure(mapperBuilder, MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, useWrapperNameAsPropertyName);

        //        ObjectMapper mapper = new ObjectMapper(factoryBuilder.build());
        ObjectMapper mapper = mapperBuilder.build();
        configure(mapper);
        return mapper;
    }

    /**
     * 使用配置信息设置传入mapper对象.
     *
     * @param mapper mapper
     */
    public void configure(ObjectMapper mapper) {
        if (mapper == null) {
            return;
        }

        if (Lang.isNotEmpty(dateFormat)) {
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
        //        configure(mapper, SerializationFeature.WRITE_NULL_MAP_VALUES, writeNullMapValues);
        //        configure(mapper, SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, writeEmptyJsonArrays);
        configure(mapper, SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, writeDateTimestampsAsNanoseconds);
        configure(mapper, SerializationFeature.EAGER_SERIALIZER_FETCH, eagerserializerfetch);

        // Feature
        //        configure(mapper, Feature.WRITE_NUMBERS_AS_STRINGS, writeNumbersAsStrings);
        configure(mapper, Feature.WRITE_BIGDECIMAL_AS_PLAIN, writeBigdecimalAsPlain);
        //        configure(mapper, Feature.ESCAPE_NON_ASCII, escapeNonAscii);
        configure(mapper, Feature.STRICT_DUPLICATE_DETECTION, strictDuplicateDetection);
        configure(mapper, Feature.IGNORE_UNKNOWN, ignoreUnknown);
        configure(mapper, Feature.AUTO_CLOSE_TARGET, autoCloseTarget);
        configure(mapper, Feature.AUTO_CLOSE_JSON_CONTENT, autoCloseJsonContent);
        //        configure(mapper, Feature.QUOTE_FIELD_NAMES, quoteFieldNames);
        //        configure(mapper, Feature.QUOTE_NON_NUMERIC_NUMBERS, quoteNonNumericNumbers);
        configure(mapper, Feature.FLUSH_PASSED_TO_STREAM, flushPassedToStream);

        configure(mapper, DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, acceptEmptyArrayAsNullObject);
        configure(mapper, DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, acceptEmptyStringAsNullObject);
        configure(mapper, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, acceptSingleValueAsArray);
        configure(mapper, DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, adjustDatesToContextTimeZone);
        configure(mapper, DeserializationFeature.EAGER_DESERIALIZER_FETCH, eagerDeserializerFetch);
        configure(mapper, DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, failOnIgnoredProperties);
        configure(mapper, DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, failOnInvalidSubtype);
        configure(mapper, DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, failOnNullForPrimitives);
        configure(mapper, DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, failOnNumbersForEnums);
        configure(mapper, DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, failOnReadingDupTreeKey);
        configure(mapper, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
        configure(mapper, DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, failOnUnresolvedObjectIds);
        configure(mapper, DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, readDateTimestampsAsNanoseconds);
        configure(mapper, DeserializationFeature.READ_ENUMS_USING_TO_STRING, readEnumsUsingToString);
        configure(mapper, DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, readUnknownEnumValuesAsNull);
        configure(mapper, DeserializationFeature.UNWRAP_ROOT_VALUE, unwrapRootValue);
        configure(mapper, DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, unwrapSingleValueArrays);
        configure(mapper, DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, useBigDecimalForFloats);
        configure(mapper, DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, useBigIntegerForInts);
        configure(mapper, DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, userJavaArrayForJsonArray);
        configure(mapper, DeserializationFeature.WRAP_EXCEPTIONS, wrapExceptions);
    }

    private void configure(JsonMapper.Builder builder, JsonReadFeature feature, Boolean state) {
        if (state != null) {
            builder.configure(feature, state);
        }
    }

    private void configure(JsonMapper.Builder builder, JsonWriteFeature feature, Boolean state) {
        if (state != null) {
            builder.configure(feature, state);
        }
    }

    private void configure(JsonMapper.Builder builder, MapperFeature feature, Boolean state) {
        if (state != null) {
            builder.configure(feature, state);
        }
    }

    private void configure(ObjectMapper mapper, SerializationFeature feature, Boolean state) {
        if (state != null) {
            mapper.configure(feature, state);
        }
    }

    //    private void configure(ObjectMapper mapper, MapperFeature feature, Boolean state) {
    //        if (state != null) {
    //            mapper.configure(feature, state);
    //        }
    //    }

    private void configure(ObjectMapper mapper, Feature feature, Boolean state) {
        if (state != null) {
            mapper.configure(feature, state);
        }
    }

    private void configure(ObjectMapper mapper, DeserializationFeature feature, Boolean state) {
        if (state != null) {
            mapper.configure(feature, state);
        }
    }

    private String dateFormat;

    private Include include;

    // JsonWriteFeature

    private Boolean writeNumbersAsStrings;

    private Boolean escapeNonAscii;

    private Boolean writeNanAsStrings;

    private Boolean quoteFieldNames/* = true*/;

    // JsonWriteFeature

    // JsonReadFeature

    private Boolean allowBackslashEscapingAnyCharacter;

    private Boolean allowJavaComments;

    private Boolean allowYamlComments;

    private Boolean allowSingleQuotes;

    private Boolean allowUnquotedFieldNames;

    private Boolean allowUnescapedControlChars;

    private Boolean allowLeadingDecimalPointForNumbers;

    private Boolean allowLeadingZerosForNumbers;

    private Boolean allowNonNumericNumbers;

    private Boolean allowMissingValues;

    private Boolean allowTrailingComma;

    // JsonReadFeature

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

    //    private Boolean writeNullMapValues/* = true*/;
    //
    //    private Boolean writeEmptyJsonArrays/* = true*/;

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

    private Boolean writeBigdecimalAsPlain;
    private Boolean strictDuplicateDetection;
    private Boolean ignoreUnknown;

    private Boolean autoCloseTarget/* = true*/;
    private Boolean autoCloseJsonContent/* = true*/;

    private Boolean flushPassedToStream/* = true*/;

    // JsonGenerator.Feature

    // DeserializationFeature

    private Boolean acceptEmptyArrayAsNullObject;
    private Boolean acceptEmptyStringAsNullObject;
    private Boolean acceptSingleValueAsArray;
    private Boolean adjustDatesToContextTimeZone;
    private Boolean eagerDeserializerFetch;
    private Boolean failOnIgnoredProperties;
    private Boolean failOnInvalidSubtype;
    private Boolean failOnNullForPrimitives;
    private Boolean failOnNumbersForEnums;
    private Boolean failOnReadingDupTreeKey;
    private Boolean failOnUnknownProperties;
    private Boolean failOnUnresolvedObjectIds;
    private Boolean readDateTimestampsAsNanoseconds;
    private Boolean readEnumsUsingToString;
    private Boolean readUnknownEnumValuesAsNull;
    private Boolean unwrapRootValue;
    private Boolean unwrapSingleValueArrays;
    private Boolean useBigDecimalForFloats;
    private Boolean useBigIntegerForInts;
    private Boolean userJavaArrayForJsonArray;

    //DeserializationFeature

    /**
     * 返回dateFormat
     *
     * @return dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * 设置dateFormat
     *
     * @param dateFormat dateFormat
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 返回writeEnumsUseingIndex
     *
     * @return writeEnumsUseingIndex
     */
    public Boolean getWriteEnumsUseingIndex() {
        return writeEnumsUseingIndex;
    }

    /**
     * 设置writeEnumsUseingIndex
     *
     * @param writeEnumsUseingIndex writeEnumsUseingIndex
     */
    public void setWriteEnumsUseingIndex(Boolean writeEnumsUseingIndex) {
        this.writeEnumsUseingIndex = writeEnumsUseingIndex;
    }

    /**
     * 返回writeEnumsUsingToString
     *
     * @return writeEnumsUsingToString
     */
    public Boolean getWriteEnumsUsingToString() {
        return writeEnumsUsingToString;
    }

    /**
     * 设置writeEnumsUsingToString
     *
     * @param writeEnumsUsingToString writeEnumsUsingToString
     */
    public void setWriteEnumsUsingToString(Boolean writeEnumsUsingToString) {
        this.writeEnumsUsingToString = writeEnumsUsingToString;
    }

    /**
     * 返回wrapRootValue
     *
     * @return wrapRootValue
     */
    public Boolean getWrapRootValue() {
        return wrapRootValue;
    }

    /**
     * 设置wrapRootValue
     *
     * @param wrapRootValue wrapRootValue
     */
    public void setWrapRootValue(Boolean wrapRootValue) {
        this.wrapRootValue = wrapRootValue;
    }

    /**
     * 返回indentOutput
     *
     * @return indentOutput
     */
    public Boolean getIndentOutput() {
        return indentOutput;
    }

    /**
     * 设置indentOutput
     *
     * @param indentOutput indentOutput
     */
    public void setIndentOutput(Boolean indentOutput) {
        this.indentOutput = indentOutput;
    }

    /**
     * 返回wrapExceptions
     *
     * @return wrapExceptions
     */
    public Boolean getWrapExceptions() {
        return wrapExceptions;
    }

    /**
     * 设置wrapExceptions
     *
     * @param wrapExceptions wrapExceptions
     */
    public void setWrapExceptions(Boolean wrapExceptions) {
        this.wrapExceptions = wrapExceptions;
    }

    /**
     * 返回failOnEmptyBeans
     *
     * @return failOnEmptyBeans
     */
    public Boolean getFailOnEmptyBeans() {
        return failOnEmptyBeans;
    }

    /**
     * 设置failOnEmptyBeans
     *
     * @param failOnEmptyBeans failOnEmptyBeans
     */
    public void setFailOnEmptyBeans(Boolean failOnEmptyBeans) {
        this.failOnEmptyBeans = failOnEmptyBeans;
    }

    /**
     * 返回failOnSelfReferences
     *
     * @return failOnSelfReferences
     */
    public Boolean getFailOnSelfReferences() {
        return failOnSelfReferences;
    }

    /**
     * 设置failOnSelfReferences
     *
     * @param failOnSelfReferences failOnSelfReferences
     */
    public void setFailOnSelfReferences(Boolean failOnSelfReferences) {
        this.failOnSelfReferences = failOnSelfReferences;
    }

    /**
     * 返回failOnUnwrappedTypeIdentifiers
     *
     * @return failOnUnwrappedTypeIdentifiers
     */
    public Boolean getFailOnUnwrappedTypeIdentifiers() {
        return failOnUnwrappedTypeIdentifiers;
    }

    /**
     * 设置failOnUnwrappedTypeIdentifiers
     *
     * @param failOnUnwrappedTypeIdentifiers failOnUnwrappedTypeIdentifiers
     */
    public void setFailOnUnwrappedTypeIdentifiers(Boolean failOnUnwrappedTypeIdentifiers) {
        this.failOnUnwrappedTypeIdentifiers = failOnUnwrappedTypeIdentifiers;
    }

    /**
     * 返回closeCloseable
     *
     * @return closeCloseable
     */
    public Boolean getCloseCloseable() {
        return closeCloseable;
    }

    /**
     * 设置closeCloseable
     *
     * @param closeCloseable closeCloseable
     */
    public void setCloseCloseable(Boolean closeCloseable) {
        this.closeCloseable = closeCloseable;
    }

    /**
     * 返回flushAfterWriteValue
     *
     * @return flushAfterWriteValue
     */
    public Boolean getFlushAfterWriteValue() {
        return flushAfterWriteValue;
    }

    /**
     * 设置flushAfterWriteValue
     *
     * @param flushAfterWriteValue flushAfterWriteValue
     */
    public void setFlushAfterWriteValue(Boolean flushAfterWriteValue) {
        this.flushAfterWriteValue = flushAfterWriteValue;
    }

    /**
     * 返回writeDatesAsTimestamps
     *
     * @return writeDatesAsTimestamps
     */
    public Boolean getWriteDatesAsTimestamps() {
        return writeDatesAsTimestamps;
    }

    /**
     * 设置writeDatesAsTimestamps
     *
     * @param writeDatesAsTimestamps writeDatesAsTimestamps
     */
    public void setWriteDatesAsTimestamps(Boolean writeDatesAsTimestamps) {
        this.writeDatesAsTimestamps = writeDatesAsTimestamps;
    }

    /**
     * 返回writeDurationsAsTimestamps
     *
     * @return writeDurationsAsTimestamps
     */
    public Boolean getWriteDurationsAsTimestamps() {
        return writeDurationsAsTimestamps;
    }

    /**
     * 设置writeDurationsAsTimestamps
     *
     * @param writeDurationsAsTimestamps writeDurationsAsTimestamps
     */
    public void setWriteDurationsAsTimestamps(Boolean writeDurationsAsTimestamps) {
        this.writeDurationsAsTimestamps = writeDurationsAsTimestamps;
    }

    /**
     * 返回writeDateKeysAsTimestamps
     *
     * @return writeDateKeysAsTimestamps
     */
    public Boolean getWriteDateKeysAsTimestamps() {
        return writeDateKeysAsTimestamps;
    }

    /**
     * 设置writeDateKeysAsTimestamps
     *
     * @param writeDateKeysAsTimestamps writeDateKeysAsTimestamps
     */
    public void setWriteDateKeysAsTimestamps(Boolean writeDateKeysAsTimestamps) {
        this.writeDateKeysAsTimestamps = writeDateKeysAsTimestamps;
    }

    /**
     * 返回writeCharArraysAsJsonArrays
     *
     * @return writeCharArraysAsJsonArrays
     */
    public Boolean getWriteCharArraysAsJsonArrays() {
        return writeCharArraysAsJsonArrays;
    }

    /**
     * 设置writeCharArraysAsJsonArrays
     *
     * @param writeCharArraysAsJsonArrays writeCharArraysAsJsonArrays
     */
    public void setWriteCharArraysAsJsonArrays(Boolean writeCharArraysAsJsonArrays) {
        this.writeCharArraysAsJsonArrays = writeCharArraysAsJsonArrays;
    }

    /**
     * 返回writeSingleElemArraysUnwrapped
     *
     * @return writeSingleElemArraysUnwrapped
     */
    public Boolean getWriteSingleElemArraysUnwrapped() {
        return writeSingleElemArraysUnwrapped;
    }

    /**
     * 设置writeSingleElemArraysUnwrapped
     *
     * @param writeSingleElemArraysUnwrapped writeSingleElemArraysUnwrapped
     */
    public void setWriteSingleElemArraysUnwrapped(Boolean writeSingleElemArraysUnwrapped) {
        this.writeSingleElemArraysUnwrapped = writeSingleElemArraysUnwrapped;
    }

    /**
     * 返回orderMapEntriesByKeys
     *
     * @return orderMapEntriesByKeys
     */
    public Boolean getOrderMapEntriesByKeys() {
        return orderMapEntriesByKeys;
    }

    /**
     * 设置orderMapEntriesByKeys
     *
     * @param orderMapEntriesByKeys orderMapEntriesByKeys
     */
    public void setOrderMapEntriesByKeys(Boolean orderMapEntriesByKeys) {
        this.orderMapEntriesByKeys = orderMapEntriesByKeys;
    }

    /**
     * 返回useEqualityForObjectId
     *
     * @return useEqualityForObjectId
     */
    public Boolean getUseEqualityForObjectId() {
        return useEqualityForObjectId;
    }

    /**
     * 设置useEqualityForObjectId
     *
     * @param useEqualityForObjectId useEqualityForObjectId
     */
    public void setUseEqualityForObjectId(Boolean useEqualityForObjectId) {
        this.useEqualityForObjectId = useEqualityForObjectId;
    }

    /**
     * 返回writeDateTimestampsAsNanoseconds
     *
     * @return writeDateTimestampsAsNanoseconds
     */
    public Boolean getWriteDateTimestampsAsNanoseconds() {
        return writeDateTimestampsAsNanoseconds;
    }

    /**
     * 设置writeDateTimestampsAsNanoseconds
     *
     * @param writeDateTimestampsAsNanoseconds writeDateTimestampsAsNanoseconds
     */
    public void setWriteDateTimestampsAsNanoseconds(Boolean writeDateTimestampsAsNanoseconds) {
        this.writeDateTimestampsAsNanoseconds = writeDateTimestampsAsNanoseconds;
    }

    /**
     * 返回eagerserializerfetch
     *
     * @return eagerserializerfetch
     */
    public Boolean getEagerserializerfetch() {
        return eagerserializerfetch;
    }

    /**
     * 设置eagerserializerfetch
     *
     * @param eagerserializerfetch eagerserializerfetch
     */
    public void setEagerserializerfetch(Boolean eagerserializerfetch) {
        this.eagerserializerfetch = eagerserializerfetch;
    }

    /**
     * 返回requireSettersForGetters
     *
     * @return requireSettersForGetters
     */
    public Boolean getRequireSettersForGetters() {
        return requireSettersForGetters;
    }

    /**
     * 设置requireSettersForGetters
     *
     * @param requireSettersForGetters requireSettersForGetters
     */
    public void setRequireSettersForGetters(Boolean requireSettersForGetters) {
        this.requireSettersForGetters = requireSettersForGetters;
    }

    /**
     * 返回useStaticTyping
     *
     * @return useStaticTyping
     */
    public Boolean getUseStaticTyping() {
        return useStaticTyping;
    }

    /**
     * 设置useStaticTyping
     *
     * @param useStaticTyping useStaticTyping
     */
    public void setUseStaticTyping(Boolean useStaticTyping) {
        this.useStaticTyping = useStaticTyping;
    }

    /**
     * 返回sortPropertiesAlphabetically
     *
     * @return sortPropertiesAlphabetically
     */
    public Boolean getSortPropertiesAlphabetically() {
        return sortPropertiesAlphabetically;
    }

    /**
     * 设置sortPropertiesAlphabetically
     *
     * @param sortPropertiesAlphabetically sortPropertiesAlphabetically
     */
    public void setSortPropertiesAlphabetically(Boolean sortPropertiesAlphabetically) {
        this.sortPropertiesAlphabetically = sortPropertiesAlphabetically;
    }

    /**
     * 返回acceptCaseInsensitiveProperties
     *
     * @return acceptCaseInsensitiveProperties
     */
    public Boolean getAcceptCaseInsensitiveProperties() {
        return acceptCaseInsensitiveProperties;
    }

    /**
     * 设置acceptCaseInsensitiveProperties
     *
     * @param acceptCaseInsensitiveProperties acceptCaseInsensitiveProperties
     */
    public void setAcceptCaseInsensitiveProperties(Boolean acceptCaseInsensitiveProperties) {
        this.acceptCaseInsensitiveProperties = acceptCaseInsensitiveProperties;
    }

    /**
     * 返回useWrapperNameAsPropertyName
     *
     * @return useWrapperNameAsPropertyName
     */
    public Boolean getUseWrapperNameAsPropertyName() {
        return useWrapperNameAsPropertyName;
    }

    /**
     * 设置useWrapperNameAsPropertyName
     *
     * @param useWrapperNameAsPropertyName useWrapperNameAsPropertyName
     */
    public void setUseWrapperNameAsPropertyName(Boolean useWrapperNameAsPropertyName) {
        this.useWrapperNameAsPropertyName = useWrapperNameAsPropertyName;
    }

    /**
     * 返回useStdBeanNaming
     *
     * @return useStdBeanNaming
     */
    public Boolean getUseStdBeanNaming() {
        return useStdBeanNaming;
    }

    /**
     * 设置useStdBeanNaming
     *
     * @param useStdBeanNaming useStdBeanNaming
     */
    public void setUseStdBeanNaming(Boolean useStdBeanNaming) {
        this.useStdBeanNaming = useStdBeanNaming;
    }

    /**
     * 返回useAnnotations
     *
     * @return useAnnotations
     */
    public Boolean getUseAnnotations() {
        return useAnnotations;
    }

    /**
     * 设置useAnnotations
     *
     * @param useAnnotations useAnnotations
     */
    public void setUseAnnotations(Boolean useAnnotations) {
        this.useAnnotations = useAnnotations;
    }

    /**
     * 返回autoDetectCreators
     *
     * @return autoDetectCreators
     */
    public Boolean getAutoDetectCreators() {
        return autoDetectCreators;
    }

    /**
     * 设置autoDetectCreators
     *
     * @param autoDetectCreators autoDetectCreators
     */
    public void setAutoDetectCreators(Boolean autoDetectCreators) {
        this.autoDetectCreators = autoDetectCreators;
    }

    /**
     * 返回autoDetectFields
     *
     * @return autoDetectFields
     */
    public Boolean getAutoDetectFields() {
        return autoDetectFields;
    }

    /**
     * 设置autoDetectFields
     *
     * @param autoDetectFields autoDetectFields
     */
    public void setAutoDetectFields(Boolean autoDetectFields) {
        this.autoDetectFields = autoDetectFields;
    }

    /**
     * 返回autoDetectGetters
     *
     * @return autoDetectGetters
     */
    public Boolean getAutoDetectGetters() {
        return autoDetectGetters;
    }

    /**
     * 设置autoDetectGetters
     *
     * @param autoDetectGetters autoDetectGetters
     */
    public void setAutoDetectGetters(Boolean autoDetectGetters) {
        this.autoDetectGetters = autoDetectGetters;
    }

    /**
     * 返回autoDetectIsGetters
     *
     * @return autoDetectIsGetters
     */
    public Boolean getAutoDetectIsGetters() {
        return autoDetectIsGetters;
    }

    /**
     * 设置autoDetectIsGetters
     *
     * @param autoDetectIsGetters autoDetectIsGetters
     */
    public void setAutoDetectIsGetters(Boolean autoDetectIsGetters) {
        this.autoDetectIsGetters = autoDetectIsGetters;
    }

    /**
     * 返回autoDetectSetters
     *
     * @return autoDetectSetters
     */
    public Boolean getAutoDetectSetters() {
        return autoDetectSetters;
    }

    /**
     * 设置autoDetectSetters
     *
     * @param autoDetectSetters autoDetectSetters
     */
    public void setAutoDetectSetters(Boolean autoDetectSetters) {
        this.autoDetectSetters = autoDetectSetters;
    }

    /**
     * 返回useGettersAsSetters
     *
     * @return useGettersAsSetters
     */
    public Boolean getUseGettersAsSetters() {
        return useGettersAsSetters;
    }

    /**
     * 设置useGettersAsSetters
     *
     * @param useGettersAsSetters useGettersAsSetters
     */
    public void setUseGettersAsSetters(Boolean useGettersAsSetters) {
        this.useGettersAsSetters = useGettersAsSetters;
    }

    /**
     * 返回canOverrideAccessModifiers
     *
     * @return canOverrideAccessModifiers
     */
    public Boolean getCanOverrideAccessModifiers() {
        return canOverrideAccessModifiers;
    }

    /**
     * 设置canOverrideAccessModifiers
     *
     * @param canOverrideAccessModifiers canOverrideAccessModifiers
     */
    public void setCanOverrideAccessModifiers(Boolean canOverrideAccessModifiers) {
        this.canOverrideAccessModifiers = canOverrideAccessModifiers;
    }

    /**
     * 返回inferPropertyMutators
     *
     * @return inferPropertyMutators
     */
    public Boolean getInferPropertyMutators() {
        return inferPropertyMutators;
    }

    /**
     * 设置inferPropertyMutators
     *
     * @param inferPropertyMutators inferPropertyMutators
     */
    public void setInferPropertyMutators(Boolean inferPropertyMutators) {
        this.inferPropertyMutators = inferPropertyMutators;
    }

    /**
     * 返回allowFinalFieldsAsMutators
     *
     * @return allowFinalFieldsAsMutators
     */
    public Boolean getAllowFinalFieldsAsMutators() {
        return allowFinalFieldsAsMutators;
    }

    /**
     * 设置allowFinalFieldsAsMutators
     *
     * @param allowFinalFieldsAsMutators allowFinalFieldsAsMutators
     */
    public void setAllowFinalFieldsAsMutators(Boolean allowFinalFieldsAsMutators) {
        this.allowFinalFieldsAsMutators = allowFinalFieldsAsMutators;
    }

    /**
     * 返回defaultViewInclusion
     *
     * @return defaultViewInclusion
     */
    public Boolean getDefaultViewInclusion() {
        return defaultViewInclusion;
    }

    /**
     * 设置defaultViewInclusion
     *
     * @param defaultViewInclusion defaultViewInclusion
     */
    public void setDefaultViewInclusion(Boolean defaultViewInclusion) {
        this.defaultViewInclusion = defaultViewInclusion;
    }

    /**
     * 返回ignoreDuplicateModuleRegistrations
     *
     * @return ignoreDuplicateModuleRegistrations
     */
    public Boolean getIgnoreDuplicateModuleRegistrations() {
        return ignoreDuplicateModuleRegistrations;
    }

    /**
     * 设置ignoreDuplicateModuleRegistrations
     *
     * @param ignoreDuplicateModuleRegistrations ignoreDuplicateModuleRegistrations
     */
    public void setIgnoreDuplicateModuleRegistrations(Boolean ignoreDuplicateModuleRegistrations) {
        this.ignoreDuplicateModuleRegistrations = ignoreDuplicateModuleRegistrations;
    }

    /**
     * 返回writeNumbersAsStrings
     *
     * @return writeNumbersAsStrings
     */
    public Boolean getWriteNumbersAsStrings() {
        return writeNumbersAsStrings;
    }

    /**
     * 设置writeNumbersAsStrings
     *
     * @param writeNumbersAsStrings writeNumbersAsStrings
     */
    public void setWriteNumbersAsStrings(Boolean writeNumbersAsStrings) {
        this.writeNumbersAsStrings = writeNumbersAsStrings;
    }

    /**
     * 返回writeBigdecimalAsPlain
     *
     * @return writeBigdecimalAsPlain
     */
    public Boolean getWriteBigdecimalAsPlain() {
        return writeBigdecimalAsPlain;
    }

    /**
     * 设置writeBigdecimalAsPlain
     *
     * @param writeBigdecimalAsPlain writeBigdecimalAsPlain
     */
    public void setWriteBigdecimalAsPlain(Boolean writeBigdecimalAsPlain) {
        this.writeBigdecimalAsPlain = writeBigdecimalAsPlain;
    }

    /**
     * 返回escapeNonAscii
     *
     * @return escapeNonAscii
     */
    public Boolean getEscapeNonAscii() {
        return escapeNonAscii;
    }

    /**
     * 设置escapeNonAscii
     *
     * @param escapeNonAscii escapeNonAscii
     */
    public void setEscapeNonAscii(Boolean escapeNonAscii) {
        this.escapeNonAscii = escapeNonAscii;
    }

    /**
     * 返回strictDuplicateDetection
     *
     * @return strictDuplicateDetection
     */
    public Boolean getStrictDuplicateDetection() {
        return strictDuplicateDetection;
    }

    /**
     * 设置strictDuplicateDetection
     *
     * @param strictDuplicateDetection strictDuplicateDetection
     */
    public void setStrictDuplicateDetection(Boolean strictDuplicateDetection) {
        this.strictDuplicateDetection = strictDuplicateDetection;
    }

    /**
     * 返回ignoreUnknown
     *
     * @return ignoreUnknown
     */
    public Boolean getIgnoreUnknown() {
        return ignoreUnknown;
    }

    /**
     * 设置ignoreUnknown
     *
     * @param ignoreUnknown ignoreUnknown
     */
    public void setIgnoreUnknown(Boolean ignoreUnknown) {
        this.ignoreUnknown = ignoreUnknown;
    }

    /**
     * 返回autoCloseTarget
     *
     * @return autoCloseTarget
     */
    public Boolean getAutoCloseTarget() {
        return autoCloseTarget;
    }

    /**
     * 设置autoCloseTarget
     *
     * @param autoCloseTarget autoCloseTarget
     */
    public void setAutoCloseTarget(Boolean autoCloseTarget) {
        this.autoCloseTarget = autoCloseTarget;
    }

    /**
     * 返回autoCloseJsonContent
     *
     * @return autoCloseJsonContent
     */
    public Boolean getAutoCloseJsonContent() {
        return autoCloseJsonContent;
    }

    /**
     * 设置autoCloseJsonContent
     *
     * @param autoCloseJsonContent autoCloseJsonContent
     */
    public void setAutoCloseJsonContent(Boolean autoCloseJsonContent) {
        this.autoCloseJsonContent = autoCloseJsonContent;
    }

    /**
     * 返回quoteFieldNames
     *
     * @return quoteFieldNames
     */
    public Boolean getQuoteFieldNames() {
        return quoteFieldNames;
    }

    /**
     * 设置quoteFieldNames
     *
     * @param quoteFieldNames quoteFieldNames
     */
    public void setQuoteFieldNames(Boolean quoteFieldNames) {
        this.quoteFieldNames = quoteFieldNames;
    }

    /**
     * get writeNanAsStrings value
     *
     * @return writeNanAsStrings
     */
    public Boolean getWriteNanAsStrings() {
        return writeNanAsStrings;
    }

    /**
     * set writeNanAsStrings value
     *
     * @param writeNanAsStrings writeNanAsStrings
     */
    public void setWriteNanAsStrings(Boolean writeNanAsStrings) {
        this.writeNanAsStrings = writeNanAsStrings;
    }

    /**
     * get allowBackslashEscapingAnyCharacter value
     *
     * @return allowBackslashEscapingAnyCharacter
     */
    public Boolean getAllowBackslashEscapingAnyCharacter() {
        return allowBackslashEscapingAnyCharacter;
    }

    /**
     * set allowBackslashEscapingAnyCharacter value
     *
     * @param allowBackslashEscapingAnyCharacter allowBackslashEscapingAnyCharacter
     */
    public void setAllowBackslashEscapingAnyCharacter(Boolean allowBackslashEscapingAnyCharacter) {
        this.allowBackslashEscapingAnyCharacter = allowBackslashEscapingAnyCharacter;
    }

    /**
     * 返回quoteNonNumericNumbers
     *
     * @return quoteNonNumericNumbers
     * @deprecated {@link #getWriteNanAsStrings()}
     */
    @Deprecated
    public Boolean getQuoteNonNumericNumbers() {
        return getWriteNanAsStrings();
    }

    /**
     * 设置quoteNonNumericNumbers
     *
     * @param quoteNonNumericNumbers quoteNonNumericNumbers
     * @deprecated {@link #setWriteNanAsStrings(Boolean)}
     */
    @Deprecated
    public void setQuoteNonNumericNumbers(Boolean quoteNonNumericNumbers) {
        setWriteNanAsStrings(quoteNonNumericNumbers);
    }

    /**
     * 返回flushPassedToStream
     *
     * @return flushPassedToStream
     */
    public Boolean getFlushPassedToStream() {
        return flushPassedToStream;
    }

    /**
     * 设置flushPassedToStream
     *
     * @param flushPassedToStream flushPassedToStream
     */
    public void setFlushPassedToStream(Boolean flushPassedToStream) {
        this.flushPassedToStream = flushPassedToStream;
    }

    /**
     * 返回include
     *
     * @return include
     */
    public Include getInclude() {
        return include;
    }

    /**
     * 设置include
     *
     * @param include include
     */
    public void setInclude(Include include) {
        this.include = include;
    }

    /**
     * 返回acceptEmptyArrayAsNullObject
     *
     * @return acceptEmptyArrayAsNullObject
     */
    public Boolean getAcceptEmptyArrayAsNullObject() {
        return acceptEmptyArrayAsNullObject;
    }

    /**
     * 设置acceptEmptyArrayAsNullObject
     *
     * @param acceptEmptyArrayAsNullObject acceptEmptyArrayAsNullObject
     */
    public void setAcceptEmptyArrayAsNullObject(Boolean acceptEmptyArrayAsNullObject) {
        this.acceptEmptyArrayAsNullObject = acceptEmptyArrayAsNullObject;
    }

    /**
     * 返回acceptEmptyStringAsNullObject
     *
     * @return acceptEmptyStringAsNullObject
     */
    public Boolean getAcceptEmptyStringAsNullObject() {
        return acceptEmptyStringAsNullObject;
    }

    /**
     * 设置acceptEmptyStringAsNullObject
     *
     * @param acceptEmptyStringAsNullObject acceptEmptyStringAsNullObject
     */
    public void setAcceptEmptyStringAsNullObject(Boolean acceptEmptyStringAsNullObject) {
        this.acceptEmptyStringAsNullObject = acceptEmptyStringAsNullObject;
    }

    /**
     * 返回acceptSingleValueAsArray
     *
     * @return acceptSingleValueAsArray
     */
    public Boolean getAcceptSingleValueAsArray() {
        return acceptSingleValueAsArray;
    }

    /**
     * 设置acceptSingleValueAsArray
     *
     * @param acceptSingleValueAsArray acceptSingleValueAsArray
     */
    public void setAcceptSingleValueAsArray(Boolean acceptSingleValueAsArray) {
        this.acceptSingleValueAsArray = acceptSingleValueAsArray;
    }

    /**
     * 返回adjustDatesToContextTimeZone
     *
     * @return adjustDatesToContextTimeZone
     */
    public Boolean getAdjustDatesToContextTimeZone() {
        return adjustDatesToContextTimeZone;
    }

    /**
     * 设置adjustDatesToContextTimeZone
     *
     * @param adjustDatesToContextTimeZone adjustDatesToContextTimeZone
     */
    public void setAdjustDatesToContextTimeZone(Boolean adjustDatesToContextTimeZone) {
        this.adjustDatesToContextTimeZone = adjustDatesToContextTimeZone;
    }

    /**
     * 返回eagerDeserializerFetch
     *
     * @return eagerDeserializerFetch
     */
    public Boolean getEagerDeserializerFetch() {
        return eagerDeserializerFetch;
    }

    /**
     * 设置eagerDeserializerFetch
     *
     * @param eagerDeserializerFetch eagerDeserializerFetch
     */
    public void setEagerDeserializerFetch(Boolean eagerDeserializerFetch) {
        this.eagerDeserializerFetch = eagerDeserializerFetch;
    }

    /**
     * 返回failOnIgnoredProperties
     *
     * @return failOnIgnoredProperties
     */
    public Boolean getFailOnIgnoredProperties() {
        return failOnIgnoredProperties;
    }

    /**
     * 设置failOnIgnoredProperties
     *
     * @param failOnIgnoredProperties failOnIgnoredProperties
     */
    public void setFailOnIgnoredProperties(Boolean failOnIgnoredProperties) {
        this.failOnIgnoredProperties = failOnIgnoredProperties;
    }

    /**
     * 返回failOnInvalidSubtype
     *
     * @return failOnInvalidSubtype
     */
    public Boolean getFailOnInvalidSubtype() {
        return failOnInvalidSubtype;
    }

    /**
     * 设置failOnInvalidSubtype
     *
     * @param failOnInvalidSubtype failOnInvalidSubtype
     */
    public void setFailOnInvalidSubtype(Boolean failOnInvalidSubtype) {
        this.failOnInvalidSubtype = failOnInvalidSubtype;
    }

    /**
     * 返回failOnNullForPrimitives
     *
     * @return failOnNullForPrimitives
     */
    public Boolean getFailOnNullForPrimitives() {
        return failOnNullForPrimitives;
    }

    /**
     * 设置failOnNullForPrimitives
     *
     * @param failOnNullForPrimitives failOnNullForPrimitives
     */
    public void setFailOnNullForPrimitives(Boolean failOnNullForPrimitives) {
        this.failOnNullForPrimitives = failOnNullForPrimitives;
    }

    /**
     * 返回failOnNumbersForEnums
     *
     * @return failOnNumbersForEnums
     */
    public Boolean getFailOnNumbersForEnums() {
        return failOnNumbersForEnums;
    }

    /**
     * 设置failOnNumbersForEnums
     *
     * @param failOnNumbersForEnums failOnNumbersForEnums
     */
    public void setFailOnNumbersForEnums(Boolean failOnNumbersForEnums) {
        this.failOnNumbersForEnums = failOnNumbersForEnums;
    }

    /**
     * 返回failOnReadingDupTreeKey
     *
     * @return failOnReadingDupTreeKey
     */
    public Boolean getFailOnReadingDupTreeKey() {
        return failOnReadingDupTreeKey;
    }

    /**
     * 设置failOnReadingDupTreeKey
     *
     * @param failOnReadingDupTreeKey failOnReadingDupTreeKey
     */
    public void setFailOnReadingDupTreeKey(Boolean failOnReadingDupTreeKey) {
        this.failOnReadingDupTreeKey = failOnReadingDupTreeKey;
    }

    /**
     * 返回failOnUnknownProperties
     *
     * @return failOnUnknownProperties
     */
    public Boolean getFailOnUnknownProperties() {
        return failOnUnknownProperties;
    }

    /**
     * 设置failOnUnknownProperties
     *
     * @param failOnUnknownProperties failOnUnknownProperties
     */
    public void setFailOnUnknownProperties(Boolean failOnUnknownProperties) {
        this.failOnUnknownProperties = failOnUnknownProperties;
    }

    /**
     * 返回failOnUnresolvedObjectIds
     *
     * @return failOnUnresolvedObjectIds
     */
    public Boolean getFailOnUnresolvedObjectIds() {
        return failOnUnresolvedObjectIds;
    }

    /**
     * 设置failOnUnresolvedObjectIds
     *
     * @param failOnUnresolvedObjectIds failOnUnresolvedObjectIds
     */
    public void setFailOnUnresolvedObjectIds(Boolean failOnUnresolvedObjectIds) {
        this.failOnUnresolvedObjectIds = failOnUnresolvedObjectIds;
    }

    /**
     * 返回readDateTimestampsAsNanoseconds
     *
     * @return readDateTimestampsAsNanoseconds
     */
    public Boolean getReadDateTimestampsAsNanoseconds() {
        return readDateTimestampsAsNanoseconds;
    }

    /**
     * 设置readDateTimestampsAsNanoseconds
     *
     * @param readDateTimestampsAsNanoseconds readDateTimestampsAsNanoseconds
     */
    public void setReadDateTimestampsAsNanoseconds(Boolean readDateTimestampsAsNanoseconds) {
        this.readDateTimestampsAsNanoseconds = readDateTimestampsAsNanoseconds;
    }

    /**
     * 返回readEnumsUsingToString
     *
     * @return readEnumsUsingToString
     */
    public Boolean getReadEnumsUsingToString() {
        return readEnumsUsingToString;
    }

    /**
     * 设置readEnumsUsingToString
     *
     * @param readEnumsUsingToString readEnumsUsingToString
     */
    public void setReadEnumsUsingToString(Boolean readEnumsUsingToString) {
        this.readEnumsUsingToString = readEnumsUsingToString;
    }

    /**
     * 返回readUnknownEnumValuesAsNull
     *
     * @return readUnknownEnumValuesAsNull
     */
    public Boolean getReadUnknownEnumValuesAsNull() {
        return readUnknownEnumValuesAsNull;
    }

    /**
     * 设置readUnknownEnumValuesAsNull
     *
     * @param readUnknownEnumValuesAsNull readUnknownEnumValuesAsNull
     */
    public void setReadUnknownEnumValuesAsNull(Boolean readUnknownEnumValuesAsNull) {
        this.readUnknownEnumValuesAsNull = readUnknownEnumValuesAsNull;
    }

    /**
     * 返回unwrapRootValue
     *
     * @return unwrapRootValue
     */
    public Boolean getUnwrapRootValue() {
        return unwrapRootValue;
    }

    /**
     * 设置unwrapRootValue
     *
     * @param unwrapRootValue unwrapRootValue
     */
    public void setUnwrapRootValue(Boolean unwrapRootValue) {
        this.unwrapRootValue = unwrapRootValue;
    }

    /**
     * 返回unwrapSingleValueArrays
     *
     * @return unwrapSingleValueArrays
     */
    public Boolean getUnwrapSingleValueArrays() {
        return unwrapSingleValueArrays;
    }

    /**
     * 设置unwrapSingleValueArrays
     *
     * @param unwrapSingleValueArrays unwrapSingleValueArrays
     */
    public void setUnwrapSingleValueArrays(Boolean unwrapSingleValueArrays) {
        this.unwrapSingleValueArrays = unwrapSingleValueArrays;
    }

    /**
     * 返回useBigDecimalForFloats
     *
     * @return useBigDecimalForFloats
     */
    public Boolean getUseBigDecimalForFloats() {
        return useBigDecimalForFloats;
    }

    /**
     * 设置useBigDecimalForFloats
     *
     * @param useBigDecimalForFloats useBigDecimalForFloats
     */
    public void setUseBigDecimalForFloats(Boolean useBigDecimalForFloats) {
        this.useBigDecimalForFloats = useBigDecimalForFloats;
    }

    /**
     * 返回useBigIntegerForInts
     *
     * @return useBigIntegerForInts
     */
    public Boolean getUseBigIntegerForInts() {
        return useBigIntegerForInts;
    }

    /**
     * 设置useBigIntegerForInts
     *
     * @param useBigIntegerForInts useBigIntegerForInts
     */
    public void setUseBigIntegerForInts(Boolean useBigIntegerForInts) {
        this.useBigIntegerForInts = useBigIntegerForInts;
    }

    /**
     * 返回userJavaArrayForJsonArray
     *
     * @return userJavaArrayForJsonArray
     */
    public Boolean getUserJavaArrayForJsonArray() {
        return userJavaArrayForJsonArray;
    }

    /**
     * 设置userJavaArrayForJsonArray
     *
     * @param userJavaArrayForJsonArray userJavaArrayForJsonArray
     */
    public void setUserJavaArrayForJsonArray(Boolean userJavaArrayForJsonArray) {
        this.userJavaArrayForJsonArray = userJavaArrayForJsonArray;
    }

    /**
     * get allowJavaComments value
     *
     * @return allowJavaComments
     */
    public Boolean getAllowJavaComments() {
        return allowJavaComments;
    }

    /**
     * set allowJavaComments value
     *
     * @param allowJavaComments allowJavaComments
     */
    public void setAllowJavaComments(Boolean allowJavaComments) {
        this.allowJavaComments = allowJavaComments;
    }

    /**
     * get allowYamlComments value
     *
     * @return allowYamlComments
     */
    public Boolean getAllowYamlComments() {
        return allowYamlComments;
    }

    /**
     * set allowYamlComments value
     *
     * @param allowYamlComments allowYamlComments
     */
    public void setAllowYamlComments(Boolean allowYamlComments) {
        this.allowYamlComments = allowYamlComments;
    }

    /**
     * get allowSingleQuotes value
     *
     * @return allowSingleQuotes
     */
    public Boolean getAllowSingleQuotes() {
        return allowSingleQuotes;
    }

    /**
     * set allowSingleQuotes value
     *
     * @param allowSingleQuotes allowSingleQuotes
     */
    public void setAllowSingleQuotes(Boolean allowSingleQuotes) {
        this.allowSingleQuotes = allowSingleQuotes;
    }

    /**
     * get allowUnquotedFieldNames value
     *
     * @return allowUnquotedFieldNames
     */
    public Boolean getAllowUnquotedFieldNames() {
        return allowUnquotedFieldNames;
    }

    /**
     * set allowUnquotedFieldNames value
     *
     * @param allowUnquotedFieldNames allowUnquotedFieldNames
     */
    public void setAllowUnquotedFieldNames(Boolean allowUnquotedFieldNames) {
        this.allowUnquotedFieldNames = allowUnquotedFieldNames;
    }

    /**
     * get allowUnescapedControlChars value
     *
     * @return allowUnescapedControlChars
     */
    public Boolean getAllowUnescapedControlChars() {
        return allowUnescapedControlChars;
    }

    /**
     * set allowUnescapedControlChars value
     *
     * @param allowUnescapedControlChars allowUnescapedControlChars
     */
    public void setAllowUnescapedControlChars(Boolean allowUnescapedControlChars) {
        this.allowUnescapedControlChars = allowUnescapedControlChars;
    }

    /**
     * get allowLeadingDecimalPointForNumbers value
     *
     * @return allowLeadingDecimalPointForNumbers
     */
    public Boolean getAllowLeadingDecimalPointForNumbers() {
        return allowLeadingDecimalPointForNumbers;
    }

    /**
     * set allowLeadingDecimalPointForNumbers value
     *
     * @param allowLeadingDecimalPointForNumbers allowLeadingDecimalPointForNumbers
     */
    public void setAllowLeadingDecimalPointForNumbers(Boolean allowLeadingDecimalPointForNumbers) {
        this.allowLeadingDecimalPointForNumbers = allowLeadingDecimalPointForNumbers;
    }

    /**
     * get allowLeadingZerosForNumbers value
     *
     * @return allowLeadingZerosForNumbers
     */
    public Boolean getAllowLeadingZerosForNumbers() {
        return allowLeadingZerosForNumbers;
    }

    /**
     * set allowLeadingZerosForNumbers value
     *
     * @param allowLeadingZerosForNumbers allowLeadingZerosForNumbers
     */
    public void setAllowLeadingZerosForNumbers(Boolean allowLeadingZerosForNumbers) {
        this.allowLeadingZerosForNumbers = allowLeadingZerosForNumbers;
    }

    /**
     * get allowNonNumericNumbers value
     *
     * @return allowNonNumericNumbers
     */
    public Boolean getAllowNonNumericNumbers() {
        return allowNonNumericNumbers;
    }

    /**
     * set allowNonNumericNumbers value
     *
     * @param allowNonNumericNumbers allowNonNumericNumbers
     */
    public void setAllowNonNumericNumbers(Boolean allowNonNumericNumbers) {
        this.allowNonNumericNumbers = allowNonNumericNumbers;
    }

    /**
     * get allowMissingValues value
     *
     * @return allowMissingValues
     */
    public Boolean getAllowMissingValues() {
        return allowMissingValues;
    }

    /**
     * set allowMissingValues value
     *
     * @param allowMissingValues allowMissingValues
     */
    public void setAllowMissingValues(Boolean allowMissingValues) {
        this.allowMissingValues = allowMissingValues;
    }

    /**
     * get allowTrailingComma value
     *
     * @return allowTrailingComma
     */
    public Boolean getAllowTrailingComma() {
        return allowTrailingComma;
    }

    /**
     * set allowTrailingComma value
     *
     * @param allowTrailingComma allowTrailingComma
     */
    public void setAllowTrailingComma(Boolean allowTrailingComma) {
        this.allowTrailingComma = allowTrailingComma;
    }
}
