package org.sooo.payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class RealPayment implements Payment {
	
	private final Date date;
    private final int amount;
    private final LocaleInfo localeInfo;

    @Inject
    RealPayment(@Assisted Date date, @Assisted int amount, LocaleInfo localeInfo) {
        this.date = date;
        this.amount = amount;
        this.localeInfo = localeInfo;
    }

  
    @Override
	public String format() {
        String result = "Paid " + Integer.toString(amount);
        if (localeInfo.isMoneySignBefore()) {
            result = localeInfo.getMoneySign() + result;
        } else {
            result += localeInfo.getMoneySign();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        result += " on " + formatter.format(date);

        return result;
    }	
}
