package game.utility.formatting;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyHelper
{
    private CurrencyHelper()
    { }

    public static NumberFormat getCurrencyFormatter()
    {
        return getCurrencyFormatter(Locale.getDefault());
    }

    public static NumberFormat getCurrencyFormatter(Locale locale)
    {
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format;
    }
}
