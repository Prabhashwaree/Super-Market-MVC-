package bo.custom;

import bo.SuperBO;
import dto.OrderDetailsDTO;

import java.sql.SQLException;

public interface UpdateOrderBO extends SuperBO {

    OrderDetailsDTO getOrder(String id) throws SQLException, ClassNotFoundException;
}
