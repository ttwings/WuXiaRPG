package com.mygdx.game.tools;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Money {
	public static String moneyStr(int amount) {
		// returns a chinese String of `amount` of money
		String output;

		if (amount / 10000 > 0) {
			output = Chinesed.number(amount / 10000) + "两黄金";
			amount %= 10000;
		} else
			output = "";
		if (amount / 100 > 0) {
			output = output + Chinesed.number(amount / 100) + "两白银";
			amount %= 100;
		}
		if (amount > 0)
			output = output + Chinesed.number(amount) + "文铜钱";
		return output;
	}

	public static String priceStr(int amount) {
		// returns a chinese String of `amount` of money
		String output;

		if (amount < 1)
			amount = 1;

		if (amount / 10000 > 0) {
			output = Chinesed.number(amount / 10000) + "两黄金";
			amount %= 10000;
		} else
			output = "";
		if (amount / 100 > 0) {
			if (output != "")
				output += "又" + Chinesed.number(amount / 100) + "两白银";
			else
				output = Chinesed.number(amount / 100) + "两白银";
			amount %= 100;
		}
		if (amount > 0)
			if (output != "")
				return output + "又" + Chinesed.number(amount) + "文铜板";
			else
				return Chinesed.number(amount) + "文铜板";
		return output;
	}
}