package com.example.danque.test;

import java.math.BigDecimal;

public class TestController {


    public static void main(String[] args) {
        int count = calc(4500, 4500, 995, 1, 100);
        System.out.println("实际推送的同步库存条数：" + count);
    }

    /**
     * @param stockQty       存货库存
     * @param saleQty        渠道库存
     * @param orderLockCount 订单数量
     * @param skuLockNum     订单占用库存数量
     * @param proportion     铺货比例
     * @return
     */
    private static int calc(int stockQty, int saleQty, int orderLockCount, int skuLockNum, int proportion) {
        int count = 0;

        int oldSaleQty = saleQty;

        int currentStockQty = stockQty;
        for (int i = 0; i < orderLockCount; i++) {
            // 当前的渠道库存
            currentStockQty = currentStockQty - skuLockNum;
            if(currentStockQty == 0){
                break;
            }

            int currentSaleQty = new BigDecimal(currentStockQty).multiply(new BigDecimal(proportion)).divide(new BigDecimal(100), 0, BigDecimal.ROUND_FLOOR).intValue();

            if (currentSaleQty == oldSaleQty) {
                // 不需要改变渠道库存
                String str = "currentStockQty:%s, currentSaleQty:%S, oldSaleQty:%s";
                System.out.println(String.format(str,currentStockQty,currentSaleQty,oldSaleQty));
            } else {
                // 需要扣减渠道库存
                oldSaleQty = currentSaleQty;
                count += 1;
            }
        }
        String str = "currentStockQty:%s, oldSaleQty:%S";
        System.out.println("========");
        System.out.println(String.format(str,currentStockQty,oldSaleQty));
        return count;
    }
}
