package by.asushenya.total.bean.util;

import java.util.List;

import by.asushenya.total.bean.Rate;

public class RatesPage {
	private List<Rate> ratesList;
	private int numberOfPages;

	public RatesPage() {
	}

	public List<Rate> getRatesList() {
		return ratesList;
	}

	public void setRatesList(List<Rate> ratesList) {
		this.ratesList = ratesList;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
