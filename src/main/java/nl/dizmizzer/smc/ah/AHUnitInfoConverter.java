package nl.dizmizzer.smc.ah;

import nl.dizmizzer.smc.ah.entity.AHSmProduct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AHUnitInfoConverter {

    private static final Pattern unitPattern = Pattern.compile("([0-9]+)\\s(\\w+)");
    public static double getUnitSize(AHSmProduct product) {
        if (product.getPrice().getUnitInfo() != null && product.getPrice().getUnitInfo().getDescription() != null) {
            return product.getPrice().getNow() / product.getPrice().getUnitInfo().getPrice();
        }
        Matcher m = unitPattern.matcher(product.getPrice().getUnitSize());
        if (!m.find()) return 0;

        return Double.parseDouble(m.group(1));
    }

    public static String getUnitType(AHSmProduct product) {
        if (product.getPrice().getUnitInfo() != null && product.getPrice().getUnitInfo().getDescription() != null) {
            return product.getPrice().getUnitInfo().getDescription();
        }
        Matcher m = unitPattern.matcher(product.getPrice().getUnitSize());
        if (!m.find()) return "";

        return m.group(2);
    }
}
