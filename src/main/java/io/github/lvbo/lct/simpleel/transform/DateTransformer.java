package io.github.lvbo.lct.simpleel.transform;

import java.util.regex.Pattern;

public class DateTransformer implements ValueTransformer {
    
    private final Pattern DATE_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}:\\d{2}:\\d{2})$");
    
    @Override
    public String transform(final String source) {
        final var m = this.DATE_PATTERN.matcher(source);
        if (m.find()) {
            final var year = Integer.parseInt(m.group(1));
            final var month = Integer.parseInt(m.group(2));
            final var day = Integer.parseInt(m.group(3));
            
            return Integer.toString(year * 10000 + month * 100 + day);
        }
        
        throw new RuntimeException("yyyy-MM-dd HH:mm:dd格式错误, str = " + source);
    }
}
