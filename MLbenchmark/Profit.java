package MLbenchmark;// This is a mutant program.
// Author : ysma

public class Profit
{

    public  double profit( double money )
    {
        double bonus = 0;
        double unit = 100000;
        double bonus1 = unit * 0.1;
        double bonus2 = bonus1 + unit * 0.075;
        double bonus4 = bonus2 + unit * 2 * 0.05;
        double bonus6 = bonus4 + unit * 2 * 0.03;
        double bonus10 = bonus6 + unit * 4 * 0.015;
        if (money <= unit) {
            bonus = money * 0.1;
        } else {
            if (money <= 2 * unit) {
                bonus = bonus1 + (money - unit) * 0.075;
            } else {
                if (money <= 4 * unit) {
                    bonus = bonus2 + (money - 2 * unit) * 0.05;
                } else {
                    if (money <= 6 * unit) {
                        bonus = bonus4 + (money - 4 * unit) * 0.03;
                    } else {
                        if (money <= 10 * unit) {
                            bonus = bonus6 + (money - 6 * unit) * 0.015;
                        } else {
                            bonus = bonus10 + (money - 10 * unit) * 0.01;
                        }
                    }
                }
            }
        }
        return bonus;
    }

}
