package cn.featherfly.web.spring.converter;

import cn.featherfly.common.lang.LangUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

@SuppressWarnings("rawtypes")
public final class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

	@SuppressWarnings("unchecked")
    @Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		Class<?> enumType = targetType;
		while (enumType != null && !enumType.isEnum()) {
			enumType = enumType.getSuperclass();
		}
		if (enumType == null) {
			throw new IllegalArgumentException(
					"The target type " + targetType.getName() + " does not refer to an enum");
		}
		return new StringToEnum(enumType);
	}


	private class StringToEnum<T extends Enum<T>> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		public T convert(String source) {
			if (LangUtils.isEmpty(source)) {
				// It's an empty enum identifier: reset the enum value to null.
				return null;
			}
			return (T) LangUtils.toEnum(this.enumType, source);
		}
	}

}