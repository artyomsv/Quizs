package lv.company.expenses.v1;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Compensation implements LongNumber {

    private final long compensation;

    public Compensation(long compensation) {
        this.compensation = compensation;
    }

    @Override
    public long toLong() {
        return compensation;
    }

    public Compensation reduce(Compensation compensation) {
        return new Compensation(this.compensation - compensation.toLong());
    }
}
