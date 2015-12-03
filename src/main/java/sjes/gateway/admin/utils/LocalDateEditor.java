package sjes.gateway.admin.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.time.LocalDate;

/**
 * Created by jiangzhe on 15-11-16.
 */
public class LocalDateEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "";
        }
        setValue(LocalDate.parse(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
