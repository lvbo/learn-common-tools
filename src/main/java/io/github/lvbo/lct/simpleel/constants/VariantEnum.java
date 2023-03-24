package io.github.lvbo.lct.simpleel.constants;

import com.google.common.collect.Maps;
import io.github.lvbo.lct.simpleel.transform.DateTransformer;
import io.github.lvbo.lct.simpleel.transform.ValueTransformer;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VariantEnum {

    /**
     * 入住时间
     */
    CHECK_IN("checkInDate", Integer.class, new DateTransformer()),

    /**
     * 离店时间
     */
    CHECK_OUT("checkOutDate", Integer.class, new DateTransformer());


    private final String var;
    private final Class<?> clazz;
    private final ValueTransformer transformer;
    private static final Map<String, Object> ctx = Maps.newHashMap();
    
    static {
        for (final var var : VariantEnum.values()) {
            if (var.getClazz().equals(Integer.class)) {
                ctx.put(var.getVar(), 1);
            } else if (var.getClazz().equals(Double.class)) {
                ctx.put(var.getVar(), 1D);
            }
        }
    }
    
    private static final Map<String, VariantEnum> MAP = Arrays.stream(VariantEnum.values())
            .collect(Collectors.toMap(VariantEnum::getVar, o -> o));
    
    public static VariantEnum getVariantEnum(final String var) {
        return MAP.get(var);
    }

}
