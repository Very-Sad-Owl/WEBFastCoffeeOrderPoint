package by.epam.training.jwd.godot.controller.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressLinePrinter extends SimpleTagSupport {
    private String message = "";
    private String locale = "en";
    private StringWriter sw = new StringWriter();
    private static final String toStringSplitterRexeg = "'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'";

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void doTag() throws JspException, IOException {
        String addressLine;
        if (message != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            addressLine = getAddressLine(message);
            addressLine = retrieveChosenLocaleData(locale, addressLine);
            out.println(addressLine);
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            addressLine = getAddressLine(message);
            addressLine = retrieveChosenLocaleData(locale, addressLine);
            getJspContext().getOut().println(addressLine);
        }
    }

    private String getAddressLine(String message){
        StringBuilder addressLine = new StringBuilder();
        Pattern pattern = Pattern.compile(toStringSplitterRexeg);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            addressLine.append(matcher.group(1)).append(", ");
        }
        addressLine.deleteCharAt(addressLine.length()-1);

        return addressLine.toString();
    }

    private String retrieveChosenLocaleData(String localeName, String source){
        String pattern;
        switch (localeName){
            case "ru":
                pattern = "[A-Za-z0-9/]+,|, [A-Za-z0-9/]+";
                source = source.replaceAll(pattern, "");
                break;
            case "en":
                pattern = "[А-Яа-я0-9/]+,|, [А-Яа-я0-9/]+";
                source = source.replaceAll(pattern, "");
                break;
        }
        return source.replaceAll(",$", "");
    }
}
