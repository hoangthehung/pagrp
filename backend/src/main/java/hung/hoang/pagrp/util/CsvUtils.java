package hung.hoang.pagrp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtils {
    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        ObjectMapper mapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("startDate")
                .addColumn("endDate")
                .build();
        ObjectReader reader = mapper.readerFor(clazz).with(csvSchema);
        return reader.<T>readValues(stream).readAll();
    }
}
