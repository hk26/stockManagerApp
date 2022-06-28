package com.example.stock_manager;

public class ModelClass {
    String TaskNames;
    String StockNames;
    int itemCount;
    int profitCount;


    public ModelClass(String taskNames) {
        TaskNames = taskNames;
//        StockNames = stockNames;
//        this.itemCount = itemCount;
//        this.profitCount = profitCount;
    }

    public String getTaskNames() {
        return TaskNames;
    }

    public void setTaskNames(String taskNames) {
        TaskNames = taskNames;
    }
//
//    public String getStockNames() {
//        return StockNames;
//    }
//
//    public void setStockNames(String stockNames) {
//        StockNames = stockNames;
//    }
//
//    public int getItemCount() {
//        return itemCount;
//    }
//
//    public void setItemCount(int itemCount) {
//        this.itemCount = itemCount;
//    }
//
//    public int getProfitCount() {
//        return profitCount;
//    }
//
//    public void setProfitCount(int profitCount) {
//        this.profitCount = profitCount;
//    }
}
