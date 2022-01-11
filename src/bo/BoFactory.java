package bo;

import bo.custom.impl.*;

public class BoFactory {
        private static BoFactory boFactory;

        private BoFactory() {
        }

        public static BoFactory getBOFactory() {
            if (boFactory == null) {
                boFactory = new BoFactory();
            }
            return boFactory;
        }

        public SuperBO getBO(BoTypes types) {
            switch (types) {
                case CUSTOMER:
                    return new CustomerBOImpl();
                case ITEM:
                    return new ItemBOImpl();
                case ORDER:
                    return new OrderBOImpl();
                case ORDERDETAIL:
                    return new OrderDetailBOImpl();
                case PURCHASE_ORDER:
                    return new PurchaseOrderBOImpl();
                case UPDATE_ORDER:
                    return new UpdateOrderBOImpl();
                default:
                    return null;
            }
        }

        public enum BoTypes {
            CUSTOMER, ITEM, ORDER, ORDERDETAIL, PURCHASE_ORDER,UPDATE_ORDER
        }
}
