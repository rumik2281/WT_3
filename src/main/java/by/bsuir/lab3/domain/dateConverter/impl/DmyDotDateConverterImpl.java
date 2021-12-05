package by.bsuir.lab3.domain.dateConverter.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.bsuir.lab3.domain.dateConverter.DateConverter;
import by.bsuir.lab3.domain.dateConverter.DateConvertionException;

public class DmyDotDateConverterImpl implements DateConverter {
    private final SimpleDateFormat converter;

    public DmyDotDateConverterImpl() {
        converter = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public Date convert(String stringDate) throws DateConvertionException {
        if (stringDate == null) {
            throw new IllegalArgumentException("Date shouldn't be null");
        }

        try {
            return converter.parse(stringDate);
        } catch (ParseException e) {
            throw new DateConvertionException("Error converting date " + stringDate, e);
        }
    }
}
