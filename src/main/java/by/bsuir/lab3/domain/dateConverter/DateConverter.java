package by.bsuir.lab3.domain.dateConverter;

import java.util.Date;

public interface DateConverter {
    Date convert(String stringDate) throws DateConvertionException;
}
