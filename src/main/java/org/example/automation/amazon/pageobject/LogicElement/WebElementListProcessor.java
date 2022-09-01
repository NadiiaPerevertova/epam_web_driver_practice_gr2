package org.example.automation.amazon.pageobject.LogicElement;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementListProcessor {
    public int checkNotSame1Brand(List<WebElement> productTitleList,String brandName){
        int check=0;
        for(WebElement e : productTitleList) {
            if (!e.getText().contains(brandName)){
                check++;
            }
        }
//        productTitleList.forEach(e->!e.getText().contains(brandName)?return false:return true);
        return  check;
    }

    public boolean checkNotSame2Brand(List<WebElement> productTitleList,String brandName1,String brandName2 ){
        boolean check= true;
        for(WebElement e : productTitleList) {
            if (e.getText().contains(brandName1)||e.getText().contains(brandName2)){
            } else{
                check=false;
                break;
            }
        }
        return  check;
    }

    public boolean checkInRange(List<WebElement> productTitleList,int lowerlimit,int toplimit ){
        boolean inRange=true;
        for(WebElement e : productTitleList) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            if(numprice<lowerlimit||numprice>toplimit){
                inRange=false;
                break;
            }
        }
        return  inRange;
    }

    public boolean checkSorted(List<WebElement> productTitleList){
        List<Double> priceList = new ArrayList<>();
        for(WebElement e : productTitleList) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            priceList.add(numprice);
        }
        return isSorted(priceList,priceList.size());
    }
    private static boolean isSorted(List<Double> listOfDouble, int index) {
        if (index < 2) {
            return true;
        }
        boolean returnVal=true;
        for (int i = 0; i < (index-2); i++) {
            if (listOfDouble.get(i + 1).compareTo(listOfDouble.get(i)) <= 0) {
                // less than 0 mean that  first value is less than the second  value
                //which mean the sort high to low is incorrect
                returnVal=false;
                break;
            }
        }
        return returnVal;

    }
}
