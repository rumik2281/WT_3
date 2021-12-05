package by.bsuir.lab3.domain.dateConverter;

import by.bsuir.lab3.domain.dateConverter.impl.DmyDotDateConverterImpl;

public class DateConverterFactory {
    private static final DateConverter converter = new DmyDotDateConverterImpl();

    public static DateConverter getConverter() {
        return converter;
    }

    private DateConverterFactory() {
    }
}
